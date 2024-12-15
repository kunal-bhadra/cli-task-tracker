package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ExportJson {
    public void writeJson(String jsonString, String jsonPath) {
        if (!jsonString.equals("NA")) {
            try {
                FileWriter file = new FileWriter(jsonPath);
                file.write("[" + jsonString + "]");
                file.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
