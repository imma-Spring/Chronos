package com.chronos.render.game.animation;

import com.chronos.exceptions.ImageNotFoundException;
import com.chronos.render.game.Sprite;
import com.chronos.render.game.Tilemap;

public class Animation extends Tilemap {
    private double lastCallTime;
    float y, x;
    int fps;

    public Animation(String path, int cols, int rows, int fps) throws ImageNotFoundException {
        super(path, cols, rows);
        lastCallTime = 0;
        y = 0;
        x  = 0;
        this.fps = fps;
    }

    public void reset() {
        y = 0;
        x = 0;
    }

    public Sprite getSprite() {
        double currentTime = System.nanoTime() / 1e9; // Convert nanoseconds to seconds
        double timeSinceLastCall = (lastCallTime == 0) ? 0 : currentTime - lastCallTime;
        y+=(float)timeSinceLastCall * fps;
        if ((int)y % getCols() == getCols() - 1)
            x++;

        lastCallTime = currentTime; // Update last call time for next invocation
        return super.getSprite((int)x%getRows(), (int)y%getCols());
    }
}
