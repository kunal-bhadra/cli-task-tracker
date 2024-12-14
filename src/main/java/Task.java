package main.java;

import java.time.ZonedDateTime;


public class Task {
    public enum Status {TODO, IN_PROGRESS, DONE}

    private int id;
    private String description;
    private Status status;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @Override
    public String toString() {
        return id + "|" + description + "|" + status + "|" + createdAt + "|" + updatedAt;
    }

    protected int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    protected String getDescription() {
        return description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected Status getStatus() {
        return status;
    }

    protected void setStatus(Status status) {
        this.status = status;
    }

    protected ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    protected void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    protected ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    protected void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    protected String stringToJson(String value) {
        return "\"" + value + "\"";
    }

    protected String numberToJson(int value) {
        return stringToJson(Integer.toString(value));
    }

    protected String enumToJson(Status value) {
        return stringToJson(value.toString());
    }

    protected String dateToJson(ZonedDateTime value) {
        return stringToJson(value.toLocalDateTime().toString());
    }

    public String toJson() {
        return "{" + stringToJson("id") + ":" + numberToJson(id) + "," +
                stringToJson("description") + ":" + stringToJson(description) + "," +
                stringToJson("status") + ":" + enumToJson(status) + "," +
                stringToJson("createdAt") + ":" + dateToJson(createdAt) + "," +
                stringToJson("updatedAt") + ":" + dateToJson(updatedAt) +
                "}";
    }
}
