package com.chronos.render.game.animation;

import com.chronos.exceptions.ImageNotFoundException;
import com.chronos.render.game.Sprite;
import com.chronos.render.game.Tilemap;

public class Animation extends Tilemap {
    private double lastCallTime;
    private float progress;
    private int fps;

    public Animation(String path, int cols, int rows, int fps) throws ImageNotFoundException {
        super(path, cols, rows);
        lastCallTime = 0;
        progress = 0;
        this.fps = fps;
    }

    public void reset() {
        progress = 0;
    }

    public Sprite getSprite() {
        double currentTime = System.nanoTime() / 1e9; // Convert nanoseconds to seconds
        double timeSinceLastCall = (lastCallTime == 0) ? 0 : currentTime - lastCallTime;

        // Calculate the interpolation factor (t) based on time and fps
        float t = (float) (timeSinceLastCall * fps);

        progress += t;

        // Update last call time for next invocation
        lastCallTime = currentTime;

        int frameIndex = (int) (progress) % (getCols() * getRows());

        return super.getSprite(frameIndex / getCols(), frameIndex % getCols());
    }
}
