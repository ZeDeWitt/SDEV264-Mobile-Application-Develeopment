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

public class TakeOutActivity extends AppCompatActivity {

    Button mOptions;
    Button mRandomize;
    TextView mChoice;
    String[] take_out;
    boolean[] checkedItems;
    final ArrayList<Integer> mUserItems = new ArrayList<>();


    Random random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_out);

        random = new Random();

        mOptions = findViewById(R.id.options_btn);
        mRandomize = findViewById(R.id.randomize_btn);
        mChoice = findViewById(R.id.takeout_txt);

        take_out = getResources().getStringArray(R.array.take_out);
        checkedItems = new boolean[take_out.length];


        mOptions.setOnClickListener(v -> {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(TakeOutActivity.this);
            mBuilder.setMultiChoiceItems(take_out, checkedItems, (dialog, which, isChecked) -> {
                if (isChecked) {
                    mUserItems.add(which);
                } else {
                    mUserItems.remove((Integer.valueOf(which)));
                }
            });

            mBuilder.setCancelable(false);
            mBuilder.setPositiveButton(R.string.ok_label, (dialogInterface, which) -> {
                String item = "";
                for (int i = 0; i < mUserItems.size(); i++) {
                    item = item + take_out[mUserItems.get(i)];
                    if (i != mUserItems.size() - 1) {
                        item = item + ", ";
                    }
                }

            });





            mBuilder.setNegativeButton(R.string.dismiss_label, (dialogInterface, i) -> dialogInterface.dismiss());

            mBuilder.setNeutralButton(R.string.clear_all_label, (dialogInterface, which) -> {
                for (int i = 0; i < checkedItems.length; i++) {
                    checkedItems[i] = false;
                    mUserItems.clear();

                }
            });

            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
        });

        mRandomize.setOnClickListener(v -> {

            int value = random.nextInt(mUserItems.size());
            mChoice.setText(String.valueOf(mUserItems.get(value)));
            System.out.println(mUserItems.get(value));


        });






    }
}



