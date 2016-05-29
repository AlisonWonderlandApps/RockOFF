package com.example.csci342.rockoff;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {
    ArrayList<Task> all_tasks = new ArrayList<Task>();
    ArrayList<String> assigned_person_name = new ArrayList<String>();
    ArrayList<String> assigned_person_uid = new ArrayList<String>();

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        Intent tempIntent = getIntent();

        Button taskadding = (Button) findViewById(R.id.taskadding);

        taskadding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AddTask("Clean the floor", "Clean the floor with your hands", null, false);
//                AddTask("Do nothing", "Slack and do nothing", "MAV20lAoG4gTaqRXnMzjkr0H0SH2", true);
//                AddTask("Eat chicken", "Eat chicken and drink some coffee", null, false);
            }
        });
    }

    private void AddTask(String name, String desc, String assignedto, boolean assigned) {
        Task task = new Task();
        DatabaseReference taskref = database.getReference("Tasks");



        task.setName(name);
        task.setDescription(desc);
        task.setTaskAssignedTo(assignedto);
        task.setAssigned(assigned);

        taskref.push().setValue(task);



    }

    private void getTaskObj() {
        DatabaseReference taskref = database.getReference("Tasks");
        taskref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    all_tasks.add(child.getValue(Task.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getAssignedPerson() {
        DatabaseReference taskref = database.getReference("Tasks");
        taskref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Task task = child.getValue(Task.class);

                    String uid = task.getTaskAssignedTo();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getPersonName(final String uid) {
        DatabaseReference userref = database.getReference("users");
        userref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.child(uid).getValue(User.class);
                assigned_person_name.add(user.getName());
                assigned_person_uid.add(uid);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
