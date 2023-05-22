package Q2;


import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ReminderController {
    //haash map to store notes with the respective date
    //no need to implement hashcode or equals because LocaldDate has already implemeneted them
    private HashMap<LocalDate, String> notes;
    @FXML
    private ComboBox<Integer> dayComboBox;//comboBox for days
    @FXML
    private ComboBox<Integer> monthComboBox;//comboBox for months
    @FXML
    private ComboBox<Integer> yearComboBox;//comboBox for years
    @FXML
    private TextField noteTextField;//text field to see and edit notes

    private static List<Integer> generateYears() {
        List<Integer> years = new ArrayList<>();
        for (int year = 1970; year <= 2023; year++) {
            years.add(year);
        }
        return years;
    }//generates the combobox years from 1970 to 2023

    private static List<Integer> generateMonths() {
        List<Integer> months = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            months.add(month);
        }
        return months;
    }//generates the combobox months from 1 to 12

    private static List<Integer> generateDays() {
        List<Integer> days = new ArrayList<>();
        for (int day = 1; day <= 31; day++) {
            days.add(day);
        }
        return days;
    }//generates the combobox days from 1 to 31

    public ReminderController() {
        notes = new HashMap<>();
    }//constructor

    //shows the note of the current date in combobox if there is no note alerts the user
    @FXML
    private void showButtonAction() {
        if (NoteExist()) {//checks if note exists for date
            String note = notes.get(getDate());
            noteTextField.setText(note);
        } else {
            noNoteExistsPopUp();
            clearNoteTextField();
        }
    }//

    //if date in combobox is valid checks if a note for that date exists
    private boolean NoteExist() {
        if (isDateValid()) {
            return notes.containsKey(getDate());
        }

        return false;
    }

    //saves text by to the date in combobox if date is valid
    @FXML
    private void saveButtonAction() {//
        if (isDateValid()) {
            LocalDate date = getDate();
            String note = noteTextField.getText();
            notes.put(date, note);
            noteSavedPopUp();
            clearNoteTextField();
        }
    }

    //alerts the user that an invalid date has been chosen
    private void invalidDatePopUp() {
        Alert popUp = new Alert(Alert.AlertType.INFORMATION);
        popUp.setTitle("Illegal date ");
        popUp.setHeaderText(null);
        popUp.setContentText("Date does not exist,please enter different date.");
        popUp.showAndWait();
    }

    //alerts the user that the note has been saved successfully
    private void noteSavedPopUp() {
        Alert popUp = new Alert(Alert.AlertType.INFORMATION);
        popUp.setTitle("Note saved ");
        popUp.setHeaderText(null);
        popUp.setContentText("the note has been successfully saved.");
        popUp.showAndWait();
    }

    //alerts the user that the chosen date has no note to show
    private void noNoteExistsPopUp() {
        Alert popUp = new Alert(Alert.AlertType.INFORMATION);
        popUp.setTitle("No note for date");
        popUp.setHeaderText(null);
        popUp.setContentText("There is no note for specified date.");
        popUp.showAndWait();
    }

    //alerts the user that the file name chosen to save/load is not valid
    private void badFileNamePopUp() {
        Alert popUp = new Alert(Alert.AlertType.INFORMATION);
        popUp.setTitle("Bad name");
        popUp.setHeaderText(null);
        popUp.setContentText("please try again with different file name.");
        popUp.showAndWait();
    }

    //alerts the user that the file loaded is not in the correct format to read form itr
    private void badFileFormatPopUp() {
        Alert popUp = new Alert(Alert.AlertType.INFORMATION);
        popUp.setTitle("Bad Format");
        popUp.setHeaderText(null);
        popUp.setContentText("the File is not according to the format Date: yyyy-mm-dd note: NOTE.");
        popUp.showAndWait();
    }

    //alerts the user that the calendar has been renewed to a blank calander
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

    //checks if chosen date is valid, if not alerts the user to choose a valid date
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

    //clears the text field text
    private void clearNoteTextField() {
        noteTextField.setText("");
    }

    //gets the date as a LocalDate from the date chosen in combo box
    private LocalDate getDate() {
        int year = yearComboBox.getValue();
        int month = monthComboBox.getValue();
        int day = dayComboBox.getValue();
        return LocalDate.of(year, month, day);
    }

    //requests a name to save file as and saves file on local package
    public void saveNotesButtonAction() {
        String newFileName = getNewFileName();//requests file name
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/Q2/" + newFileName + ".txt"));
            LocalDate date;
            String note;
            for (LocalDate key : notes.keySet()) {//iterates over all notes and rites them
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

    //requests a new name to save file
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

    //loads notes from existing file, alerts user if no such file exists or the format in file is bad.
    public void loadNotesButtonAction() {
        String loadedFile = getNewFileName();
        String filePath = "src/Q2/" + loadedFile + ".txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            notes = loadedNotes(reader);//switches the current notes with new loaded notes
        } catch (FileNotFoundException e) {
            badFileNamePopUp();
        } catch (IOException e) {
            badFileFormatPopUp();
        } catch (DateTimeParseException e) {
            badFileFormatPopUp();
        }
    }

    //inserts the line to current new calendar
    public void insertNewNote(String line, HashMap<LocalDate, String> newNotes) throws DateTimeParseException, IOException {
        LocalDate date = getDateFromLine(line);
        String note = getNoteFromLine(line);
        newNotes.put(date, note);
    }

    //converts date text from loaded text file into LocalDate format
    private LocalDate getDateFromLine(String line) throws DateTimeParseException {
        String date = line.substring(6, 16);
        return LocalDate.parse(date);
    }

    //converts the note text from loaded text file into String format
    private String getNoteFromLine(String line) throws IOException {
        if (line.charAt(22) != ' ') {//if the note section format is not according to format alerts the user
            throw new IOException();
        }
        String note = line.substring(23);
        return note;
    }

    //reads all lines from loaded text file and returns a new hashmap containing them.
    private HashMap<LocalDate, String> loadedNotes(BufferedReader reader) throws IOException, DateTimeParseException {
        String line;
        HashMap<LocalDate, String> newNotes = new HashMap<>();
        while ((line = reader.readLine()) != null) {
            insertNewNote(line, newNotes);
        }
        return newNotes;
    }

    //deletes current calendar and makes a new clean one
    public void newCalenderButtonAction() {
        notes = new HashMap<LocalDate, String>();
        NewCalenderPopUp();
        clearNoteTextField();
    }
}


