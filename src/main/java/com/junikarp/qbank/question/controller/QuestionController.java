package com.junikarp.qbank.question.controller;

import com.junikarp.qbank.question.controller.port.QuestionService;
import com.junikarp.qbank.question.controller.response.BookmarkQuestionListResponse;
import com.junikarp.qbank.question.controller.response.RandomQuestionListResponse;
import com.junikarp.qbank.question.domain.Question;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/question")
@Builder
public class QuestionController {

    @Autowired
    private final QuestionService questionService;

    @GetMapping("/random/{quantity}")
    public ResponseEntity<RandomQuestionListResponse> getRandomQuestionList(@PathVariable int quantity) {

        List<Question> RandomQuestionList = questionService.createRandomQuestionList(quantity);

        return ResponseEntity
                .ok()
                .body(RandomQuestionListResponse.from(RandomQuestionList));
    }

    @GetMapping(value = "/bookmarked/{userId}")
    public ResponseEntity<BookmarkQuestionListResponse> getQuestionListByUserId(@PathVariable Long userId) {
        return ResponseEntity
                .ok()
                .body(BookmarkQuestionListResponse.from(questionService.findBookmarkedQuestions(userId)));
    }
}
