package com.example.bus_trail.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.bus_trail.Helper_Classes.SessionManager;
import com.example.bus_trail.R;

public class MainActivity extends AppCompatActivity {

    Button next;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        next = findViewById(R.id.nextButton);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.setFirstTimeUser();

                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        sessionManager = new SessionManager(getApplicationContext(),SessionManager.TYPE_LOGINSESSION);
        if(!sessionManager.isFirstTime()) {
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }

    }
}