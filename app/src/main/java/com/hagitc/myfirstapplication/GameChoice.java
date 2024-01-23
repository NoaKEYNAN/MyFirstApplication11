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

    User creator;

    private String gameId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_choice);

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
                gameId = documentReference.getId();
                codeTextView.setText("Your game code is: " + gameId + " .Share it with your friends!!!");
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

    /*
            RoomGame roomgame = new RoomGame();
        roomgame.setStatus("CREATED");
        addRoomToFB(roomgame);

     */


    public void shareWithFriends(View view)
    {
       // implicit intent - אינטרנט מרומז
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        //this action indicates that you want to send data.
        shareIntent.setType("text/plain"); // for sharing text
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Hello! THIS IS THE CODE FOR" + creator.getName() + "'S GAME." + creator.getName() + "S INVITING YOU TO JOIN!");
        startActivity(Intent.createChooser(shareIntent, "Share using"));



    }

    public void onclickCreateGame(View view) {
        RoomGame roomgame = new RoomGame();
        roomgame.setStatus("CREATED");
        addRoomToFB(roomgame);
        shareWithFriends(view);
    }
}
