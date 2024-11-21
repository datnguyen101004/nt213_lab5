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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
//                    User user = new User(name_text, email_text, password_text);
//                    SQLiteConnector db = new SQLiteConnector(Register.this);
//                    if(db.addUser(user)) {
//                        Toast.makeText(Register.this, "Add user successfully", Toast.LENGTH_SHORT).show();
//                    }
                    User user = new User(name_text, email_text, password_text);

                    //Retrofit
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.1.2:8080/") // IP máy tính
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    // Tạo interface API
                    ApiService apiService = retrofit.create(ApiService.class);

                    // Gọi API
                    Call<User> call = apiService.registerUser(user);
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                Toast.makeText(Register.this, "User registered successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Register.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            // Xử lý lỗi khi không thể kết nối hoặc lỗi khác
                            Toast.makeText(Register.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}