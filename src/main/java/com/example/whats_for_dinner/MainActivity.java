package com.example.whats_for_dinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {


    private EditText mEmail , mPass;
    private FirebaseAuth mAuth;
    Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = findViewById(R.id.email_login);
        mPass = findViewById(R.id.password_login);
        Button signInBtn = findViewById(R.id.btn_login);
        signUpBtn = findViewById(R.id.signup_btn);

        mAuth = FirebaseAuth.getInstance();

        signUpBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this , RegistrationActivity.class)));

        signInBtn.setOnClickListener(v -> loginUser());

    }
    private void loginUser(){
        String email = mEmail.getText().toString();
        String pass = mPass.getText().toString();

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if (!pass.isEmpty()){
                mAuth.signInWithEmailAndPassword(email , pass)
                        .addOnSuccessListener(authResult -> {
                            Toast.makeText(MainActivity.this, "Login Successful !!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this , HomeActivity.class));
                            finish();
                        }).addOnFailureListener(e -> Toast.makeText(MainActivity.this, "Login Failed !!", Toast.LENGTH_SHORT).show());
            }else{
                mPass.setError("Empty Fields Are not Allowed");
            }
        }else if(email.isEmpty()){
            mEmail.setError("Empty Fields Are not Allowed");
        }else{
            mEmail.setError("Pleas Enter Correct Email");
        }
    }

}
