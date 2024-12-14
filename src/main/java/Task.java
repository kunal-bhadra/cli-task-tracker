package main.java;

import java.util.Date;

public class Task {
    private int id;
    private String description;
    private String status;

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

    protected String getStatus() {
        return status;
    }

    protected void setStatus(String status) {
        this.status = status;
    }

    protected Date getUpdatedAt() {
        return (Date) this.updatedAt.clone();
    }

    protected void setUpdatedAt(Date updatedAt) {
        this.updatedAt = (Date) updatedAt.clone();
    }

    protected Date getCreatedAt() {
        return (Date) this.createdAt.clone();
    }

    protected void setCreatedAt(Date createdAt) {
        this.createdAt = (Date) createdAt.clone();
    }

    private Date createdAt;
    private Date updatedAt;
}
