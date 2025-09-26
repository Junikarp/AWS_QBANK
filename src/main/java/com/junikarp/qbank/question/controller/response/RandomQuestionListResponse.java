package com.junikarp.qbank.question.controller.response;

import com.junikarp.qbank.question.domain.Question;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RandomQuestionListResponse {

    private List<QuestionResponse> list;

    public static RandomQuestionListResponse from (List<Question> questions) {

        List<QuestionResponse> list = questions.stream()
                .map(QuestionResponse::from)
                .toList();

        return RandomQuestionListResponse.builder()
                .list(list)
                .build();
    }
}
