package com.Chronos.render;

import com.Chronos.util.vector.Vector2;

import java.awt.*;

public class Screen extends Canvas {
    public static final int BLACK = 0xffffffff, WHITE = 0xff000000;
    public int backgroundColor;
    public Screen() {
        this(100, 100, BLACK);
    }
    public Screen(int w, int h) {
        this(w, h, BLACK);
    }
    public Screen(Vector2<Integer> size) {
        this(size.x, size.y, BLACK);
    }

    public Screen(int color) {
        this(100, 100, color);
    }
    public Screen(int w, int h, int color) {
        setSize(w, h);
        backgroundColor = color;
    }
    public Screen(Vector2<Integer> size, int color) {
        this(size.x, size.y, color);
    }
    
    public void clear() {
        for (int i = 0; i < getWidth() * getHeight(); i++) {
            
        }
    }
}
