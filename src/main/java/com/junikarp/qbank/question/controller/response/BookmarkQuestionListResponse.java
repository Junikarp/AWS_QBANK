package com.junikarp.qbank.question.controller.response;

import com.junikarp.qbank.question.domain.Question;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BookmarkQuestionListResponse {

    private List<QuestionResponse> list;

    public static BookmarkQuestionListResponse from (List<Question> questions) {
        List<QuestionResponse> list = questions.stream()
                .map(QuestionResponse::from)
                .toList();

        return BookmarkQuestionListResponse.builder()
                .list(list)
                .build();
    }
}
