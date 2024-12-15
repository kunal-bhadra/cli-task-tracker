package main.java;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ArgumentParser {

    ZoneId zone = ZoneId.of("Asia/Kolkata");
    JsonParser jsonParser = new JsonParser();

    public boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String addNewTask(String[] args, boolean jsonExists, String jsonPath) {

        // Find max ID if JSON file already exists
        int maxId = 0;
        if (jsonExists) {
            maxId = jsonParser.findMaxId(jsonPath);
        }
        final AtomicInteger count = new AtomicInteger(maxId);

        // Add a new Task with required fields
        Task task = new Task();
        if (args.length < 2) {
            throw new IllegalArgumentException("Exactly 2 arguments are required!");
        }
        if (!isInt(args[1])) {
            String description = args[1];
            task.setDescription(description);
            task.setId(count.incrementAndGet());
            task.setCreatedAt(ZonedDateTime.now(zone).truncatedTo(ChronoUnit.SECONDS));
            task.setUpdatedAt(ZonedDateTime.now(zone).truncatedTo(ChronoUnit.SECONDS));
            task.setStatus(Task.Status.TODO);
            System.out.println("Task added successfully (ID: " + task.getId() + ")");
        }

        // Return new JSON entry
        return task.toJson();
    }

    public String listTasks(String[] args, boolean jsonExists, String jsonPath) {

        // Check if JSON file exists
        if(!jsonExists) {
            throw new IllegalArgumentException("JSON File missing!");
        }

        // Print tasks with required Status
        if (args.length == 2) {
            String listStatus = args[1];
            if (!listStatus.matches("done|todo|in-progress")) {
                throw new IllegalArgumentException("Only the following status is valid: done, todo, in-progress");
            }
            for (Task.Status s : Task.Status.values()) {
                if (s.getListStatus().equals(listStatus)) {
                    jsonParser.listRequiredTasks(jsonPath, listStatus);
                    return "NA";
                }
            }
        }
        // Print all tasks
        jsonParser.listAllTasks(jsonPath);

        return "NA";
    }

    public String parseArguments(String[] args, boolean jsonExists, String jsonPath) {

        if (isInt(args[0])) {
            throw new IllegalArgumentException("First argument should be a string!");
        }

        String action = args[0];
        if (action.equals("add")) {
            return addNewTask(args, jsonExists, jsonPath);
        } else if (action.equals("list")) {
            return listTasks(args, jsonExists, jsonPath);
        } else {
            throw new IllegalArgumentException("First argument is invalid!");
        }
    }
}
