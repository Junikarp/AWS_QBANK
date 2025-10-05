package com.junikarp.qbank.question.service;

import com.junikarp.qbank.bookmark.service.BookmarkServiceImpl;
import com.junikarp.qbank.choice.domain.Choice;
import com.junikarp.qbank.mock.FakeBookmarkRepository;
import com.junikarp.qbank.mock.QuestionShufflerTest;
import com.junikarp.qbank.question.controller.port.QuestionService;
import com.junikarp.qbank.question.controller.port.QuestionShuffler;
import com.junikarp.qbank.question.domain.Question;
import com.junikarp.qbank.mock.FakeQuestionRepository;
import com.junikarp.qbank.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

    private QuestionService questionService;
    private List<Question> initQuestionList;
    FakeQuestionRepository fakeQuestionRepository;

    @InjectMocks
    private BookmarkServiceImpl bookmarkService;

    @BeforeEach
    void init() {
        fakeQuestionRepository = new FakeQuestionRepository(new FakeBookmarkRepository());
        QuestionShuffler questionShuffler = new QuestionShufflerTest();

        this.questionService = QuestionServiceImpl.builder()
                .questionRepository(fakeQuestionRepository)
                .questionShuffler(questionShuffler)
                .build();

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

        fakeQuestionRepository.save(question1);
        fakeQuestionRepository.save(question2);
        fakeQuestionRepository.save(question3);

        initQuestionList = new ArrayList<>();
        initQuestionList.add(question1);
        initQuestionList.add(question2);
        initQuestionList.add(question3);

    }

    @Test
    void createRandomQuestionList_메서드로_랜덤한_순서의_문제를_지정된_수량만큼_리스트로_반환한다() {
        //given
        int quantity = 2;

        //when
        List<Question> result = questionService.createRandomQuestionList(quantity);

        //then
        assertThat(result.size()).isEqualTo(quantity);
        assertThat(initQuestionList).containsAll(result);
        assertThat(result).isNotEqualTo(initQuestionList);
    }

    @Test
    void findQuestionsById_메서드를_실행하면_Id가_일치하는_문제들만_골라_리스트를_반환한다() {
        //given
        List<Long> idList = new ArrayList<>();
        idList.add(1L);
        idList.add(3L);

        //when
        List<Question> result = questionService.findQuestionsById(idList);

        //then
        assertThat(initQuestionList).containsAll(result);

        assertThat(result.size()).isEqualTo(2);

        assertThat(result.get(0).getId()).isEqualTo(initQuestionList.get(0).getId());
        assertThat(result.get(0).getQuestion()).isEqualTo(initQuestionList.get(0).getQuestion());
        assertThat(result.get(0).getExplanation()).isEqualTo(initQuestionList.get(0).getExplanation());
        assertThat(result.get(0).getChoices()).isEqualTo(initQuestionList.get(0).getChoices());

        assertThat(result.get(1).getId()).isEqualTo(initQuestionList.get(2).getId());
        assertThat(result.get(1).getQuestion()).isEqualTo(initQuestionList.get(2).getQuestion());
        assertThat(result.get(1).getExplanation()).isEqualTo(initQuestionList.get(2).getExplanation());
        assertThat(result.get(1).getChoices()).isEqualTo(initQuestionList.get(2).getChoices());
    }

    @Test
    void userId를_이용해_북마크한_문제_리스트를_가져올_수_있다(){
        //given
        Long userId = 1L;

        User user = User.builder()
                .id(userId)
                .build();

        fakeQuestionRepository.addBookmark(user, initQuestionList.get(0));
        fakeQuestionRepository.addBookmark(user, initQuestionList.get(2));

        //when
        List<Question> result = questionService.findBookmarkedQuestions(userId);

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getId()).isEqualTo(1);
        assertThat(result.get(0).getExplanation()).isEqualTo("1번 문제에 대한 설명입니다.");
        assertThat(result.get(1).getExplanation()).isEqualTo("3번 문제에 대한 설명입니다.");
    }

    @Test
    void getById를_이용해_문제아이디로_문제를_조회할_수_있다() {
        //given
        Long questionId = 2L;

        //when
        Question result = questionService.getById(questionId);

        //then
        assertThat(result.getId()).isEqualTo(2L);
        assertThat(result.getQuestion()).isEqualTo("2번 문제 입니다.");
        assertThat(result.getExplanation()).isEqualTo("2번 문제에 대한 설명입니다.");
        assertThat(result.getChoices().size()).isEqualTo(4);
        assertThat(result.getChoices().get(1).getText()).isEqualTo("B번 보기입니다.");
    }
}