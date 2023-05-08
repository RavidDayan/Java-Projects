package Q1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Suduko extends Application{
        public void start(Stage stage) throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("SudukoUI.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Suduko");
            stage.setScene(scene);
            stage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    }