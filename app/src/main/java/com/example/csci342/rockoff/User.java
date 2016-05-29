package com.example.csci342.rockoff;

import android.net.Uri;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dakeh on 5/29/2016.
 */
public class User {
    private String name;
    private String email;
    private Uri image;
    private int rating;
    Map<String, Boolean> groups;
    Map<String, Boolean> groupadmin;
    Map<String, Boolean> tasks;
    ArrayList<String>join_group_messages;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public Uri getImage() {
        return this.image;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return this.rating;
    }

    public void setTasks(Map<String, Boolean> tasks) {
        this.tasks = tasks;
    }
    public Map<String, Boolean> getTasks () {
        return this.tasks;
    }

    public void addTasks(String taskid) {
        if (tasks == null)
            tasks = new HashMap<>();

        tasks.put(taskid, true);
    }

    public void setGroups(Map<String, Boolean> groups) {
        this.groups = groups;
    }
    public Map<String, Boolean> getGroups () {
        return this.groups;
    }

    public void addGroup(String groupID) {
        if (groups == null)
            groups = new HashMap<>();

        groups.put(groupID, true);
    }

    public ArrayList<String> getJoin_group_messages(){
        return join_group_messages;
    }

    public void setJoin_group_messages(ArrayList<String> messages) {
        this.join_group_messages = messages;
    }

    public void append_join_group_messages(String message) {
        join_group_messages.add(message);
    }

//    public void setGroupAdmin(Map<String, Boolean> groupadmin) {
//        this.groupadmin = groupadmin;
//    }
//
//    public void addGroupAdmin(String groupName) {
//        if (groupadmin == null)
//            groupadmin = new HashMap<>();
//
//        groupadmin.put(groupName, true);
//    }

}
