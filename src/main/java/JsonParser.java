package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {
    ZoneId zone = ZoneId.of("Asia/Kolkata");

    public List<Task> parseJson(String filePath) {
        List<Task> tasks = new ArrayList<>();

        // Regex pattern to match all fields
        String regex = "\\{\"id\":\"(\\d+)\",\"description\":\"(.*?)\",\"status\":\"(.*?)\",\"createdAt\":\"(.*?)\",\"updatedAt\":\"(.*?)\"}";
        Pattern pattern = Pattern.compile(regex);

        // Add each task to list line by line
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    Task task = new Task();
                    task.setId(Integer.parseInt(matcher.group(1)));
                    task.setDescription(matcher.group(2));
                    task.setStatus(Task.Status.valueOf(matcher.group(3)));
                    task.setCreatedAt(LocalDateTime.parse(matcher.group(4)).atZone(zone));
                    task.setUpdatedAt(LocalDateTime.parse(matcher.group(5)).atZone(zone));
                    tasks.add(task);
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int findMaxId(String filePath) {
        int maxId = Integer.MIN_VALUE;
        List<Task> tasks = parseJson(filePath);

        // Extract max ID from list of Task objects
        for (Task task: tasks) {
            int taskId = task.getId();
            maxId = Math.max(maxId, taskId);
        }

        return maxId;
    }

    public void listAllTasks(String filePath) {
        List<Task> tasks = parseJson(filePath);

        for(Task task : tasks) {
            System.out.println(task);
        }
    }

    public void listRequiredTasks(String filePath, String requiredStatus) {
        List<Task> tasks = parseJson(filePath);

        for(Task task : tasks) {
            if (task.getStatus().getListStatus().equals(requiredStatus)) {
                System.out.println(task);
            }
        }
    }

    public List<Task> updateTasks(String filePath, int taskId, String newDescription) {
        List<Task> tasks = parseJson(filePath);

        for(Task task : tasks) {
            if (task.getId() == taskId) {
                task.setDescription(newDescription);
                task.setUpdatedAt(ZonedDateTime.now(zone).truncatedTo(ChronoUnit.SECONDS));
            }
        }

        return tasks;
    }

    public List<Task> markTask(String filePath, int taskId, String markStatus) {
        List<Task> tasks = parseJson(filePath);

        for(Task task : tasks) {
            if (task.getId() == taskId) {
                for (Task.Status s : Task.Status.values()) {
                    if (s.getMarkStatus().equals(markStatus)) {
                        task.setStatus(s);
                        task.setUpdatedAt(ZonedDateTime.now(zone).truncatedTo(ChronoUnit.SECONDS));
                    }
                }
            }
        }

        return tasks;
    }

    public List<Task> deleteTask(String filePath, int taskId) {
        List<Task> tasks = parseJson(filePath);
        tasks.removeIf(task -> task.getId() == taskId);
        return tasks;
    }

    public List<Task> addTask(String filePath, String description, boolean jsonExists) {

        // Find max ID if JSON file already exists
        int maxId = 0;
        List<Task> tasks = new ArrayList<>();
        if (jsonExists) {
            maxId = findMaxId(filePath);
            tasks = parseJson(filePath);
        }
        final AtomicInteger count = new AtomicInteger(maxId);

        // Create new task
        Task task = new Task();
        task.setDescription(description);
        task.setId(count.incrementAndGet());
        task.setCreatedAt(ZonedDateTime.now(zone).truncatedTo(ChronoUnit.SECONDS));
        task.setUpdatedAt(ZonedDateTime.now(zone).truncatedTo(ChronoUnit.SECONDS));
        task.setStatus(Task.Status.TODO);
        tasks.add(task);
        System.out.println("Task added successfully (ID: " + task.getId() + ")");

        return tasks;
    }
}
