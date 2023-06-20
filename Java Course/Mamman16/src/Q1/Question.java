package Q1;

import java.io.Serializable;

public class Question implements Serializable {//question is serializable to be able to be sent over network communication
    private String question;//questions text
    private String[] answers;// 4 answers to question
    private int correctAnswerIndex;//index of the correct answer in answers

    public Question(String question, String[] answers) {
        this.question = question;
        this.answers = new String[answers.length];
        for (int i = 0; i < answers.length; i++) {
            this.answers[i] = answers[i];
        }
        scrambleQuestions();
    }//constructor

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex + 1;
    }//returns the correct answer of question and adjusts for button

    public String getQuestion() {
        return question;
    }//returns question text

    public String getAnswer(int index) {
        return answers[index];
    }//returns answer in index i

    private void scrambleQuestions() {
        String temp;
        int switchingIndex = (int) (Math.random() * 4);
        temp = answers[switchingIndex];
        answers[switchingIndex] = answers[0];
        answers[0] = temp;
        correctAnswerIndex = switchingIndex;
    }//switches the first answer which is always the right one with one of the wrong answers to create a randomness in correct answer
}
