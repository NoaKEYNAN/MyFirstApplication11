package com.hagitc.myfirstapplication;

import android.graphics.Color;
import android.view.MotionEvent;
import android.widget.Toast;

public class GamePresenter
{
    //This class is responsible for the logic of this game.

    private BoardGame boardGame;
    private GameLogic gameLogic;

    public GamePresenter(BoardGame boardGame, GameLogic gameLogic)
    {
        this.boardGame = boardGame;
        this.gameLogic = gameLogic;
    }
    public void userClick(int column)
    {
        int row = gameLogic.userClick(column);
        if (row != -1)
        {
            if (row != (-1))
            {
                if (gameLogic.getCurrentPlayer() == 1)
                {
                    boardGame.updateBoard(row,column,Color.RED);
                    gameLogic.setCounter(gameLogic.getCounter()+1);
                }
                else
                {
                    boardGame.updateBoard(row,column,Color.YELLOW);
                    gameLogic.setCounter(gameLogic.getCounter()+1);
                }
            }
            if (gameLogic.getCounter() >= 4 && gameLogic.getCounter()<=42)
            {
                boolean result = gameLogic.checkForWin();
                if (result == true) {
                    int currentplayer1 = 0;
                    if (gameLogic.getCurrentPlayer() == 1) {
                        currentplayer1 = 1;
                    } else {
                        currentplayer1 = 2;
                    }
                    boardGame.displayMessage("PLAYER" + currentplayer1 + " WON!");


                }
            }
        else
        {
            this.boardGame.displayMessage("MOSAHE SAVIDS");
        }
                if(gameLogic.isBoardFull()==true)
                {
                    this.boardGame.displayMessage("TIE");
                }
    }