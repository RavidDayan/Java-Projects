package Q2;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReminderController {
    private HashMap<LocalDate, String> notes;
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

    private static List<Integer> generateYears() {
        List<Integer> years = new ArrayList<>();
        for (int year = 1970; year <= 2023; year++) {
            years.add(year);
        }
        return years;
    }

    private static List<Integer> generateMonths() {
        List<Integer> months = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            months.add(month);
        }
        return months;
    }

    private static List<Integer> generateDays() {
        List<Integer> days = new ArrayList<>();
        for (int day = 1; day <= 31; day++) {
            days.add(day);
        }
        return days;
    }

    public ReminderController() {
        notes = new HashMap<>();
    }
    @FXML
    private void saveButtonAction(){
        int year=yearComboBox.getValue();
        int month=monthComboBox.getValue();
        int day=dayComboBox.getValue();
        if(isDateValid(year,month,day)){
            LocalDate date=LocalDate.of(year,month,day);
            String note=noteTextField.getText();
            notes.put(date,note);
        }
        else{
            System.out.println("no data to save");
        }
    }
    @FXML
    public void initialize() {
        dayComboBox.setValue(1);
        dayComboBox.getItems().addAll(generateDays());
        monthComboBox.setValue(1);
        monthComboBox.getItems().addAll(generateMonths());
        yearComboBox.setValue(2023);
        yearComboBox.getItems().addAll(generateYears());
    }
    private boolean isDateValid(int year,int month,int day){
        try{
            LocalDate.of(year,month,day);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

