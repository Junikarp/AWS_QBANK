package com.junikarp.qbank.bookmark.service.port;

import com.junikarp.qbank.bookmark.domain.Bookmark;

import java.util.List;

public interface BookmarkRepository {

    Bookmark save(Bookmark bookmark);

    List<Bookmark> findByUserId(Long userId);

    void delete(Long userId, Long questionId);
}
