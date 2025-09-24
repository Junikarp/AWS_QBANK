package com.junikarp.qbank.question.infrastructure;

import com.junikarp.qbank.question.domain.Question;
import com.junikarp.qbank.question.service.port.QuestionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

    private QuestionJpaRepository questionJpaRepository;

    public List<Question> findAll() {
        return questionJpaRepository.findAll().stream()
                .map(QuestionEntity::to)
                .collect(Collectors.toList());
    }
}
