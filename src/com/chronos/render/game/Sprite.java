package com.chronos.render.game;

import com.chronos.exceptions.ImageNotFoundException;
import com.chronos.util.vector.Vector2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents a sprite.
 */
public class Sprite {

    protected int[] p; // Pixel array
    protected int w, h; // Width and height of the sprite

    /**
     * Constructs a sprite from an image file.
     *
     * @param path The file path of the image.
     * @throws ImageNotFoundException If the image file is not found.
     */
    public Sprite(String path) throws ImageNotFoundException {
        this(path, 0, 1);
    }

    /**
     * Constructs a scaled sprite from an image file.
     *
     * @param path   The file path of the image.
     * @param scale  The scale factor.
     * @throws ImageNotFoundException If the image file is not found.
     */
    public Sprite(String path, int scale) throws ImageNotFoundException {
        this(path, 0, scale);
    }

    /**
     * Constructs a sprite from an image file with rotations and scaling.
     *
     * @param path       The file path of the image.
     * @param rotations  The number of clockwise rotations (90-degree increments).
     * @param scale      The scale factor.
     * @throws ImageNotFoundException If the image file is not found.
     */
    public Sprite(String path, int rotations, int scale) throws ImageNotFoundException {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new ImageNotFoundException("Image at file location %s does not exist".formatted(path));
        }

        w = image.getWidth();
        h = image.getHeight();
        p = image.getRGB(0, 0, w, h, null, 0, w);
        image.flush();

        // Apply rotations and scaling
        rotate(rotations);
        scale(scale);
    }

    /**
     * Constructs a sprite from a pixel array with specified width and height.
     *
     * @param p The pixel array.
     * @param w The width of the sprite.
     * @param h The height of the sprite.
     */
    public Sprite(int[] p, int w, int h) {
        this(p, w, h, 1);
    }

    /**
     * Constructs a scaled sprite from a pixel array with specified width and height.
     *
     * @param p     The pixel array.
     * @param w     The width of the sprite.
     * @param h     The height of the sprite.
     * @param scale The scale factor.
     */
    public Sprite(int[] p, int w, int h, int scale) {
        this(p, w, h, 0, scale);
    }

    /**
     * Constructs a rotated and scaled sprite from a pixel array with specified width and height.
     *
     * @param p         The pixel array.
     * @param w         The width of the sprite.
     * @param h         The height of the sprite.
     * @param rotations The number of clockwise rotations (90-degree increments).
     * @param scale     The scale factor.
     */
    public Sprite(int[] p, int w, int h, int rotations, int scale) {
        this.p = p;
        this.w = w;
        this.h = h;
        rotate(rotations);
        scale(scale);
    }

    /**
     * Rotates the sprite by the specified number of clockwise rotations.
     *
     * @param rotations The number of clockwise rotations (90-degree increments).
     */
    public void rotate(int rotations) {
        if(rotations < 0) {
            rotations += 4;
            rotations = Math.abs(rotations);
        }
        int[] m = new int[w * h];
        if(rotations % 4 == 1) {
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    m[y + x * h] = p[x + y * w];
                }
            }
            p = m;
            int q = w;
            w = h;
            h = q;
        } else if (rotations % 4 == 2) {
            for (int y = h - 1, j = 0; j < h; y--, j++) {
                for (int x = w - 1, i = 0; i < w; x--, i++) {
                    m[i + j * w] = p[x + y * w];
                }
            }
            p = m;
        } else if (rotations % 4 == 3) {
            for (int x = w - 1, j = 0; j < w; x--, j++) {
                for (int y = h - 1, i = 0; i < h; y--, i++) {
                    m[i + j * h] = p[x + y * w];
                }
            }
            p = m;
            int q = w;
            w = h;
            h = q;
        }
    }

    /**
     * Scales the sprite by the specified factor.
     *
     * @param scale The scale factor.
     */
    public void scale(double scale) {
        if(scale == 1 || scale == 0) return;
        int[] m = new int[(int) ((w * scale) * (h * scale))];
        for(double y = 0, i = 0; y < h; y += 1.0 / scale, i++) {
            for(double x = 0, j = 0; x < w; x += 1.0 / scale, j++) {
                m[(int)(j + i * (w * scale))] = p[(int)(x + (int)y * w)];
            }
        }
        w *= (int) scale;
        h *= (int) scale;
        p = m;
    }


    /**
     * Gets the width of the sprite.
     *
     * @return The width of the sprite.
     */
    public int w() {
        return w;
    }

    /**
     * Gets the height of the sprite.
     *
     * @return The height of the sprite.
     */
    public int h() {
        return h;
    }

    /**
     * Gets the pixel array of the sprite.
     *
     * @return The pixel array.
     */
    public int[] p() {
        return p;
    }

    /**
     * Gets the size of the sprite as a 2D vector.
     *
     * @return The size of the sprite.
     */
    public Vector2<Integer> getSize() {
        return new Vector2<>(w, h);
    }
}
