package main.java;

import java.io.File;
import java.io.IOException;

class Main {

    public static void main(String[] args) throws IOException {
        ArgumentParser driver = new ArgumentParser();
        final String JSON_PATH = "notes.json";
        boolean jsonExists = false;

        if (args.length > 0) {
            // Print out all CLI arguments
            System.out.println("The command line arguments are:");
            for (String arg : args) {
                System.out.println(arg);
            }

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
            System.out.println("No command line arguments found.");
        }

    }
}