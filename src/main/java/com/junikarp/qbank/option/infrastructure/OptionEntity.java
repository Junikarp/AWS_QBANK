package com.junikarp.qbank.option.infrastructure;

import com.junikarp.qbank.question.infrastructure.QuestionEntity;
import jakarta.persistence.*;

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

    @Column(name = "question_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private QuestionEntity questionEntity;

}
