package com.chronos.render.sprites.shapes.abstractShape;

import com.chronos.util.vector.Vector2;
import com.chronos.util.vector.Vector3;

/**
 * Abstract class representing a triangle, which is a special shape.
 */
public abstract class Triangle extends Shape {
    protected Vector3<Vector2<Integer>> points;

    /**
     * Constructs a triangle with specified points and color.
     *
     * @param points The points of the triangle.
     * @param color  The color of the triangle.
     */
    public Triangle(Vector3<Vector2<Integer>> points, int color) {
        this(points, 1, color);
    }

    /**
     * Constructs a triangle with specified points, scale, and color.
     *
     * @param points The points of the triangle.
     * @param scale  The scale of the triangle.
     * @param color  The color of the triangle.
     */
    public Triangle(Vector3<Vector2<Integer>> points, int scale, int color) {
        super(new int[
                        (points.x.x >= points.y.x && points.x.x >= points.z.x) ? points.x.x + 1 :
                                ((points.y.x >= points.x.x && points.y.x >= points.z.x) ? points.y.x + 1 : points.z.x + 1)
                                        * ((points.x.y >= points.y.y && points.x.y >= points.z.y) ? points.x.y + 1 :
                                        ((points.y.y >= points.x.y && points.y.y >= points.z.y) ? points.y.y + 1 : points.z.y + 1))],
                (points.x.x >= points.y.x && points.x.x >= points.z.x) ? points.x.x + 1 :
                        ((points.y.x >= points.x.x && points.y.x >= points.z.x) ? points.y.x + 1 : points.z.x + 1),
                ((points.x.y >= points.y.y && points.x.y >= points.z.y) ? points.x.y + 1 :
                        (points.y.y >= points.x.y && points.y.y >= points.z.y) ? points.y.y + 1 : points.z.y + 1),
                scale, color);
        this.points = points;
    }

    @Override
    public Vector2<Integer> getSize() {
        return new Vector2<>(w, h);
    }

    public int getColor() {
        return color;
    }
}
