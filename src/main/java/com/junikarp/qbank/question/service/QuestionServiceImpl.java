package com.junikarp.qbank.question.service;

import com.junikarp.qbank.question.controller.port.QuestionService;
import com.junikarp.qbank.question.domain.Question;
import com.junikarp.qbank.question.infrastructure.QuestionEntity;
import com.junikarp.qbank.question.service.port.QuestionRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Builder
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private final QuestionRepository questionRepository;

    @Override
    public List<Question> createRandomQuestionList(int Quantity) {

        List<Question> wholeQuestionList = questionRepository.findAll();

        Collections.shuffle(wholeQuestionList);

        return wholeQuestionList.stream()
                .limit(Quantity)
                .toList();

    }

    @Override
    public List<Question> findQuestionsById(List<Long> idList) {

        List<Question> wholeQuestionList = questionRepository.findAll();

        return  wholeQuestionList.stream()
                .filter(question -> idList.contains(question.getId()))
                .toList();
    }

}
