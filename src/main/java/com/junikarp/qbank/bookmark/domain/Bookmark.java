package com.junikarp.qbank.bookmark.domain;

import com.junikarp.qbank.question.domain.Question;
import com.junikarp.qbank.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Bookmark {

    private Long id;

    private User user;

    private Question question;

    public static Bookmark from(User user, Question question) {
        return Bookmark.builder()
                .user(user)
                .question(question)
                .build();
    }
}
