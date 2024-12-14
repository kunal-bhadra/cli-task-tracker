package main.java;

import java.io.FileWriter;
import java.io.IOException;

public class ExportJson {
    public void writeJson(Task task, boolean jsonExists, String jsonPath) throws IOException {
        String jsonString = task.toJson();
        System.out.println(jsonString);

        if (!jsonExists) {
            FileWriter file = new FileWriter(jsonPath);
            file.write("[" + jsonString + "]");
            file.close();
        }
    }
}
