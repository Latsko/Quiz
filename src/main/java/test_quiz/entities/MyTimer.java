package test_quiz.entities;

import javax.swing.*;

public class MyTimer {
    private int timerCount = 0;

    public Timer getTimer(JLabel label) {
        return new Timer(1000, e -> {
            timerCount++;
            if (timerCount < 1800) {
                label.setText(convertNumToMinutes(timerCount));
            } else {
                ((Timer) (e.getSource())).stop();
            }
        });
    }

    private String convertNumToMinutes(final int unformattedTime) {
        int seconds, minutes;
        String time;
        seconds = unformattedTime % 60;
        minutes = unformattedTime / 60;
        if (minutes < 10 && seconds < 10) {
            time = "0" + minutes + ":0" + seconds;
        } else if (minutes < 10) {
            time = "0" + minutes + ":" + seconds;
        } else if (seconds < 10) {
            time = minutes + ":0" + seconds;
        } else {
            time = minutes + ":" + seconds;
        }

        return time;
    }
}
