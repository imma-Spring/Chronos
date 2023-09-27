package com.chronos.render.sprites.shapes.abstractShape;

import com.chronos.render.sprites.Sprite;
import com.chronos.util.vector.Vector2;

/**
 * Abstract class representing a shape.
 */
public abstract class Shape extends Sprite {

    // Color of the shape
    protected int color;

    /**
     * Constructs a shape with specified parameters.
     *
     * @param p     The position coordinates.
     * @param w     The width of the shape.
     * @param h     The height of the shape.
     * @param color The color of the shape.
     */
    public Shape(int[] p, int w, int h, int color) {
        super(p, w, h);
        this.color = color;
    }

    /**
     * Constructs a shape with specified parameters.
     *
     * @param p     The position coordinates.
     * @param w     The width of the shape.
     * @param h     The height of the shape.
     * @param scale The scale of the shape.
     * @param color The color of the shape.
     */
    public Shape(int[] p, int w, int h, int scale, int color) {
        super(p, w, h, scale);
        this.color = color;
//        fill(); // Uncomment if 'fill' functionality is implemented.
    }

    /**
     * Constructs a shape with specified parameters.
     *
     * @param p          The position coordinates.
     * @param w          The width of the shape.
     * @param h          The height of the shape.
     * @param rotations  The number of rotations.
     * @param scale      The scale of the shape.
     * @param color      The color of the shape.
     */
    public Shape(int[] p, int w, int h, int rotations, int scale, int color) {
        super(p, w, h, rotations, scale);
        this.color = color;
//        fill(); // Uncomment if 'fill' functionality is implemented.
    }

    /**
     * Fills the shape with color.
     * (This method should be implemented by subclasses)
     */
    public abstract void fill();

    /**
     * Gets the size of the shape.
     *
     * @return The size of the shape as a Vector2 object.
     */
    @Override
    public abstract Vector2<Integer> getSize();

    /**
     * Gets the color of the shape.
     *
     * @return The color of the shape.
     */
    public int getColor() {
        return color;
    }
}
