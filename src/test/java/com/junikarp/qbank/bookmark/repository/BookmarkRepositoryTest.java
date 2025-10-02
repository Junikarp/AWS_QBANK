package com.junikarp.qbank.bookmark.repository;

import com.junikarp.qbank.bookmark.domain.Bookmark;
import com.junikarp.qbank.bookmark.infrastructure.BookmarkEntity;
import com.junikarp.qbank.bookmark.infrastructure.BookmarkJpaRepository;
import com.junikarp.qbank.bookmark.infrastructure.BookmarkRepositoryImpl;
import com.junikarp.qbank.choice.infrastructure.ChoiceEntity;
import com.junikarp.qbank.mock.TestFixtures;
import com.junikarp.qbank.question.infrastructure.QuestionEntity;
import com.junikarp.qbank.user.infrastructure.UserEntity;
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
    private BookmarkRepositoryImpl BookmarkRepositoryImpl;
    @Mock
    private BookmarkJpaRepository bookmarkJpaRepository;

    TestFixtures testFixtures = new TestFixtures();

    @Test
    void save_메서드로_북마크를_저장할_수_있다() {
        //given
        Bookmark bookmark = Bookmark.builder()
                .id(1L)
                .userId(1L)
                .questionId(1L)
                .build();

        List<ChoiceEntity> choices1 = List.of(
                testFixtures.createChoiceEntity(1L, "A번", "A번 보기입니다.", false),
                testFixtures.createChoiceEntity(2L, "B번", "B번 보기입니다.", true)
        );

        UserEntity userEntity = testFixtures.createUserEntity();
        QuestionEntity questionEntity = testFixtures.createQuestionEntity(1L, "1번 문제입니다", "1번 문제 해설입니다", choices1);

        BookmarkEntity bookmarkEntity = BookmarkEntity.from(bookmark);
        bookmarkEntity.setQuestionEntity(questionEntity);
        bookmarkEntity.setUserEntity(userEntity);

        //when
        when(bookmarkJpaRepository.save(any(BookmarkEntity.class))).thenReturn(bookmarkEntity);
        Bookmark result = BookmarkRepositoryImpl.save(bookmark);

        //then
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getUserId()).isEqualTo(1L);
        assertThat(result.getQuestionId()).isEqualTo(1L);

        verify(bookmarkJpaRepository).save(any(BookmarkEntity.class));
    }

/* TODO
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
*/
}
