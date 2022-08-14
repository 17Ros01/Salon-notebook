package com.yokish.salon.utils;

import javafx.scene.control.TextField;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

public class SaveInFileSalon {
    public static void writeFileSalon(TextField txtSalonName, TextField txtSalonAddress, TextField txtSalonTelephone) throws IOException {
        String strSalon = txtSalonName.getText().toUpperCase(Locale.ROOT).trim().toString() + "\n" +
                txtSalonAddress.getText().toUpperCase(Locale.ROOT).trim().toString() + "\n" +
                txtSalonTelephone.getText().toUpperCase(Locale.ROOT).trim().toString();

        ArrayList<String> stringSalonSaveInFile = new ArrayList(Arrays.asList(strSalon.split("\n")));
        Collections.reverse(stringSalonSaveInFile);
        System.out.println(stringSalonSaveInFile);
        File file = new File("D:\\WriteTableSalon.txt");
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(String.valueOf(stringSalonSaveInFile));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
