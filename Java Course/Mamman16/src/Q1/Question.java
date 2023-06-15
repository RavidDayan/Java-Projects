package Q1;

public class Question {
    private String question;
    private String[] answers;
    private int correctAnswerIndex;
    public Question(String question,String )
    private void scrambler(){
        String temp;
        int switchingIndex=(int)(Math.random()*4);
        temp=answers[switchingIndex];
        answers[switchingIndex]=answers[0];
        answers[0]=temp;
        correctAnswerIndex=switchingIndex;
    }
}
