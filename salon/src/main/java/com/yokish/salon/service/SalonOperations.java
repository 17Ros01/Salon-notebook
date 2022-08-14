package com.yokish.salon.service;

import  com.yokish.salon.dao.sqlCommands.SalonSql;
import com.yokish.salon.models.Salon;
import com.yokish.salon.utils.ReadFileSalon;
import com.yokish.salon.utils.SaveInFileSalon;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.io.File;

public class SalonOperations extends WindowsOptions {

    public void saveSalon(TextField txtSalonName, TextField txtSalonAddress, TextField txtSalonTelephone, String salonName,
                          String salonAddress, String salonTelephone) {
        if (txtSalonName.getText().length() == 0 && txtSalonAddress.getText().length() == 0
                && txtSalonTelephone.getText().length() == 0) {
            allerWindowFieldIsEmpty();
            return;
        }
        salonName = txtSalonName.getText();
        salonAddress = txtSalonAddress.getText();
        salonTelephone = txtSalonTelephone.getText();
        try {
            SalonSql.saveSalonSQL(salonName, salonAddress, salonTelephone);
            allerWindowSave();
            clearFormsSalon(txtSalonName, txtSalonAddress, txtSalonTelephone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSalon(TextField txtSalonName, TextField txtSalonAddress, TextField txtSalonTelephone, int myIndex,
                            TableView<Salon> tableSalon, int id, String salonName, String salonAddress, String salonTelephone) {
        if (txtSalonName.getText().length() == 0 && txtSalonAddress.getText().length() == 0
                && txtSalonTelephone.getText().length() == 0) {
           allerWindowFieldIsEmpty();
            return;
        }
        myIndex = tableSalon.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(tableSalon.getItems().get(myIndex).getIdSalon()));
        salonName = txtSalonName.getText();
        salonAddress = txtSalonAddress.getText();
        salonTelephone = txtSalonTelephone.getText();
        clearFormsSalon( txtSalonName, txtSalonAddress, txtSalonTelephone);
        try {
            SalonSql.updateSalonSQL(salonName, salonAddress, salonTelephone, id);
            allerWindowUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteSalon(TextField txtSalonName, TextField txtSalonAddress, TextField txtSalonTelephone,int myIndex,
                            TableView<Salon> tableSalon, int id) {
        if (txtSalonName.getText().length() == 0 && txtSalonAddress.getText().length() == 0
                && txtSalonTelephone.getText().length() == 0) {
            allerWindowFieldIsEmpty();
            return;
        }
        myIndex = tableSalon.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(tableSalon.getItems().get(myIndex).getIdSalon()));
        clearFormsSalon( txtSalonName, txtSalonAddress, txtSalonTelephone);
        try {
            SalonSql.deleteSalonSQL(id);
            allerWindowDelete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveSalonInFileOperation(TextField txtSalonName, TextField txtSalonAddress, TextField txtSalonTelephone) throws Exception {
        if (txtSalonName.getText().length() == 0 && txtSalonAddress.getText().length() == 0 && txtSalonTelephone.getText().length() == 0) {
            allerWindowFieldIsEmpty();
            return;
        }
        SaveInFileSalon.writeFileSalon(txtSalonName, txtSalonAddress, txtSalonTelephone);
        allerWindowSave();
    }

    public void downloadFileForReadSalon(TextField textFieldSelectSalon, File fileObject, ObservableList<String> listSalon,
                                         ListView<String> listViewWindowSalon) throws Exception {
        if (textFieldSelectSalon.getText().length() == 0 ){
            allerWindowFieldIsEmpty();
            return;
        }
        ReadFileSalon.readFileSalon(fileObject, listSalon);
        listViewWindowSalon.setItems(listSalon);
    }

    public static void clearFormsSalon(TextField txtSalonName, TextField txtSalonAddress, TextField txtSalonTelephone) {
        txtSalonName.setText("");
        txtSalonAddress.setText("");
        txtSalonTelephone.setText("");
        txtSalonName.requestFocus();
    }
}
