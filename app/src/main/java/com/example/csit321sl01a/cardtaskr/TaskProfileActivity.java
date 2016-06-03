package com.example.csit321sl01a.cardtaskr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class TaskProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ViewGroup.OnClickListener
{
    //UI Elements
    Toolbar dashToolbar;
    DrawerLayout dashDrawer;

    TextView taskNameText;
    TextView taskDescText;
    ImageView cardImage;
    Button flipCardButton;
    Button volunteerButton;

    CardModel thisTask;
    CardImageList cardImages;

    //Properties
    String taskName;
    String taskDesc;
    int taskIcon;


    //Networking & data retrieval


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_profile);
        taskName = getIntent().getExtras().getString("name");
        taskDesc = getIntent().getExtras().getString("desc");
        taskIcon = getIntent().getExtras().getInt("icon");

        cardImages = new CardImageList(this);

        initLayout();
    }

    public void initLayout()
    {
        dashToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(dashToolbar);
        getSupportActionBar().setTitle("Pick A Card");

        dashDrawer = (DrawerLayout) findViewById(R.id.task_profile_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, dashDrawer, dashToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dashDrawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.inflateMenu(R.menu.activity_dash_drawer);
        navigationView.setNavigationItemSelectedListener(this);

        taskNameText = (TextView) findViewById(R.id.taskName);
        taskNameText.setText(taskName);
        taskDescText = (TextView) findViewById(R.id.taskDescription);
        taskDescText.setText(taskDesc);
        cardImage = (ImageView) findViewById(R.id.cardImage);
        flipCardButton = (Button) findViewById(R.id.cardButton);
        flipCardButton.setOnClickListener(this);
        volunteerButton = (Button) findViewById(R.id.volunteerButton);
        volunteerButton.setOnClickListener(this);

    }

    @Override
    public void onBackPressed()
    {
        if (dashDrawer.isDrawerOpen(GravityCompat.START))
        {
            dashDrawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            //TODO: go to settings
            Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id==R.id.edit)
        {
            //TODO: go to edit profile view
            Toast.makeText(this, "Edit your Profile selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        switch(item.getItemId())
        {
            case R.id.user_profile:
                Intent intent = new Intent(this, UserActivity.class);
                startActivity(intent);
                break;

            case R.id.group_list:
                intent = new Intent(this, GroupsActivity.class);
                startActivity(intent);
                break;

            case R.id.task_list:
                intent = new Intent(this, TasksActivity.class);
                startActivity(intent);
                break;

            case R.id.calendar:
                intent = new Intent(this, GroupProfileActivity.class);
                startActivity(intent);
                break;

        }

        dashDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //CardView Clicks - goes to appropriate screen
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.cardButton:
                //get the card from the CardModel
                //int cardID = thisTask.getCardID();
                Random rand = new Random();
                int num = rand.nextInt(52);
                int imageID = cardImages.getCardImage(num);

                //TODO: AlphaAnimation fadeIn = new AlphaAnimation();

                cardImage.setImageResource(imageID);
                //flip the card
                //set CardModel cardSeen boolean to true
                //thisTask.setSeenCard(true);
                //change the button to volunteer?? / disable the button
                flipCardButton.setEnabled(false);

                //TODO: create this layout
                break;
            case R.id.volunteerButton:
                //set CardModel card picked to -1 (or flag value so we know someone volunteered)
                //Also set all TaskModel list of CardModels to false - noone else needs to pick a card
                //disable buttons
                break;

        }
    }

}
