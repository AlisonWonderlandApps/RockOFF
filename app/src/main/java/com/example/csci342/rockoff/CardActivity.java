package com.example.csci342.rockoff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class CardActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ArrayList<Card> cardandplayer = new ArrayList<Card>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        Intent intent = getIntent();

        Random random = new Random();
        final int randnum = random.nextInt(54);

        Button playbutton = (Button) findViewById(R.id.play);
        Button winbutton = (Button) findViewById(R.id.getwin);

        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawcard(randnum);
            }
        });

        winbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_all_the_cards();
            }
        });


    }

    public void drawcard(int randnum) {
        Card card = new Card();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference cardref = database.getReference("cards");

        card.setCardnum(randnum);
        card.setUid(mAuth.getCurrentUser().getUid());

        cardref.push().setValue(card);
    }

    private void get_all_the_cards() {
        DatabaseReference cardref = database.getReference("cards");

        cardref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    cardandplayer.add(child.getValue(Card.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void findwinner() {

    }
}
