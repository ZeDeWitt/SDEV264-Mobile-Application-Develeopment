package com.example.whats_for_dinner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button mTakeOut;
    Button mDineIn;
    Button mCookDinner;
    Button mHelp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTakeOut = findViewById(R.id.takeOut_btn);
        mDineIn = findViewById(R.id.dineIn_btn);
        mCookDinner = findViewById(R.id.makeDinner_btn);
        mHelp = findViewById(R.id.help_btn);

        mTakeOut.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this , TakeOutActivity.class)));

        mDineIn.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this , DineInActivity.class)));

        mCookDinner.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this , CookDinnerActivity.class)));

        mHelp.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this , HelpActivity.class)));

    }
}
