package com.junikarp.qbank.bookmark.infrastructure;

import com.junikarp.qbank.bookmark.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookmarkJpaRepository extends JpaRepository<BookmarkEntity, Long> {

    List<BookmarkEntity> findByUserEntityId(Long userId);

    void deleteByUserEntityIdAndQuestionEntityId(Long userId, Long questionId);
}
