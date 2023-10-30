package com.chronos.render;

import com.chronos.body.Body;
import com.chronos.engine.Chronos;
import com.chronos.render.game.Sprite;
import com.chronos.util.vector.Vector2;

import java.awt.image.DataBufferInt;
import java.util.Arrays;

/**
 * Represents a screen for rendering graphics.
 */
public class Screen {

    public static final int BLACK = 0xff000000;
    public static final int WHITE = 0xffffffff;

    private final int pW; // Pixel width
    private final int pH; // Pixel height
    private final int[] p; // Pixel array
    private final int[] zB; // Z-buffer (depth buffer)
    public int backgroundColor;
    public static boolean blur; // Is the contents of the screen blurred?

    public final Vector2<Integer> center;

    /**
     * Constructs a screen with default size and color.
     *
     * @param c The Chronos engine instance.
     */
    public Screen(Chronos c) {
        this(100, 100, BLACK, c);
    }

    /**
     * Constructs a screen with custom size and default color.
     *
     * @param w The width of the screen.
     * @param h The height of the screen.
     * @param c The Chronos engine instance.
     */
    public Screen(int w, int h, Chronos c) {
        this(w, h, BLACK, c);
    }

    /**
     * Constructs a screen with custom size and color.
     *
     * @param w     The width of the screen.
     * @param h     The height of the screen.
     * @param color The background color of the screen.
     * @param c     The Chronos engine instance.
     */
    public Screen(int w, int h, int color, Chronos c) {
        backgroundColor = color;
        pW = w;
        pH = h;
        p = ((DataBufferInt) c.getWindow().getImage().getRaster().getDataBuffer()).getData();
        zB = new int[p.length];
        center = new Vector2<>(w / 2, h / 2);
    }

    /**
     * Constructs a screen with custom size and default color.
     *
     * @param size The size of the screen as a 2D vector (width, height).
     * @param c    The Chronos engine instance.
     */
    public Screen(Vector2<Integer> size, Chronos c) {
        this(size.x, size.y, BLACK, c);
    }

    /**
     * Constructs a screen with default size and custom color.
     *
     * @param color The background color of the screen.
     * @param c     The Chronos engine instance.
     */
    public Screen(int color, Chronos c) {
        this(100, 100, color, c);
    }

    /**
     * Constructs a screen with custom size, color, and center position.
     *
     * @param size  The size of the screen as a 2D vector (width, height).
     * @param color The background color of the screen.
     * @param c     The Chronos engine instance.
     */
    public Screen(Vector2<Integer> size, int color, Chronos c) {
        this(size.x, size.y, color, c);
    }

    /**
     * Clears the screen by filling it with the background color.
     */
    public void clear() {
        Arrays.fill(p, backgroundColor);
        Arrays.fill(zB, 0);
    }

    /**
     * Draws a pixel at the specified coordinates with the specified color.
     *
     * @param x     The x-coordinate of the pixel.
     * @param y     The y-coordinate of the pixel.
     * @param color The color of the pixel.
     */
    public void drawPixel(int x, int y, int color) {
        int alpha = ((color >> 24) & 0xff);
        if (x >= pW || x < 0 || y >= pH || y < 0 || alpha == 0 || color == p[x + y * pW]) return;
        p[x + y * pW] = color;
    }

    /**
     * Draws a sprite at the specified position on the screen.
     *
     * @param sprite    The sprite to be drawn.
     * @param position  The position at which the sprite will be drawn.
     */
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
        if (blur) {
            blur();
        }
    }

    /**
     * Blurs the screen
     */
    private void blur() {
        int width = pW;
        int height = pH;
        int[] blurredPixels = new int[width * height];
        int weightsSum = (2  + 1) * (2  + 1);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int totalAlpha = 0;
                int totalRed = 0;
                int totalGreen = 0;
                int totalBlue = 0;

                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        int nx = Math.min(Math.max(x + dx, 0), width - 1);
                        int ny = Math.min(Math.max(y + dy, 0), height - 1);
                        int color = p[nx + ny * width];
                        totalAlpha += (color >>> 24) & 0xFF;
                        totalRed += (color >> 16) & 0xFF;
                        totalGreen += (color >> 8) & 0xFF;
                        totalBlue += color & 0xFF;
                    }
                }

                int avgAlpha = totalAlpha / weightsSum;
                int avgRed = totalRed / weightsSum;
                int avgGreen = totalGreen / weightsSum;
                int avgBlue = totalBlue / weightsSum;

                blurredPixels[x + y * width] = (avgAlpha << 24) | (avgRed << 16) | (avgGreen << 8) | avgBlue;
            }
        }
        System.arraycopy(blurredPixels, 0, p, 0, p.length);
    }

    /**
     * Draws a sprite at the center of the screen.
     *
     * @param sprite The sprite to be drawn.
     */
    public void drawSprite(Sprite sprite) {
        drawSprite(sprite, Vector2.center(center, sprite.getSize()));
    }

    /**
     * Draws a body's sprite at the center of the body's position.
     *
     * @param body The body whose sprite will be drawn.
     */
    public void drawSprite(Body body) {
        drawSprite(body.getSprite(), Vector2.center(body.getPosition().toVec2(), body.getSprite().getSize()));
    }
}