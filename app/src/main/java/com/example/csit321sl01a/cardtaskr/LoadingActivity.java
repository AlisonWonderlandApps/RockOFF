package com.example.csit321sl01a.cardtaskr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * App entry point -
 */
public class LoadingActivity extends AppCompatActivity
{
    //login?
    private boolean Islogin = false;
    Context context;

    //UI
    private ProgressBar spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        context = getApplicationContext();


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putBoolean("Islogin", Islogin).apply();

        new CountDownTimer(2000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {

            }

            @Override
            public void onFinish()
            {
                checkLogin();
            }
        }.start();

    }

    public void checkLogin()
    {
        if (Islogin)
        {   // condition true means user is already login
            Intent launchNextActivity = new Intent(this, DashboardActivity.class);
            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(launchNextActivity);
        }
        else
        {
            // condition false take it user on login form
            Intent launchNextActivity = new Intent(context, GoogleLoginActivity.class);
            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(launchNextActivity);
        }
    }

    public void loadUserDetails()
    {
        //uo
    }

    public void setIslogin(boolean islogin) {
        this.Islogin = islogin;
    }
}