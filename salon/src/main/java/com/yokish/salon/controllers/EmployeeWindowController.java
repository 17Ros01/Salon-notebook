package com.yokish.salon.controllers;

import com.yokish.salon.MainWindow;
import com.yokish.salon.dao.sqlCommands.EmployeeSql;
import com.yokish.salon.models.Employee;
import com.yokish.salon.service.EmployeeOperations;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeWindowController implements Initializable   {

    @FXML
    private TableColumn<Employee, String> AddressEmployeeColumn;

    @FXML
    private  TableColumn<Employee, String> NameEmployeeColumn;

    @FXML
    private  TableColumn<Employee, String> PatronymicEmployeeColumn;

    @FXML
    private  TableColumn<Employee, String> PositionEmployeeColumn;

    @FXML
    private  TableColumn<Employee, String> SurnameEmployeeColumn;

    @FXML
    private  TableColumn<Employee, String> TelephoneEmployeeColumn;

    @FXML
    private  TableView<Employee> tableEmployee;

    @FXML
    private  TextField txtAddressEmployee;

    @FXML
    public  TextField txtNameEmployee;

    @FXML
    private  TextField txtPatronymicEmployee;

    @FXML
    private  TextField txtPositionEmployee;

    @FXML
    private  TextField txtSearchEmployee;

    @FXML
    private  TextField txtSurnameEmployee;

    @FXML
    private  TextField txtTelephoneEmployee;

    @FXML
    private  ListView<String> listViewWindowEmployee;

    @FXML
    private  TextField textFieldSelectEmployee;

    String name;
    String surname;
    String patronymic;
    String address;
    String telephone;
    String position;
    EmployeeOperations employeeOperations = new EmployeeOperations();
    @FXML
    void saveEmployeeAction(ActionEvent event) {
        employeeOperations.saveEmployee(txtNameEmployee, txtSurnameEmployee, txtPatronymicEmployee, txtAddressEmployee,
                txtTelephoneEmployee, txtPositionEmployee, name, surname, patronymic, address, telephone, position);
        tableEmployee();
    }

    int myIndex;
    int id;
    @FXML
    void updateEmployeeAction(ActionEvent event) throws Exception {
        employeeOperations.updateEmployee(txtNameEmployee, txtSurnameEmployee, txtPatronymicEmployee, txtAddressEmployee,
                txtTelephoneEmployee, txtPositionEmployee, myIndex, tableEmployee, id, name, surname, patronymic,
                address, telephone, position);
        tableEmployee();
    }

    @FXML
    void deleteEmployeeAction(ActionEvent event) {
        employeeOperations.deleteEmployee(txtNameEmployee, txtSurnameEmployee, txtPatronymicEmployee, txtAddressEmployee,
                txtTelephoneEmployee, txtPositionEmployee, myIndex, tableEmployee, id);
        tableEmployee();
    }

    @FXML
    void clearEmployeeAction(ActionEvent event)  {
        EmployeeOperations.clearFormsEmployee(txtNameEmployee, txtSurnameEmployee, txtPatronymicEmployee, txtAddressEmployee,
                txtTelephoneEmployee, txtPositionEmployee);
    }

    @FXML
    void saveEmployeeInFile(ActionEvent event) {
        employeeOperations.saveEmployeeInFileOperation(txtNameEmployee, txtSurnameEmployee, txtPatronymicEmployee, txtAddressEmployee,
                txtTelephoneEmployee, txtPositionEmployee);
    }

    EmployeeSql employeeSql = new EmployeeSql();
    @FXML
    void searchEmployeeAction(ActionEvent event) {
        try {
            employeeSql.searchEmployee(txtSearchEmployee, txtNameEmployee, txtSurnameEmployee, txtPatronymicEmployee,
                    txtAddressEmployee, txtTelephoneEmployee, txtPositionEmployee);
        } catch (SQLException ex) {
            System.out.println("Error");
        }
    }

    @FXML
    void backEmployeeAction(ActionEvent event) throws IOException {
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
    void selectFileForReadEmployee(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage primaryStage = (Stage) source.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter tXTFilter = new FileChooser.ExtensionFilter("TXT files(*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(tXTFilter);
        fileChooser.getExtensionFilters().addAll(tXTFilter);
        fileChooser.setTitle("Выбор файла");
        fileObject = fileChooser.showOpenDialog(primaryStage);
        try {
            textFieldSelectEmployee.setText(fileObject.getPath());
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    ObservableList<String> listEmployee = FXCollections.observableArrayList();
    @FXML
    void downloadFileToReadEmployee(ActionEvent event) throws Exception {
        employeeOperations.downloadFileForReadEmployee(textFieldSelectEmployee, fileObject, listEmployee, listViewWindowEmployee);
    }

    @FXML
    void clearFormEmployee(ActionEvent event) {
        listViewWindowEmployee.getItems().clear();
    }

    @FXML
    void exitEmployeeAction(ActionEvent event) {
        employeeOperations.exitWindow();
    }

    @FXML
    void showAllTableEmployee(ActionEvent event) {
        employeeSql.getAll();
    }

    @FXML
    void showInfoAboutDirectorInConsole(ActionEvent event) {
        employeeOperations.showAboutDirectorInfo();
    }

    public void tableEmployee() {
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        try {
            employeeSql.viewTableEmployee(employees);
            } catch (Exception e) {
            e.printStackTrace();
        }
        tableEmployee.setItems(employees);
        employeeSql.columnValueEmployee(NameEmployeeColumn, SurnameEmployeeColumn, PatronymicEmployeeColumn, AddressEmployeeColumn,
                TelephoneEmployeeColumn, PositionEmployeeColumn);
        tableEmployee.setRowFactory(tv -> {
            TableRow<Employee> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    employeeSql.getValueTableEmployee(myIndex, tableEmployee, txtNameEmployee, txtSurnameEmployee, txtPatronymicEmployee,
                            txtAddressEmployee, txtTelephoneEmployee, txtPositionEmployee);
                }
            });
            return myRow;
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableEmployee();
    }
}
