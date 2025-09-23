package com.junikarp.qbank.question.infrastructure;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question", columnDefinition = "TEXT")
    private String question;

    @Column(name = "explanation", columnDefinition = "TEXT")
    private String explanation;

    @Column(name = "options")
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OptionEntity> options;
}
