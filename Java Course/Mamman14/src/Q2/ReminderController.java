package Q2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.HashMap;

public class ReminderController {
    private HashMap<LocalDate,String> notes;
    @FXML
    private Button showButton;
    @FXML
    private Button saveButton;
    @FXML
    private ComboBox<Integer> dayComboBox;
    @FXML
    private ComboBox<Integer> monthComboBox;
    @FXML
    private ComboBox<Integer> yearComboBox;
    @FXML
    private TextField noteTextField;


    }
}

