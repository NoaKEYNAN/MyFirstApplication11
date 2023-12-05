package com.hagitc.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GameActivity extends AppCompatActivity {
    BoardGame boardGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boardGame = new BoardGame(this);
        setContentView(boardGame);
    }
}