package com.junikarp.qbank.bookmark.controller;

import com.junikarp.qbank.bookmark.controller.response.BookmarkResponse;
import com.junikarp.qbank.bookmark.domain.Bookmark;
import com.junikarp.qbank.choice.domain.Choice;
import com.junikarp.qbank.mock.TestContainer;
import com.junikarp.qbank.question.domain.Question;
import com.junikarp.qbank.user.domain.User;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BookmarkControllerTest {

    private TestContainer testContainer;

    @BeforeEach
    void init() {
        testContainer = TestContainer.builder().build();

        User user = User.builder()
                .id(1L)
                .email("jjkkp@email.com")
                .nickname("jjkkp")
                .build();

        Question question1 = Question.builder()
                .id(1L)
                .question("1번 문제 입니다.")
                .explanation("1번 문제에 대한 설명입니다.")
                .choice(new Choice(1L,"A","A번 보기입니다.",false,1L))
                .build();

        Question question2 = Question.builder()
                .id(2L)
                .question("2번 문제 입니다.")
                .explanation("2번 문제에 대한 설명입니다.")
                .choice(new Choice(2L,"A","A번 보기입니다.",false,2L))
                .build();

        Question question3 = Question.builder()
                .id(3L)
                .question("3번 문제 입니다.")
                .explanation("3번 문제에 대한 설명입니다.")
                .choice(new Choice(3L,"A","A번 보기입니다.",false,2L))
                .build();

        Bookmark bookmark1 = Bookmark.builder()
                .user(user)
                .question(question1)
                .build();

        Bookmark bookmark2 = Bookmark.builder()
                .user(user)
                .question(question3)
                .build();

        testContainer.userRepository.save(user);
        testContainer.questionRepository.save(question1);
        testContainer.questionRepository.save(question2);
        testContainer.questionRepository.save(question3);
        testContainer.bookmarkRepository.save(bookmark1);
        testContainer.bookmarkRepository.save(bookmark2);
    }

    @Test
    void 사용자는_북마크를_생성할_수_있다() {
        //given
        Long userId = 1L;
        Long questionId = 2L;

        //when
        ResponseEntity<BookmarkResponse> result = testContainer.bookmarkController.create(userId, questionId);
        List<Question> bookmarkQuestions = testContainer.questionService.findBookmarkedQuestions(userId);

        //then
        Assertions.assertNotNull(result.getBody());
        assertThat(result.getBody().getUserId()).isEqualTo(userId);
        assertThat(result.getBody().getQuestionId()).isEqualTo(questionId);
        assertThat(bookmarkQuestions.size()).isEqualTo(3);
    }

    @Test
    void 사용자는_존재하지_않는_문제아이디로_북마크를_등록할_경우_에러가_난다() {
        //given
        Long userId = 1L;
        Long questionId = 444L;

        //when

        //then
        assertThatThrownBy(() -> {
            testContainer.bookmarkController.create(userId, questionId);
        }).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void 사용자는_존재하지_않는_유저아이디로_북마크를_등록할_경우_에러가_난다(){
        //given
        Long userId = 444L;
        Long questionId = 1L;

        //when

        //then
        assertThatThrownBy(() -> {
            testContainer.bookmarkController.create(userId, questionId);
        }).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void 사용자는_북마크를_삭제할_수_있다() {
        //given
        Long userId = 1L;
        Long questionId = 1L;

        //when
        ResponseEntity<Void> result = testContainer.bookmarkController.delete(userId, questionId);
        List<Bookmark> bookmarks = testContainer.bookmarkService.findListByUserId(userId);

        //then
        assertThat(result.getBody()).isNull();
        assertThat(bookmarks.size()).isEqualTo(1);
        assertThat(bookmarks.get(0).getQuestion().getId()).isEqualTo(3L);
    }
}
