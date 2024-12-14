package main.java;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class ArgumentParser {
    private static final AtomicInteger count = new AtomicInteger(0);
    ZoneId zone = ZoneId.of( "Asia/Kolkata" );

    public boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void parseArguments(String[] args) {
        // Make an object of Task
        Task task = new Task();

        // Add a task
        String action = args[0];
        if (args.length < 2) {
            throw new IllegalArgumentException("Exactly 2 arguments are required!");
        }
        if (!isInt(args[1])) {
            String description = args[1];
            task.setDescription(description);
            task.setId(count.incrementAndGet());
            task.setCreatedAt(ZonedDateTime.now(zone));
            task.setUpdatedAt(ZonedDateTime.now(zone));
            task.setStatus(Task.Status.TODO);
            System.out.println(task);
        }
    }
}
