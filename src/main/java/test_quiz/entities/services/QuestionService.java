package test_quiz.entities.services;

import org.json.JSONArray;
import org.json.JSONTokener;
import test_quiz.entities.Answer;
import test_quiz.entities.Question;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class QuestionService {
    public List<Question> readQuestionFromFile() throws FileNotFoundException, MalformedURLException {
        List<Question> questions = new ArrayList<>();

        URL url = new URL("https://api.jsonbin.io/v3/b/64db27789d312622a3916a70");

        JSONArray read = new JSONArray(new JSONTokener(new FileInputStream("questions.json")));
        for (int i = 0; i < read.length(); i++) {
            String questionContent = read.getJSONObject(i).getString("question");
            String answer = read.getJSONObject(i).getString("answer");
            JSONArray options = read.getJSONObject(i).getJSONArray("options");
            List<String> stringOptions = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                stringOptions.add(options.getString(j));
            }
            questions.add(new Question(questionContent, stringOptions, new Answer(answer)));
        }

        return questions;
    }
}
