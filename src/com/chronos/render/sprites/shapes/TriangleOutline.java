package com.chronos.render.sprites.shapes;

import com.chronos.render.sprites.shapes.abstractShape.Triangle;
import com.chronos.util.vector.Vector2;
import com.chronos.util.vector.Vector3;

/**
 * Represents an outline of a triangle shape.
 */
public class TriangleOutline extends Triangle {

    /**
     * Constructs an outline of a triangle with specified points and color.
     *
     * @param points The vertices of the triangle.
     * @param color  The color of the outline.
     */
    public TriangleOutline(Vector3<Vector2<Integer>> points, int color) {
        super(points, color);
        fill();
    }

    /**
     * Draws the outline of the triangle.
     * This method overrides the abstract method in the parent class.
     * It draws the three sides of the triangle.
     */
    @Override
    public void fill() {
        drawLine(points.x, points.y);
        drawLine(points.x, points.z);
        drawLine(points.y, points.z);
    }

    /**
     * Draws a line between two points.
     *
     * @param point1 The starting point.
     * @param point2 The ending point.
     */
    public void drawLine(Vector2<Integer> point1, Vector2<Integer> point2) {
        int x1 = point1.x;
        int y1 = point1.y;
        int x2 = point2.x;
        int y2 = point2.y;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;
        int err = dx - dy;

        while (true) {
            // Check if the coordinates are within bounds
            if (x1 >= 0 && x1 < w && y1 >= 0 && y1 < h) {
                p[x1 + y1 * w] = color; // Set the grid cell to the specified color
            }

            if (x1 == x2 && y1 == y2) break;

            int e2 = 2 * err;
            if (e2 > -dy) {
                err = err - dy;
                x1 = x1 + sx;
            }
            if (e2 < dx) {
                err = err + dx;
                y1 = y1 + sy;
            }
        }
    }
}
