package com.junikarp.qbank.bookmark.repository;

import com.junikarp.qbank.bookmark.infrastructure.BookmarkEntity;
import com.junikarp.qbank.bookmark.infrastructure.BookmarkJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource("classpath:/test-application.properties")
@SqlGroup({
        @Sql(value = "/sql/bookmark-repository-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/all-data-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
public class BookmarkRepositoryTest {

    @Autowired
    private BookmarkJpaRepository bookmarkJpaRepository;

    @Test
    void 유저_아이디로_북마크_리스트를_가져올_수_있다() {
        //given
        Long userId = 1L;

        //when
        List<BookmarkEntity> result = bookmarkJpaRepository.findByUserEntityId(userId);

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getUserEntity().getId()).isEqualTo(1);
        assertThat(result.get(0).getQuestionEntity().getId()).isEqualTo(1);
        assertThat(result.get(1).getQuestionEntity().getId()).isEqualTo(3);
    }

    @Test
    void 문제_아이디와_유저_아이디가_일치하는_북마크를_삭제할_수_있다(){
        //given
        Long userId = 1L;
        Long questionId = 1L;

        //when
        bookmarkJpaRepository.deleteByUserEntityIdAndQuestionEntityId(userId, questionId);

        //then
        List<BookmarkEntity> result = bookmarkJpaRepository.findByUserEntityId(userId);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getQuestionEntity().getId()).isEqualTo(3);
    }

}
