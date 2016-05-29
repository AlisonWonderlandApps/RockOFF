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

public class GroupActivity extends AppCompatActivity {
    private String TAG = "Group Activity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        Intent intent = getIntent();

        Button add_group = (Button) findViewById(R.id.addgroup);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final DatabaseReference groupref = database.getReference("groups");
        final Group group = new Group(10, 6, 4, "Wasabi", user.getUid());

        

        add_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupref.push().setValue(group);
            }
        });
    }
}
