package com.company;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int characterSize = 16;
    final int characterScaling = 3;
    public final int tileSize = characterSize*characterScaling;
    final int maxScreenCol = 24; // how big actual window is
    final int maxScreenRow = 16;
    final int screenWidth = maxScreenCol * tileSize;
    final int screenHeight = maxScreenRow * tileSize;

    public final int maxWorldCol = 50; // how big map is
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    Thread gameThread;




    public GameBoard(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setDoubleBuffered(true);
        this.setFocusable(true);

    }
    TileManager TileManager = new TileManager(this);

    public void StartThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000/60;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer =0;


        while (gameThread!= null){

            currentTime = System.nanoTime();
            timer += (currentTime - lastTime);
            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {

                repaint();
                delta--;

            }
            if (timer >= 1000000000){
                //System.out.println("FPS:"+ drawCount);
                timer = 0;
            }
        }
    }

    public void paintComponent(Graphics g2){
        super.paintComponent(g2);
        Graphics2D g2D = (Graphics2D) g2;
        TileManager.draw(g2D);
        g2D.dispose();

    }
}
