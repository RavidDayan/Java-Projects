package Q1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.awt.*;

public class UserController {
    private Question question;
    private int questionCounter;
    private int score;
    private Button[] answerButtons;
    @FXML
    private Label scoreLabel;
    @FXML
    private ProgressBar timeMeter;
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

    private void resetGameApearance() {
        questionLabel.setStyle("-fx-background-color: white;");
        a1Button.setStyle("-fx-background-color: white;");
        a2Button.setStyle("-fx-background-color: white;");
        a3Button.setStyle("-fx-background-color: white;");
        a4Button.setStyle("-fx-background-color: white;");
        timeMeter.setProgress(0);
    }

    private void feedbackOnUserAnswer(int i) {
        if (question.getCorrectAnswerIndex() == i) {
            questionLabel.setText("Good answer!");
            questionLabel.setStyle("-fx-background-color: green;");
        } else {
            questionLabel.setText("Wrong");
            questionLabel.setStyle("-fx-background-color: red;");
        }
    }

    private void changeButtonColorByAnswer() {
        for (int i = 0; i < 4; i++) {
            if (question.getCorrectAnswerIndex() == i) {
                answerButtons[i].setStyle("-fx-background-color: green;");
            } else {
                answerButtons[i].setStyle("-fx-background-color: red;");
            }
        }
    }
}
