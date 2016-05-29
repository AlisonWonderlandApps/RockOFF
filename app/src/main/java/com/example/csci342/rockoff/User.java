package com.example.csci342.rockoff;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dakeh on 5/29/2016.
 */
public class User {
    private String name;
    private String email;
    private String image;
    Map<String, Boolean> groups;
    Map<String, Boolean> groupadmin;

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

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return this.image;
    }


    public void setGroups(Map<String, Boolean> groups) {
        this.groups = groups;
    }
    public Map<String, Boolean> getGroups () {
        return this.groups;
    }

    public void addGroup(String groupName) {
        if (groups == null)
            groups = new HashMap<>();

        groups.put(groupName, true);
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
