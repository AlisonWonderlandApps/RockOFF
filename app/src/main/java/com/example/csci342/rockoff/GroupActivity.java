package com.example.csci342.rockoff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class GroupActivity extends AppCompatActivity {
    private String TAG = "Group Activity";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ArrayList<String> memberlist = new ArrayList<String>();
    ArrayList<String> memberuid = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        Intent intent = getIntent();

        Button add_group = (Button) findViewById(R.id.addgroup);

        add_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AddGroup();
                getmemberuid();

            }
        });
    }

    private void AddGroup() {
        Group group = new Group();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference groupref = database.getReference("groups");
        HashMap<String, Boolean> map = new HashMap<String, Boolean>();
        map.put(user.getUid(), true);

        group.setAdmin(user.getDisplayName());
        group.setGroupname("Wasabi");
        group.setGrouptype("Sports");
        group.setAssignedtask(6);
        group.setNumberoftask(10);
        group.setNotassignedtask(4);
        group.setGroups(map);

        groupref.push().setValue(group);

    }

    private void getmemberuid() {
        final DatabaseReference groupref = database.getReference("groups");

        groupref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Group group = child.getValue(Group.class);
                    for (String key : group.getMembers().keySet()) {
                        getmembername(key);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getmembername(final String uid) {
        final DatabaseReference userref = database.getReference("users");

        userref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.child(uid).getValue(User.class);
                memberlist.add(user.getName());
                memberuid.add(uid);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
