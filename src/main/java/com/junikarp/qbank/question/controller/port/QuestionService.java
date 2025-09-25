package com.junikarp.qbank.question.controller.port;

import com.junikarp.qbank.question.domain.Question;
import com.junikarp.qbank.question.infrastructure.QuestionEntity;

import java.util.List;

public interface QuestionService {

    List<Question> createRandomQuestionList(int Quantity);
    
}
