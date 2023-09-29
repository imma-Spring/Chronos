package com.chronos.render.game.shapes.abstractShape;

import com.chronos.util.vector.Vector2;

/**
 * Abstract class representing a rectangle shape.
 */
public abstract class Rectangle extends Shape {

    /**
     * Constructs a rectangle with specified parameters.
     *
     * @param p     The position coordinates.
     * @param w     The width of the rectangle.
     * @param h     The height of the rectangle.
     * @param color The color of the rectangle.
     */
    public Rectangle(int[] p, int w, int h, int color) {
        super(p, w, h, color);
    }

    /**
     * Constructs a rectangle with specified parameters.
     *
     * @param p     The position coordinates.
     * @param w     The width of the rectangle.
     * @param h     The height of the rectangle.
     * @param scale The scale of the rectangle.
     * @param color The color of the rectangle.
     */
    public Rectangle(int[] p, int w, int h, int scale, int color) {
        super(p, w, h, scale, color);
    }

    /**
     * Gets the size of the rectangle.
     *
     * @return The size of the rectangle as a Vector2 object.
     */
    @Override
    public Vector2<Integer> getSize() {
        return new Vector2<>(w, h);
    }

    /**
     * Gets the color of the rectangle.
     *
     * @return The color of the rectangle.
     */
    public int getColor() {
        return color;
    }
}
