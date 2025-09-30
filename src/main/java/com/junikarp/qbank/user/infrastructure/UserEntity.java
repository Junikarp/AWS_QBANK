package com.junikarp.qbank.user.infrastructure;

import com.junikarp.qbank.bookmark.infrastructure.BookmarkEntity;
import com.junikarp.qbank.user.domain.User;
import com.junikarp.qbank.wrong_answer.domain.WrongAnswer;
import com.junikarp.qbank.wrong_answer.infrastructure.WrongAnswerEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "nickname")
    private String nickname;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WrongAnswerEntity> wrongAnswers = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookmarkEntity> bookmarks = new ArrayList<>();

    public static UserEntity from(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.id = user.getId();
        userEntity.email = user.getEmail();
        userEntity.nickname = user.getNickname();
        return userEntity;
    }

    public User to() {
        return User.builder()
                .id(id)
                .email(email)
                .nickname(nickname)
                .build();
    }
}
