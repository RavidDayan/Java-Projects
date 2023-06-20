package Q1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class User extends Application {
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
        String[] args = getParameters().getRaw().toArray(new String[0]);
        loader.setControllerFactory(c -> new UserController(args[0], args[1]));//sets the loaders controller to be an instance I initiate with time and ipAdress from command line
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Question Game");
        stage.setScene(scene);
        stage.setResizable(false);//makes scene not resizable to keep user experience
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

