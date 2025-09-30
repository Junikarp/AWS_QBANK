package com.junikarp.qbank.question.service;

import com.junikarp.qbank.bookmark.controller.port.BookmarkService;
import com.junikarp.qbank.bookmark.domain.Bookmark;
import com.junikarp.qbank.bookmark.service.port.BookmarkRepository;
import com.junikarp.qbank.question.controller.port.QuestionService;
import com.junikarp.qbank.question.domain.Question;
import com.junikarp.qbank.question.service.port.QuestionRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Builder
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final BookmarkService bookmarkService;

    @Override
    public List<Question> createRandomQuestionList(int Quantity) {

        List<Question> wholeQuestionList = questionRepository.findAll();

        questionShuffler(wholeQuestionList);

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

    @Override
    public List<Question> questionShuffler(List<Question> questionList) {
        Collections.shuffle(questionList);
        return questionList;
    }

    @Override
    public List<Question> findBookmarkedQuestions(Long userId) {
        List<Bookmark> bookmarks = bookmarkService.findListByUserId(userId);
        return findQuestionsById(bookmarkService.getQuestionIdList(bookmarks));
    }

}
