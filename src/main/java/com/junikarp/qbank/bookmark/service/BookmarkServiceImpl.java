package com.junikarp.qbank.bookmark.service;

import com.junikarp.qbank.bookmark.controller.port.BookmarkService;
import com.junikarp.qbank.bookmark.domain.Bookmark;
import com.junikarp.qbank.bookmark.service.port.BookmarkRepository;
import com.junikarp.qbank.question.domain.Question;
import com.junikarp.qbank.question.infrastructure.QuestionJpaRepository;
import com.junikarp.qbank.user.domain.User;
import com.junikarp.qbank.user.infrastructure.UserJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Builder
public class BookmarkServiceImpl implements BookmarkService {

    private final QuestionJpaRepository questionJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final BookmarkRepository bookmarkRepository;

    @Override
    public List<Bookmark> findListByUserId(Long userId) {
        return bookmarkRepository.findByUserId(userId);
    }

    @Override
    public Bookmark create(Long userId, Long questionId) {
        User user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"))
                .to();
        Question question = questionJpaRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"))
                .to();
        Bookmark bookmark = Bookmark.from(user, question);
        return bookmarkRepository.save(bookmark);
    }

    @Override
    @Transactional
    public void delete(Long userId, Long questionId) {
        bookmarkRepository.delete(userId, questionId);
    }

    @Override
    public List<Question> getQuestionList(List<Bookmark> list) {
        return list.stream()
                .map(Bookmark::getQuestion)
                .toList();
    }
}
