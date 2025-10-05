package com.junikarp.qbank.question.service;

import com.junikarp.qbank.choice.domain.Choice;
import com.junikarp.qbank.question.controller.port.QuestionShuffler;
import com.junikarp.qbank.question.domain.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class QuestionShufflerImplTest {

    private final QuestionShuffler questionShuffler = new QuestionShufflerImpl();

    @Test
    void shuffle_메서드를_호출_할_수_있다() {
        //given
        Question question1 = Question.builder()
                .id(1L)
                .question("1번 문제 입니다.")
                .explanation("1번 문제에 대한 설명입니다.")
                .choice(new Choice(1L,"A","A번 보기입니다.",false,1L))
                .choice(new Choice(2L,"B","B번 보기입니다.",false,1L))
                .build();

        Question question2 = Question.builder()
                .id(1L)
                .question("1번 문제 입니다.")
                .explanation("1번 문제에 대한 설명입니다.")
                .choice(new Choice(1L,"A","A번 보기입니다.",false,1L))
                .choice(new Choice(2L,"B","B번 보기입니다.",false,1L))
                .build();

        List<Question> questions = Arrays.asList(question1,question2);

        //when
        List<Question> result =  questionShuffler.shuffle(questions);

        //then
        assertThat(result).hasSize(2);
        assertThat(result).containsExactlyInAnyOrder(question1, question2);
    }
}
