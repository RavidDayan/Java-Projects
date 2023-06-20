package Q1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class UserController {
    private int time;
    private String hostIp;//host ip to connect
    private Timer timer;//timer to countdown for questions
    private String defaultStyle;//default style of buttons to reset them
    private Socket socket;//socet to connect to server
    private ObjectInputStream inputStream;//input from server(questions)
    private PrintWriter sendRequest;//output to server(send next question request)
    private Question question;//current displayed question
    private int questionCounter;//counts how many questions have been shown
    private int score;//current user score
    private Button[] answerButtons;//array for easy access to all answer buttons
    @FXML
    private Button startButton;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label timeMeter;
    @FXML
    private Label questionLabel;
    @FXML
    private Button a1Button;
    @FXML
    private Button a2Button;
    @FXML
    private Button a3Button;
    @FXML
    private Button a4Button;
    @FXML
    private Label questionNumberLabel;

    public UserController() {
        time = 30;
        hostIp = "localhost";
    }//constructor 1

    public UserController(String host, String time) {
        this.time = Integer.valueOf(time);
        this.hostIp = host;
    }//constructor 2

    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        startButton.setText("Start");
        try {
            socket = new Socket(hostIp, 3333);
        } catch (IOException e) {
            System.out.println("Unable to connect to Server");
        }
        System.out.println("Connected successfully");
        answerButtons = new Button[4];
        answerButtons[0] = a1Button;
        answerButtons[1] = a2Button;
        answerButtons[2] = a3Button;
        answerButtons[3] = a4Button;
        defaultStyle = a1Button.getStyle();
        disableAnswerButtons();
    }//establish connection to server and load game appearance

    private void getNewQuestion() throws IOException, ClassNotFoundException {
        sendRequest = new PrintWriter(socket.getOutputStream());
        sendRequest.println("send");
        sendRequest.flush();
        inputStream = new ObjectInputStream(socket.getInputStream());
        question = (Question) inputStream.readObject();
    }//request new question from server and save it

    private void ShowQuestion() {
        questionLabel.setText(question.getQuestion());
        for (int i = 0; i < 4; i++) {
            answerButtons[i].setText(question.getAnswer(i));
        }
    }//show the question text and answer options

    private void resetGameAppearance() {
        questionLabel.setStyle(defaultStyle);
        a1Button.setStyle(defaultStyle);
        a2Button.setStyle(defaultStyle);
        a3Button.setStyle(defaultStyle);
        a4Button.setStyle(defaultStyle);
    }//resets game apearance

    private void feedbackOnUserAnswer(int answerIndex) {
        int timeLeft = Integer.valueOf(timeMeter.getText());
        if (question.getCorrectAnswerIndex() == answerIndex && timeLeft > 0) {
            questionLabel.setText("Good answer!");
            questionLabel.setStyle("-fx-background-color: green;");
            score += 10;
            scoreLabel.setText("Score:" + score);

        } else {
            questionLabel.setText("Try again");
            questionLabel.setStyle("-fx-background-color: red;");
            score -= 5;
            scoreLabel.setText("Score:" + score);
        }
    }//tell the user if he picked the correct or incorrect by showing a message with green/red respectively

    private void changeButtonColorByAnswer() {
        for (int i = 0; i < 4; i++) {
            if (question.getCorrectAnswerIndex() == i + 1) {
                answerButtons[i].setStyle("-fx-background-color: green;");
            } else {
                answerButtons[i].setStyle("-fx-background-color: red;");
            }
        }
    }//change all the answer buttons to red if the answer is incorrect or green if its correct

    @FXML
    public void startButtonAction() throws IOException, ClassNotFoundException {
        if (questionCounter == 20) {
            disConnectServer();
            disableStartButton();
            startButton.setText("done");
            resetGameAppearance();
            endGameShowScore();
        } else {
            startButton.setText("Next");
            questionCounter++;
            questionNumberLabel.setText("Question:" + String.valueOf(questionCounter));
            disableStartButton();
            System.out.println("disabled");
            resetGameAppearance();
            getNewQuestion();
            ShowQuestion();
            startTimer();
            enableAnswerButtons();
        }
    }// as long as we haven't reached 20 questions requests a question from server, shows it on fx ,disables the start button and enables the answer buttons, if 20 is reached closes communication with server

    @FXML
    public void a1ButtonAction() {
        afterAnswerProcedures(1);
    }//states that answer 1 is picked

    @FXML
    public void a2ButtonAction() {
        afterAnswerProcedures(2);
    }//states that answer 2 is picked

    @FXML
    public void a3ButtonAction() {
        afterAnswerProcedures(3);
    }//states that answer 3 is picked

    @FXML
    public void a4ButtonAction() {
        afterAnswerProcedures(4);

    }//states that answer 4 is picked

    private void afterAnswerProcedures(int i) {
        timer.interupt();
        feedbackOnUserAnswer(i);
        changeButtonColorByAnswer();
        disableAnswerButtons();
        enableStartButton();
    }//procedures after answer has been picked, stop the clock,show if user was correct and change points accordingly, disable the ability to press a answer button and enable the start button

    private void disableAnswerButtons() {
        for (Button button : answerButtons) {
            button.setDisable(true);
        }
    }//disables all answer buttons

    private void enableAnswerButtons() {
        for (Button button : answerButtons) {
            button.setDisable(false);
        }
    }//enables all answer buttons

    private void endGameShowScore() {
        questionNumberLabel.setText("Game Over");
        questionLabel.setText("your score is:" + score);
    }//shoes the final score of game

    private void enableStartButton() {
        startButton.setDisable(false);
    }//enable the start button

    private void disableStartButton() {
        startButton.setDisable(true);
    }//disable the start button

    private void startTimer() {
        timer = new Timer(timeMeter, time);
        timer.start();
    }//start a clockdown of the question

    private void disConnectServer() throws IOException {
        socket.close();
        inputStream.close();
        sendRequest.close();
        System.out.println("disconnect successfully");
    }//disconnects from server socketand closes.
}
