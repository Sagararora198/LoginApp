package com.example.loginapp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomePage extends AppCompatActivity {
    TextView nameField,emailField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
        initializeView();


    }

    private void initializeView() {
        nameField = findViewById(R.id.show_name);
        emailField = findViewById(R.id.show_email);
        String username = getIntent().getStringExtra("username");
        String email = getIntent().getStringExtra("email");
        nameField.setText(username);
        emailField.setText(email);

    }
}