package com.junikarp.qbank.bookmark.controller;

import com.junikarp.qbank.bookmark.controller.port.BookmarkService;
import com.junikarp.qbank.bookmark.controller.response.BookmarkResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Builder
@RequestMapping("/api/bookmark")
public class BookmarkController {

    @Autowired
    private final BookmarkService bookmarkService;

    @DeleteMapping(value = "/{userId}/{questionId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId, @PathVariable Long questionId) {
        bookmarkService.delete(userId, questionId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{userId}/{questionId}")
    public ResponseEntity<BookmarkResponse> create(@PathVariable Long userId, @PathVariable Long questionId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BookmarkResponse.from(bookmarkService.create(userId, questionId)));
    }

}
