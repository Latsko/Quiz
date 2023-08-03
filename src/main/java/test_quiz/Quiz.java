package test_quiz;

import test_quiz.entities.MyTimer;
import test_quiz.entities.Question;
import test_quiz.entities.services.QuestionService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Quiz extends JFrame implements ActionListener {
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

    Quiz(String s) {
        super(s);
        this.questionList = new QuestionService().setData();
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
        startButton.setBackground(Color.green);
        timerPanel.add(startButton);
        startButton.addActionListener(this);

        // creating buttons, which will hold answers, and others, necessary to traverse my programme
        for (int i = 0; i < 5; i++) {
            radioButtons[i] = new JRadioButton();
            radioButtons[i].setEnabled(false);
            radioButtons[i].setVisible(false);
            testPanel.add(radioButtons[i]);
            buttonGroup.add(radioButtons[i]);
        }

        // hide result button and add action listener to next and result buttons
        btnResult.setVisible(false);
        btnResult.setEnabled(false);
        btnResult.addActionListener(this);

        btnNext.setVisible(false);
        btnNext.setEnabled(false);
        btnNext.addActionListener(this);

        testPanel.add(btnNext);
        testPanel.add(btnResult);

        //sets data for radial buttons
        radioButtons[4].setVisible(false);
        setDataForButtons();

        //setting buttons bounds
        radioButtons[0].setBounds(100, 200, 200, 50);
        radioButtons[1].setBounds(100, 275, 200, 50);
        radioButtons[2].setBounds(350, 200, 200, 50);
        radioButtons[3].setBounds(350, 275, 200, 50);

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
        progressPanel.setBackground(Color.green);
        add(progressPanel);
        progressPanel.setVisible(true);

        progressBar.setValue(0);
        progressBar.setBounds(250, 15, 500, 20);
        progressPanel.add(progressBar);
        progressBar.setVisible(false);
    }

    private void setLabels() {
        // greeting label
        greetingLabel.setBounds(100, 150, 600, 150);
        greetingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        greetingLabel.setForeground(Color.BLUE);
        greetingLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        greetingLabel.setVisible(true);

        // question label
        questionLabel.setBounds(100, 20, 600, 150);
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        questionLabel.setBackground(Color.BLACK);
        questionLabel.setForeground(Color.lightGray);
        questionLabel.setOpaque(true);
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        questionLabel.setVisible(false);

        // timer label
        timerLabel.setBounds(50, 75, 100, 100);
        timerLabel.setOpaque(true);
        timerLabel.setBackground(Color.BLACK);
        timerLabel.setVerticalAlignment(SwingConstants.CENTER);
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void setPanels() {
        add(timerPanel);
        add(testPanel);

        // test panel
        testPanel.setBackground(Color.GRAY);
        testPanel.setBounds(0, 0, 800, 500);
        testPanel.add(questionLabel);
        testPanel.setLayout(null);

        //timer panel
        timerPanel.setBackground(Color.red);
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
            startButton.setVisible(false);
            questionLabel.setVisible(true);
            btnNext.setVisible(true);
            progressBar.setVisible(true);
            btnNext.setEnabled(true);
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
            progressValue = progressValue + (int) Math.ceil(100.0/6);
            progressBar.setValue(progressValue);
            progressBar.setStringPainted(true);
            progressBar.setString(current + "/" + 6);
            setDataForButtons();
            if (current == 6) {
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
