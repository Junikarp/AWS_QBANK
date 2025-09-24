package com.junikarp.qbank.question.domain;

import com.junikarp.qbank.option.domain.Option;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Question {

    private Long id;

    private String question;

    private String explanation;

    private List<Option> options;

}
