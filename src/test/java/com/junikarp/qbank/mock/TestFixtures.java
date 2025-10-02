package com.junikarp.qbank.mock;

import com.junikarp.qbank.choice.infrastructure.ChoiceEntity;
import com.junikarp.qbank.question.infrastructure.QuestionEntity;
import com.junikarp.qbank.user.infrastructure.UserEntity;

import java.util.List;

public class TestFixtures {

    // 테스트를 위한 ChoiceEntity 객체 생성
    public ChoiceEntity createChoiceEntity(Long id, String number, String text, Boolean isCorrect) {
        ChoiceEntity choice = new ChoiceEntity();
        choice.setId(id);
        choice.setNumber(number);
        choice.setText(text);
        choice.setIsCorrect(isCorrect);
        return choice;
    }

    // 테스트를 위한 QuestionEntity 객체 생성
    public QuestionEntity createQuestionEntity(Long id, String questionText, String explanation, List<ChoiceEntity> choices) {
        QuestionEntity question = new QuestionEntity();
        question.setId(id);
        question.setQuestion(questionText);
        question.setExplanation(explanation);
        question.setChoices(choices);
        choices.forEach(choiceEntity -> choiceEntity.setQuestionEntity(question));
        return question;
    }

    public UserEntity createUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        return userEntity;
    }
}
