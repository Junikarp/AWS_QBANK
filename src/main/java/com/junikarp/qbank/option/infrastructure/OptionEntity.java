package com.junikarp.qbank.option.infrastructure;

import com.junikarp.qbank.option.domain.Option;
import com.junikarp.qbank.question.infrastructure.QuestionEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "option")
@Getter
@Setter
public class OptionEntity {

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

    public static OptionEntity from(Option option) {
        OptionEntity optionEntity = new OptionEntity();
        optionEntity.id = option.getId();
        optionEntity.number = option.getNumber();
        optionEntity.text = option.getText();
        optionEntity.isCorrect = option.getIsCorrect();

        return optionEntity;
    }

    public Option to() {
        return Option.builder()
                .id(id)
                .number(number)
                .text(text)
                .isCorrect(isCorrect)
                .questionId(questionEntity.getId())
                .build();
    }
}
