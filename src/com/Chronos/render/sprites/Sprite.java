package com.Chronos.render.sprites;

import com.Chronos.exceptions.ImageNotFoundException;
import com.Chronos.util.vector.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Sprite {
    protected int[] p;
    protected int w, h;
    public Sprite(String path) throws ImageNotFoundException {
        this(path, 0, 1);
    }
    public Sprite(String path, int scale) throws ImageNotFoundException {
        this(path, 0, scale);
    }
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

    }

    public Sprite(int[] p, int w, int h) {
        this(p, w, h, 1);
    }

    public Sprite(int[] p, int w, int h, int scale) {
        this(p, w, h, 0, scale);
    }

    public Sprite(int[] p, int w, int h, int rotations, int scale) {
        this.p = p;
        this.w = w;
        this.h = h;
        rotate(rotations);
        scale(scale);
    }

    public void rotate(int rotations) {
        int[] temp = new int[p.length];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {

            }
        }
    }

    public void scale(int scale) {

    }

    public int w() {
        return w;
    }

    public int h() {
        return h;
    }

    public int[] p() {
        return p;
    }

    public Vector2<Integer> getSize() {
        return new Vector2<>(w, h);
    }
}
