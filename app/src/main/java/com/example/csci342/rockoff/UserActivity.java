package com.example.csci342.rockoff;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserActivity extends AppCompatActivity {
    ArrayList<String> groupUID;
    ArrayList<String> groupname;

    ArrayList<String> taskname;
    ArrayList<String> taskUID;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    Map<String, Boolean> map;

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent intent = getIntent();

        //AddGoogleUser();
        //getUserGroupName();
        //getUserObj();

    }

    private void get_groupname(final String uid) {
        final DatabaseReference groupref = database.getReference("groups");
        groupref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Group group = dataSnapshot.child(uid).getValue(Group.class);
                groupUID.add(uid);
                groupname.add(group.getGroupname());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void getUserGroupName() {
        final DatabaseReference useref = database.getReference("users");
        useref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    User user = child.getValue(User.class);
                    for (String key : user.getGroups().keySet()) {
                        String uid = key;
                        get_groupname(uid);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void AddGoogleUser() {
        User user = new User();
        FirebaseUser fire = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference useref = database.getReference("users");
        ArrayList<String> messages = new ArrayList<String>(Arrays.asList("Hello, you will be joining team Wasabi!"));
        map = new HashMap<String, Boolean>();
        //TODO Let user choose which team to join
        map.put("-KIxA84u6rt-iKw6H1SF", true);

        user.setEmail(fire.getEmail());
        user.setName(fire.getDisplayName());
        user.setRating(4);
        user.setJoin_group_messages(messages);
        user.setGroups(map);
        useref.child(fire.getUid()).setValue(user);
    }

    private void getUserObj() {
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final DatabaseReference useref = database.getReference("users");
        useref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.child(mAuth.getCurrentUser().getUid()).getValue(User.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
