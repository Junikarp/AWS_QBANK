package com.junikarp.qbank.midium;

import com.junikarp.qbank.question.infrastructure.QuestionEntity;
import com.junikarp.qbank.question.infrastructure.QuestionJpaRepository;
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
        @Sql(value = "/sql/question-repository-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/all-data-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
public class QuestionJpaRepositoryTest {

    @Autowired
    QuestionJpaRepository questionJpaRepository;

    @Test
    void findAll로_모든_문제_데이터를_가져올_수_있다() {
        //given
        //when
        List<QuestionEntity> result = questionJpaRepository.findAll();

        //then
        assertThat(result.get(0).getQuestion()).isEqualTo("1번 문제 입니다.");
        assertThat(result.get(1).getChoices().size()).isEqualTo(4);
        assertThat(result.get(0).getChoices().get(2).getIsCorrect()).isTrue();
    }

    @Test
    void 유저_아이디를_매개변수로_북마크된_문제_리스트를_가져올_수_있다() {
        //given
        Long userId = 1L;

        //when
        List<QuestionEntity> result = questionJpaRepository.findBookmarkedQuestionsByUserId(userId);

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getQuestion()).isEqualTo("1번 문제 입니다.");
        assertThat(result.get(0).getChoices().size()).isEqualTo(4);
    }
}
