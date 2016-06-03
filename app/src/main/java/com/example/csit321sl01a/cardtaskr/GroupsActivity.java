package com.example.csit321sl01a.cardtaskr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

public class GroupsActivity extends AppCompatActivity
            implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener
{
    //UI Elements
    Toolbar groupsToolbar;
    NavigationView groupsNavView;
    DrawerLayout groupsDrawer;
    FloatingActionButton groupsFab, fab1, fab2, fab3;


    Animation fab_open,fab_close,rotate_forward,rotate_backward;
    Boolean isFabOpen = false;
    Animation show_fab_1, show_fab_2, show_fab_3;
    Animation hide_fab_1, hide_fab_2, hide_fab_3;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        initLayout();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.groups_recycler_root, GroupsListFragment.newInstance(), "groupList")
                .commit();
    }

    public void initLayout()
    {
        groupsToolbar = (Toolbar) findViewById(R.id.groupsToolbar);
        setSupportActionBar(groupsToolbar);
        getSupportActionBar().setTitle("My Groups");

     //   groupsFab = (FloatingActionButton) findViewById(R.id.groupsFAB);
//        groupsFab.setOnClickListener(this);

        //userFabMailPopOut = (FloatingActionButton) findViewById(R.id.fabMailPopOut);
        //userFabMailPopOut.setOnClickListener(this);

        groupsDrawer = (DrawerLayout) findViewById(R.id.groups_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, groupsDrawer, groupsToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        groupsDrawer.addDrawerListener(toggle);
        toggle.syncState();

        groupsNavView = (NavigationView) findViewById(R.id.nav_view);
        groupsNavView.setNavigationItemSelectedListener(this);

 /*       fab1 = (FloatingActionButton) findViewById(R.id.fab1compass);
        fab1.setOnClickListener(this);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2places);
        fab2.setOnClickListener(this);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3share);
        fab3.setOnClickListener(this); */

        //initAnims();
    }

    public void initAnims()
    {
        //Floating Action Button Anims
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);
        show_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_show);
        hide_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_hide);
        show_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_show);
        hide_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_hide);
        show_fab_3 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab3_show);
        hide_fab_3 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab3_hide);
    }


    @Override
    public void onBackPressed()
    {
        if (groupsDrawer.isDrawerOpen(GravityCompat.START))
        {
            groupsDrawer.closeDrawer(GravityCompat.START);
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
        getMenuInflater().inflate(R.menu.group_toolbar, menu);
        return true;
    }

    //Toolbar options
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //navdrawer item selected
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        switch(item.getItemId())
        {
            case R.id.dash:
                Intent intent = new Intent(this, DashboardActivity.class);
                startActivity(intent);
                break;

            case R.id.user_profile:
                intent = new Intent(this, UserActivity.class);
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

        groupsDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //fab button menu https://www.sitepoint.com/animating-android-floating-action-button/
    //FAB button
    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.groupsFAB:
                animateFAB();
                break;
            case R.id.fab2places:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }
    }

    //https://www.learn2crack.com/2015/10/android-floating-action-button-animations.html
    public void animateFAB()
    {

        if(isFabOpen)
        {

            groupsFab.startAnimation(rotate_backward);
            hideFABmenu();
            //userFabMailPopOut.startAnimation(fab_close);
            //userFabMailPopOut.setClickable(false);

            isFabOpen = false;
        }
        else
        {

            groupsFab.startAnimation(rotate_forward);
            showFABmenu();
            //userFabMailPopOut.startAnimation(fab_open);
            //userFabMailPopOut.setClickable(true);

            isFabOpen = true;
        }
    }

    public void showFABmenu()
    {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
        layoutParams.rightMargin += (int) (fab1.getWidth() * 1.7);
        layoutParams.bottomMargin += (int) (fab1.getHeight() * 0.25);
        fab1.setLayoutParams(layoutParams);

        layoutParams = (FrameLayout.LayoutParams) fab2.getLayoutParams();
        layoutParams.rightMargin += (int) (fab2.getWidth() * 1.5);
        layoutParams.bottomMargin += (int) (fab2.getHeight() * 1.5);
        fab2.setLayoutParams(layoutParams);

        layoutParams = (FrameLayout.LayoutParams) fab3.getLayoutParams();
        layoutParams.rightMargin += (int) (fab3.getWidth() * 0.25);
        layoutParams.bottomMargin += (int) (fab3.getHeight() * 1.7);
        fab3.setLayoutParams(layoutParams);

        fab1.startAnimation(show_fab_1);
        fab1.setClickable(true);

        fab2.startAnimation(show_fab_2);
        fab2.setClickable(true);

        fab3.startAnimation(show_fab_3);
        fab3.setClickable(true);
    }

    public void hideFABmenu()
    {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
        layoutParams.rightMargin -= (int) (fab1.getWidth() * 1.7);
        layoutParams.bottomMargin -= (int) (fab1.getHeight() * 0.25);
        fab1.setLayoutParams(layoutParams);

        layoutParams = (FrameLayout.LayoutParams) fab2.getLayoutParams();
        layoutParams.rightMargin -= (int) (fab2.getWidth() * 1.5);
        layoutParams.bottomMargin -= (int) (fab2.getHeight() * 1.5);
        fab2.setLayoutParams(layoutParams);

        layoutParams = (FrameLayout.LayoutParams) fab3.getLayoutParams();
        layoutParams.rightMargin -= (int) (fab3.getWidth() * 0.25);
        layoutParams.bottomMargin -= (int) (fab3.getHeight() * 1.7);
        fab3.setLayoutParams(layoutParams);

        fab1.startAnimation(hide_fab_1);
        fab1.setClickable(false);

        fab2.startAnimation(hide_fab_2);
        fab2.setClickable(false);

        fab3.startAnimation(hide_fab_3);
        fab3.setClickable(false);
    }
}

