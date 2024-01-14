package com.hagitc.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserActivity extends AppCompatActivity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

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
        fb.collection("Users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(UserActivity.this, "SUCCESS",Toast.LENGTH_LONG);
            }
        });

        String uid = mAuth.getCurrentUser().getUid();
        DocumentReference ref = fb.collection("Users").document(uid);

        ref.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(UserActivity.this, "SET SUCCESS", Toast.LENGTH_LONG);
                Intent intent = new Intent(UserActivity.this, BoardGame.class);
                startActivity(intent);

            }
        });

    {
                                                }


    }
}