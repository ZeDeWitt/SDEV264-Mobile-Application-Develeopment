package com.example.whats_for_dinner;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class DineInActivity extends AppCompatActivity {

    Button mOptions;
    Button mRandomize;
    TextView mChoice;
    String[] dine_in;
    boolean[] checkedItems;
    final ArrayList<Integer> mDine = new ArrayList<>();
    Random random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dine_in);

        random = new Random();

        mOptions = findViewById(R.id.options_btn);
        mRandomize = findViewById(R.id.randomize_btn);
        mChoice = findViewById(R.id.takeout_txt);

        dine_in = getResources().getStringArray(R.array.dine_in);
        checkedItems = new boolean[dine_in.length];


        mOptions.setOnClickListener(v -> {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(DineInActivity.this);
            mBuilder.setMultiChoiceItems(dine_in, checkedItems, (dialog, which, isChecked) -> {
                if (isChecked) {
                    mDine.add(which);
                } else {
                    mDine.remove((Integer.valueOf(which)));
                }
            });

            mBuilder.setCancelable(false);
            mBuilder.setPositiveButton(R.string.ok_label, (dialogInterface, which) -> {
                String item = "";
                for (int i = 0; i < mDine.size(); i++) {
                    item = item + dine_in[mDine.get(i)];
                    if (i != mDine.size() - 1) {
                        item = item + ", ";
                    }
                }

            });





            mBuilder.setNegativeButton(R.string.dismiss_label, (dialogInterface, i) -> dialogInterface.dismiss());

            mBuilder.setNeutralButton(R.string.clear_all_label, (dialogInterface, which) -> {
                for (int i = 0; i < checkedItems.length; i++) {
                    checkedItems[i] = false;
                    mDine.clear();

                }
            });

            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
        });

        mRandomize.setOnClickListener(v -> {

            int value = random.nextInt(mDine.size());
            mChoice.setText(String.valueOf(mDine.get(value)));


        });






    }
}