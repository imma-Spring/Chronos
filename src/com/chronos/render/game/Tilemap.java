package com.chronos.render.game;

import com.chronos.exceptions.ImageNotFoundException;

public class Tilemap extends Sprite {
    private Sprite[][] tilemap;
    private int cols, rows;

    public Tilemap(String path, int cols, int rows) throws ImageNotFoundException {
        super(path);
        System.out.printf("Width: %d, Height: %d\n", w, h); // Add this line to print the dimensions
        this.cols = cols;
        this.rows = rows;
        updateTileMap();
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
        updateTileMap();
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
        updateTileMap();
    }

    private void updateTileMap() {
        tilemap = new Sprite[rows][cols];
        int tileWidth = this.w / cols;
        int tileHeight = this.h / rows;

        for (int spriteY = 0; spriteY < rows; spriteY++) {
            for (int spriteX = 0; spriteX < cols; spriteX++) {
                int[] sp = new int[tileWidth * tileHeight];
                for (int y = 0; y < tileHeight; y++) {
                    for (int x = 0; x < tileWidth; x++) {
                        int pixelX = spriteX * tileWidth + x;
                        int pixelY = spriteY * tileHeight + y;
                        sp[x + y * tileWidth] = this.p[pixelX + pixelY * this.w];
                    }
                }
                tilemap[spriteY][spriteX] = new Sprite(sp, tileWidth, tileHeight);
            }
        }
    }



    public Sprite getSprite(int row, int col) {
        return tilemap[row][col];
    }
}

