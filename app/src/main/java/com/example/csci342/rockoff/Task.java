package com.example.csci342.rockoff;

import android.net.Uri;

/**
 * Created by dakeh on 5/30/2016.
 */
public class Task {

    private String name;
    private String description;
    private Uri image;
    private String taskAssignedTo;
    private boolean assigned;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public Uri getImage() {
        return this.image;
    }

    public void setTaskAssignedTo(String taskAssignedTo) {
        this.taskAssignedTo = taskAssignedTo;
    }

    public String getTaskAssignedTo() {
        return this.taskAssignedTo;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public boolean getAssigned() {
        return this.assigned;
    }

}
