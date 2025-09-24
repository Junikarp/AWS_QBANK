package com.junikarp.qbank.question.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionJpaRepository extends JpaRepository<QuestionEntity, Long> {

    Optional<QuestionEntity> findById();

    List<QuestionEntity> findAll();
}
