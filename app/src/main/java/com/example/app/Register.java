package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.sql.SQLiteConnector;
import com.example.app.entity.User;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        EditText name, email, password, matchPassword;
        Button btn_register;

        name = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        matchPassword = findViewById(R.id.matchPassword);
        btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener(v-> {
            String name_text = name.getText().toString();
            String email_text = email.getText().toString();
            String password_text = password.getText().toString();
            String matchPassword_text = matchPassword.getText().toString();
            if (email_text.isEmpty() || password_text.isEmpty() || matchPassword_text.isEmpty()) {
                Toast.makeText(Register.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            } else {
                if (password_text.equals(matchPassword_text)) {
                    User user = new User(name_text, email_text, password_text);
                    SQLiteConnector db = new SQLiteConnector(Register.this);
                    if(db.addUser(user)) {
                        Toast.makeText(Register.this, "Add user successfully", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(Register.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}