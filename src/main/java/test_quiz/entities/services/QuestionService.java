package test_quiz.entities.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import test_quiz.entities.Answer;
import test_quiz.entities.Question;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class QuestionService {
    public List<Question> readQuestionFromFile() throws IOException {
        List<Question> questions = new ArrayList<>();

        URL url = new URL("https://api.jsonbin.io/v3/b/64db27789d312622a3916a70");
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();

        JSONObject readObject = new JSONObject(new JSONTokener(httpsURLConnection.getInputStream()));
        JSONArray objects = readObject.getJSONArray("record");
        for (int i = 0; i < objects.length(); i++) {
            String questionContent = objects.getJSONObject(i).getString("question");
            String answer = objects.getJSONObject(i).getString("answer");
            JSONArray options = objects.getJSONObject(i).getJSONArray("options");
            List<String> stringOptions = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                stringOptions.add(options.getString(j));
            }
            questions.add(new Question(questionContent, stringOptions, new Answer(answer)));
        }

        return questions;
    }
}
