package Q2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Reminder.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Note Calendar");
        stage.setScene(scene);
        stage.setResizable(false);//makes scene not resizable to keep user experience
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
