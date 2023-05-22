package Q2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    private static List<Integer> generateYears(){
        List<Integer> years = new ArrayList<>();
        for (int year = 1970; year <=2023 ; year++) {
            years.add(year);
        }
        return years;
    }
    private static List<Integer> generateMonths(){
        List<Integer> months = new ArrayList<>();
        for (int month = 1; month <=12 ; month++) {
            months.add(month);
        }
        return months;
    }
    private static List<Integer> generateDays(){
        List<Integer> days = new ArrayList<>();
        for (int day = 1; day <=31 ; day++) {
            days.add(day);
        }
        return days;
    }
}

