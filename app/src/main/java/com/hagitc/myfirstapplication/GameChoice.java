package com.hagitc.myfirstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class GameChoice extends AppCompatActivity {
    LinearLayout linearLayout;
    BoardGame boardGame;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_choice);
        RoomGame roomgame = new RoomGame();
        roomgame.setStatus("CREATED");
        addRoomToFB(roomgame);

    }
    private void addRoomToFB(RoomGame roomGame)
    {
        FirebaseFirestore fb = FirebaseFirestore.getInstance();
        fb.collection("GameRooms").add(roomGame).addOnSuccessListener(new OnSuccessListener<DocumentReference>()
        {
            @Override
            public void onSuccess(DocumentReference documentReference)
            {
                TextView codeTextView = findViewById(R.id.codeTextView);
                ImageView shareImage = findViewById(R.id.shareicon);
                codeTextView.setText("Your game code is: " + documentReference.getId() + " .Share it with your friends!!!");
                codeTextView.setVisibility(View.VISIBLE);
                shareImage.setVisibility(View.VISIBLE);

                Log.d("ONSUCCESS", "id:" + documentReference.getId());


            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("ONFAILER", "onFailure: " + e.getMessage());
                    }
                });




    }


    public void shareWithFriends(View view) {
    }
}
