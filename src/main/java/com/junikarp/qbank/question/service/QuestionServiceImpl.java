package com.junikarp.qbank.question.service;

import com.junikarp.qbank.question.controller.port.QuestionService;
import com.junikarp.qbank.question.infrastructure.QuestionEntity;
import com.junikarp.qbank.question.service.port.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<QuestionEntity> createRandomQuestionList(Long Quantity) {

        return List.of();
    }

}
