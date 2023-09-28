package com.chronos.render;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Represents the game window.
 */
public class Window extends JFrame {

    private final BufferedImage image;
    private final Canvas screen;
    private final BufferStrategy bs;
    private final Graphics g;

    /**
     * Constructs a game window with specified parameters.
     *
     * @param game  The name of the game.
     * @param w     The width of the window.
     * @param h     The height of the window.
     * @param scale The scaling factor.
     */
    public Window(String game, int w, int h, int scale) {
        super(game);

        // Initialize image, canvas, and buffer strategy
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        screen = new Canvas();
        screen.setPreferredSize(new Dimension(w * scale, h * scale));
        screen.setMaximumSize(new Dimension(w * scale, h * scale));
        screen.setMinimumSize(new Dimension(w * scale, h * scale));

        // Set up window properties
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(screen, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        // Create buffer strategy
        screen.createBufferStrategy(2);
        screen.requestFocusInWindow();
        bs = screen.getBufferStrategy();
        g = bs.getDrawGraphics();
    }

    /**
     * Updates the window with the current image.
     */
    public void update() {
        g.drawImage(image, 0, 0, screen.getWidth(), screen.getHeight(), null);
        bs.show();
    }

    /**
     * Gets the buffered image of the window.
     *
     * @return The buffered image.
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Gets the canvas of the window.
     *
     * @return The canvas.
     */
    public Canvas getScreen() {
        return screen;
    }
}
