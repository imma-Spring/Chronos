package com.Chronos.render.sprites.shapes.abstractShape;

import com.Chronos.util.vector.Vector2;
import com.Chronos.util.vector.Vector3;

public abstract class Triangle extends Shape {
    protected Vector3<Vector2<Integer>> points;
    public Triangle(Vector3<Vector2<Integer>> points, int color) {
        this(points, 1, color);
    }

    public Triangle(Vector3<Vector2<Integer>> points, int scale,  int color) {
        super(new int[
                (points.x.x >= points.y.x && points.x.x >= points.z.x) ? points.x.x + 1 :
                        ((points.y.x >= points.x.x && points.y.x >= points.z.x) ? points.y.x + 1 : points.z.x + 1)
                        * ((points.x.y >= points.y.y && points.x.y >= points.z.y) ? points.x.y + 1 :
                                ((points.y.y >= points.x.y && points.y.y >= points.z.y) ? points.y.y + 1 : points.z.y + 1))],
                (points.x.x >= points.y.x && points.x.x >= points.z.x) ? points.x.x + 1:
                        ((points.y.x >= points.x.x && points.y.x >= points.z.x) ? points.y.x + 1 : points.z.x + 1)
                , ((points.x.y >= points.y.y && points.x.y >= points.z.y) ? points.x.y + 1 :
                        (points.y.y >= points.x.y && points.y.y >= points.z.y) ? points.y.y + 1 : points.z.y + 1), scale, color);
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
