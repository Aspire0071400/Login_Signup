package com.example.loginsignup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewbinding.ViewBinding;

import com.example.loginsignup.databinding.ActivitySignupBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SignupActivity extends AppCompatActivity {

    private final List<String> gender_list = new ArrayList<>();
    ActivitySignupBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String name, email, gender, password, rePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences("Auth",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        gender_list.add("Gender");
        gender_list.add("Male");
        gender_list.add("Female");
        gender_list.add("Other");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, gender_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.genderSpinner.setAdapter(adapter);

        binding.existingUserToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentGender = binding.genderSpinner.getSelectedItemPosition();
                name = binding.edtUsername.getText().toString().trim();
                email = binding.edtEmail.getText().toString().trim();
                gender = gender_list.get(currentGender);
                password = binding.edtPass.getText().toString().trim();
                rePassword = binding.edtRePass.getText().toString().trim();

                if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !rePassword.isEmpty()){
                        if(password.equals(rePassword)){

                            editor.putString("name", name);
                            editor.putString("email", email);
                            editor.putString("gender", gender);
                            editor.putString("pass", password);
                            editor.commit();

                            Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(i);
                            finish();

                        }else {
                            Toast.makeText(SignupActivity.this,"Password and re-password doesn't match",Toast.LENGTH_SHORT).show();
                        }
                }else{
                    Toast.makeText(SignupActivity.this,
                            "Fields : name, email, password, re-password can't be empty",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}