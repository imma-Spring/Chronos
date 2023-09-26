package com.Chronos.render.sprites.shapes;

import com.Chronos.render.sprites.shapes.abstractShape.Rectangle;

import java.util.Arrays;

public class RectangleOutline extends Rectangle {
    private final int thickness;
    public RectangleOutline(int w, int h, int color, int thickness) {
        super(new int[w * h], w, h, color);
        this.thickness = thickness;
        fill();
    }

    public RectangleOutline(int w, int h, int scale, int color, int thickness) {
        super(new int[w * h], w, h, scale, color);
        this.thickness = thickness;
        fill();
    }

    @Override
    public void fill() {
        Arrays.fill(p, 0);
        for (int i = 0; i < thickness; i++) {
            for (int j = i; j < w - i; j++) {
                p[j + i * w] = color;
            }
            for (int j = i; j < w - i; j++) {
                p[j + (h-1-i) * w] = color;
            }
            for (int j = i + 1; j < h - i; j++) {
                p[i + j * w] = color;
            }
            for (int j = i + 1; j < h - i; j++) {
                p[(w-1-i) + j * w] = color;
            }
        }
    }
    @Override
    public String toString() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (p[j + i * w] == 0)
                    System.out.print(" ");
                else System.out.print("*");
            }
            System.out.println();
        }
        return null;
    }
}
