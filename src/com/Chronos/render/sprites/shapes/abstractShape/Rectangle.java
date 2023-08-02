package com.Chronos.render.sprites.shapes.abstractShape;

import com.Chronos.util.vector.Vector2;

public abstract class Rectangle extends Shape {
    public Rectangle(int[] p, int w, int h, int color) {
        super(p, w, h, color);
    }

    public Rectangle(int[] p, int w, int h, int scale, int color) {
        super(p, w, h, scale, color);
    }

    @Override
    public Vector2<Integer> getSize() {
        return new Vector2<>(w, h);
    }
    public int getColor() {
        return color;
    }
}
