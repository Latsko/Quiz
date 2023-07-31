package org.testQuiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quiz extends JFrame implements ActionListener{
    private final JLabel questionLabel;
    private final JLabel greetingLabel;
    private final JLabel timerLabel;
    private final JPanel testPanel;
    private final JPanel timerPanel;
    private final Timer timer;
    private final JButton startButton, btnNext, btnResult;
    private final JRadioButton[] radioButtons = new JRadioButton[5];
    private final ButtonGroup buttonGroup;
    private int countCorrect, current;

    Quiz(String s) {
        super(s);
        // here I set all necessary parameters to my frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(1016, 500);
        getContentPane().setBackground(Color.DARK_GRAY);

        testPanel = new JPanel();
        timerPanel = new JPanel();

        questionLabel = new JLabel();
        timerLabel = new JLabel("Timer");
        greetingLabel  = new JLabel ("Hello! Please press start, to begin quiz.");

        setPanels();
        setLabels();

        // initialize timer and show in timerLabel
        timer = new MyTimer().getTimer(timerLabel);

        startButton = new JButton("Start Test");
        btnNext = new JButton("Next");
        btnResult = new JButton("Result");
        buttonGroup = new ButtonGroup();

        setButtons();
    }

    private void setDataForButtons() {
        radioButtons[4].setSelected(true);
        radioButtons[4].setVisible(false);
        if (current == 0) {
            questionLabel.setText("<HTML>Q1: Which is official language for " +
                    "Android developers Which is official language for Android developers" +
                    "Which is official language for Android developersWhich is official language for Android developers" +
                    "Which is official language for Android developers3</HTML>");
            radioButtons[0].setText("<HTML>Java how many symbols does it take to be too much for button" +
                    " not this many obviously</HTML>");
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
        setDataForButtons();

        //setting buttons bounds
        radioButtons[0].setBounds(100, 200, 200, 50);
        radioButtons[1].setBounds(100, 275, 200, 50);
        radioButtons[2].setBounds(350, 200, 200, 50);
        radioButtons[3].setBounds(350, 275, 200, 50);

        btnNext.setBounds(75, 375, 250, 50);
        btnResult.setBounds(400, 375, 250, 50);
    }

    private void setLabels() {
        // greeting label
        greetingLabel.setBounds(100, 150, 600, 150);
        greetingLabel.setHorizontalAlignment(JLabel.CENTER);
        greetingLabel.setForeground(Color.BLUE);
        greetingLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        greetingLabel.setVisible(true);

        // question label
        questionLabel.setBounds(100, 20, 600, 150);
        questionLabel.setHorizontalTextPosition(JLabel.CENTER);
        questionLabel.setBackground(Color.BLACK);
        questionLabel.setForeground(Color.lightGray);
        questionLabel.setOpaque(true);
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        questionLabel.setVisible(false);

        // timer label
        timerLabel.setBounds(50, 75, 100, 100);
        timerLabel.setOpaque(true);
        timerLabel.setBackground(Color.BLACK);
        timerLabel.setVerticalAlignment(JLabel.CENTER);
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
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

    private boolean checkAns() {
        // here we check which one of the buttons is the right answer simply asking what button was
        // selected, which means, we are setting right answers here as well, simply assigning
        //  the radioButtons array an int number containing the wright answer
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
            greetingLabel.setVisible(false);
            startButton.setVisible(false);
            questionLabel.setVisible(true);
            btnNext.setVisible(true);
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
            setDataForButtons();
            if (current == 5) {
                btnNext.setEnabled(false);
                btnResult.setVisible(true);
            }
        }

        if (e.getActionCommand().equals("Result")) {
            if (checkAns()) {
                countCorrect++;
            }
            current++;
            timer.stop();
            JOptionPane.showMessageDialog(this,  "You have " + countCorrect + " correct answers");
            System.exit(0);
        }
    }
}
