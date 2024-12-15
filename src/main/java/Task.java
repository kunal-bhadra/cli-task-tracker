package main.java;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class Task {
    protected enum Status {
        TODO("todo", "add"),
        IN_PROGRESS("in-progress", "mark-in-progress"),
        DONE("done", "mark-done");

        private final String listStatus;
        private final String markStatus;

        Status(String listStatus, String markStatus) {
            this.listStatus = listStatus;
            this.markStatus = markStatus;
        }

        public String getListStatus() {
            return listStatus;
        }

        public String getMarkStatus() {
            return markStatus;
        }
    }

    private int id;
    private String description;
    private Status status;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @Override
    public String toString() {
        return "ID:" + id +
                " Status:" + status +
                " - Description:\"" + description +
                "\" - Created At:" + createdAt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) +
                " - Updated At:" + updatedAt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
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
