package com.junikarp.qbank.question.controller.port;

import com.junikarp.qbank.question.domain.Question;
import java.util.List;

public interface QuestionShuffler {

    List<Question> shuffle(List<Question> questionList);

}
