package Q1;

import java.util.ArrayList;

public class Game {
    private ArrayList<Question> questions;

    public Question getQuestion() {
        int randomQuestionIndex=(int)(Math.random()*questions.size());
        Question randomQuestion=questions.get(randomQuestionIndex);
        questions.remove(randomQuestionIndex);
        return randomQuestion;
    }
    public void addQuestion(Question question){
        questions.add(question);
    }
}
