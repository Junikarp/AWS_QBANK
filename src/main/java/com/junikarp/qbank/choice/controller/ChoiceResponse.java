package com.junikarp.qbank.choice.controller;

import com.junikarp.qbank.choice.domain.Choice;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChoiceResponse {

    private Long id;

    private String number;

    private String text;

    private Boolean isCorrect;

    private Long questionId;

    public static ChoiceResponse from(Choice choice) {
        return ChoiceResponse.builder()
                .id(choice.getId())
                .number(choice.getNumber())
                .text(choice.getText())
                .isCorrect(choice.getIsCorrect())
                .build();
    }
}
