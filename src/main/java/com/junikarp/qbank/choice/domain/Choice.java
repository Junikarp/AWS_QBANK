package com.junikarp.qbank.choice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Choice {

    private Long id;

    private String number;

    private String text;

    private Boolean isCorrect;

    private Long questionId;
}
