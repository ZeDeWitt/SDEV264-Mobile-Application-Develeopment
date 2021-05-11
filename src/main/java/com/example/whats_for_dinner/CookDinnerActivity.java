package com.example.whats_for_dinner;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class CookDinnerActivity extends AppCompatActivity {

    Button mOptions;
    Button mRandomize;
    TextView mChoice;
    String[] cook_dinner;
    boolean[] checkedItems;
    final ArrayList<Integer> mCook = new ArrayList<>();

    Random random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_dinner);

        random = new Random();

        mOptions = findViewById(R.id.options_btn);
        mRandomize = findViewById(R.id.randomize_btn);
        mChoice = findViewById(R.id.takeout_txt);

        cook_dinner = getResources().getStringArray(R.array.cook_dinner);
        checkedItems = new boolean[cook_dinner.length];


        mOptions.setOnClickListener(v -> {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(CookDinnerActivity.this);
            mBuilder.setMultiChoiceItems(cook_dinner, checkedItems, (dialog, which, isChecked) -> {
                if (isChecked) {
                    mCook.add(which);
                } else {
                    mCook.remove((Integer.valueOf(which)));
                }
            });

            mBuilder.setCancelable(false);
            mBuilder.setPositiveButton(R.string.ok_label, (dialogInterface, which) -> {
                String item = "";
                for (int i = 0; i < mCook.size(); i++) {
                    item = item + cook_dinner[mCook.get(i)];
                    if (i != mCook.size() - 1) {
                        item = item + ", ";
                    }
                }

            });


            mBuilder.setNegativeButton(R.string.dismiss_label, (dialogInterface, i) -> dialogInterface.dismiss());

            mBuilder.setNeutralButton(R.string.clear_all_label, (dialogInterface, which) -> {
                for (int i = 0; i < checkedItems.length; i++) {
                    checkedItems[i] = false;
                    mCook.clear();
                    mChoice.setText("");
                }
            });

            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
        });

        mRandomize.setOnClickListener(v -> {

            int value = random.nextInt(mCook.size());
            mChoice.setText(String.valueOf(mCook.get(value)));
        });
    }
}

