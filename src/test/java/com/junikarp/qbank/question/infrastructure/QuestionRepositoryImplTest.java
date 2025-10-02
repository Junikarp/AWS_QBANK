package com.junikarp.qbank.question.infrastructure;

import com.junikarp.qbank.choice.domain.Choice;
import com.junikarp.qbank.choice.infrastructure.ChoiceEntity;
import com.junikarp.qbank.mock.TestFixtures;
import com.junikarp.qbank.question.domain.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionRepositoryImplTest {

    @Mock
    private QuestionJpaRepository questionJpaRepository;
    @InjectMocks
    private QuestionRepositoryImpl questionRepositoryImpl;

    TestFixtures testFixtures = new TestFixtures();

    @Test
    void findAll_메서드는_전체_문제_리스트를_도메인_엔티티로_변환_후_반환한다() {
        // given
        List<QuestionEntity> questions = new ArrayList<>();

        List<ChoiceEntity> choices1 = List.of(
                testFixtures.createChoiceEntity(1L, "A번", "A번 보기입니다.", false),
                testFixtures.createChoiceEntity(2L, "B번", "B번 보기입니다.", true)
        );
        List<ChoiceEntity> choices2 = List.of(
                testFixtures.createChoiceEntity(3L, "A번", "A번 보기입니다.", true),
                testFixtures.createChoiceEntity(4L, "B번", "B번 보기입니다.", false)
        );

        QuestionEntity questionEntity1 = testFixtures.createQuestionEntity(1L, "1번 문제입니다", "1번 문제 해설입니다", choices1);
        QuestionEntity questionEntity2 = testFixtures.createQuestionEntity(2L, "2번 문제입니다", "2번 문제 해설입니다", choices2);

        questions.add(questionEntity1);
        questions.add(questionEntity2);

        // when
        when(questionJpaRepository.findAll()).thenReturn(questions);
        List<Question> result = questionRepositoryImpl.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getExplanation()).isEqualTo("1번 문제 해설입니다");
    }

    @Test
    void save_메서드는_도메인_엔티티를_영속성_객체로_변환_후_저장하고_다시_도메인_엔티티로_변환하여_반환한다() {
        // given
        List<Choice> choices = List.of(
                new Choice(1L, "A번", "A번 보기입니다.", false, 1L),
                new Choice(2L, "B번", "B번 보기입니다.", false, 1L),
                new Choice(3L, "C번", "C번 보기입니다.", true, 1L),
                new Choice(4L, "D번", "D번 보기입니다.", false, 1L)
        );
        Question question = Question.builder()
                .id(1L)
                .question("1번 문제입니다")
                .explanation("1번 문제 해설입니다")
                .choices(choices)
                .build();

        QuestionEntity questionEntity = QuestionEntity.from(question);

        // when
        when(questionJpaRepository.save(any(QuestionEntity.class))).thenReturn(questionEntity);
        Question result = questionRepositoryImpl.save(question);

        // then
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getQuestion()).isEqualTo("1번 문제입니다");
        assertThat(result.getExplanation()).isEqualTo("1번 문제 해설입니다");
        assertThat(result.getChoices().size()).isEqualTo(4);
        assertThat(result.getChoices().get(1).getText()).isEqualTo("B번 보기입니다.");

        verify(questionJpaRepository).save(any(QuestionEntity.class));

    }

    @Test
    void findBookmarkedQuestionsByUserId_메서드는_유저아이디로_북마크한_문제를_불러올_수_있다() {
        //given
        Long userId = 1L;

        List<QuestionEntity> questions = new ArrayList<>();

        List<ChoiceEntity> choices1 = List.of(
                testFixtures.createChoiceEntity(1L, "A번", "A번 보기입니다.", false),
                testFixtures.createChoiceEntity(2L, "B번", "B번 보기입니다.", true)
        );
        List<ChoiceEntity> choices2 = List.of(
                testFixtures.createChoiceEntity(3L, "A번", "A번 보기입니다.", true),
                testFixtures.createChoiceEntity(4L, "B번", "B번 보기입니다.", false),
                testFixtures.createChoiceEntity(5L, "C번", "C번 보기입니다.", false)
        );

        QuestionEntity questionEntity1 = testFixtures.createQuestionEntity(1L, "1번 문제입니다", "1번 문제 해설입니다", choices1);
        QuestionEntity questionEntity2 = testFixtures.createQuestionEntity(2L, "2번 문제입니다", "2번 문제 해설입니다", choices2);

        questions.add(questionEntity1);
        questions.add(questionEntity2);

        //when
        when(questionJpaRepository.findBookmarkedQuestionsByUserId(any(Long.class))).thenReturn(questions);
        List<Question> result = questionRepositoryImpl.findBookmarkedQuestionsByUserId(userId);


        //then
        assertThat(result.get(0).getId()).isEqualTo(1L);
        assertThat(result.get(0).getQuestion()).isEqualTo("1번 문제입니다");
        assertThat(result.get(1).getExplanation()).isEqualTo("2번 문제 해설입니다");
        assertThat(result.get(0).getChoices().size()).isEqualTo(2);
        assertThat(result.get(1).getChoices().size()).isEqualTo(3);

        verify(questionJpaRepository).findBookmarkedQuestionsByUserId(any(Long.class));
    }
}
