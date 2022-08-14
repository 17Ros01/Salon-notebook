package com.yokish.salon.service;

import com.yokish.salon.dao.ConnectDB;
import com.yokish.salon.dao.sqlCommands.EmployeeSql;
import com.yokish.salon.models.Employee;
import com.yokish.salon.utils.ReadFileEmployee;
import com.yokish.salon.utils.SaveInFileEmployee;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class EmployeeOperations extends WindowsOptions {
    public void saveEmployee(TextField txtNameEmployee, TextField txtSurnameEmployee, TextField txtPatronymicEmployee,
                             TextField txtAddressEmployee, TextField txtTelephoneEmployee, TextField txtPositionEmployee,
                             String name, String surname, String patronymic, String address, String telephone, String position) {
        if (txtNameEmployee.getText().length() == 0 && txtSurnameEmployee.getText().length() == 0
                && txtPatronymicEmployee.getText().length() == 0
                && txtAddressEmployee.getText().length() == 0
                && txtTelephoneEmployee.getText().length() == 0
                && txtPositionEmployee.getText().length() == 0) {
            allerWindowFieldIsEmpty();
            return;
        }
        name = txtNameEmployee.getText();
        surname = txtSurnameEmployee.getText();
        patronymic = txtPatronymicEmployee.getText();
        address = txtAddressEmployee.getText();
        telephone = txtTelephoneEmployee.getText();
        position = txtPositionEmployee.getText();
        try {
            EmployeeSql.saveEmployeeSQL(name, surname, patronymic, address, telephone, position);
            allerWindowSave();
            clearFormsEmployee(txtNameEmployee, txtSurnameEmployee, txtPatronymicEmployee, txtAddressEmployee,
                    txtTelephoneEmployee, txtPositionEmployee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(TextField txtNameEmployee, TextField txtSurnameEmployee, TextField txtPatronymicEmployee,
                               TextField txtAddressEmployee, TextField txtTelephoneEmployee, TextField txtPositionEmployee,
                               int myIndex, TableView<Employee> tableEmployee, int id, String name, String surname,
                               String patronymic, String address, String telephone, String position) throws Exception {
        if (txtNameEmployee.getText().length() == 0 && txtSurnameEmployee.getText().length() == 0
                && txtPatronymicEmployee.getText().length() == 0
                && txtAddressEmployee.getText().length() == 0
                && txtTelephoneEmployee.getText().length() == 0
                && txtPositionEmployee.getText().length() == 0) {
            allerWindowFieldIsEmpty();
        } else {
            myIndex = tableEmployee.getSelectionModel().getSelectedIndex();
            id = Integer.parseInt(String.valueOf(tableEmployee.getItems().get(myIndex).getIdEmployee()));
            name = txtNameEmployee.getText();
            surname = txtSurnameEmployee.getText();
            patronymic = txtPatronymicEmployee.getText();
            address = txtAddressEmployee.getText();
            telephone = txtTelephoneEmployee.getText();
            position = txtPositionEmployee.getText();
            clearFormsEmployee(txtNameEmployee, txtSurnameEmployee, txtPatronymicEmployee,
                    txtAddressEmployee, txtTelephoneEmployee, txtPositionEmployee);
            try {
                EmployeeSql.updateEmployeeSQL(name, surname, patronymic, address, telephone, position, id);
                allerWindowUpdate();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
        }
    }

    public void deleteEmployee(TextField txtNameEmployee, TextField txtSurnameEmployee, TextField txtPatronymicEmployee,
                               TextField txtAddressEmployee, TextField txtTelephoneEmployee, TextField txtPositionEmployee,
                               int myIndex, TableView<Employee> tableEmployee, int id) {
        if (txtNameEmployee.getText().length() == 0 && txtSurnameEmployee.getText().length() == 0
                && txtPatronymicEmployee.getText().length() == 0
                && txtAddressEmployee.getText().length() == 0
                && txtTelephoneEmployee.getText().length() == 0
                && txtPositionEmployee.getText().length() == 0) {
            allerWindowFieldIsEmpty();
        } else {
            myIndex = tableEmployee.getSelectionModel().getSelectedIndex();
            id = Integer.parseInt(String.valueOf(tableEmployee.getItems().get(myIndex).getIdEmployee()));
            clearFormsEmployee(txtNameEmployee, txtSurnameEmployee, txtPatronymicEmployee,
                    txtAddressEmployee, txtTelephoneEmployee, txtPositionEmployee);
            try {
                EmployeeSql.deleteEmployeeSQL(id);
                allerWindowDelete();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveEmployeeInFileOperation(TextField txtNameEmployee, TextField txtSurnameEmployee, TextField txtPatronymicEmployee,
                                            TextField txtAddressEmployee, TextField txtTelephoneEmployee, TextField txtPositionEmployee) {
        if (txtNameEmployee.getText().length() == 0 && txtSurnameEmployee.getText().length() == 0
                && txtPatronymicEmployee.getText().length() == 0
                && txtAddressEmployee.getText().length() == 0
                && txtTelephoneEmployee.getText().length() == 0
                && txtPositionEmployee.getText().length() == 0) {
            allerWindowFieldIsEmpty();
            return;
        }
        SaveInFileEmployee.writeInFileEmployee(txtNameEmployee, txtSurnameEmployee, txtPatronymicEmployee,
                txtAddressEmployee, txtTelephoneEmployee, txtPositionEmployee);
        allerWindowSave();
    }

    public void downloadFileForReadEmployee(TextField textFieldSelectEmployee, File fileObject, ObservableList<String> listEmployee,
                                            ListView<String> listViewWindowEmployee) throws Exception {
        if (textFieldSelectEmployee.getText().length() == 0) {
            allerWindowFieldIsEmpty();
            return;
        }
        ReadFileEmployee.readFileEmployee(fileObject, listEmployee);
        listViewWindowEmployee.setItems(listEmployee);
    }

    public static void clearFormsEmployee(TextField txtNameEmployee, TextField txtSurnameEmployee, TextField txtPatronymicEmployee,
                                          TextField txtAddressEmployee, TextField txtTelephoneEmployee, TextField txtPositionEmployee) {
        txtNameEmployee.setText("");
        txtSurnameEmployee.setText("");
        txtPatronymicEmployee.setText("");
        txtAddressEmployee.setText("");
        txtTelephoneEmployee.setText("");
        txtPositionEmployee.setText("");
        txtNameEmployee.requestFocus();
    }

    public void showAboutDirectorInfo() {
        ConnectDB salonCourse = new ConnectDB();
        String query = "select * from employee";
        try {
            Statement statement = salonCourse.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Employee employee = new Employee();
                List<Employee> employees = List.of(employee);
                employee.setNameEmployee(resultSet.getString("nameEmployee"));
                employee.setSurnameEmployee(resultSet.getString("surnameEmployee"));
                employee.setPatronymicEmployee(resultSet.getString("patronymicEmployee"));
                employee.setAddressEmployee(resultSet.getString("addressEmployee"));
                employee.setTelephoneEmployee(resultSet.getString("telephoneEmployee"));
                employee.setPositionEmployee(resultSet.getString("positionEmployee"));
                Optional<Employee> isHasPosition = employees.stream()
                        .filter(empl -> empl.getPositionEmployee().equals("Director"))
                        .findAny();
                System.out.println(isHasPosition);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
