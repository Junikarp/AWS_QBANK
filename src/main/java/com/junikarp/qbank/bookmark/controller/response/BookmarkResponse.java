package com.junikarp.qbank.bookmark.controller.response;

import com.junikarp.qbank.bookmark.domain.Bookmark;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookmarkResponse {
    Long id;
    Long userId;
    Long questionId;

    public static BookmarkResponse from(Bookmark bookmark) {
        return BookmarkResponse.builder()
                .id(bookmark.getId())
                .userId(bookmark.getUser().getId())
                .questionId(bookmark.getQuestion().getId())
                .build();
    }
}
