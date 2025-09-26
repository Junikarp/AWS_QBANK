package com.junikarp.qbank.question.mock;

import com.junikarp.qbank.question.controller.QuestionController;
import com.junikarp.qbank.question.controller.port.QuestionService;
import com.junikarp.qbank.question.service.QuestionServiceImpl;
import com.junikarp.qbank.question.service.port.QuestionRepository;
import lombok.Builder;

public class TestContainer {

    public final QuestionRepository questionRepository;
    public final QuestionService questionService;
    public final QuestionController questionController;

    @Builder
    public TestContainer() {
        this.questionRepository = new FakeQuestionRepository();
        this.questionService = QuestionServiceImpl.builder()
                .questionRepository(this.questionRepository)
                .build();
        this.questionController = QuestionController.builder()
                .questionService(this.questionService)
                .build();
    }
}
