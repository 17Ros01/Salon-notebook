package com.yokish.salon.dao.sqlCommands;

import com.yokish.salon.service.ViewDB;
import com.yokish.salon.dao.ConnectDB;
import com.yokish.salon.models.Salon;
import com.yokish.salon.service.WindowsOptions;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.*;
import java.util.*;

public class SalonSql implements ViewDB{

    public static void saveSalonSQL(String salonName, String salonAddress, String salonTelephone) throws Exception {
        Connection connection = new ConnectDB().getConnection();
        PreparedStatement pst;
        pst = connection.prepareStatement("insert into salon(salonName, salonAddress, salonTelephone)values(?,?,?)");
        pst.setString(1, salonName);
        pst.setString(2, salonAddress);
        pst.setString(3, salonTelephone);
        pst.executeUpdate();
    }

    public static void updateSalonSQL(String salonName, String salonAddress, String salonTelephone, int id) throws Exception {
        Connection connection = new ConnectDB().getConnection();
        PreparedStatement pst;
        pst = connection.prepareStatement("update salon set salonName = ?, salonAddress = ?, " +
                "salonTelephone = ? where idSalon = ? ");
        pst.setString(1, salonName);
        pst.setString(2, salonAddress);
        pst.setString(3, salonTelephone);
        pst.setInt(4, id);
        pst.executeUpdate();
    }

    public static void deleteSalonSQL(int id) throws Exception {
        Connection connection = new ConnectDB().getConnection();
        PreparedStatement pst;
        pst = connection.prepareStatement("delete from salon where idSalon = ? ");
        pst.setInt(1, id);
        pst.executeUpdate();
    }

    public void searchSalon(TextField txtSearchSalon, TextField txtSalonName, TextField txtSalonAddress,
                            TextField txtSalonTelephone) throws SQLException {
        Connection connection = new ConnectDB().getConnection();
        PreparedStatement pst;
        String searchNameSalon = txtSearchSalon.getText();
        pst = connection.prepareStatement("select salonName, salonAddress, salonTelephone from salon where salonName = ?");
        pst.setString(1, searchNameSalon);
        ResultSet rs = pst.executeQuery();
        if (rs.next() == true) {
            String salonName = rs.getString(1);
            String salonAddress = rs.getString(2);
            String salonTelephone = rs.getString(3);

            txtSalonName.setText(salonName);
            txtSalonAddress.setText(salonAddress);
            txtSalonTelephone.setText(salonTelephone);
        } else {
            WindowsOptions.allerWindowFieldSearchIsEmptyOfHavent();
            txtSearchSalon.setText("");
        }
    }

    public void viewTableSalon(ObservableList<Salon> salons) throws Exception {
        Connection connection = new ConnectDB().getConnection();
        PreparedStatement pst;
        pst = connection.prepareStatement("select idSalon,salonName,salonAddress,salonTelephone from salon");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Salon salon = new Salon();
            salon.setIdSalon(rs.getString("idSalon"));
            salon.setSalonName(rs.getString("salonName"));
            salon.setSalonAddress(rs.getString("salonAddress"));
            salon.setSalonTelephone(rs.getString("salonTelephone"));
            salons.add(salon);
        }
    }

    public void columnValueSalon(TableColumn<Salon, String> SalonNameColumn, TableColumn<Salon, String> SalonAddressColumn,
                                 TableColumn<Salon, String> SalonTelephoneColumn) {
        SalonNameColumn.setCellValueFactory(salon -> new SimpleStringProperty(salon.getValue().getSalonName()));
        SalonAddressColumn.setCellValueFactory(salon -> new SimpleStringProperty(salon.getValue().getSalonAddress()));
        SalonTelephoneColumn.setCellValueFactory(salon -> new SimpleStringProperty(salon.getValue().getSalonTelephone()));
    }

    public void getValueTableSalon(int myIndex, TableView<Salon> tableSalon, TextField txtSalonName,
                                   TextField txtSalonAddress, TextField txtSalonTelephone) {
        myIndex = tableSalon.getSelectionModel().getSelectedIndex();
        txtSalonName.setText(tableSalon.getItems().get(myIndex).getSalonName());
        txtSalonAddress.setText(tableSalon.getItems().get(myIndex).getSalonAddress());
        txtSalonTelephone.setText(tableSalon.getItems().get(myIndex).getSalonTelephone());
    }

    public  List<Salon> getAll() {
        ConnectDB salonCourse = new ConnectDB();
        String query = "select * from salon";
        try {
            Statement statement = salonCourse.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            Salon salon = new Salon();
            while (resultSet.next()) {
                salon.setIdSalon(resultSet.getString("idSalon"));
                salon.setSalonName(resultSet.getString("salonName"));
                salon.setSalonAddress(resultSet.getString("salonAddress"));
                salon.setSalonTelephone(resultSet.getString("salonTelephone"));
                Optional<String> isHaveName = Optional.ofNullable(salon.getSalonName());
                isHaveName.ifPresent(name -> {
                    salon.setSalonName(name.toUpperCase().trim());
                });
                System.out.println(salon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
