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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent intent = getIntent();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser fire = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference useref = database.getReference("users");
        final DatabaseReference groupref = database.getReference("groups");

        User user = new User();
        final Group group = new Group();
        HashMap<String, Boolean> map = new HashMap<String, Boolean>();


        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String c = child.getKey();
                    Log.d("Child ID", c);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Error", "Failed to read value.", databaseError.toException());
            }
        };
        groupref.addValueEventListener(valueEventListener);

        

//
//        map.put("-KIua1BGVpg_mLyC1Swg", true);
//
//        user.setEmail(fire.getEmail());
//        user.setName(fire.getDisplayName());
//        user.setImage(String.valueOf(fire.getPhotoUrl()));
//        user.setGroups(map);
//        useref.push().setValue(user);

    }
}
