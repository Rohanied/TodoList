package com.example.thecontactapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo")
public class Todo {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String title;

    private String description;

    private String date;

    private String time;

    private Boolean isCompleted;

    public Todo(long id, String title, String description, String date, String time, Boolean isCompleted) {
        this.title = title;
        this.id=id;
        this.description = description;
        this.date = date;
        this.time = time;
        this.isCompleted = isCompleted;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public Boolean getIsCompleted() { return isCompleted; }


    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setIsCompleted(Boolean isCompleted){ this.isCompleted = isCompleted; }

}
