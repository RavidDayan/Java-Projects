package Q1;

public class Question {
    private String question;
    private String[] answers;
    private int correctAnswerIndex;
    public Question(String question,String[] answers){
        this.question=question;
        this.answers=new String[answers.length];
        for(int i=0;i<answers.length;i++){
            this.answers[i]=answers[i];
        }
        scrambleQuestions();
    }
    public int getCorrectAnswerIndex(){
        return correctAnswerIndex;
    }
    public String getQuestion(){
        return question;
    }
    public String getAnswer(int index){
        return answers[index];
    }
    private void scrambleQuestions(){
        String temp;
        int switchingIndex=(int)(Math.random()*4);
        temp=answers[switchingIndex];
        answers[switchingIndex]=answers[0];
        answers[0]=temp;
        correctAnswerIndex=switchingIndex;
    }
}
