package Q1;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Game extends Thread {
    private ArrayList<Question> questions;//all game questions
    Socket socket;//client connected socket
    InputStream input;//input from user
    ObjectOutputStream output;//output to user

    public Game(ArrayList<Question> allQuestions, Socket socket) {
        this.socket = socket;
        questions = new ArrayList<>();
        for (Question question : allQuestions) {
            addQuestion(question);
        }//hard copy all game  from question pool to this specific game
    }//constructor

    public Question getQuestion() {
        int randomQuestionIndex = (int) (Math.random() * questions.size());
        Question randomQuestion = questions.get(randomQuestionIndex);
        questions.remove(randomQuestionIndex);
        return randomQuestion;
    }//returns random question from game question pool and removes after usage to avoid multiple sendings of same question

    private void addQuestion(Question question) {
        questions.add(question);
    }//adds question to game question pool

    @Override
    public void run() {
        BufferedReader reader = null;
        while (questions.size() > 0) {
            try {
                input = socket.getInputStream();
                reader = new BufferedReader(new InputStreamReader(input));
            } catch (IOException e) {
                System.out.println("cant read user input");
                break;
            }
            try {
                if (reader.readLine().equals("send")) {
                    output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(getQuestion());
                }
            } catch (IOException e) {
                System.out.println("cant read user input");
                break;
            }
        }
        try {
            disConnectUser();
            System.out.println("disconnect successfully");
        } catch (IOException e) {
            System.out.println("did not disconnect successfully");
        }
    }//runs the instance of game with user , as long as there are questions to be sent , sends the user a new random question after user requests(each game will have only 20 questions)

    private void disConnectUser() throws IOException {
        socket.close();
        input.close();
        output.close();
    }//disconnect from user socket
}
