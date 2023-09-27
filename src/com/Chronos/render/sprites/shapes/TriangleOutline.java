package com.Chronos.render.sprites.shapes;

import com.Chronos.render.sprites.shapes.abstractShape.Triangle;
import com.Chronos.util.vector.Vector2;
import com.Chronos.util.vector.Vector3;

public class TriangleOutline extends Triangle {


    public TriangleOutline(Vector3<Vector2<Integer>> points, int color) {
        super(points, color);
        fill();
    }

    @Override
    public void fill() {
        drawLine(points.x, points.y);
        drawLine(points.x, points.z);
        drawLine(points.y, points.z);
    }

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
