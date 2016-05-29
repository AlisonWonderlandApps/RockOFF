package com.example.csci342.rockoff;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dakeh on 5/29/2016.
 */
public class Group {

    private int numberoftask;
    private int assignedtask;
    private int notassignedtask;
    private String grouptype;
    private String groupname;
    private String admin;
    private Map<String, Boolean> members;

    public Group() {

    }



    public void setNumberoftask(int numberoftask) {
        this.numberoftask = numberoftask;
    }

    public int getNumberoftask() {
        return this.numberoftask;
    }

    public void setAssignedtask(int assignedtask) {
        this.assignedtask = assignedtask;
    }

    public int getAssignedtask() {
        return this.assignedtask;
    }

    public void setNotassignedtask(int notassignedtask) {
        this.notassignedtask = notassignedtask;
    }

    public int getNotassignedtask() {
        return this.notassignedtask;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getGroupname() {
        return this.groupname;
    }

    public void setGroups(Map<String, Boolean> members) {
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

    public void setGrouptype(String grouptype) {
        this.grouptype = grouptype;
    }

    public String getGrouptype() {
        return this.grouptype;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getAdmin() {
        return this.admin;
    }
}
