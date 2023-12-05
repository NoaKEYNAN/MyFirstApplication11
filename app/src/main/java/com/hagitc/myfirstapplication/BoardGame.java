package com.hagitc.myfirstapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class BoardGame extends View {
    Square [][] squares;
    Context context;
    Paint misgeret;
    Paint fill;

    public BoardGame(Context context)
    {
        super(context);
        this.context = context;
        squares = new Square [6][7];
        misgeret = new Paint();
        misgeret.setStyle(Paint.Style.STROKE);
        misgeret.setColor(Color.BLACK);
        misgeret.setStrokeWidth(10);
        fill = new Paint();
        fill.setColor(Color.WHITE);
    }
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        drawBoard(canvas);
    }
    public void drawBoard(Canvas canvas)
    {
        int x = 0;
        int y = 0;
        int h = canvas.getWidth();
        int w = canvas.getWidth();
        int color;

    }


}
