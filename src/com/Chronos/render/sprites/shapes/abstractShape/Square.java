package com.Chronos.render.sprites.shapes.abstractShape;

import com.Chronos.render.sprites.shapes.abstractShape.Rectangle;

public abstract class Square extends Rectangle {
    public Square(int[] p, int s, int color) {
        super(p, s, s, color);
    }

    public Square(int[] p, int s, int scale, int color) {
        super(p, s, s, scale, color);
    }
}
