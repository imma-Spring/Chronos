package com.chronos.render.game.shapes;

import com.chronos.render.game.shapes.abstractShape.Triangle;
import com.chronos.util.vector.Vector2;
import com.chronos.util.vector.Vector3;

/**
 * Represents a solid filled triangle shape.
 */
public class SolidTriangle extends Triangle {

    /**
     * Constructs a solid filled triangle with specified points and color.
     *
     * @param points The vertices of the triangle.
     * @param color  The color of the triangle.
     */
    public SolidTriangle(Vector3<Vector2<Integer>> points, int color) {
        super(points, color);
        fill();
    }

    /**
     * Fills the triangle shape.
     * This method overrides the abstract method in the parent class.
     * It draws the three sides of the triangle and then fills it.
     */
    @Override
    public void fill() {
        drawLine(points.x, points.y);
        drawLine(points.x, points.z);
        drawLine(points.y, points.z);
        fillTriangle();
    }

    /**
     * Draws a line between two points.
     *
     * @param point1 The starting point.
     * @param point2 The ending point.
     */
    private void drawLine(Vector2<Integer> point1, Vector2<Integer> point2) {
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

    /**
     * Fills the pixels inside the triangle.
     *
     * This method uses scanline algorithm to fill the pixels inside the triangle.
     */
    private void fillTriangle() {
        int minY = smallestPointY();
        int maxY = largestPointY();

        for (int y = minY; y < maxY; y++) {
            int startX = Integer.MAX_VALUE;
            int endX = Integer.MIN_VALUE;

            // Find the starting and ending points for this scanline
            for (int x = 0; x < w; x++) {
                if (p[x + y * w] == color) {
                    if (x < startX) startX = x;
                    if (x > endX) endX = x;
                }
            }

            // Fill the pixels in this scanline
            for (int x = startX; x <= endX; x++) {
                p[x + y * w] = color;
            }
        }
    }

    /**
     * Returns the largest Y-coordinate among the triangle's points.
     *
     * @return The largest Y-coordinate.
     */
    private int largestPointY() {
        if (points.x.y >= points.y.y && points.x.y >= points.z.y)
            return points.x.y;
        if (points.y.y >= points.x.y && points.y.y >= points.z.y)
            return points.y.y;
        if (points.z.x >= points.x.y)
            return points.z.y;
        else return -1;
    }

    /**
     * Returns the smallest Y-coordinate among the triangle's points.
     *
     * @return The smallest Y-coordinate.
     */
    private int smallestPointY() {
        if (points.x.y <= points.y.y && points.x.y <= points.z.y)
            return points.x.y;
        if (points.y.y <= points.x.y && points.y.y <= points.z.y)
            return points.y.y;
        return points.z.y;
    }
}
