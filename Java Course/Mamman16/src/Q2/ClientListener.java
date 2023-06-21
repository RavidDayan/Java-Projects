package Q2;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.time.LocalDate;
import java.time.LocalTime;

public class ClientListener extends Thread {
    private Label messageLabel;//message label to be changed in client ui

    private MulticastSocket socket;//socket to listen to
    private boolean interrupted;//statues if Listener is interrupted from the outside

    public void interrupt() {
        interrupted = true;
    }//changes interrupted statue to true


    public ClientListener(MulticastSocket socket, Label messageLabel) {
        this.socket = socket;
        this.messageLabel = messageLabel;
        interrupted = false;
    }//constructor

    private String getMessage() throws IOException {
        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        buf = packet.getData();
        int len = packet.getLength();
        return new String((buf)).substring(0, len);
    }//gets message from group and returns it as a string

    public void run() {
        String message = null;

        while (!interrupted) {
            try {
                message = getMessage();
                System.out.println("Message received:" + messageLabel.getText());
            } catch (IOException e) {
                System.out.println("Message NOT received:" + Thread.currentThread().getName());
            }
            String finalMessage = message;
            Platform.runLater(() -> {

                messageLabel.setText(messageLabel.getText() + System.lineSeparator() + "Message received at " + LocalDate.now() + " " + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + System.lineSeparator() + "Message:" + finalMessage);

            });//updates label as a javafx from outside the main javafx thread
        }
        Platform.runLater(() -> {
            messageLabel.setText("");
        });//clears message text area
    }//as long as the program is running it listens to messages from group and adds the to message board
}

