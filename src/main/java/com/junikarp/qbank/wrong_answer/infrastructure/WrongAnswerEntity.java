package com.junikarp.qbank.wrong_answer.infrastructure;

import com.junikarp.qbank.question.domain.Question;
import com.junikarp.qbank.question.infrastructure.QuestionEntity;
import com.junikarp.qbank.user.infrastructure.UserEntity;
import com.junikarp.qbank.wrong_answer.domain.WrongAnswer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "wrong_answer" , uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "question_id"})
})
public class WrongAnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity questionEntity;

    public static WrongAnswerEntity from(WrongAnswer wrongAnswer) {
        WrongAnswerEntity wrongAnswerEntity = new WrongAnswerEntity();
        wrongAnswerEntity.id = wrongAnswer.getId();
        return wrongAnswerEntity;
    }

    public WrongAnswer to() {
        return WrongAnswer.builder()
                .id(id)
                .userId(userEntity.getId())
                .questionId(questionEntity.getId())
                .build();
    }

    public WrongAnswerEntity create(UserEntity userEntity, QuestionEntity questionEntity) {
        WrongAnswerEntity wrongAnswerEntity = new WrongAnswerEntity();
        wrongAnswerEntity.setUserEntity(userEntity);
        wrongAnswerEntity.setQuestionEntity(questionEntity);
        return wrongAnswerEntity;
    }
}
