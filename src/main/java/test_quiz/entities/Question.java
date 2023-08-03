package test_quiz.entities;

import test_quiz.entities.Answer;

import java.util.List;

public record Question(String questionContents, List<String> variants, Answer answer) {
    public boolean isTrueAnswer(final String givenAnswer) {
        return givenAnswer.equals(answer.answerContents());
    }
}
