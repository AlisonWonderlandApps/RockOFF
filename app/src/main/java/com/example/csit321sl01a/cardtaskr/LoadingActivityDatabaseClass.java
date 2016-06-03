package com.example.csit321sl01a.cardtaskr;

import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v4.util.Pair;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.api.model.StringList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ObjectOutputStream;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dakeh on 5/31/2016.
 */

public class LoadingActivityDatabaseClass {

    GoogleLoginActivity googleLoginActivity = new GoogleLoginActivity();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();

    private DatabaseReference userref = database.getReference("users");
    private DatabaseReference groupref = database.getReference("groups");
    private DatabaseReference taskref = database.getReference("Tasks");
    private DatabaseReference cardref = database.getReference("cards");

    float userrating = 0;

    ArrayList<String> allgrouid = new ArrayList<String>();
    ArrayList<String> allgroupname = new ArrayList<String>();
    ArrayList<TaskModel> allAtask = new ArrayList<TaskModel>();
    ArrayList<TaskModel> allUAtask = new ArrayList<TaskModel>();
    ArrayList<GroupModel> allgroups = new ArrayList<GroupModel>();
    ArrayList<TaskModel> alltasks = new ArrayList<TaskModel>();

    HashMap<String, String> gidandmsg = new HashMap<String, String>();
    HashMap<String, GroupModel> idtogrouop = new HashMap<String, GroupModel>();
    HashMap<String, TaskModel> idtotask = new HashMap<String, TaskModel>();

    boolean check = false;

    public LoadingActivityDatabaseClass()
    {
        /*
        if(userLoggedIn)
            DashboardActivity.thisUser = getUserModel();
        else(checkPassword)
                ... */
    }

    //USER MODEL
    public UserModel getUserModel()
    {
        add_user_to_database();

        String name = getUserName();
        String email = getUserEmail();
        float rating = getUserRating();
        Uri picture =  getUserPicture();
        ArrayList<Pair<String, String>> userGroups = getUserGroups(); //returns the name and type of the group
        ArrayList<Pair<String, String>> userAssignedTasks = getUserAssignedTasks(); //str1 = taskname, str2 = groupName task belongs to
        ArrayList<Pair<String, String>> allUnassignedTasks = getUnassignedTasks(); //all unassigned tasks from all groups the user is a member of
        ArrayList<Pair<String, String>> groupJoinMessages = getUserGroupJoinMessages(); //group id, message
        ArrayList<Pair<String, String>> userCardPickTasks = getTasksToDrawCardsFor(); //str1 = taskname, str2 = groupName taskbelongs to


        UserModel userModel = new UserModel(name, email, rating, picture, userGroups,
                userAssignedTasks, allUnassignedTasks, groupJoinMessages, userCardPickTasks);

        return userModel;
    }

    private void add_user_to_database() {
        UserModel userModel = new UserModel();
        userModel.setUserName(user.getDisplayName());
        userModel.setRating(3.5f);

        userref.child(user.getUid()).setValue(userModel);
    }

    private String getUserName() {
        return user.getDisplayName();
    }

    private String getUserEmail() {
        return user.getEmail();
    }

    private float getUserRating() {
        return userrating;
    }

    private Uri getUserPicture() {
        return user.getPhotoUrl();
    }

    private ArrayList<Pair<String, String>> getUserGroups() {
        ArrayList<Pair<String, String>> temp = new ArrayList<Pair<String, String>>();
        for (GroupModel group : allgroups) {
            allgroupname.add(group.getGroupName());
            Pair<String, String> usergroup = new Pair<String, String>(group.getGroupName(), group.getGroupType());
            temp.add(usergroup);
        }

        return temp;
    }

    private ArrayList<Pair<String, String>> getUserAssignedTasks() {
        ArrayList<Pair<String, String>> temp = new ArrayList<Pair<String, String>>();
        for (TaskModel task : allAtask) {
            if (task.getTaskAssignedTo().equals(user.getDisplayName())) {
                String temps = retrieve_gname_from_gid(task.getGroupid());
                Pair<String, String> userAtask = new Pair<String, String>(task.getName(), temps);
                Log.d("Check task name", "Value: " + task.getName());
                temp.add(userAtask);
            }
        }

        return temp;
    }

    private ArrayList<Pair<String, String>> getUnassignedTasks() {
        ArrayList<Pair<String, String>> temp = new ArrayList<Pair<String, String>>();
        for (TaskModel task : allUAtask) {
            String temps = retrieve_gname_from_gid(task.getGroupid());
            Pair<String, String> userAtask = new Pair<String, String>(task.getName(), temps);
            temp.add(userAtask);
        }

        return temp;
    }

    private ArrayList<Pair<String, String>> getTasksToDrawCardsFor() {
        ArrayList<Pair<String, String>> temp = new ArrayList<Pair<String, String>>();
        for (String key : idtogrouop.keySet()) {
            for (TaskModel task : alltasks) {
                if (task.getGroupid().equals(key)) {
                    Pair<String, String> data = new Pair<String, String>(task.getName(), idtogrouop.get(key).getGroupName());
                    temp.add(data);
                }
            }
        }

        return temp;
    }

    private ArrayList<Pair<String, String>> getUserGroupJoinMessages() {
        ArrayList<Pair<String, String>> temp = new ArrayList<Pair<String, String>>();

        for (String gid : gidandmsg.keySet()) {
            Pair<String, String> msg = new Pair<String, String>(gid, gidandmsg.get(gid));

            temp.add(msg);
        }

        return temp;
    }

    private void retrieve_from_users() {
        DatabaseReference useruser = database.getReference("/users/" + "/" + user.getUid() + "/");
        useruser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserModel user = dataSnapshot.getValue(UserModel.class);
                Log.d("Check username", "Value: " + user.getUserName());
                userrating = user.getRating();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    private void retrieve_from_groups() {
        groupref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    for (String uid : allgrouid) {
                        if (uid.equals(child.getKey())) {
                            GroupModel group = child.getValue(GroupModel.class);
                            idtogrouop.put(child.getKey(), group);
                            allgroups.add(group);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void retrieve_from_tasks() {
        taskref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    //Log.d("Check key", "Value: " + child.getKey());
                    for (String gid : allgrouid) {
                        final TaskModel task = child.getValue(TaskModel.class);
                        if (gid.equals(task.getGroupid())) {

                            if (task.getTaskAssigned()) {
                                task.setGroupid(gid);
                                allAtask.add(task);
                            }

                            else {
                                task.setGroupid(gid);
                                allUAtask.add(task);
                            }
                        }
                    }


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private String retrieve_gname_from_gid(final String gid) {
        for (String a : idtogrouop.keySet()) {
            if (a.equals(gid)) {
                return idtogrouop.get(a).getGroupName();
            }
        }

        return null;
    }

    public void retrievedata() {
//        HashMap<String, Boolean> map = new HashMap<String, Boolean>();
//        ArrayList<Pair<String, String>> messages = new ArrayList<Pair<String, String>>();
//        Pair<String, String> message = new Pair<String, String>("shouheng tan", "Wasabi");
//        messages.add(message);
//        map.put("-KJ6aXIE3tPIqpfzY4wz", true);
//        map.put("-KJ78hPZJmoewC6di769", true);
//        UserModel user = new UserModel();
//        user.setUserName("kenji");
//        user.setGroups(map);
//        user.setGroupJoinMessages(messages);
//        user.setRating(3.5f);
//
//        userref.push().setValue(user);
//
//        GroupModel group = new GroupModel();
//
//        HashMap<String, Boolean> map = new HashMap<String, Boolean>();
//        map.put(useruid, true);
//        group.setGroupName("SkyLight");
//        group.setGroupType("Sports");
//        group.setMembers(map);
//
//        groupref.push().setValue(group);

//        TaskModel task = new TaskModel();
//        task.setName("task task 2");
//        task.setDescription("test task2");
//        task.setTaskAssigned(false);
//        task.setGroupid("-KJ78hPZJmoewC6di769");
//        task.setTaskAssignedTo(null);
//        taskref.push().setValue(task);

        retrieve_from_users();

        new CountDownTimer(5000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {

            }

            @Override
            public void onFinish()
            {
                retrieve_from_groups();
            }
        }.start();

        new CountDownTimer(10000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {

            }

            @Override
            public void onFinish()
            {
                retrieve_from_tasks();
            }
        }.start();


//        DatabaseReference refref = database.getReference();
//        HashMap<String, String> map = new HashMap<String, String>();
//        HashMap<String, Object> childupdate = new HashMap<String, Object>();
//        map.put("-KJ6aXIE3tPIqpfzY4wz", "Hi, please join our group.");
//        childupdate.put("/users/" + useruid + "/groupjoin/", map);
//        UserModel user = new UserModel();
//        user.setGroupjoinmsg(map);
//        refref.updateChildren(childupdate);
    }

}
