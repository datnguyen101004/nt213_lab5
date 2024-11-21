package com.example.app;

import retrofit2.Call;
import com.example.app.entity.User;

public interface ApiService {
    @retrofit2.http.POST("api/v1/user/register")
    Call<User> registerUser(@retrofit2.http.Body User user);

    @retrofit2.http.POST("api/v1/user/login")
    Call<User> loginUser(@retrofit2.http.Body User user);
}
