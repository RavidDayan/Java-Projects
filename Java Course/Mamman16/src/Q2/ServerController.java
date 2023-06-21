package Q2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.*;

public class ServerController {
    @FXML
    public Button startServerButton;//starts and stops the server button
    @FXML
    public TextField messageTextField;//field to insert message to send
    @FXML
    public Button sendButton;//sends message
    @FXML
    public TextField ipField;//ip to send message to
    private InetAddress groupIp;
    int port;//port server is broadcasting on
    private DatagramSocket socket;//socket of server
    private boolean serverRunning;//server running status

    public ServerController() {
        serverRunning = false;
        groupIp = null;
        port = 6666;
    }//constructor

    @FXML
    public void initialize() {
        disableMessageSend();
        disableMessageTextField();
    }//disable sending message button and text field(until server will go up)

    private void disableMessageTextField() {
        messageTextField.setDisable(true);
        messageTextField.setText("");
    }

    private void enableMessageTextField() {
        messageTextField.setDisable(false);
    }

    private void getGroupIp() throws UnknownHostException {
        groupIp = InetAddress.getByName(ipField.getText());
    }//retrieve group id address

    private void disableIpField() {
        ipField.setDisable(true);
    }

    private void enableIpField() {
        ipField.setDisable(false);
    }

    private void startSocket() throws SocketException {
        socket = new DatagramSocket();
    }//starts a new socket for server

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
        changeStartButtonText();
        if (serverRunning) {
            try {
                startSocket();
                getGroupIp();
                disableIpField();
                enableMessageSend();
                enableMessageTextField();
                System.out.println("Server online");
            } catch (SocketException e) {
                System.out.println("Server could not load,try again");
                serverRunning = false;
                changeStartButtonText();
            } catch (UnknownHostException e) {
                System.out.println("Server could not load,check IP address");
                closeSocket();
                serverRunning = false;
                changeStartButtonText();
            }
        } else {
            disableMessageSend();
            enableIpField();
            disableMessageSend();
            disableMessageTextField();
            closeSocket();
            System.out.println("Server offline");
        }
    }//button toggles between starting the server and closing it

    public void changeStartButtonText() {
        if (serverRunning) {
            startServerButton.setText("Close Server");
        } else {
            startServerButton.setText("Start Server");
        }
    }

    @FXML
    public void sendMessage() {
        DatagramPacket packet;
        byte[] buf = new byte[256];
        String message = messageTextField.getText();
        buf = message.getBytes();
        packet = new DatagramPacket(buf, buf.length, groupIp, port);
        try {
            socket.send(packet);
            System.out.println("Message sent successfully");
        } catch (IOException e) {
            System.out.println("Message could not be sent");
        }
    }//sends message to group as a packet
}
