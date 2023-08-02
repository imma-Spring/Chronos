package com.Chronos.render.sprites.shapes;

import com.Chronos.render.sprites.shapes.abstractShape.Rectangle;

import java.util.Arrays;

public class SolidRectangle extends Rectangle {
    public SolidRectangle(int w, int h, int color) {
        super(new int[w * h], w, h, color);
        fill();
    }

    @Override
    public void fill() {
        Arrays.fill(p, color);
    }


    public SolidRectangle(int w, int h, int scale, int color) {
        super(new int[w * h], w, h, scale, color);
        fill();
    }


}
