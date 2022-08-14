package com.yokish.salon.service;
import com.yokish.salon.utils.SecondThread;
import javafx.scene.control.Alert;

public class WindowsOptions {
    public void exitWindow() {
        System.exit(0);
    }

    public static void allerWindowSave() {
        Thread myThread = new SecondThread();
        myThread.setName("Second thread");
        myThread.start();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Save window");
        alert.setHeaderText("Saved");
        alert.setContentText("It's finished");
        alert.showAndWait();
    }

    public static void allerWindowUpdate() {
        Thread myThread = new SecondThread();
        myThread.setName("Second thread");
        myThread.start();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update window");
        alert.setHeaderText("Updated");
        alert.setContentText("It's finished");
        alert.showAndWait();
    }

    public static void allerWindowDelete() {
        Thread myThread = new SecondThread();
        myThread.setName("Second thread");
        myThread.start();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Delete window");
        alert.setHeaderText("Deleted");
        alert.setContentText("It's finished");
        alert.showAndWait();
    }

    public static void allerWindowFieldIsEmpty() {
        Alert emptyField = new Alert(Alert.AlertType.WARNING);
        emptyField.setTitle("Warning!");
        emptyField.setHeaderText(null);
        emptyField.setContentText("Forms is clear");
        emptyField.showAndWait();
    }

    public static void allerWindowFieldSearchIsEmptyOfHavent() {
        Alert emptyField = new Alert(Alert.AlertType.WARNING);
        emptyField.setTitle("Warning!");
        emptyField.setHeaderText(null);
        emptyField.setContentText("Haven't employee of form is clear");
        emptyField.showAndWait();
    }
}
