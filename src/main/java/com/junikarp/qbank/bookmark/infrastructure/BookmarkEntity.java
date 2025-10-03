package com.junikarp.qbank.bookmark.infrastructure;

import com.junikarp.qbank.bookmark.domain.Bookmark;
import com.junikarp.qbank.question.infrastructure.QuestionEntity;
import com.junikarp.qbank.user.infrastructure.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bookmark", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "question_id"})
})
public class BookmarkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity questionEntity;

    public static BookmarkEntity from(Bookmark bookmark) {
        BookmarkEntity bookmarkEntity = new BookmarkEntity();
        bookmarkEntity.id = bookmark.getId();
        bookmarkEntity.setUserEntity(UserEntity.from(bookmark.getUser()));
        bookmarkEntity.setQuestionEntity(QuestionEntity.from(bookmark.getQuestion()));
        return bookmarkEntity;
    }

    public Bookmark to() {
        return Bookmark.builder()
                .id(id)
                .user(userEntity.to())
                .question(questionEntity.to())
                .build();
    }
}
