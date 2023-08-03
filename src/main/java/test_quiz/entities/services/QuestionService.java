package test_quiz.entities.services;

import test_quiz.entities.Answer;
import test_quiz.entities.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionService {
    public List<Question> setData() {
        List<Question> questionList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            List<String> options = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                options.add(j, "option " + j);
            }
            questionList.add(new Question("Q:" + i + " question content",
                            options, new Answer("option 1")));
        }

        return questionList;
    }
}
