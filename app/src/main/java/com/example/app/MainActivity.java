package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText username = findViewById(R.id.editUsername);
        EditText password = findViewById(R.id.editPassword);
        Button login = findViewById(R.id.btn_login);
        Button register = findViewById(R.id.btn_register);

        register.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, Register.class);
            startActivity(intent);
        });

        View.OnClickListener loginEvent = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("root")){
                    Intent intent = new Intent(MainActivity.this, User.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        };

        login.setOnClickListener(loginEvent);
    }
}