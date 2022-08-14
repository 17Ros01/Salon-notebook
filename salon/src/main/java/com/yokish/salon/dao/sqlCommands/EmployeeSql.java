package com.yokish.salon.dao.sqlCommands;

import com.yokish.salon.dao.ConnectDB;
import com.yokish.salon.models.Employee;
import com.yokish.salon.service.WindowsOptions;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class EmployeeSql {
    public static void saveEmployeeSQL(String name, String surname, String patronymic, String address, String telephone,
                                       String position) throws Exception {
        Connection connection = new ConnectDB().getConnection();
        PreparedStatement pst = connection.prepareStatement("insert into employee(nameEmployee, surnameEmployee, patronymicEmployee, " +
                                            "addressEmployee, telephoneEmployee, positionEmployee)values(?,?,?,?,?,?)");
        pst.setString(1, name);
        pst.setString(2, surname);
        pst.setString(3, patronymic);
        pst.setString(4, address);
        pst.setString(5, telephone);
        pst.setString(6, position);
        pst.executeUpdate();
    }

    public static void updateEmployeeSQL(String name, String surname, String patronymic, String address, String telephone,
                                         String position, int id) throws Exception {
        Connection connection = new ConnectDB().getConnection();
        PreparedStatement pst = connection.prepareStatement("update employee set nameEmployee = ?, surnameEmployee = ?," +
                                            " patronymicEmployee = ?, addressEmployee= ?, telephoneEmployee= ?, " +
                                            "positionEmployee= ? where idEmployee = ? ");
        pst.setString(1, name);
        pst.setString(2, surname);
        pst.setString(3, patronymic);
        pst.setString(4, address);
        pst.setString(5, telephone);
        pst.setString(6, position);
        pst.setInt(7, id);
        pst.executeUpdate();
    }

    public static void deleteEmployeeSQL(int id) throws SQLException {
        Connection connection = new ConnectDB().getConnection();
        PreparedStatement pst = connection.prepareStatement("delete from employee where idEmployee = ? ");
        pst.setInt(1, id);
        pst.executeUpdate();
    }

    public void searchEmployee(TextField txtSearchEmployee, TextField txtNameEmployee, TextField txtSurnameEmployee,
                               TextField txtPatronymicEmployee, TextField txtAddressEmployee, TextField txtTelephoneEmployee,
                               TextField txtPositionEmployee) throws SQLException {
        Connection connection = new ConnectDB().getConnection();
        String searchNameEmployee = txtSearchEmployee.getText();
        PreparedStatement pst = connection.prepareStatement("select nameEmployee, surnameEmployee, patronymicEmployee, addressEmployee, " +
                "telephoneEmployee, positionEmployee from employee where surnameEmployee = ?");
        pst.setString(1, searchNameEmployee);
        ResultSet rs = pst.executeQuery();
        if (rs.next() == true) {
            String nameEmployee = rs.getString(1);
            String surnameEmployee = rs.getString(2);
            String patronymicEmployee = rs.getString(3);
            String addressEmployee = rs.getString(4);
            String telephoneEmployee = rs.getString(5);
            String positionEmployee = rs.getString(6);

            txtNameEmployee.setText(nameEmployee);
            txtSurnameEmployee.setText(surnameEmployee);
            txtPatronymicEmployee.setText(patronymicEmployee);
            txtAddressEmployee.setText(addressEmployee);
            txtTelephoneEmployee.setText(telephoneEmployee);
            txtPositionEmployee.setText(positionEmployee);
        } else {
            WindowsOptions.allerWindowFieldSearchIsEmptyOfHavent();
            txtSearchEmployee.setText("");
            }
    }

    public void viewTableEmployee( ObservableList<Employee> employees) throws Exception {
        Connection connection = new ConnectDB().getConnection();
        PreparedStatement pst = connection.prepareStatement("select idEmployee,nameEmployee,surnameEmployee,patronymicEmployee, " +
                                            "addressEmployee, telephoneEmployee, positionEmployee from employee");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Employee employee = new Employee();
            employee.setIdEmployee(rs.getString("idEmployee"));
            employee.setNameEmployee(rs.getString("nameEmployee"));
            employee.setSurnameEmployee(rs.getString("surnameEmployee"));
            employee.setPatronymicEmployee(rs.getString("patronymicEmployee"));
            employee.setAddressEmployee(rs.getString("addressEmployee"));
            employee.setTelephoneEmployee(rs.getString("telephoneEmployee"));
            employee.setPositionEmployee(rs.getString("positionEmployee"));
            employees.add(employee);
        }
    }

    public void columnValueEmployee(TableColumn<Employee, String> NameEmployeeColumn, TableColumn<Employee,
                                    String> SurnameEmployeeColumn, TableColumn<Employee, String> PatronymicEmployeeColumn,
                                    TableColumn<Employee, String> AddressEmployeeColumn, TableColumn<Employee,
                                    String> TelephoneEmployeeColumn, TableColumn<Employee, String> PositionEmployeeColumn) {
        NameEmployeeColumn.setCellValueFactory(employee -> new SimpleStringProperty(employee.getValue().getNameEmployee()));
        SurnameEmployeeColumn.setCellValueFactory(employee -> new SimpleStringProperty(employee.getValue().getSurnameEmployee()));
        PatronymicEmployeeColumn.setCellValueFactory(employee -> new SimpleStringProperty(employee.getValue().getPatronymicEmployee()));
        AddressEmployeeColumn.setCellValueFactory(employee -> new SimpleStringProperty(employee.getValue().getAddressEmployee()));
        TelephoneEmployeeColumn.setCellValueFactory(employee -> new SimpleStringProperty(employee.getValue().getTelephoneEmployee()));
        PositionEmployeeColumn.setCellValueFactory(employee -> new SimpleStringProperty(employee.getValue().getPositionEmployee()));
    }

    public void getValueTableEmployee(int myIndex, TableView<Employee> tableEmployee, TextField txtNameEmployee,
                                      TextField txtSurnameEmployee, TextField txtPatronymicEmployee, TextField txtAddressEmployee,
                                      TextField txtTelephoneEmployee, TextField txtPositionEmployee) {
            myIndex = tableEmployee.getSelectionModel().getSelectedIndex();
            txtNameEmployee.setText(tableEmployee.getItems().get(myIndex).getNameEmployee());
            txtSurnameEmployee.setText(tableEmployee.getItems().get(myIndex).getSurnameEmployee());
            txtPatronymicEmployee.setText(tableEmployee.getItems().get(myIndex).getPatronymicEmployee());
            txtAddressEmployee.setText(tableEmployee.getItems().get(myIndex).getAddressEmployee());
            txtTelephoneEmployee.setText(tableEmployee.getItems().get(myIndex).getTelephoneEmployee());
            txtPositionEmployee.setText(tableEmployee.getItems().get(myIndex).getPositionEmployee());
    }

    public List<Employee> getAll()  {
        Connection connection = new ConnectDB().getConnection();
        String query = "select * from employee";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            Employee employee = new Employee();
            while (resultSet.next()) {
                employee.setIdEmployee(resultSet.getString("idEmployee"));
                employee.setNameEmployee(resultSet.getString("nameEmployee"));
                employee.setSurnameEmployee(resultSet.getString("surnameEmployee"));
                employee.setPatronymicEmployee(resultSet.getString("patronymicEmployee"));
                employee.setAddressEmployee(resultSet.getString("addressEmployee"));
                employee.setTelephoneEmployee(resultSet.getString("telephoneEmployee"));
                employee.setPositionEmployee(resultSet.getString("positionEmployee"));
                Optional<String> isHaveName = Optional.ofNullable(employee.getSurnameEmployee());
                isHaveName.ifPresent(name -> {
                    employee.setSurnameEmployee(name.toUpperCase().trim());
                });
                    System.out.println(employee);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}





