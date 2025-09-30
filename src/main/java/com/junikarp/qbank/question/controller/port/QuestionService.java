package com.junikarp.qbank.question.controller.port;

import com.junikarp.qbank.question.domain.Question;
import com.junikarp.qbank.question.infrastructure.QuestionEntity;

import java.util.List;

public interface QuestionService {
    // 랜덤 문제 생성
    List<Question> createRandomQuestionList(int quantity);
    // 지정된 번호 문제 가져오기
    List<Question> findQuestionsById(List<Long> idList);
    // 북마크된 문제 리스트 가져오기
    List<Question> findBookmarkedQuestions(Long userId);
}
