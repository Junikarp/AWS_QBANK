package com.junikarp.qbank.bookmark.service;

import com.junikarp.qbank.bookmark.controller.port.BookmarkService;
import com.junikarp.qbank.bookmark.domain.Bookmark;
import com.junikarp.qbank.bookmark.service.port.BookmarkRepository;
import com.junikarp.qbank.question.domain.Question;
import com.junikarp.qbank.question.service.port.QuestionRepository;
import com.junikarp.qbank.user.domain.User;
import com.junikarp.qbank.user.service.port.UserRepository;
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

    private final BookmarkRepository bookmarkRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    @Override
    public List<Bookmark> findListByUserId(Long userId) {
        return bookmarkRepository.findByUserId(userId);
    }

    @Override
    public Bookmark create(Long userId, Long questionId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));
        Bookmark bookmark = Bookmark.from(user, question);
        return bookmarkRepository.save(bookmark);
    }

    @Override
    @Transactional
    public void delete(Long userId, Long questionId) {
        bookmarkRepository.delete(userId, questionId);
    }

}
