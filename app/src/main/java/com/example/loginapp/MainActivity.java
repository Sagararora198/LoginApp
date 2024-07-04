package com.example.loginapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    Button loginBtn,signUpbtn;
    FragmentManager fragmentManager  = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initializeView();

    }

    private void initializeView() {
        loginBtn = findViewById(R.id.activity_login);
        signUpbtn = findViewById(R.id.activity_signup);

        loginBtn.setOnClickListener(v-> fragmentManager.beginTransaction()
                .replace(R.id.fragment_container,LoginFragment.class,null)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit());
        signUpbtn.setOnClickListener(v-> fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RegisterFragment.class,null)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit());
    }
}