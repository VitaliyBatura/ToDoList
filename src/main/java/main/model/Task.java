package main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private LocalDateTime creationTime;
    private boolean isDone;
    private String title;
    private String description;

    public Task() {
    }

    public Task(LocalDateTime creationTime, boolean isDone, String title, String description) {
        this.creationTime = creationTime;
        this.isDone = isDone;
        this.title = title;
        this.description = description;
    }

    public Task(String title, String description, LocalDateTime creationTime) {
    }

    public Task(int id, LocalDateTime creationTime, boolean isDone, String title, String description) {
        this.id = id;
        this.creationTime = creationTime;
        this.isDone = isDone;
        this.title = title;
        this.description = description;
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
