package com.hagitc.myfirstapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Square
{
    BoardGame boardGame;
    float x, y, w, h;
    private Paint p;
    public Square (BoardGame boardGame, float x, float y, float w, float h,Paint p)
    {
        this.boardGame = boardGame;
        this.x = x;
        this.y = y;
        this.boardGame = boardGame;
        this.p = p;
        this.w = w;
        this.h = h;
    }

    public void changeColor(int c)
    {
        p.setColor(c);

    }


    public void draw (Canvas canvas)
    {
        canvas.drawRect(x,y, x+w, y+h, p);
    }




}
