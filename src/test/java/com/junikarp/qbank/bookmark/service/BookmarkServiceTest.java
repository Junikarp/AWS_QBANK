package com.junikarp.qbank.bookmark.service;

import com.junikarp.qbank.bookmark.controller.port.BookmarkService;
import com.junikarp.qbank.bookmark.domain.Bookmark;
import com.junikarp.qbank.choice.domain.Choice;
import com.junikarp.qbank.mock.FakeBookmarkRepository;
import com.junikarp.qbank.mock.FakeQuestionRepository;
import com.junikarp.qbank.mock.FakeUserRepository;
import com.junikarp.qbank.question.domain.Question;
import com.junikarp.qbank.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class BookmarkServiceTest {

    private BookmarkService bookmarkService;
    FakeQuestionRepository questionRepository;
    FakeUserRepository userRepository;
    FakeBookmarkRepository bookmarkRepository;

    @BeforeEach
    void init() {
        bookmarkRepository = new FakeBookmarkRepository();
        userRepository = new FakeUserRepository();
        questionRepository = new FakeQuestionRepository(bookmarkRepository);

        bookmarkService = BookmarkServiceImpl.builder()
                .questionRepository(questionRepository)
                .userRepository(userRepository)
                .bookmarkRepository(bookmarkRepository)
                .build();

        User user = User.builder()
                .id(1L)
                .email("jjkkp@email.com")
                .nickname("jjkkp")
                .build();

        Question question1 = Question.builder()
                .id(1L)
                .question("1번 문제 입니다.")
                .explanation("1번 문제에 대한 설명입니다.")
                .choice(new Choice(1L,"A","A번 보기입니다.",false,1L))
                .build();

        Question question2 = Question.builder()
                .id(2L)
                .question("2번 문제 입니다.")
                .explanation("2번 문제에 대한 설명입니다.")
                .choice(new Choice(2L,"A","A번 보기입니다.",false,2L))
                .build();

        Question question3 = Question.builder()
                .id(3L)
                .question("3번 문제 입니다.")
                .explanation("3번 문제에 대한 설명입니다.")
                .choice(new Choice(3L,"A","A번 보기입니다.",false,2L))
                .build();

        Bookmark bookmark1 = Bookmark.builder()
                .user(user)
                .question(question1)
                .build();

        Bookmark bookmark2 = Bookmark.builder()
                .user(user)
                .question(question3)
                .build();

        userRepository.save(user);
        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(question3);
        bookmarkRepository.save(bookmark1);
        bookmarkRepository.save(bookmark2);
    }

    @Test
    void findListByUserId_메서드는_유저_아이디로_북마크_리스트를_찾을_수_있다(){
        //given
        Long userId = 1L;

        //when
        List<Bookmark> result = bookmarkService.findListByUserId(userId);

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getUser().getId()).isEqualTo(1L);
        assertThat(result.get(0).getQuestion().getId()).isEqualTo(1L);
        assertThat(result.get(1).getQuestion().getId()).isEqualTo(3L);
    }

    @Test
    void create를_이용하여_북마크를_생성_할_수_있다() {
        //given
        Long userId = 1L;
        Long questionId = 2L;

        //when
        Bookmark result = bookmarkService.create(userId, questionId);

        //then
        assertThat(result.getUser().getId()).isEqualTo(userId);
        assertThat(result.getQuestion().getId()).isEqualTo(questionId);
    }

    @Test
    void delete를_이용하여_북마크를_삭제_할_수_있다() {
        //given
        Long userId = 1L;
        Long questionId = 1L;

        //when
        bookmarkService.delete(userId, questionId);
        List<Bookmark> result = bookmarkRepository.findByUserId(userId);

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getUser().getId()).isEqualTo(1L);
        assertThat(result.get(0).getQuestion().getId()).isEqualTo(3L);
    }
}
