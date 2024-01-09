package com.hagitc.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }
    public void addProfile(View view)
    {
        EditText etYear = findViewById(R.id.etUserAge);
        EditText etName = findViewById(R.id.etUserName);

        if(!TextUtils.isEmpty(etYear.getText()) && !TextUtils.isEmpty(etName.getText()))
        {
            int year = Integer.valueOf(etYear.getText().toString());
            String name = etName.getText().toString();
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            String email = mAuth.getCurrentUser().getEmail();

            User user = new User(name, email,year);

            addUsertoFirestote(user);

        }

    }

    private void addUsertoFirestote(User user)
    {
        FirebaseFirestore fb = FirebaseFirestore.getInstance();
        fb.collection("Users").add(user);

    }
}