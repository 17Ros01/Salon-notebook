package com.yokish.salon.controllers;

import com.yokish.salon.MainWindow;
import  com.yokish.salon.dao.sqlCommands.SalonSql;
import com.yokish.salon.models.Salon;
import com.yokish.salon.service.SalonOperations;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SalonWindowController  implements Initializable  {

    @FXML
    private TableColumn<Salon, String> SalonAddressColumn;

    @FXML
    private TableColumn<Salon, String> SalonNameColumn;

    @FXML
    private TableColumn<Salon, String> SalonTelephoneColumn;

    @FXML
    private TableView<Salon> tableSalon;

    @FXML
    private TextField txtSalonAddress;

    @FXML
    private TextField txtSalonName;

    @FXML
    private TextField txtSalonTelephone;

    @FXML
    private TextField txtSearchSalon;

    @FXML
    private ListView<String> listViewWindowSalon;

    @FXML
    private TextField textFieldSelectSalon;

    String salonName;
    String salonAddress;
    String salonTelephone;
    SalonOperations salonOperations = new SalonOperations();
    @FXML
    void saveSalonAction(ActionEvent event) {
        salonOperations.saveSalon(txtSalonName, txtSalonAddress, txtSalonTelephone, salonName, salonAddress, salonTelephone);
        tableSalon();
    }

    int myIndex;
    int id;

    @FXML
    void updateSalonAction(ActionEvent event) {
        salonOperations.updateSalon(txtSalonName, txtSalonAddress, txtSalonTelephone, myIndex, tableSalon, id, salonName,
                salonAddress, salonTelephone);
        tableSalon();
    }

    @FXML
    void deleteSalonAction(ActionEvent event) {
        salonOperations.deleteSalon(txtSalonName, txtSalonAddress, txtSalonTelephone, myIndex, tableSalon, id);
        tableSalon();
    }

    @FXML
    void clearSalonAction(ActionEvent event) {
        SalonOperations.clearFormsSalon(txtSalonName, txtSalonAddress, txtSalonTelephone);
    }

    @FXML
    void btnSaveSalonInFile(ActionEvent event) throws Exception {
        salonOperations.saveSalonInFileOperation(txtSalonName, txtSalonAddress, txtSalonTelephone);
    }

    SalonSql salonSql = new SalonSql();
    @FXML
    void searchSalonAction(ActionEvent event) {
        try {
            salonSql.searchSalon(txtSearchSalon,txtSalonName, txtSalonAddress, txtSalonTelephone);
        } catch (SQLException ex) {
            System.out.println("Error");
        }
    }

    @FXML
    void backSalonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainWindow.class.getResource("mainWindow.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Main window");
        stage.setMaxWidth(600);
        stage.setMaxHeight(450);
        stage.setMinWidth(600);
        stage.setMinHeight(450);
        stage.show();
    }

    File fileObject;
    @FXML
    void selectFileForReadSalon(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage primaryStage = (Stage) source.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter tXTFilter = new FileChooser.ExtensionFilter("TXT files(*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(tXTFilter);
        fileChooser.getExtensionFilters().addAll(tXTFilter);
        fileChooser.setTitle("Выбор файла");
        fileObject = fileChooser.showOpenDialog(primaryStage);
        try {
            textFieldSelectSalon.setText(fileObject.getPath());
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    ObservableList<String> listSalon = FXCollections.observableArrayList();
    @FXML
    void downloadFileForRead(ActionEvent event) throws Exception {
        salonOperations.downloadFileForReadSalon(textFieldSelectSalon, fileObject, listSalon, listViewWindowSalon);
    }

    @FXML
    void clearFormSalon(ActionEvent event) {
        listViewWindowSalon.getItems().clear();
    }

    @FXML
    void showAllTableSalon(ActionEvent event) {
        salonSql.getAll();
    }

    @FXML
    void exitSalonAction(ActionEvent event) {
        salonOperations.exitWindow();
    }

    public void tableSalon() {
        ObservableList<Salon> salons = FXCollections.observableArrayList();
        try {
            salonSql.viewTableSalon(salons);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tableSalon.setItems(salons);
        salonSql.columnValueSalon(SalonNameColumn, SalonAddressColumn, SalonTelephoneColumn);
        tableSalon.setRowFactory(tv -> {
            TableRow<Salon> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    salonSql.getValueTableSalon(myIndex, tableSalon, txtSalonName, txtSalonAddress, txtSalonTelephone);
                }
            });
            return myRow;
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableSalon();
    }
}





