package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        EditText username, password, matchPassword;
        Button btn_register;

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        matchPassword = findViewById(R.id.matchPassword);
        btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener(v-> {
            String username_text = username.getText().toString();
            String password_text = password.getText().toString();
            String matchPassword_text = matchPassword.getText().toString();
            if (username_text.isEmpty() || password_text.isEmpty() || matchPassword_text.isEmpty()) {
                Toast.makeText(Register.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            } else {
                if (password_text.equals(matchPassword_text)) {
                    Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}