package com.junikarp.qbank.question.service;

import com.junikarp.qbank.question.controller.port.QuestionService;
import com.junikarp.qbank.question.infrastructure.QuestionEntity;
import com.junikarp.qbank.question.service.port.QuestionRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private final QuestionRepository questionRepository;

    @Override
    public List<QuestionEntity> createRandomQuestionList(Long Quantity) {

        return List.of();
    }

}
