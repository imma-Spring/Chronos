package com.chronos.render.sprites.shapes.abstractShape;

/**
 * Abstract class representing a square, which is a special case of a rectangle.
 */
public abstract class Square extends Rectangle {

    /**
     * Constructs a square with specified parameters.
     *
     * @param p     The position coordinates.
     * @param s     The side length of the square.
     * @param color The color of the square.
     */
    public Square(int[] p, int s, int color) {
        super(p, s, s, color);
    }

    /**
     * Constructs a square with specified parameters.
     *
     * @param p     The position coordinates.
     * @param s     The side length of the square.
     * @param scale The scale of the square.
     * @param color The color of the square.
     */
    public Square(int[] p, int s, int scale, int color) {
        super(p, s, s, scale, color);
    }
}
