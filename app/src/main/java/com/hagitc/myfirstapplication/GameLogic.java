package com.hagitc.myfirstapplication;

public class GameLogic {
    private int arr [][] = new int [6][7];
    private int currentPlayer;

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public GameLogic()
    {//מאפס את כל התאים של המערך הדו מימדי של המונים להיות 0. בהמשך, כאשר המשתמש ילחץ על משבצת והיא תהיה ריקה, אני אעדכן את התא להיות -1
        for(int i=0; i<arr.length; i++)
        {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = 0;
            }
        }
        this.currentPlayer = 1;
    }

    public void switchPlayer ()
    {
        this.currentPlayer= this.currentPlayer * (-1);
    }

    public boolean userClick(int touchedColumn)
    {
        boolean found = false;
        switchPlayer();
        for(int i=6; i>=0 &&!found;i--)
        {
            if(arr[i][touchedColumn] == 0)
            {
                arr[i][touchedColumn] = currentPlayer;
                found = true;
            }
        }
        if (!found)
        {
            switchPlayer();
        }
    }
}
