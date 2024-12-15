package main.java;

import java.io.File;

class Main {

    public static void main(String[] args) {
        ArgumentParser driver = new ArgumentParser();
        final String JSON_PATH = "notes.json";
        boolean jsonExists = false;

        if (args.length > 0) {

            // Check if JSON file exists
            File f = new File(JSON_PATH);
            if (f.exists() && !f.isDirectory()) {
                jsonExists = true;
            }

            // Parse the arguments
            String jsonString = driver.parseArguments(args, jsonExists, JSON_PATH);

            // Write to JSON file
            ExportJson writer = new ExportJson();
            writer.writeJson(jsonString, JSON_PATH);

        } else {
            throw new IllegalArgumentException("No CLI arguments found!");
        }
    }
}