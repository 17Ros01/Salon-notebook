package com.yokish.salon.utils;

import javafx.collections.ObservableList;
import java.io.*;

public class ReadFileEmployee {

    public static void readFileEmployee(File fileObject, ObservableList<String> listEmployee) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileObject), "cp1251"));
        String line = reader.readLine();
        listEmployee.add(line);
        while (line != null) {
            line = reader.readLine();
        }
    }
 }
