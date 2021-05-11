package com.example.whats_for_dinner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HelpActivity extends AppCompatActivity {

    Button mBack;
    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        mText = findViewById(R.id.helpText_txt);
        mBack = findViewById(R.id.Back_btn);

        mBack.setOnClickListener(v -> startActivity(new Intent(HelpActivity.this , HomeActivity.class)));
    }
}
