package com.junikarp.qbank.question.service.port;

import com.junikarp.qbank.question.domain.Question;
import com.junikarp.qbank.question.infrastructure.QuestionEntity;

import java.util.List;

public interface QuestionRepository {

    Question save(Question question);

    List<Question> findAll();
}
