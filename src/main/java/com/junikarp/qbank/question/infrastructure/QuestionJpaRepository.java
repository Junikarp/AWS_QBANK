package com.junikarp.qbank.question.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionJpaRepository extends JpaRepository<QuestionEntity, Long> {

    Optional<QuestionEntity> findById();

}
