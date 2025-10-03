package com.junikarp.qbank.bookmark.repository;

import com.junikarp.qbank.bookmark.domain.Bookmark;
import com.junikarp.qbank.bookmark.infrastructure.BookmarkEntity;
import com.junikarp.qbank.bookmark.infrastructure.BookmarkJpaRepository;
import com.junikarp.qbank.bookmark.infrastructure.BookmarkRepositoryImpl;
import com.junikarp.qbank.choice.infrastructure.ChoiceEntity;
import com.junikarp.qbank.mock.TestFixtures;
import com.junikarp.qbank.question.infrastructure.QuestionEntity;
import com.junikarp.qbank.question.infrastructure.QuestionJpaRepository;
import com.junikarp.qbank.user.infrastructure.UserEntity;
import com.junikarp.qbank.user.infrastructure.UserJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookmarkRepositoryTest {

    @InjectMocks
    private BookmarkRepositoryImpl bookmarkRepositoryImpl;
    @Mock
    private BookmarkJpaRepository bookmarkJpaRepository;
    @Mock
    private UserJpaRepository userJpaRepository;
    @Mock
    private QuestionJpaRepository questionJpaRepository;

    TestFixtures testFixtures = new TestFixtures();
    UserEntity userEntity;
    QuestionEntity questionEntity1;
    QuestionEntity questionEntity2;
    List<ChoiceEntity> choices1;
    List<ChoiceEntity> choices2;

    @BeforeEach
    void init() {
        choices1 = List.of(
                testFixtures.createChoiceEntity(1L, "A번", "A번 보기입니다.", false),
                testFixtures.createChoiceEntity(2L, "B번", "B번 보기입니다.", true)
        );

        choices2 = List.of(
                testFixtures.createChoiceEntity(3L, "A번", "A번 보기입니다.", true),
                testFixtures.createChoiceEntity(4L, "B번", "B번 보기입니다.", false)
        );

        userEntity = testFixtures.createUserEntity();
        questionEntity1 = testFixtures.createQuestionEntity(1L, "1번 문제입니다", "1번 문제 해설입니다", choices1);
        questionEntity2 = testFixtures.createQuestionEntity(2L, "2번 문제입니다", "2번 문제 해설입니다", choices2);
    }

    @Test
    void save_메서드로_북마크를_저장할_수_있다() {
        //given
        Bookmark bookmark = Bookmark.builder()
                .id(1L)
                .user(userEntity.to())
                .question(questionEntity1.to())
                .build();

        BookmarkEntity bookmarkEntity = BookmarkEntity.from(bookmark);

        //when
        when(bookmarkJpaRepository.save(any(BookmarkEntity.class))).thenReturn(bookmarkEntity);
        Bookmark result = bookmarkRepositoryImpl.save(bookmark);

        //then
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getUser().getId()).isEqualTo(1L);
        assertThat(result.getQuestion().getId()).isEqualTo(1L);

        verify(bookmarkJpaRepository).save(any(BookmarkEntity.class));
    }

    @Test
    void findByUserId_메서드로_북마크_리스트를_가져올_수_있다() {
        //given
        Long userId = 1L;

        Bookmark bookmark1 = Bookmark.builder()
                .id(1L)
                .user(userEntity.to())
                .question(questionEntity1.to())
                .build();
        Bookmark bookmark2 = Bookmark.builder()
                .id(1L)
                .user(userEntity.to())
                .question(questionEntity2.to())
                .build();

        BookmarkEntity bookmarkEntity1 = BookmarkEntity.from(bookmark1);
        BookmarkEntity bookmarkEntity2 = BookmarkEntity.from(bookmark2);

        List<BookmarkEntity> bookmarkEntityList = List.of(bookmarkEntity1, bookmarkEntity2);

        //when
        when(bookmarkJpaRepository.findByUserEntityId(userId)).thenReturn(bookmarkEntityList);
        List<Bookmark> result = bookmarkRepositoryImpl.findByUserId(userId);

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getUser().getId()).isEqualTo(1L);
        assertThat(result.get(1).getUser().getId()).isEqualTo(1L);
        assertThat(result.get(0).getQuestion().getId()).isEqualTo(1L);
        assertThat(result.get(1).getQuestion().getId()).isEqualTo(2L);

        verify(bookmarkJpaRepository).findByUserEntityId(userId);
    }

    @Test
    void delete_메서드는_JpaRepository의_delete_메서드를_호출한다() {
        //given
        Long userId = 1L;
        Long questionId = 1L;

        //when
        bookmarkRepositoryImpl.delete(userId, questionId);

        //then
        verify(bookmarkJpaRepository).deleteByUserEntityIdAndQuestionEntityId(userId, questionId);
    }
}
