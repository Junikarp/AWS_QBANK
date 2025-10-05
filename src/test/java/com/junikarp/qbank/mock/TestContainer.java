package com.junikarp.qbank.mock;

import com.junikarp.qbank.bookmark.controller.BookmarkController;
import com.junikarp.qbank.bookmark.controller.port.BookmarkService;
import com.junikarp.qbank.bookmark.infrastructure.BookmarkRepositoryImpl;
import com.junikarp.qbank.bookmark.service.BookmarkServiceImpl;
import com.junikarp.qbank.bookmark.service.port.BookmarkRepository;
import com.junikarp.qbank.question.controller.QuestionController;
import com.junikarp.qbank.question.controller.port.QuestionService;
import com.junikarp.qbank.question.controller.port.QuestionShuffler;
import com.junikarp.qbank.question.service.QuestionServiceImpl;
import com.junikarp.qbank.question.service.port.QuestionRepository;
import com.junikarp.qbank.user.service.port.UserRepository;
import lombok.Builder;

public class TestContainer {

    public final FakeQuestionRepository questionRepository;
    public final QuestionService questionService;
    public final QuestionController questionController;
    public final QuestionShuffler questionShuffler;

    public final FakeBookmarkRepository bookmarkRepository;
    public final BookmarkService bookmarkService;
    public final BookmarkController bookmarkController;

    public final FakeUserRepository userRepository;

    @Builder
    public TestContainer() {
        this.userRepository = new FakeUserRepository();
        this.bookmarkRepository = new FakeBookmarkRepository();
        this.questionShuffler = new QuestionShufflerTest();
        this.questionRepository = new FakeQuestionRepository(bookmarkRepository);
        this.questionService = QuestionServiceImpl.builder()
                .questionRepository(this.questionRepository)
                .questionShuffler(this.questionShuffler)
                .build();
        this.questionController = QuestionController.builder()
                .questionService(this.questionService)
                .build();
        this.bookmarkService = BookmarkServiceImpl.builder()
                .bookmarkRepository(this.bookmarkRepository)
                .questionRepository(this.questionRepository)
                .userRepository(this.userRepository)
                .build();
        this.bookmarkController = BookmarkController.builder()
                .bookmarkService(this.bookmarkService)
                .build();
    }
}
