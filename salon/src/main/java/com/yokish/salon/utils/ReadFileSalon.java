package com.yokish.salon.utils;

import javafx.collections.ObservableList;
import java.io.*;

public class ReadFileSalon {

    public static void readFileSalon(File fileObject, ObservableList<String> listSalon) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileObject), "cp1251"));
        String line = reader.readLine();
        listSalon.add(line);
        while (line != null) {
            line = reader.readLine();
        }
    }
}
