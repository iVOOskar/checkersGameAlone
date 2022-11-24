package com.company;

public class Main {

    public static void main(String[] args) {
        GameBoard gb = new GameBoard();
        Screen gameScreen = new Screen();
        gameScreen.add(gb);
        gameScreen.pack();
        gameScreen.setLocationRelativeTo(null);
        gameScreen.setVisible(true);


        gb.StartThread();


    }
}
