package com.example.csci342.rockoff;

/**
 * Created by dakeh on 5/29/2016.
 */
public class Group {

    private int numberoftask;
    private int assignedtask;
    private int notassignedtask;
    private String groupname;
    private String admin;

    public Group() {

    }

    public Group(int numberoftask, int assignedtask, int notassignedtask, String groupname, String admin) {
        this.numberoftask = numberoftask;
        this.assignedtask = assignedtask;
        this.notassignedtask = notassignedtask;
        this.groupname = groupname;
        this.admin = admin;
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

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getAdmin() {
        return this.admin;
    }
}
