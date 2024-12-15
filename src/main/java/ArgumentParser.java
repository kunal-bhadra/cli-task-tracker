package main.java;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
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

    public String getCompleteJsonString(List<Task> tasks) {

        // Update the entire JSON String
        StringBuilder fullJsonString = new StringBuilder();
        for(int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            fullJsonString.append(task.toJson());
            if (i != tasks.size() - 1) {
                fullJsonString.append(",\n");
            }
        }

        return String.valueOf(fullJsonString);
    }

    public String addNewTask(String[] args, boolean jsonExists, String jsonPath) {

        if (args.length < 2) {
            throw new IllegalArgumentException("Exactly 2 arguments are required!");
        }

        List<Task> tasks = jsonParser.addTask(jsonPath, args[1], jsonExists);

        return getCompleteJsonString(tasks);
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

    public String updateTasks(String[] args, boolean jsonExists, String jsonPath) {

        if(!jsonExists) {
            throw new IllegalArgumentException("JSON File missing!");
        }
        if (args.length < 3) {
            throw new IllegalArgumentException("Exactly 3 arguments are required!");
        }
        if (!isInt(args[1])) {
            throw new IllegalArgumentException("The 2nd argument should be numerical ID!");
        }

        // Update the description for that task
        int taskId = Integer.parseInt(args[1]);
        String newDescription = args[2];
        List<Task> tasks = jsonParser.updateTasks(jsonPath, taskId, newDescription);

        return getCompleteJsonString(tasks);
    }

    public String parseArguments(String[] args, boolean jsonExists, String jsonPath) {

        if (isInt(args[0])) {
            throw new IllegalArgumentException("First argument should be a string!");
        }

        String action = args[0];
        return switch (action) {
            case "add" -> addNewTask(args, jsonExists, jsonPath);
            case "list" -> listTasks(args, jsonExists, jsonPath);
            case "update" -> updateTasks(args, jsonExists, jsonPath);
            default -> throw new IllegalArgumentException("First argument is invalid!");
        };
    }
}
