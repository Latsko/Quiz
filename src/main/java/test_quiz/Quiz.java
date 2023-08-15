package test_quiz;

import test_quiz.entities.MyTimer;
import test_quiz.entities.Question;
import test_quiz.entities.services.QuestionService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class Quiz extends JFrame implements ActionListener {
    private static final String MAIN_COLOR = "#551fbd";
    private static final String DARKER_MAIN_COLOR = "#390879";
    private static final String SECONDARY_COLOR = "#a2eacb";
    private static final String DARKER_SECONDARY_COLOR = "#2cd9c1";
    private final JLabel questionLabel;
    private final JLabel greetingLabel;
    private final JLabel timerLabel;
    private final JPanel progressPanel;
    private final JPanel testPanel;
    private final JPanel timerPanel;
    private final JButton startButton, btnNext, btnResult;
    private final JRadioButton[] radioButtons = new JRadioButton[5];
    private final ButtonGroup buttonGroup;
    private final Timer timer;
    private final JProgressBar progressBar;
    private int countCorrect, current, progressValue;
    private final List<Question> questionList;

    Quiz(String s) throws IOException {
        super(s);
        this.questionList = new QuestionService().readQuestionFromFile();
        // here I set all necessary parameters to my frame
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(1016, 590);
        getContentPane().setBackground(Color.DARK_GRAY);

        progressPanel = new JPanel();
        progressBar = new JProgressBar();

        testPanel = new JPanel();
        timerPanel = new JPanel();

        greetingLabel = new JLabel("Hello! Please press start, to begin quiz.");
        questionLabel = new JLabel();
        timerLabel = new JLabel("Timer");

        setPanels();
        setLabels();
        setProgressBar();

        // initialize timer and show in timerLabel
        timer = new MyTimer().getTimer(timerLabel);

        startButton = new JButton("Start Test");
        btnNext = new JButton("Next");
        btnResult = new JButton("Result");
        buttonGroup = new ButtonGroup();

        setButtons();
    }

    private void setButtons() {
        // start button
        startButton.setBounds(50, 200, 100, 30);
        startButton.setForeground(Color.decode(MAIN_COLOR));
        startButton.setBackground(Color.decode(DARKER_SECONDARY_COLOR));
        timerPanel.add(startButton);
        startButton.addActionListener(this);

        // creating buttons, which will hold answers, and others, necessary to traverse my programme
        for (int i = 0; i < 5; i++) {
            radioButtons[i] = new JRadioButton();
            radioButtons[i].setEnabled(false);
            radioButtons[i].setVisible(false);
            radioButtons[i].setFont(new Font("Arial", Font.PLAIN, 10));
            testPanel.add(radioButtons[i]);
            buttonGroup.add(radioButtons[i]);
        }

        // hide result button and add action listener to next and result buttons
        btnResult.setVisible(false);
        btnResult.setEnabled(false);
        btnResult.setFocusable(false);
        btnResult.setForeground(Color.decode(SECONDARY_COLOR));
        btnResult.setBackground(Color.decode(DARKER_MAIN_COLOR));
        btnResult.addActionListener(this);

        btnNext.setVisible(false);
        btnNext.setEnabled(false);
        btnNext.setFocusable(false);
        btnNext.setForeground(Color.decode(SECONDARY_COLOR));
        btnNext.setBackground(Color.decode(DARKER_MAIN_COLOR));
        btnNext.addActionListener(this);

        testPanel.add(btnNext);
        testPanel.add(btnResult);

        //sets data for radial buttons
        radioButtons[4].setVisible(false);
        setDataForButtons();

        //setting buttons bounds
        radioButtons[0].setBounds(100, 200, 250, 50);
        radioButtons[1].setBounds(100, 275, 250, 50);
        radioButtons[2].setBounds(400, 200, 250, 50);
        radioButtons[3].setBounds(400, 275, 250, 50);
        for (int i = 0; i < 4; i++) {
            radioButtons[i].setFocusable(false);
            radioButtons[i].setBackground(Color.decode(DARKER_SECONDARY_COLOR));
            radioButtons[i].setForeground(Color.decode(DARKER_MAIN_COLOR));
        }

        btnNext.setBounds(75, 375, 250, 50);
        btnResult.setBounds(400, 375, 250, 50);
    }

    private void setDataForButtons() {
        radioButtons[4].setSelected(true);

        for (int i = 0; i < questionList.size(); i++) {
            if(current == i) {
                questionLabel.setText(questionList.get(i).questionContents());
                for (int j = 0; j < 4; j++) {
                    radioButtons[j].setText(questionList.get(i).variants().get(j));
                }
            }
        }
    }

    private boolean checkAns() {
        for (int i = 0; i < questionList.size(); i++) {
            if(current == i) {
                for (int j = 0; j < 4; j++) {
                    if(radioButtons[j].isSelected()) {
                        return questionList.get(i).isTrueAnswer(radioButtons[j].getText());
                    }
                }
            }
        }
        return false;
    }

    private void setProgressBar() {
        // progress bar
        progressPanel.setLayout(null);
        progressPanel.setBounds(0, 500, 1000, 50);
        progressPanel.setOpaque(true);
        progressPanel.setBackground(Color.decode(DARKER_SECONDARY_COLOR));
        progressPanel.setVisible(true);
        add(progressPanel);

        progressBar.setValue(0);
        progressBar.setBounds(250, 15, 500, 20);
        progressBar.setBackground(Color.DARK_GRAY);
        progressBar.setForeground(Color.decode(MAIN_COLOR));
        progressBar.setVisible(false);
        progressPanel.add(progressBar);
    }

    private void setLabels() {
        // greeting label
        greetingLabel.setBounds(100, 150, 600, 150);
        greetingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        greetingLabel.setForeground(Color.decode(SECONDARY_COLOR));
        greetingLabel.setFont(new Font("Arial", Font.ITALIC, 30));
        greetingLabel.setVisible(true);

        // question label
        questionLabel.setBounds(100, 20, 600, 150);
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setVerticalAlignment(SwingConstants.CENTER);
        questionLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        questionLabel.setVerticalTextPosition(SwingConstants.CENTER);
        questionLabel.setBackground(Color.DARK_GRAY);
        questionLabel.setForeground(Color.decode(SECONDARY_COLOR));
        questionLabel.setOpaque(true);
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        questionLabel.setVisible(false);

        // timer label
        timerLabel.setBounds(50, 75, 100, 100);
        timerLabel.setOpaque(true);
        timerLabel.setForeground(Color.decode(MAIN_COLOR));
        timerLabel.setBackground(Color.decode(DARKER_SECONDARY_COLOR));
        timerLabel.setVerticalAlignment(SwingConstants.CENTER);
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void setPanels() {
        add(timerPanel);
        add(testPanel);

        // test panel
        testPanel.setBackground(Color.decode(MAIN_COLOR));
        testPanel.setBounds(0, 0, 800, 500);
        testPanel.add(questionLabel);
        testPanel.setLayout(null);

        //timer panel
        timerPanel.setBackground(Color.decode(DARKER_MAIN_COLOR));
        timerPanel.setBounds(800, 0, 200, 500);
        timerPanel.add(timerLabel);
        timerPanel.setLayout(null);

        //greeting panel
        testPanel.add(greetingLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            greetingLabel.setVisible(false);
            questionLabel.setVisible(true);
            progressBar.setVisible(true);
            btnNext.setVisible(true);
            btnNext.setEnabled(true);
            startButton.setVisible(false);
            btnResult.setEnabled(true);
            for (int i = 0; i < 4; i++) {
                radioButtons[i].setEnabled(true);
                radioButtons[i].setVisible(true);
            }
            timer.start();
        }

        if (e.getSource() == btnNext) {
            if (checkAns()) {
                countCorrect++;
            }
            current++;
            progressValue = progressValue + (int) Math.ceil(100.0/questionList.size());
            progressBar.setValue(progressValue);
            progressBar.setStringPainted(true);
            progressBar.setString(current + "/" + questionList.size());
            setDataForButtons();
            if (current == questionList.size()) {
                btnNext.setEnabled(false);
                btnResult.setVisible(true);
                for (int i = 0; i < 4; i++) {
                    radioButtons[i].setEnabled(false);
                }
            }
        }

        if (e.getActionCommand().equals("Result")) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "You have " + countCorrect + " correct answers");
            System.exit(0);
        }
    }
}
