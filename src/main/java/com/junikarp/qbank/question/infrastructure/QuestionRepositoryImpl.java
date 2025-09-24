package com.junikarp.qbank.question.infrastructure;

import com.junikarp.qbank.question.service.port.QuestionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

    private QuestionJpaRepository questionJpaRepository;
}
