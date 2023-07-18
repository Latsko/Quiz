package org.testQuiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quiz extends JFrame implements ActionListener {
    // a blank label, which can hold text and picture
    private final JLabel questionLabel;
    private final JLabel timerLabel;
    private final Timer timer;
    private int timerCount;
    private final JPanel testPanel;
    private final JPanel timerPanel;
    // radial buttons, we create 5, because fifth will be always set as a default,
    // so no other is selected from start
    private final JRadioButton[] radioButtons = new JRadioButton[5];
    // our buttons to go through program
    private final JButton startButton, btnNext, btnResult;
    // creates button group, where when one is selected, others are disabled
    private final ButtonGroup buttonGroup;
    private int countCorrect, current;

    Quiz(String s) {
        super(s);
        // here i set all necessary parameters to my frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(1000, 500);
        getContentPane().setBackground(Color.DARK_GRAY);

        // configuring my panel dedicated for the test part
        testPanel = new JPanel();
        testPanel.setBackground(Color.GRAY);
        testPanel.setBounds(0, 0, 700, 300);

        // configuring timer panel
        timerPanel = new JPanel();
        timerPanel.setBackground(Color.red);
        timerPanel.setBounds(700, 0, 200, 300);

        // configuring question text label
        questionLabel = new JLabel();
        questionLabel.setBounds(125, 15, 450, 50);
        questionLabel.setOpaque(true);
        questionLabel.setBackground(Color.BLACK);
        questionLabel.setForeground(Color.lightGray);
        questionLabel.setVerticalAlignment(JLabel.TOP);
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        questionLabel.setVisible(false);
        testPanel.add(questionLabel);

        // setting up timer label
        timerLabel = new JLabel("Click to start");
        timerLabel.setBounds(50, 100, 100, 100);
        timerLabel.setOpaque(true);
        timerLabel.setBackground(Color.BLACK);
        timerLabel.setVerticalAlignment(JLabel.CENTER);
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerPanel.add(timerLabel);

        // initialize timer and show in timerLabel
        timer = new Timer(1000, e -> {
            timerCount++;
            if (timerCount < 1800) {
                timerLabel.setText(convertNumToMinutes(timerCount));
            } else {
                ((Timer) (e.getSource())).stop();
            }
        });
        timer.setInitialDelay(1000);

        // those methods here sets panel layout to the same JFrame has by default
        testPanel.setLayout(new BorderLayout());
        timerPanel.setLayout(new BorderLayout());

        // start button
        startButton = new JButton("Start Test");
        startButton.setBounds(20, 20, 100, 30);
        startButton.setBackground(Color.green);
        timerPanel.add(startButton);
        startButton.addActionListener(this);

        // creating buttons with answers and others, necessary to traverse my programme
        buttonGroup = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            radioButtons[i] = new JRadioButton();
            radioButtons[i].setEnabled(false);
            testPanel.add(radioButtons[i]);
            buttonGroup.add(radioButtons[i]);
        }
        btnNext = new JButton("Next");
        btnResult = new JButton("Result");

        // hide result button and add action listener to next and result buttons
        btnResult.setVisible(false);
        btnResult.setEnabled(false);
        btnResult.addActionListener(this);

        btnNext.setEnabled(false);
        btnNext.addActionListener(this);

        testPanel.add(btnNext);
        testPanel.add(btnResult);

        add(timerPanel);
        add(testPanel);

        //sets data for radial buttons
        setData();

        //setting buttons bounds
        for (int i = 80, j = 0; i < 170; i += 30, j++) {
            radioButtons[j].setBounds(50, i, 200, 20);
        }
        btnNext.setBounds(100, 240, 100, 30);
        btnResult.setBounds(270, 240, 100, 30);

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

    private void setData() {
        radioButtons[4].setSelected(true);
        radioButtons[4].setVisible(false);
        if (current == 0) {
            questionLabel.setText("<HTML>Q1: Which is official language for " +
                    "Android developers Which is official language for Android developers</HTML>");
            radioButtons[0].setText("Java");
            radioButtons[1].setText("Kotlin");
            radioButtons[2].setText("C++");
            radioButtons[3].setText("another text");
        }
        if (current == 1) {
            questionLabel.setText("Q1: Which is official language for Android developers");
            radioButtons[0].setText("Java");
            radioButtons[1].setText("Kotlin");
            radioButtons[2].setText("C++");
            radioButtons[3].setText("JavaScript");
        }
        if (current == 2) {
            questionLabel.setText("Q1: Which is official language for Android developers");
            radioButtons[0].setText("Java");
            radioButtons[1].setText("Kotlin");
            radioButtons[2].setText("C++");
            radioButtons[3].setText("JavaScript");
        }
        if (current == 3) {
            questionLabel.setText("Q1: Which is official language for Android developers");
            radioButtons[0].setText("Java");
            radioButtons[1].setText("Kotlin");
            radioButtons[2].setText("C++");
            radioButtons[3].setText("JavaScript");
        }
        if (current == 4) {
            questionLabel.setText("Q1: Which is official language for Android developers");
            radioButtons[0].setText("Java");
            radioButtons[1].setText("Kotlin");
            radioButtons[2].setText("C++");
            radioButtons[3].setText("JavaScript");
        }
        if (current == 5) {
            questionLabel.setText("Q1: Which is official language for Android developers");
            radioButtons[0].setText("Java");
            radioButtons[1].setText("Kotlin");
            radioButtons[2].setText("C++");
            radioButtons[3].setText("JavaScript");
        }

        for (int i = 0, j = 0; i <= 90; i += 30, j++) {
            radioButtons[j].setBounds(50, 80 + i, 200, 20);
        }
    }

    boolean checkAns() {
        // here we check which one of the buttons is the right answer simply asking what button was
        // selected, which means, we are setting right answers here as well, simply writing giving
        // a number int the radioButtons array
        if (current == 0) {
            return (radioButtons[1]).isSelected();
        }
        if (current == 1) {
            return (radioButtons[1]).isSelected();
        }
        if (current == 2) {
            return (radioButtons[1]).isSelected();
        }
        if (current == 3) {
            return (radioButtons[1]).isSelected();
        }
        if (current == 4) {
            return (radioButtons[1]).isSelected();
        }
        if (current == 5) {
            return (radioButtons[1]).isSelected();
        }

        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            startButton.setVisible(false);
            questionLabel.setVisible(true);
            btnNext.setEnabled(true);
            btnResult.setEnabled(true);
            for (int i = 0; i < 4; i++) {
                radioButtons[i].setEnabled(true);
            }

            timer.start();
        }

        if (e.getSource() == btnNext) {
            if (checkAns()) {
                countCorrect++;
            }
            current++;
            setData();
            if (current == 6) {
                btnNext.setEnabled(false);
                btnResult.setVisible(true);
            }
        }

        if (e.getActionCommand().equals("Result")) {
            if (checkAns()) {
                countCorrect++;
            }
            current++;
            JOptionPane.showMessageDialog(this, "Correct answers are: " + countCorrect);
            System.exit(0);
        }
    }
}
