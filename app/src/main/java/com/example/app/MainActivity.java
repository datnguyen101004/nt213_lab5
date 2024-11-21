package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.sql.SQLiteConnector;
import com.example.app.entity.User;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        Button login = findViewById(R.id.btn_login);
        Button register = findViewById(R.id.btn_register);
        //SQLiteConnector db = new SQLiteConnector(this);

        register.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, Register.class);
            startActivity(intent);
        });

        View.OnClickListener loginEvent = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                boolean result = db.checkUser(username.getText().toString(), password.getText().toString());
//                if (result){
//                    Intent intent = new Intent(MainActivity.this,User.class);
//                    startActivity(intent);
//                }else{
//                    Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
//                }

                User user = new User();
                user.setName(username.getText().toString());
                user.setPassword(password.getText().toString());

                //Retrofit
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.2:8080/") // IP máy tính
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiService apiService = retrofit.create(ApiService.class);

                //Gọi api
                Call<User> call = apiService.loginUser(user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Toast.makeText(MainActivity.this, "Login successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, com.example.app.User.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Username or password is invalid", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        // Xử lý lỗi khi không thể kết nối hoặc lỗi khác
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        login.setOnClickListener(loginEvent);
    }
}