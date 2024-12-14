package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ExportJson {
    public void writeJson(String jsonString, boolean jsonExists, String jsonPath) throws IOException {
        System.out.println(jsonString);

        if (!jsonExists) {
            try {
                FileWriter file = new FileWriter(jsonPath);
                file.write("[" + jsonString + "]");
                file.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                // Read JSON file content
                StringBuilder existingData = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new FileReader(jsonPath))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        existingData.append(line).append("\n");
                    }
                }

                // Remove closing bracket
                int lastBracketIndex = -1;
                for (int i = existingData.length() - 1; i >= 0; i--) {
                    if (!Character.isWhitespace(existingData.charAt(i))) {
                        if (existingData.charAt(i) == ']') {
                            lastBracketIndex = i;
                            break;
                        } else {
                            throw new IOException("Invalid JSON format: last character is not ']'");
                        }
                    }
                }
                existingData.delete(lastBracketIndex, existingData.length());

                // Add the new task entry
                existingData.append(",");
                existingData.append("\n").append(jsonString);
                existingData.append("]");

                // Write the updated file
                FileWriter file = new FileWriter(jsonPath);
                file.write(String.valueOf(existingData));
                file.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
