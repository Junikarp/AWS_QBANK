package com.junikarp.qbank.option.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Option {

    private Long id;

    private String number;

    private String text;

    private Boolean isCorrect;

    private Long questionId;
}
