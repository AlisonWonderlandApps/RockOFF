package com.example.csci342.rockoff;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class InviteActivity extends AppCompatActivity {
    ArrayList<String> userUID = new ArrayList<String>();
    DatabaseReference userref;
    private String userinput = null;
    private String senduid = null;
    FirebaseAuth mAuth;
    boolean exist = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invite_activity);
        Intent intent = getIntent();

        final EditText useremail = (EditText) findViewById(R.id.useremail);
        final Button submit = (Button) findViewById(R.id.submitbutton);

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        userref = database.getReference("users");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userinput = useremail.getText().toString().trim();

                ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot child : dataSnapshot.getChildren()) {
                            int index = 0;
                            userUID.add(child.getKey());

                            index++;
                        }

                        checkemail();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                };
                userref.addValueEventListener(valueEventListener);
            }
        });


    }

    private void checkemail() {

        if (userinput != null) {

            for (final String uid : userUID) {
                userref.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);

                        Log.d("Email", user.getEmail());
                        Log.d("Input", userinput);

                        if (userinput.equals(user.getEmail())) {
                            exist = true;
                            if (userinput.equals(mAuth.getCurrentUser().getEmail()))
                            {
                                Toast.makeText(getApplicationContext(), "This is the current user's email", Toast.LENGTH_SHORT).show();
                            }

                            else {
                                senduid = uid;
                                Toast.makeText(getApplicationContext(), "Invited", Toast.LENGTH_SHORT).show();
                                Log.d("Send UID", "Value: " + senduid);
                            }
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            if (exist) {
                Toast.makeText(this, "No such user exist", Toast.LENGTH_SHORT).show();
            }

        }

        else {
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
        }

    }


}
