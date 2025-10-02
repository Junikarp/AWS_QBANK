package com.junikarp.qbank.question.service.port;

import com.junikarp.qbank.question.domain.Question;
import com.junikarp.qbank.question.infrastructure.QuestionEntity;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository {

    Question save(Question question);

    List<Question> findAll();

    List<Question> findBookmarkedQuestionsByUserId(Long userId);

}
