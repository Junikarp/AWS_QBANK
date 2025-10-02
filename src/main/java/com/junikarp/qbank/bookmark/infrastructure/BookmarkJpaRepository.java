package com.junikarp.qbank.bookmark.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkJpaRepository extends JpaRepository<BookmarkEntity, Long> {

    List<BookmarkEntity> findByUserEntityId(Long userId);
    // 유저 아이디와 문제 아이디로 북마크 내역 삭제
    void deleteByUserEntityIdAndQuestionEntityId(Long userId, Long questionId);
}
