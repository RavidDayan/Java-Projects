package Q2;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ReminderController {
    private HashMap<LocalDate, String> notes;
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

    private void badFileNamePopUp() {
        Alert popUp = new Alert(Alert.AlertType.INFORMATION);
        popUp.setTitle("Bad name");
        popUp.setHeaderText(null);
        popUp.setContentText("please try again with different file name.");
        popUp.showAndWait();
    }

    private void badFileFormatPopUp() {
        Alert popUp = new Alert(Alert.AlertType.INFORMATION);
        popUp.setTitle("Bad Format");
        popUp.setHeaderText(null);
        popUp.setContentText("the File is not according to the format Date: yyyy-mm-dd note: NOTE.");
        popUp.showAndWait();
    }

    private void NewCalenderPopUp() {
        Alert popUp = new Alert(Alert.AlertType.INFORMATION);
        popUp.setTitle("new Calender");
        popUp.setHeaderText(null);
        popUp.setContentText("The calender has been renewed.");
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


    public void saveNotesButtonAction() {
        String newFileName = getNewFileName();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/Q2/" + newFileName + ".txt"));
            LocalDate date;
            String note;
            for (LocalDate key : notes.keySet()) {
                date = key;
                note = notes.get(date);
                writer.write("Date: " + date + " Note: " + note);
                writer.flush();
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            badFileNamePopUp();
        }
    }

    private String getNewFileName() {
        String name;
        TextInputDialog input = new TextInputDialog();
        input.setTitle("New file name");
        input.setHeaderText(null);
        input.setContentText("Please enter the files name:");
        Optional<String> optionalInput = input.showAndWait();
        name = optionalInput.get();
        return name;
    }

    public void loadNotesButtonAction() {
        String loadedFile = getNewFileName();
        String filePath = "src/Q2/" + loadedFile + ".txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            notes = loadedNotes(reader);
        } catch (FileNotFoundException e) {
            badFileNamePopUp();
        } catch (IOException e) {
            badFileFormatPopUp();
        } catch (DateTimeParseException e) {
            badFileFormatPopUp();
        }
    }


    public void insertNewNote(String line, HashMap<LocalDate, String> newNotes) throws DateTimeParseException, IOException {
        LocalDate date = getDateFromLine(line);
        String note = getNoteFromLine(line);
        newNotes.put(date, note);
    }

    private LocalDate getDateFromLine(String line) throws DateTimeParseException {
        String date = line.substring(6, 16);
        return LocalDate.parse(date);
    }

    private String getNoteFromLine(String line) throws IOException {
        if(line.charAt(22)!=' '){
            throw new IOException();
        }
        String note = line.substring(23);
        return note;
    }

    private HashMap<LocalDate, String> loadedNotes(BufferedReader reader) throws IOException, DateTimeParseException {
        String line;
        HashMap<LocalDate, String> newNotes = new HashMap<>();
        while ((line = reader.readLine()) != null) {
            insertNewNote(line, newNotes);
        }
        return newNotes;
    }

    public void newCalenderButtonAction() {
        notes = new HashMap<LocalDate, String>();
        NewCalenderPopUp();
        clearNoteTextField();
    }
}


