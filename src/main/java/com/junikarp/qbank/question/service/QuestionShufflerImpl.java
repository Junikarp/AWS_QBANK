package com.junikarp.qbank.question.service;

import com.junikarp.qbank.question.controller.port.QuestionShuffler;
import com.junikarp.qbank.question.domain.Question;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class QuestionShufflerImpl implements QuestionShuffler {

    @Override
    public List<Question> shuffle(List<Question> questionList) {
        Collections.shuffle(questionList);
        return questionList;
    }

}
