package com.junikarp.qbank.bookmark.infrastructure;

import com.junikarp.qbank.bookmark.domain.Bookmark;
import com.junikarp.qbank.bookmark.service.port.BookmarkRepository;
import com.junikarp.qbank.question.infrastructure.QuestionEntity;
import com.junikarp.qbank.question.infrastructure.QuestionJpaRepository;
import com.junikarp.qbank.question.service.port.QuestionRepository;
import com.junikarp.qbank.user.infrastructure.UserEntity;
import com.junikarp.qbank.user.infrastructure.UserJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements BookmarkRepository {

    private final BookmarkJpaRepository bookmarkJpaRepository;

    public Bookmark save(Bookmark bookmark) {
        return bookmarkJpaRepository.save(BookmarkEntity.from(bookmark)).to();
    }

    @Override
    public List<Bookmark> findByUserId(Long userId) {
        List<BookmarkEntity> list = bookmarkJpaRepository.findByUserEntityId(userId);
        return list.stream()
                .map(BookmarkEntity::to)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long userId, Long questionId) {
        bookmarkJpaRepository.deleteByUserEntityIdAndQuestionEntityId(userId, questionId);
    }
}
