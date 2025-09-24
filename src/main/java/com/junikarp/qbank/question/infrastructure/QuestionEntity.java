package com.junikarp.qbank.question.infrastructure;

import com.junikarp.qbank.option.domain.Option;
import com.junikarp.qbank.option.infrastructure.OptionEntity;
import com.junikarp.qbank.question.domain.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "question")
@Getter
@Setter
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question", columnDefinition = "TEXT")
    private String question;

    @Column(name = "explanation", columnDefinition = "TEXT")
    private String explanation;

    @OneToMany(mappedBy = "questionEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OptionEntity> options;


    public static QuestionEntity from(Question question) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.id = question.getId();
        questionEntity.question = question.getQuestion();
        questionEntity.explanation = question.getExplanation();
        questionEntity.options = question.getOptions().stream()
                .map(option -> {
                    OptionEntity optionEntity = OptionEntity.from(option);
                    optionEntity.setQuestionEntity(questionEntity);
                    return optionEntity;
                })
                .collect(Collectors.toList());

        return questionEntity;
    }

    public Question to() {
        return Question.builder()
                .id(id)
                .question(question)
                .explanation(explanation)
                .options(options.stream()
                        .map(OptionEntity::to)
                        .collect(Collectors.toList()))
                .build();
    }
}
