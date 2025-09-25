//package com.junikarp.qbank.question.service;
//
//import com.junikarp.qbank.option.domain.Option;
//import com.junikarp.qbank.question.controller.port.QuestionService;
//import com.junikarp.qbank.question.domain.Question;
//import com.junikarp.qbank.question.mock.FakeQuestionRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//public class QuestionServiceTest {
//
//    private QuestionService questionService;
//
//    @BeforeEach
//    void init() {
//        FakeQuestionRepository fakeQuestionRepository = new FakeQuestionRepository();
//
//        this.questionService = QuestionServiceImpl.builder()
//                .questionRepository(fakeQuestionRepository)
//                .build();
//
//        Question question1 = Question.builder()
//                .id(1L)
//                .question("1번 문제 입니다.")
//                .explanation("1번 문제에 대한 설명입니다.")
//                .option(new Option(1L,"A","A번 보기입니다.",false,1L))
//                .option(new Option(2L,"B","B번 보기입니다.",false,1L))
//                .option(new Option(3L,"C","C번 보기입니다.",true,1L))
//                .option(new Option(4L,"D","D번 보기입니다.",false,1L))
//                .build();
//
//        Question question2 = Question.builder()
//                .id(2L)
//                .question("2번 문제 입니다.")
//                .explanation("2번 문제에 대한 설명입니다.")
//                .option(new Option(5L,"A","A번 보기입니다.",false,2L))
//                .option(new Option(6L,"B","B번 보기입니다.",true,2L))
//                .option(new Option(7L,"C","C번 보기입니다.",false,2L))
//                .option(new Option(8L,"D","D번 보기입니다.",false,2L))
//                .build();
//
//        Question question3 = Question.builder()
//                .id(3L)
//                .question("3번 문제 입니다.")
//                .explanation("3번 문제에 대한 설명입니다.")
//                .option(new Option(9L,"A","A번 보기입니다.",false,3L))
//                .option(new Option(10L,"B","B번 보기입니다.",true,3L))
//                .option(new Option(11L,"C","C번 보기입니다.",false,3L))
//                .option(new Option(12L,"D","D번 보기입니다.",false,3L))
//                .build();
//
//        fakeQuestionRepository.save(question1);
//        fakeQuestionRepository.save(question2);
//        fakeQuestionRepository.save(question3);
//    }
//
//    @Test
//    void findAll로_모든_문제_데이터를_가져올_수_있다() {
//        //given
//        //when
//
//        //then
//
//    }
//}
