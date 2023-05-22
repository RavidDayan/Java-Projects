package Q2;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private void showButtonAction() {
        if (NoteExist()) {
            String note = notes.get(getDate());
            noteTextField.setText(note);
        } else {
            noNoteExistsPopUp();
            clearNoteTextField();
        }
    }

    private boolean NoteExist() {
        if (isDateValid()) {
            return notes.containsKey(getDate());
        }

        return false;
    }

    @FXML
    private void saveButtonAction() {
        if (isDateValid()) {
            LocalDate date = getDate();
            String note = noteTextField.getText();
            notes.put(date, note);
            noteSavedPopUp();
            clearNoteTextField();
        }
    }

    private void invalidDatePopUp() {
        Alert popUp = new Alert(Alert.AlertType.INFORMATION);
        popUp.setTitle("Illegal date ");
        popUp.setHeaderText(null);
        popUp.setContentText("Date does not exist,please enter different date.");
        popUp.showAndWait();
    }

    private void noteSavedPopUp() {
        Alert popUp = new Alert(Alert.AlertType.INFORMATION);
        popUp.setTitle("Note saved ");
        popUp.setHeaderText(null);
        popUp.setContentText("the note has been successfully saved.");
        popUp.showAndWait();
    }

    private void noNoteExistsPopUp() {
        Alert popUp = new Alert(Alert.AlertType.INFORMATION);
        popUp.setTitle("No note for date");
        popUp.setHeaderText(null);
        popUp.setContentText("There is no Note for specified date.");
        popUp.showAndWait();
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

    private boolean isDateValid() {
        try {
            int year = yearComboBox.getValue();
            int month = monthComboBox.getValue();
            int day = dayComboBox.getValue();
            LocalDate.of(year, month, day);
            return true;
        } catch (Exception e) {
            invalidDatePopUp();
            return false;
        }
    }

    private void clearNoteTextField() {
        noteTextField.setText("");
    }

    private LocalDate getDate() {
        int year = yearComboBox.getValue();
        int month = monthComboBox.getValue();
        int day = dayComboBox.getValue();
        return LocalDate.of(year, month, day);
    }


}

