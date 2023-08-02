package com.Chronos.render.sprites.shapes.abstractShape;

import com.Chronos.render.sprites.Sprite;
import com.Chronos.util.vector.Vector2;

public abstract class Shape extends Sprite {
    protected int color;
    public Shape(int[] p, int w, int h, int color) {
        super(p, w, h);
        this.color = color;
    }

    public Shape(int[] p, int w, int h, int scale, int color) {
        super(p, w, h, scale);
        this.color = color;
        fill();
    }

    public Shape(int[] p, int w, int h, int rotations, int scale, int color) {
        super(p, w, h, rotations, scale);
        this.color = color;
        fill();
    }

    public abstract void fill();
    @Override
    public abstract Vector2<Integer> getSize();
}
