package com.junikarp.qbank.question.infrastructure;

import com.junikarp.qbank.question.domain.Question;
import com.junikarp.qbank.question.service.port.QuestionRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepository {

    private final QuestionJpaRepository questionJpaRepository;

    public List<Question> findAll() {
        return questionJpaRepository.findAll().stream()
                .map(QuestionEntity::to)
                .collect(Collectors.toList());
    }

    @Override
    public Question save(Question question) {
        return questionJpaRepository.save(QuestionEntity.from(question)).to();
    }

    @Override
    public List<Question> findBookmarkedQuestionsByUserId(Long userId) {
        return questionJpaRepository.findBookmarkedQuestionsByUserId(userId).stream()
                .map(QuestionEntity::to)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Question> findById(Long id) {
        return questionJpaRepository.findById(id).map(QuestionEntity::to);
    }
}
