package com.junikarp.qbank.question.infrastructure;

import com.junikarp.qbank.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionJpaRepository extends JpaRepository<QuestionEntity, Long> {

    List<QuestionEntity> findAll();

    @Query("SELECT q FROM QuestionEntity q JOIN BookmarkEntity b ON q.id = b.questionEntity.id WHERE b.userEntity.id = :userId")
    List<QuestionEntity> findBookmarkedQuestionsByUserId(@Param("userId") Long userId);
}
