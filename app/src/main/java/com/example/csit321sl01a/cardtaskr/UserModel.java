package com.example.csit321sl01a.cardtaskr;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.util.Pair;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserModel
{
    //GET FROM DATABASE
    private String userName;
    private String email;
    private float rating;
    private Uri profilePicture;
    private Map<String, Boolean> groups;
    private Map<String, Boolean> tasks;
    private Map<String, String> groupjoinmsg;
    private ArrayList<Pair<String, String>> userGroups = new ArrayList<Pair<String, String>>();
    private ArrayList<Pair<String, String>> userAssignedTasks = new ArrayList<Pair<String, String>>();
    private ArrayList<Pair<String, String>> allUnassignedTasks = new ArrayList<Pair<String, String>>();
    private ArrayList<Pair<String, String>> groupJoinMessages = new ArrayList<Pair<String, String>>();
    private ArrayList<Pair<String, String>> userCardPickTasks = new ArrayList<Pair<String, String>>();


    //not in database
    private ArrayList<String> pickCardMessages; ///comes from unassigned tasks array

    public UserModel() {

    }

    public UserModel(String name, String email, float rating, Uri picture, ArrayList<Pair<String, String>> userGroups,
                     ArrayList<Pair<String, String>> userAssignedTasks, ArrayList<Pair<String, String>> allUnassignedTasks,
                     ArrayList<Pair<String, String>> groupJoinMessages, ArrayList<Pair<String, String>> userCardPickTasks) {

        this.userName = name;
        this.email = email;
        this.rating = rating;
        this.profilePicture = picture;
        this.userGroups = userGroups;
        this. userAssignedTasks = userAssignedTasks;
        this.allUnassignedTasks = allUnassignedTasks;
        this.groupJoinMessages = groupJoinMessages;
        this.userCardPickTasks = userCardPickTasks;

    }

    public void UserModel()
    {
        userName = "No Name";
        email = "No email";
        pickCardMessages = new ArrayList<>();
    }

    public void UserModel(String nm, String eml, Uri im, float rate, ArrayList<String> group, ArrayList<String> card)
    {
        userName =nm;
        email = eml;
        profilePicture = im; //TODO:save this to an image drawable>??
        rating = rate;
        pickCardMessages = new ArrayList<>(card);
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Uri getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Uri profilePicture) {
        this.profilePicture = profilePicture;
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean hasGJMsg()
    {
       //return (groupJoinMessages.isEmpty());
        return true;
    }

    public boolean hasCardMsg()
    {
        //return (pickCardMessages.isEmpty());
        return true;
    }

    public void setGroupjoinmsg(Map<String, String> msg) {
        this.groupjoinmsg = msg;
    }

    public Map<String, String> getGroupjoinmsg() {
        return this.groupjoinmsg;
    }
}
