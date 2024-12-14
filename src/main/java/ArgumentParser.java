package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgumentParser {

    public int findMaxId(String filePath) {
        int maxId = Integer.MIN_VALUE;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Pattern idPattern = Pattern.compile("\"id\"\\s*:\\s*\"(\\d+)\"");

            while ((line = reader.readLine()) != null) {
                Matcher matcher = idPattern.matcher(line);
                while (matcher.find()) {
                    String idValueStr = matcher.group(1);
                    try {
                        int idValue = Integer.parseInt(idValueStr);
                        maxId = Math.max(maxId, idValue);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid 'id' value: " + idValueStr);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return maxId;
    }

    public boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String parseArguments(String[] args, boolean jsonExists, String jsonPath) {
        // Find max ID if JSON file already exists
        int maxId = 0;
        if (jsonExists) {
            maxId = findMaxId(jsonPath);
        }

        final AtomicInteger count = new AtomicInteger(maxId);
        ZoneId zone = ZoneId.of("Asia/Kolkata");

        // Make an object of Task
        Task task = new Task();

        // Add a task
        String action = args[0];
        if (action.equals("add")) {
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
        }

        return task.toJson();
    }
}
