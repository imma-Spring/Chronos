package com.chronos.render.game.shapes;

import com.chronos.render.game.shapes.abstractShape.Rectangle;

import java.util.Arrays;

/**
 * Represents an outline of a rectangle shape.
 */
public class RectangleOutline extends Rectangle {
    private final int thickness;

    /**
     * Constructs a rectangle outline with specified width, height, color, and thickness.
     *
     * @param w         The width of the rectangle.
     * @param h         The height of the rectangle.
     * @param color     The color of the rectangle outline.
     * @param thickness The thickness of the outline.
     */
    public RectangleOutline(int w, int h, int color, int thickness) {
        super(new int[w * h], w, h, color);
        this.thickness = thickness;
        fill();
    }

    /**
     * Constructs a rectangle outline with specified width, height, scale, color, and thickness.
     *
     * @param w         The width of the rectangle.
     * @param h         The height of the rectangle.
     * @param scale     The scale of the rectangle.
     * @param color     The color of the rectangle outline.
     * @param thickness The thickness of the outline.
     */
    public RectangleOutline(int w, int h, int scale, int color, int thickness) {
        super(new int[w * h], w, h, scale, color);
        this.thickness = thickness;
        fill();
    }

    /**
     * Fills the rectangle outline with the specified color.
     */
    @Override
    public void fill() {
        Arrays.fill(p, 0);
        for (int i = 0; i < thickness; i++) {
            for (int j = i; j < w - i; j++) {
                p[j + i * w] = color;
            }
            for (int j = i; j < w - i; j++) {
                p[j + (h - 1 - i) * w] = color;
            }
            for (int j = i + 1; j < h - i; j++) {
                p[i + j * w] = color;
            }
            for (int j = i + 1; j < h - i; j++) {
                p[(w - 1 - i) + j * w] = color;
            }
        }
    }

    /**
     * Converts the rectangle outline to a string for printing.
     *
     * @return The string representation of the rectangle outline.
     */
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
