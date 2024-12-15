package main.java;

import java.io.FileWriter;
import java.io.IOException;

public class ExportJson {
    public void writeJson(String jsonString, String jsonPath) {

        // Overwrite JSON file with updated content every time unless tasks were listed
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
