package com.hagitc.myfirstapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.icu.text.RelativeDateTimeFormatter;
import android.view.MotionEvent;
import android.view.View;

public class BoardGame extends View
{
    Square [][] squares;
    Context context;
    Paint misgeret;
    Paint fill;
    GameLogic g = new GameLogic();
    MyCircle circle1;
    MyCircle circle2;



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
    public void SetFill(int color)
    {
        fill.setColor(color);
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        drawBoard(canvas);//מצייר לוח ריק
    }

    public void drawBoard(Canvas canvas)
    {
        int x = 0;
        int y = 0;
        int w = canvas.getWidth()/7;
        int h = canvas.getWidth()/6;

        for(int i=0; i<squares.length; i++)
        {
            for(int j=0;j<squares[0].length; j++)
            {
                misgeret = new Paint();
                misgeret.setStyle(Paint.Style.STROKE);
                misgeret.setColor(Color.BLACK);
                misgeret.setStrokeWidth(10);


                squares[i][j] = new Square(this,x,y,w,h,  misgeret);
                squares[i][j].draw(canvas);
                x = x + w;
            }
            y = y + h;
            x = 0;
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            int touchedRow = (int) (y / (getHeight() / 6));
            int touchedColumn = (int) (x / (getWidth() / 7));
            System.out.println("TOUCHED SQUARE: [" + touchedRow + "][" + touchedColumn + "]");
            int row = g.userClick(touchedColumn);
            if (row != (-1)) {
                if (g.getCurrentPlayer() == 1) {

                    circle1 = new MyCircle(touchedRow, touchedColumn, 20, 65536);
                    invalidate();
                }
                else
                {
                    circle2 = new MyCircle(touchedRow, touchedColumn, 20, 256);
                    invalidate();
                }
            }
        }
        return true;

    }

}
