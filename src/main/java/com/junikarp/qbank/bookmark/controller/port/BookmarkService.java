package com.junikarp.qbank.bookmark.controller.port;

import com.junikarp.qbank.bookmark.domain.Bookmark;

import java.util.List;

public interface BookmarkService {

    /* TODO 유저 아이디로 북마크 리스트 가져오기, 유저 아이디랑 문제 아이디로 새로운 북마크 생성, 북마크 취소 기능
    */

    // 유저 아이디로 북마크 리스트 가져오기
    List<Bookmark> findListByUserId(Long userId);

    // 북마크 생성
    Bookmark create(Long userId, Long questionId);

    // 북마크 삭제
    void delete(Long userId, Long questionId);

    // 문제 아이디 리스트 추출
    List<Long> getQuestionIdList(List<Bookmark> list);

}
