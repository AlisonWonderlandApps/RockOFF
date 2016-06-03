package com.example.csit321sl01a.cardtaskr;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.*;

public class DashboardActivity extends AppCompatActivity
        implements ViewGroup.OnClickListener, NavigationView.OnNavigationItemSelectedListener //, RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    //UI Elements
    Toolbar dashToolbar;
    DrawerLayout dashDrawer;
    ImageView userProfile;
    TextView userName;
    RatingBar userRating;
    CardView groupJoinMessages;
    CardView cardPickMessages;
    TextView groupJoinText;
    TextView cardPickText;

    //Properties
    UserModel thisUser;
    boolean hasGroupJoinMessages = false;
    boolean hasCardPickTasks = false;

    //Networking & data retrieval


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);



        final LoadingActivityDatabaseClass load = new LoadingActivityDatabaseClass();

        load.retrievedata();
        new CountDownTimer(3000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {

            }

            @Override
            public void onFinish()
            {
                setContentView(R.layout.activity_dash);
                thisUser = load.getUserModel(); ///get the user's saved details
                userRating = (RatingBar) findViewById(R.id.dashRatingBar);
                userRating.setRating(thisUser.getRating());
                Log.d("Check rating", String.valueOf(thisUser.getRating()));
                initLayout();
                checkForMessages();
            }
        }.start();







    }

    public void initLayout()
    {
        dashToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(dashToolbar);
        getSupportActionBar().setTitle("My Profile");

        dashDrawer = (DrawerLayout) findViewById(R.id.dashdrawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, dashDrawer, dashToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dashDrawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.inflateMenu(R.menu.activity_dash_drawer);
        navigationView.setNavigationItemSelectedListener(this);

        userProfile = (ImageView) findViewById(R.id.dashUserPicture);
        Picasso.with(this).load(thisUser.getProfilePicture()).into(userProfile);

        userName = (TextView) findViewById(R.id.dashUserName);
        userName.setText(thisUser.getUserName());




        groupJoinMessages = (CardView) findViewById(R.id.groupJoinCardView);
        cardPickMessages = (CardView) findViewById(R.id.cardPickCardView);
        groupJoinMessages.setOnClickListener(this);
        cardPickMessages.setOnClickListener(this);

        groupJoinText = (TextView) findViewById(R.id.groupJoinText);
        cardPickText = (TextView) findViewById(R.id.cardPickText);

    }

    public void checkForMessages()
    {
        if (thisUser.hasGJMsg())
        {
            hasGroupJoinMessages = true;
            groupJoinText.setText("You have new group invites!");
            //TODO: animate the icon
        }

        if (thisUser.hasCardMsg())
        {
            hasCardPickTasks = true;
            cardPickText.setText("You have tasks to draw cards for!!");
        }
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
        //TODO: change this
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
            case R.id.groupJoinCardView:
                //go to appropriate place
                Intent intent = new Intent(this, GroupInviteActivity.class);
                startActivity(intent);
                //TODO: create this layout
                break;
            case R.id.cardPickCardView:
                //go to appropriate place
                intent = new Intent(this, CardTasksPendingActivity.class);
                startActivity(intent);
                //TODO: create this layout
                break;
        }
    }
}
