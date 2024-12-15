package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {
    public List<Task> parseJson(String filePath) {
        List<Task> tasks = new ArrayList<>();
        ZoneId zone = ZoneId.of("Asia/Kolkata");

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
}
