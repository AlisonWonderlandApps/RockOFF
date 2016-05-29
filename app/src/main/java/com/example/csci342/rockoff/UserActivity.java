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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserActivity extends AppCompatActivity {
    ArrayList<String> groupUID;
    FirebaseDatabase database;
    ArrayList<String> groupname;
    Map<String, Boolean> map;
    User user;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent intent = getIntent();

        database = FirebaseDatabase.getInstance();
        FirebaseUser fire = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference useref = database.getReference("users");
        groupUID = new ArrayList<String>();
        user = new User();
        map = new HashMap<String, Boolean>();

        final Group group = new Group();


        useref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    User user = child.getValue(User.class);
                    for (String key : user.getGroups().keySet()) {
                        String uid = key;
                        display_groupname(uid);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//
//        map.put("-KIua1BGVpg_mLyC1Swg", true);
//
//        user.setEmail(fire.getEmail());
//        user.setName(fire.getDisplayName());
//        user.setImage(String.valueOf(fire.getPhotoUrl()));
//        user.setGroups(map);
//        useref.push().setValue(user);

    }

    private void display_groupname(final String uid) {
        final DatabaseReference groupref = database.getReference("groups");
        groupref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Group group = dataSnapshot.child(uid).getValue(Group.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
