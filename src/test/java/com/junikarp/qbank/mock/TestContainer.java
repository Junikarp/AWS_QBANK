package com.junikarp.qbank.mock;

import com.junikarp.qbank.bookmark.infrastructure.BookmarkRepositoryImpl;
import com.junikarp.qbank.bookmark.service.port.BookmarkRepository;
import com.junikarp.qbank.question.controller.QuestionController;
import com.junikarp.qbank.question.controller.port.QuestionService;
import com.junikarp.qbank.question.controller.port.QuestionShuffler;
import com.junikarp.qbank.question.service.QuestionServiceImpl;
import com.junikarp.qbank.question.service.port.QuestionRepository;
import lombok.Builder;

public class TestContainer {

    public final QuestionRepository questionRepository;
    public final QuestionService questionService;
    public final QuestionController questionController;
    public final QuestionShuffler questionShuffler;
    public final BookmarkRepository bookmarkRepository;

    @Builder
    public TestContainer() {
        FakeBookmarkRepository fakeBookmarkRepository = new FakeBookmarkRepository();
        this.bookmarkRepository = fakeBookmarkRepository;
        this.questionShuffler = new QuestionShufflerTest();
        this.questionRepository = new FakeQuestionRepository(fakeBookmarkRepository);
        this.questionService = QuestionServiceImpl.builder()
                .questionRepository(this.questionRepository)
                .questionShuffler(this.questionShuffler)
                .build();
        this.questionController = QuestionController.builder()
                .questionService(this.questionService)
                .build();
    }
}
