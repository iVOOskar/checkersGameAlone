package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GameBoard gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GameBoard gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("map/boardMap.txt");
    }


    public void getTileImage() {
        try {
            tile[0] = new Tile(ImageIO.read(new FileInputStream("sprites/blacksquare.png")));

            tile[1] = new Tile(ImageIO.read(new FileInputStream("sprites/redsquare.png")));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void loadMap(String filePath){
        try {

            InputStream is = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < 8 && row < 8) {
                String line = br.readLine();

                while (col < 8) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;

                }
                if (col == 8) {
                    col = 0;
                    row++;
                }
            }

            br.close();
            //System.out.println(Arrays.deepToString(mapTileNum));

        }catch(Exception e) {
            System.out.println(e);
        }
    }



    public void draw(Graphics2D g2D) {
        int currentCol = 0;
        int currentRow = 0;
        //System.out.println("drawing");
        while(currentCol<8 && currentRow<8){
            int tileNum = mapTileNum[currentCol][currentRow];
            int screenX = currentCol * gp.tileSize;
            int screenY = currentRow * gp.tileSize;
            g2D.drawImage(tile[tileNum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
            currentCol++;
            if (currentCol == gp.maxScreenCol){
                currentCol = 0;
                currentRow ++;
            }
        }
    }

}