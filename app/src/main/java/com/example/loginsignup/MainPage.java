package com.example.loginsignup;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.loginsignup.databinding.ActivityMainPageBinding;

public class MainPage extends AppCompatActivity {
    ActivityMainPageBinding binding;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainPageBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sharedPreferences = getSharedPreferences("Auth", MODE_PRIVATE);

        String username = sharedPreferences.getString("name", "");
        String email = sharedPreferences.getString("email", "");
        String gender = sharedPreferences.getString("gender", "");

        binding.username.setText(username);
        binding.email.setText(email);
        binding.gender.setText(gender);

    }
}