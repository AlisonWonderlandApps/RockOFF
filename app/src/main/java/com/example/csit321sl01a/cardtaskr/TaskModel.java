package com.example.csit321sl01a.cardtaskr;


import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskModel implements Serializable
{
    //GET FROM DATABASE
    private String name;                //TASK NAME
    private String groupname;
    private String description;         //TASK DESCRIPTION
    private int imageId;                //NULL FOR NOW - SAVE AS URI THOUGH
    private String taskAssignedTo;          //name of person doing task. Null if not assigned yet
    private boolean taskAssigned;           //is this task assigned to someone yet?? - has everyone picke a card
    private String groupid;
    private ArrayList<CardModel> cardsList; //current cards drawn for this task and their user/owner - set when task created

    public TaskModel()
    {    }

    public TaskModel(String nm, String de, int im, String person, boolean taskAssigned)
    {
        name = nm;
        description = de;
        imageId = im;
        taskAssignedTo = person;
        this.taskAssigned = taskAssigned;
    }

    public String getName() {
        return name;
    }

    public String getTaskAssignedTo() {
        return taskAssignedTo;
    }

    public void setTaskAssignedTo(String taskAssignedTo) {
        this.taskAssignedTo = taskAssignedTo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setTaskAssigned(boolean taskAssigned) {this.taskAssigned = taskAssigned;}

    public boolean getTaskAssigned() {return this.taskAssigned;}

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getGroupid() {
        return this.groupid;
    }

    public void setgroupname(String gname) {
        this.groupname = gname;
    }

    public String getGroupname () {
        return this.groupname;
    }
}
