package Q2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    private TrafficLightsController  controller;
    public void start(Stage stage) throws Exception {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("TrafficLights.fxml"));
        String[] args=getParameters().getRaw().toArray(new String[0]);
        loader.setControllerFactory(c -> new TrafficLightsController(args[0],args[1]));//sets the loaders controller to be an instance I initiate with times from command line
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Traffic Lights");
        stage.setScene(scene);
        stage.setResizable(false);//makes scene not resizable to keep user experience
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
