package com.junikarp.qbank.question.domain;

import com.junikarp.qbank.choice.domain.Choice;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Getter
@Builder
public class Question {

    private Long id;

    private String question;

    private String explanation;

    @Singular
    private List<Choice> choices;

}
