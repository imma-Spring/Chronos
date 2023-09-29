package com.chronos.render.game.shapes;

import com.chronos.render.game.shapes.abstractShape.Rectangle;

import java.util.Arrays;

/**
 * Represents a solid filled rectangle shape.
 */
public class SolidRectangle extends Rectangle {

    /**
     * Constructs a solid filled rectangle with specified width, height, and color.
     *
     * @param w     The width of the rectangle.
     * @param h     The height of the rectangle.
     * @param color The color of the rectangle.
     */
    public SolidRectangle(int w, int h, int color) {
        super(new int[w * h], w, h, color);
        fill();
    }

    /**
     * Fills the rectangle with the specified color.
     */
    @Override
    public void fill() {
        Arrays.fill(p, color);
    }

    /**
     * Constructs a solid filled rectangle with specified width, height, scale, and color.
     *
     * @param w     The width of the rectangle.
     * @param h     The height of the rectangle.
     * @param scale The scale of the rectangle.
     * @param color The color of the rectangle.
     */
    public SolidRectangle(int w, int h, int scale, int color) {
        super(new int[w * h], w, h, scale, color);
        fill();
    }
}
