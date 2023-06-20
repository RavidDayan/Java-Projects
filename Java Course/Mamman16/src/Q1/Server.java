package Q1;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    public ArrayList<Question> getQuestionBank() {
        return questionBank;
    }

    private ArrayList<Question> questionBank;//question pool for games
    private ServerSocket serverSocket;//socket of the server

    boolean listening;

    public boolean isListening() {
        return listening;
    }//listening status of server

    public Server() {
        listening = true;
        try {
            serverSocket = new ServerSocket(3333);
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
        questionBank = new ArrayList<>();
        getQuestionsFromFile();
        System.out.println("Server Online on port 3333 and listening");
    }//constructor

    public ServerSocket getServerSocket() {
        return serverSocket;
    }//returns servers socket

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        ServerSocket serverSocket = server.getServerSocket();
        Socket socket = null;
        Game game = null;
        while (server.isListening()) {
            System.out.println("waiting for new user");
            socket = serverSocket.accept();
            System.out.println("new user request");
            game = new Game(server.getQuestionBank(), socket);
            game.start();
        }//listens to connections from users on port 3333 and make a new thread game for each user connection
    }

    private void getQuestionsFromFile() {
        File questionFile = new File("src/Q1/question files.txt");
        Scanner input = null;
        try {
            input = new Scanner(questionFile);
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
        while (input.hasNext()) {
            String question = input.nextLine();
            String[] answers = new String[4];
            for (int i = 0; i < 4; i++) {
                answers[i] = input.nextLine();
            }
            Question newQuestion = new Question(question, answers);
            questionBank.add(newQuestion);
        }
    }//retrieves question from question file(assumes correct structure of question/right answer/wrong answer/wrong answer/wrong answer)
}
