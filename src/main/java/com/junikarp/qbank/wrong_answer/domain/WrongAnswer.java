package com.junikarp.qbank.wrong_answer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class WrongAnswer {

    private Long id;

    private Long userId;

    private Long questionId;

}
