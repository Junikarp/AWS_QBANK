package com.junikarp.qbank.question.controller.port;

import com.junikarp.qbank.question.infrastructure.QuestionEntity;

import java.util.List;

public interface QuestionService {

    List<QuestionEntity> createRandomQuestionList(Long Quantity);
    
}
