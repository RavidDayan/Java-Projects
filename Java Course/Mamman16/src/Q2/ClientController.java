package Q2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class ClientController {
    private ClientListener listener;//listen to messages from server
    private boolean connected;//status if connected to server or not
    private MulticastSocket socket;//socket connected to
    private InetAddress group;//socket address IP
    private int port;//socket port
    @FXML
    public Label messageLabel;//message area from server
    @FXML
    public Button enterServerButton;//enter and leave the server to receive/stop receiving messages
    @FXML
    public TextField ipField;//IP Address inserted by user
    @FXML
    public Button clearButton;//clear message board

    @FXML
    public void initialize() {
        messageLabel.setStyle("-fx-background-color: white;");
        disableClearButton();
    }//sets message board bg white and disable the clear button

    public ClientController() {
        connected = false;
        port = 6666;
    }//constructor

    private void getGroupAddress() throws UnknownHostException {
        group = InetAddress.getByName(ipField.getText());
    }//retrieves IP adress inserted by user

    private void joinGroup() throws IOException {
        socket = new MulticastSocket(port);
        socket.joinGroup(group);
    }//joins the group socket

    private void leaveGroup() throws IOException {
        socket.leaveGroup(group);
        socket.close();
    }//leaves the group socket and closes the socket

    private void closeListener() {
        listener.interrupt();
    }//closes the group listener by interrupting it

    private void disableClearButton() {
        clearButton.setDisable(true);
    }

    private void enableClearButton() {
        clearButton.setDisable(false);
    }

    private void disableIpField() {
        ipField.setDisable(true);
    }

    private void enableIpField() {
        ipField.setDisable(false);
    }

    public void connectToServer() {
        connected = !connected;
        if (connected) {
            try {
                getGroupAddress();
                joinGroup();
                listener = new ClientListener(socket, messageLabel);//activates listener to seek messages from server
                listener.start();
                disableIpField();
                enableClearButton();
                waitForMessageText();
                enterServerButton.setText("leave Server");
                System.out.println("Connected successfully");
            } catch (UnknownHostException e) {
                System.out.println("Connection failed,try different IP address");
                connected = false;
            } catch (IOException e) {
                System.out.println("Connection failed,try again");
                connected = false;
            }
        } else {
            try {
                closeListener();
                leaveGroup();
                enterServerButton.setText("Enter Server");
                enableIpField();
                disableClearButton();
                System.out.println("disconnected successfully");
            } catch (IOException e) {
                System.out.println("disconnection failed");
            }
        }
    }//connects and disconnects to server depending on current statues

    @FXML
    public void clearButtonAction() {
        clearMessageLabel();
    }

    public void clearMessageLabel() {
        messageLabel.setText("");
    }

    public void waitForMessageText() {
        messageLabel.setText("waiting for message...");
    }
}

