package com.junikarp.qbank.question.infrastructure;

import com.junikarp.qbank.question.domain.Question;
import com.junikarp.qbank.question.service.port.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
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
public class QuestionRepositoryTest {

    @Autowired
    QuestionJpaRepository questionRepository;

    @Test
    void findAll로_모든_문제_데이터를_가져올_수_있다() {
        //given
        //when
        List<QuestionEntity> list = questionRepository.findAll();

        //then
        assertThat(list.get(0).getQuestion()).isEqualTo("1번 문제 입니다.");
        assertThat(list.get(0).getOptions().size()).isEqualTo(4);
        assertThat(list.get(0).getOptions().get(2).getIsCorrect()).isTrue();
    }
}
