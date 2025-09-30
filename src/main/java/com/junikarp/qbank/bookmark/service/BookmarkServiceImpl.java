package com.junikarp.qbank.bookmark.service;

import com.junikarp.qbank.bookmark.controller.port.BookmarkService;
import com.junikarp.qbank.bookmark.domain.Bookmark;
import com.junikarp.qbank.bookmark.service.port.BookmarkRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Builder
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    @Override
    public List<Bookmark> findListByUserId(Long userId) {
        return bookmarkRepository.findByUserId(userId);
    }

    @Override
    public Bookmark create(Long userId, Long questionId) {
        Bookmark bookmark = Bookmark.from(userId, questionId);
        return bookmarkRepository.save(bookmark);
    }

    @Override
    @Transactional
    public void delete(Long userId, Long questionId) {
        bookmarkRepository.delete(userId, questionId);
    }

    @Override
    public List<Long> getQuestionIdList(List<Bookmark> list) {
        return list.stream()
                .map(Bookmark::getQuestionId)
                .toList();
    }
}
