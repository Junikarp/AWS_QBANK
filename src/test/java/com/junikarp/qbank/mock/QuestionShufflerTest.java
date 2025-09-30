package com.junikarp.qbank.mock;

import com.junikarp.qbank.question.controller.port.QuestionShuffler;
import com.junikarp.qbank.question.domain.Question;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class QuestionShufflerTest implements QuestionShuffler {

    @Override
    public List<Question> shuffle(List<Question> questionList) {
            return questionList;
    }
}
