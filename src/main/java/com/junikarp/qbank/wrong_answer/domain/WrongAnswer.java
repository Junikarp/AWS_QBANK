package com.junikarp.qbank.wrong_answer.domain;

import com.junikarp.qbank.question.domain.Question;
import com.junikarp.qbank.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class WrongAnswer {

    private Long id;

    private User user;

    private Question question;

}
