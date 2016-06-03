package com.example.csit321sl01a.cardtaskr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class CardTasksPendingActivity extends AppCompatActivity
{
    //UI Elements
    Toolbar ctpToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskspending_app_bar);


        initLayout();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.tasks_recycler_root, TasksNotAssignedFragment.newInstance(), "taskList")
                .commit();

    }

    public void initLayout()
    {
        ctpToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(ctpToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        if(id==android.R.id.home)
        {
            this.finish();
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
