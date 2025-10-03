package com.junikarp.qbank.question.Controller;

import com.junikarp.qbank.bookmark.domain.Bookmark;
import com.junikarp.qbank.choice.domain.Choice;
import com.junikarp.qbank.mock.FakeQuestionRepository;
import com.junikarp.qbank.question.controller.response.BookmarkQuestionListResponse;
import com.junikarp.qbank.question.controller.response.RandomQuestionListResponse;
import com.junikarp.qbank.question.domain.Question;
import com.junikarp.qbank.mock.TestContainer;
import com.junikarp.qbank.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionControllerTest {

    List<Question> initQuestionList;

    @BeforeEach
    void init() {
        Question question1 = Question.builder()
                .id(1L)
                .question("1번 문제 입니다.")
                .explanation("1번 문제에 대한 설명입니다.")
                .choice(new Choice(1L,"A","A번 보기입니다.",false,1L))
                .choice(new Choice(2L,"B","B번 보기입니다.",false,1L))
                .choice(new Choice(3L,"C","C번 보기입니다.",true,1L))
                .choice(new Choice(4L,"D","D번 보기입니다.",false,1L))
                .build();

        Question question2 = Question.builder()
                .id(2L)
                .question("2번 문제 입니다.")
                .explanation("2번 문제에 대한 설명입니다.")
                .choice(new Choice(5L,"A","A번 보기입니다.",false,2L))
                .choice(new Choice(6L,"B","B번 보기입니다.",true,2L))
                .choice(new Choice(7L,"C","C번 보기입니다.",false,2L))
                .choice(new Choice(8L,"D","D번 보기입니다.",false,2L))
                .build();

        Question question3 = Question.builder()
                .id(3L)
                .question("3번 문제 입니다.")
                .explanation("3번 문제에 대한 설명입니다.")
                .choice(new Choice(9L,"A","A번 보기입니다.",false,3L))
                .choice(new Choice(10L,"B","B번 보기입니다.",true,3L))
                .choice(new Choice(11L,"C","C번 보기입니다.",false,3L))
                .choice(new Choice(12L,"D","D번 보기입니다.",false,3L))
                .build();

        initQuestionList = new ArrayList<>();
        initQuestionList.add(question1);
        initQuestionList.add(question2);
        initQuestionList.add(question3);

    }

    @Test
    void 사용자는_지정된_수량만큼_랜덤한_문제_리스트를_조회_할_수_있다() {
        //given
        TestContainer testContainer = TestContainer.builder()
                .build();

        testContainer.questionRepository.save(initQuestionList.get(0));
        testContainer.questionRepository.save(initQuestionList.get(1));
        testContainer.questionRepository.save(initQuestionList.get(2));

        //when
        ResponseEntity<RandomQuestionListResponse> result = testContainer.questionController.getRandomQuestionList(2);

        //then
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getList().size()).isEqualTo(2);
        assertThat(result.getBody().getList().get(0).getId()).isEqualTo(1);
    }

    @Test
    void 사용자는_북마크한_문제리스트를_조회_할_수_있다() {
        //given
        TestContainer testContainer = TestContainer.builder()
                .build();

        Long userId = 1L;

        User user = User.builder()
                .id(userId)
                .build();

        Bookmark bookmark1 = Bookmark.builder()
                .user(user)
                .question(initQuestionList.get(0))
                .build();

        Bookmark bookmark2 = Bookmark.builder()
                .user(user)
                .question(initQuestionList.get(1))
                .build();

        testContainer.questionRepository.save(initQuestionList.get(0));
        testContainer.questionRepository.save(initQuestionList.get(1));
        testContainer.questionRepository.save(initQuestionList.get(2));

        testContainer.bookmarkRepository.save(bookmark1);
        testContainer.bookmarkRepository.save(bookmark2);

        //when
        ResponseEntity<BookmarkQuestionListResponse> result = testContainer.questionController.getBookmarkQuestionListByUserId(userId);

        //then
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getList().size()).isEqualTo(2);
    }
}
