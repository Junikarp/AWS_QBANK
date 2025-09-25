package com.junikarp.qbank.question.service;

import com.junikarp.qbank.option.domain.Option;
import com.junikarp.qbank.question.controller.port.QuestionService;
import com.junikarp.qbank.question.domain.Question;
import com.junikarp.qbank.question.mock.FakeQuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionServiceTest {

    private QuestionServiceImpl questionService;
    private List<Question> initQuestionList;

    @BeforeEach
    void init() {
        FakeQuestionRepository fakeQuestionRepository = new FakeQuestionRepository();

        this.questionService = QuestionServiceImpl.builder()
                .questionRepository(fakeQuestionRepository)
                .build();

        Question question1 = Question.builder()
                .id(1L)
                .question("1번 문제 입니다.")
                .explanation("1번 문제에 대한 설명입니다.")
                .option(new Option(1L,"A","A번 보기입니다.",false,1L))
                .option(new Option(2L,"B","B번 보기입니다.",false,1L))
                .option(new Option(3L,"C","C번 보기입니다.",true,1L))
                .option(new Option(4L,"D","D번 보기입니다.",false,1L))
                .build();

        Question question2 = Question.builder()
                .id(2L)
                .question("2번 문제 입니다.")
                .explanation("2번 문제에 대한 설명입니다.")
                .option(new Option(5L,"A","A번 보기입니다.",false,2L))
                .option(new Option(6L,"B","B번 보기입니다.",true,2L))
                .option(new Option(7L,"C","C번 보기입니다.",false,2L))
                .option(new Option(8L,"D","D번 보기입니다.",false,2L))
                .build();

        Question question3 = Question.builder()
                .id(3L)
                .question("3번 문제 입니다.")
                .explanation("3번 문제에 대한 설명입니다.")
                .option(new Option(9L,"A","A번 보기입니다.",false,3L))
                .option(new Option(10L,"B","B번 보기입니다.",true,3L))
                .option(new Option(11L,"C","C번 보기입니다.",false,3L))
                .option(new Option(12L,"D","D번 보기입니다.",false,3L))
                .build();

        fakeQuestionRepository.save(question1);
        fakeQuestionRepository.save(question2);
        fakeQuestionRepository.save(question3);

        initQuestionList = new ArrayList<Question>();
        initQuestionList.add(question1);
        initQuestionList.add(question2);
        initQuestionList.add(question3);

    }

    @Test
    void createRandomQuestionList_메서드로_지정된_수량만큼_랜덤한_순서의_문제를_리스트로_반환한다() {
        //given
        int quantity = 2;

        //when
        List<Question> randomQuestionList = questionService.createRandomQuestionList(quantity);

        //then
        assertThat(randomQuestionList.size()).isEqualTo(quantity);
        assertThat(initQuestionList).containsAll(randomQuestionList);
        assertThat(randomQuestionList).isNotEqualTo(initQuestionList); // 아주 가끔 실패 뜰 수도 있음
    }

    @Test
    void findQuestionsById_메서드를_실행하면_Id가_일치하는_문제들만_골라_리스트를_반환한다() {
        //given
        List<Long> idList = new ArrayList<>();
        idList.add(1L);
        idList.add(3L);

        //when
        List<Question> questionList = questionService.findQuestionsById(idList);

        //then
        assertThat(initQuestionList).containsAll(questionList);

        assertThat(questionList.size()).isEqualTo(2);

        assertThat(questionList.get(0).getId()).isEqualTo(initQuestionList.get(0).getId());
        assertThat(questionList.get(0).getQuestion()).isEqualTo(initQuestionList.get(0).getQuestion());
        assertThat(questionList.get(0).getExplanation()).isEqualTo(initQuestionList.get(0).getExplanation());
        assertThat(questionList.get(0).getOptions()).isEqualTo(initQuestionList.get(0).getOptions());

        assertThat(questionList.get(1).getId()).isEqualTo(initQuestionList.get(2).getId());
        assertThat(questionList.get(1).getQuestion()).isEqualTo(initQuestionList.get(2).getQuestion());
        assertThat(questionList.get(1).getExplanation()).isEqualTo(initQuestionList.get(2).getExplanation());
        assertThat(questionList.get(1).getOptions()).isEqualTo(initQuestionList.get(2).getOptions());
    }
}
