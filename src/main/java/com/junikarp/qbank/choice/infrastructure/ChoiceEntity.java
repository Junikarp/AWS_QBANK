package com.junikarp.qbank.choice.infrastructure;

import com.junikarp.qbank.choice.domain.Choice;
import com.junikarp.qbank.question.infrastructure.QuestionEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "choice")
@Getter
@Setter
public class ChoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "text")
    private String text;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private QuestionEntity questionEntity;

    public static ChoiceEntity from(Choice choice) {
        ChoiceEntity choiceEntity = new ChoiceEntity();
        choiceEntity.id = choice.getId();
        choiceEntity.number = choice.getNumber();
        choiceEntity.text = choice.getText();
        choiceEntity.isCorrect = choice.getIsCorrect();

        return choiceEntity;
    }

    public Choice to() {
        return Choice.builder()
                .id(id)
                .number(number)
                .text(text)
                .isCorrect(isCorrect)
                .questionId(questionEntity.getId())
                .build();
    }
}
