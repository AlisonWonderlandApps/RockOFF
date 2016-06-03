package com.example.csit321sl01a.cardtaskr;


import android.net.Uri;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GroupModel
{
    //GET FROM DATABASE!!!!
    private String groupName;           //GROUP NAME
    private String groupType;           //GROUP TYPE (ie sport, school, house)
    private Map<String, Boolean> admin;
    private Map<String, Boolean> members;
    private Map<String, Boolean> assignedtasks;
    private Map<String, Boolean> unassignedtasks;
    private ArrayList<TaskModel> groupTasksAssignedArray;       //group tasks that are assigned else NULL
    private ArrayList<TaskModel> groupTasksUnassignedArray;     //group tasks that are unassigned else NULL
    private ArrayList<String> membersListArray;     //NAMES of all members of the group

    private int groupIcon;              //not in database

    public GroupModel()
    {
        groupName = "No name";
        groupType = "No Type";
        groupIcon = R.drawable.ic_group_flower1;
        groupTasksUnassignedArray = new ArrayList<>();
        groupTasksAssignedArray = new ArrayList<>();
        membersListArray = new ArrayList<>();
    }

    public GroupModel(String nm, String type, int icon, ArrayList<TaskModel> tasksA, ArrayList<TaskModel> tasksUA, ArrayList<String> mem)
    {
        groupName = nm;
        groupType = type;
        groupIcon = icon;
        groupTasksAssignedArray = new ArrayList<>();
//        groupTasksAssignedArray.addAll(tasksA);
        groupTasksUnassignedArray = new ArrayList<>();
    //    groupTasksUnassignedArray.addAll(tasksUA);
        //membersListArray = new ArrayList<>(mem);
    }

    public ArrayList<TaskModel> getGroupTasksAssignedArray()
    {

        return groupTasksAssignedArray;
    }

    public void setGroupTasksAssignedArray(ArrayList<TaskModel> groupTasksAssignedArray) {
        this.groupTasksAssignedArray = groupTasksAssignedArray;
    }

    public ArrayList<TaskModel> getGroupTasksUnassignedArray() {
        return groupTasksUnassignedArray;
    }

    public void setGroupTasksUnassignedArray(ArrayList<TaskModel> groupTasksUnassignedArray) {
        this.groupTasksUnassignedArray = groupTasksUnassignedArray;
    }

    public ArrayList<String> getMembersListArray() {
        return membersListArray;
    }

    public void setMembersListArray(ArrayList<String> membersListArray) {
        this.membersListArray = membersListArray;
    }

    public int getGroupIcon() {

        return groupIcon;
    }

    public void setGroupIcon(int groupIcon) {
        this.groupIcon = groupIcon;
    }

    public String getGroupType() {

        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getGroupName() {

        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setMembers(Map<String, Boolean> members) {
        this.members = members;
    }
    public Map<String, Boolean> getMembers () {
        return this.members;
    }

    public void addMember(String memberID) {
        if (members == null)
            members = new HashMap<>();

        members.put(memberID, true);
    }

    public void setAssignedtasks(Map<String, Boolean> assignedtaskstasks) {
        this.assignedtasks = assignedtaskstasks;
    }
    public Map<String, Boolean> getAssignedtasks () {
        return this.assignedtasks;
    }

    public void addAssignedtasks(String taskid) {
        if (assignedtasks == null)
            assignedtasks = new HashMap<>();

        assignedtasks.put(taskid, true);
    }

    public void setUnassignedtasks(Map<String, Boolean> unassignedtasks) {
        this.unassignedtasks = unassignedtasks;
    }
    public Map<String, Boolean> getUnassignedtasks () {
        return this.unassignedtasks;
    }

    public void addUnassignedtasks(String taskid) {
        if (unassignedtasks == null)
            unassignedtasks = new HashMap<>();

        unassignedtasks.put(taskid, true);
    }

    public void setAdmin(Map<String, Boolean> admin) {
        this.admin = admin;
    }

    public Map<String, Boolean> getAdmin() {
        return this.admin;
    }
}
