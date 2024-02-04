package com.hagitc.myfirstapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private RoomGame roomGame;

    //User creator = new User();

    private String gameId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_choice);

    }
    private void addRoomToFB()
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
                //codeTextView.setText("Hello! THIS IS THE CODE FOR" + creator.getName() + "'S GAME." + creator.getName() + "IS INVITING YOU TO JOIN! THE CODE IS: " + gameId);
                codeTextView.setText("Your game code is: " + gameId + " .Share it with your friends!!!");
                codeTextView.setVisibility(View.VISIBLE);
                shareImage.setVisibility(View.VISIBLE);

                Log.d("ONSUCCESS", "id:" + documentReference.getId());
             //   shareWithFriends(gameId);



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
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Hello! THIS IS THE CODE FOR THE GAME: " + gameId + " JOIN THE GAME! THE CREATOR IS WAITING FOR YOU!");
        startActivityForResult(Intent.createChooser(shareIntent, "Share using"),1);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Intent i = new Intent(this,GameActivity.class);
                i.putExtra("gameId",gameId);
                i.putExtra("player","host");
                startActivity(i);
    }

    public void onclickCreateGame(View view)
    {
        roomGame = new RoomGame();
        roomGame.setStatus("CREATED");
        addRoomToFB();

    }

    public void practicefunction(View view)
    {
        Intent intent = new Intent(GameChoice.this, GameActivity.class);
        startActivity(intent);
    }

    public void joinGame(View view)
    {
        //I need do check in FB if the code exists
        EditText enterCodeText = findViewById(R.id.entercodeET);
        Button clickToJoin = findViewById(R.id.joinroomgameB);
        enterCodeText.setVisibility(View.VISIBLE);
        clickToJoin.setVisibility(View.VISIBLE);

    }

    public void joinIntoAnExistRoomGame(View view)
    {
        //אחרי שהשחקן הוזמן למשחק על ידי משתמש אחר, הוא יכניס את הקוד שהוא קיבל.
        //לאחר שהוא יכניס את הקוד שהוא קיבל הוא ילחץ על הכפתור של הצטרפות למשחק.
        //בפעולה שלהלן הכפתור ישלח את השחקן לחדר משחק בהתאם לקוד שהוא הכניס.
        EditText etCode = findViewById(R.id.entercodeET);
        String gameCode = etCode.getText().toString();
        Intent i = new Intent(this,GameActivity.class);
        i.putExtra("gameId",gameCode);
        i.putExtra("player","other");
        startActivity(i);
    }
}
