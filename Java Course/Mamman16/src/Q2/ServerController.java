package Q2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.*;

public class ServerController {
    @FXML
    public Button startServerButton;
    @FXML
    public TextField messageTextField;
    @FXML
    public Button sendButton;
    @FXML
    public TextField ipField;
    public Label UsersListeningLabel;
    private InetAddress groupIp;
    int port;
    private DatagramSocket socket;
    private boolean serverRunning;

    public ServerController() {
        serverRunning = false;
        groupIp = null;
        port = 6666;
    }

    @FXML
    public void initialize() {
        startServerButton = new Button();
        sendButton = new Button();
        disableSendButton();
        disableMessageTextField();
    }

    private void disableSendButton() {
        sendButton.setDisable(true);
    }

    private void disableMessageTextField() {
        messageTextField.setDisable(true);
        messageTextField.setText("");
    }

    private void enableMessageTextField() {
        messageTextField.setDisable(false);
    }

    private void ZeroUserListeningLabel() {
        UsersListeningLabel.setText("0");
    }

    private void emptyUserListeningLabel() {
        UsersListeningLabel.setText("");
    }

    private void getGroupIp() throws UnknownHostException {
        groupIp = InetAddress.getByName(ipField.getText());
    }

    private void disableIpField() {
        ipField.setDisable(true);
    }

    private void enableIpField() {
        ipField.setDisable(false);
    }

    private void startSocket() throws SocketException {
        socket = new DatagramSocket();
    }

    private void closeSocket() {
        socket.close();
    }

    private void enableMessageSend() {
        sendButton.setDisable(false);
    }

    private void disableMessageSend() {
        sendButton.setDisable(true);
    }

    @FXML
    public void startServerAction() {
        serverRunning = !serverRunning;
        if (serverRunning) {
            try {
                startSocket();
                getGroupIp();
                disableIpField();
                ZeroUserListeningLabel();
                enableMessageSend();
                enableMessageTextField();
                System.out.println("Server online");
            } catch (SocketException e) {
                System.out.println("Server could not load,try again");
                serverRunning = false;
            } catch (UnknownHostException e) {
                System.out.println("Server could not load,check IP address");
                closeSocket();
                serverRunning = false;
            }
        } else {
            disableMessageSend();
            emptyUserListeningLabel();
            enableIpField();
            disableSendButton();
            disableMessageTextField();
            closeSocket();
            System.out.println("Server offline");
        }
    }

    @FXML
    public void sendMessage() {
        DatagramPacket packet;
        byte[] buf;
        String message = messageTextField.getText();
        buf = message.getBytes();
        packet = new DatagramPacket(buf, buf.length, groupIp, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            System.out.println("Message could not be sent");
        }
    }
}
