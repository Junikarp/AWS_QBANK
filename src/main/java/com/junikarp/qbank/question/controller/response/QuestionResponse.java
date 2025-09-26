package com.junikarp.qbank.question.controller.response;

import com.junikarp.qbank.choice.controller.ChoiceResponse;
import com.junikarp.qbank.choice.domain.Choice;
import com.junikarp.qbank.question.domain.Question;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QuestionResponse {

    private Long id;

    private String question;

    private String explanation;

    private List<Choice> choices;

    public static QuestionResponse from(Question question) {

        List<ChoiceResponse> choices = question.getChoices().stream()
                .map(ChoiceResponse::from)
                .toList();

        return QuestionResponse.builder()
                .id(question.getId())
                .question(question.getQuestion())
                .explanation(question.getExplanation())
                .choices(question.getChoices())
                .build();
    }
}
