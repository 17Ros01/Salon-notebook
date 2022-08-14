package com.yokish.salon.controllers;

import com.yokish.salon.MainWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindowController {
    private Stage stage = new Stage();

    @FXML
    void onEmployeePage(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(MainWindow.class.getResource("employee.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Employees window");
        stage.setMaxWidth(931);
        stage.setMaxHeight(660);
        stage.setMinWidth(931);
        stage.setMinHeight(660);
        stage.show();
    }

    @FXML
    void onSalonPage(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(MainWindow.class.getResource("salon.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Salon window");
        stage.setMaxWidth(931);
        stage.setMaxHeight(660);
        stage.setMinWidth(931);
        stage.setMinHeight(660);
        stage.show();
    }
}
