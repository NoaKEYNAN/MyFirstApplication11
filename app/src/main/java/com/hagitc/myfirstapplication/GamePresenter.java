package com.hagitc.myfirstapplication;

import android.graphics.Color;
import android.view.MotionEvent;
import android.widget.Toast;

public class GamePresenter
{
    //This class is responsible for the logic of this game.

    private BoardGame boardGame;
    private GameLogic gameLogic;

    public GamePresenter(BoardGame boardGame)
    {
        this.boardGame = boardGame;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            //     int touchedRow = (int) (y / (getHeight() / 6));
            int touchedColumn = (int) (x / (this.boardGame.getWidth() / 7));
            // System.out.println("TOUCHED SQUARE: [" + touchedRow + "][" + touchedColumn + "]");
            int row = gameLogic.userClick(touchedColumn);
            if (row != (-1)) {
                if (gameLogic.getCurrentPlayer() == 1)
                {
                    boardGame.updateBoard(row, touchedColumn, Color.RED);
                    gameLogic.setCounter(gameLogic.getCounter() + 1);
                }
                else
                {
                    boardGame.updateBoard(row, touchedColumn, Color.YELLOW);
                    gameLogic.setCounter(gameLogic.getCounter() + 1);
                }
            }
            if (gameLogic.getCounter() >= 4 && gameLogic.getCounter() <= 42) {
                boolean result = gameLogic.checkForWin();
                if (result == true)
                {
                    int currentplayer1 = 0;
                    if (gameLogic.getCurrentPlayer() == 1)
                    {
                        currentplayer1 = 1;
                    }
                    else
                    {
                        currentplayer1 = 2;
                    }
                    Toast.makeText(this.context, "PLAYER" + currentplayer1 + " WON!", Toast.LENGTH_LONG).show();
                    return true;
                }

            }
        }
        return false;
    }


}
