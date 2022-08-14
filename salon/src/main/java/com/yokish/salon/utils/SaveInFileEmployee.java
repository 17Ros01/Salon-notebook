package com.yokish.salon.utils;

import javafx.scene.control.TextField;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SaveInFileEmployee {
    public static void writeInFileEmployee(TextField txtNameEmployee, TextField txtSurnameEmployee, TextField txtPatronymicEmployee,
                                           TextField txtAddressEmployee, TextField txtTelephoneEmployee, TextField txtPositionEmployee) {
        String strEmployee = txtNameEmployee.getText().toUpperCase(Locale.ROOT).toString() + "\n" +
                txtSurnameEmployee.getText().toUpperCase(Locale.ROOT).toString() + "\n" +
                txtPatronymicEmployee.getText().toUpperCase(Locale.ROOT).toString() + "\n" +
                txtAddressEmployee.getText().toUpperCase(Locale.ROOT).toString() + "\n" +
                txtTelephoneEmployee.getText().toUpperCase(Locale.ROOT).toString() + "\n" +
                txtPositionEmployee.getText().toUpperCase(Locale.ROOT).toString();

        ArrayList<String> stringEmployeeSaveInFile = new ArrayList(Arrays.asList(strEmployee.split("\n")));
        Collections.swap(stringEmployeeSaveInFile, 0, 1);
        System.out.println(stringEmployeeSaveInFile);
        File file = new File("D:\\WriteTableEmployee.txt");
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(String.valueOf(stringEmployeeSaveInFile));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}