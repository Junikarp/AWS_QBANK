package com.junikarp.qbank.bookmark.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Bookmark {

    private Long id;

    private Long userId;

    private Long questionId;

}
