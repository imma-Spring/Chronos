package com.Chronos.render;

import com.Chronos.body.Body;
import com.Chronos.engine.Chronos;
import com.Chronos.render.sprites.Sprite;
import com.Chronos.util.vector.Vector2;

import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class Screen {
    public static final int BLACK = 0xff000000, WHITE = 0xffffffff;
    private final int pW, pH;
    private final int[] p;
    private int[] zB;
    public int backgroundColor;

    public final Vector2<Integer> center;

    public Screen(Chronos c) {
        this(100, 100, BLACK, c);
    }

    public Screen(int w, int h, Chronos c) {
        this(w, h, BLACK, c);
    }

    public Screen(Vector2<Integer> size, Chronos c) {
        this(size.x, size.y, BLACK, c);
    }

    public Screen(int color, Chronos c) {
        this(100, 100, color, c);
    }

    public Screen(int w, int h, int color, Chronos c) {
        backgroundColor = color;
        pW = w;
        pH = h;
        p = ((DataBufferInt) c.getWindow().getImage().getRaster().getDataBuffer()).getData();
        zB = new int[p.length];
        center = new Vector2<>(w / 2, h / 2);
    }

    public Screen(Vector2<Integer> size, int color, Chronos c) {
        this(size.x, size.y, color, c);
    }

    public void clear() {
        Arrays.fill(p, backgroundColor);
        Arrays.fill(zB, 0);
    }

    public void drawPixel(int x, int y, int color) {
        int alpha = ((color >> 24) & 0xff);
        if (x >= pW || x < 0 || y >= pH || y < 0 || alpha == 0 || color == p[x + y * pW]) return;
        p[x + y * pW] = color;
    }

    public void drawSprite(Sprite sprite, Vector2<Integer> position) {
        int newX = 0;
        int newY = 0;
        int newWidth = sprite.w();
        int newHeight = sprite.h();
        if (newWidth + position.x > pW) newWidth -= newWidth + position.x - pW;
        if (newHeight + position.y > pH) newHeight -= newHeight + position.y - pH;

        if (position.x < 0) newX -= position.x;
        if (position.y < 0) newY -= position.y;


        if (position.x < -newWidth) return;
        if (position.y < -newHeight) return;
        if (position.x >= pW) return;
        if (position.y >= pH) return;

        for (int i = newY; i < newHeight; i++)
            for (int j = newX; j < newWidth; j++) {
                int x = position.x + j, y = position.y + i;
                drawPixel(x, y, sprite.p()[j + i * sprite.w()]);
            }
    }

    public void drawSprite(Sprite sprite) {
        drawSprite(sprite, Vector2.center(center, sprite.getSize()));
    }

    public void drawSprite(Body body) {
        drawSprite(body.getSprite(), Vector2.center(body.getPosition().toVec2(), body.getSprite().getSize()));
    }
}
