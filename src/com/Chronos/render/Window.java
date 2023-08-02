package com.Chronos.render;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Window extends JFrame {
    private BufferedImage image;
    private Canvas screen;
    private BufferStrategy bs;
    private Graphics g;

    public Window(String game, int w, int h, int scale) {
        super();
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        screen = new Canvas();
        screen.setPreferredSize(new Dimension(w * scale, h * scale));
        screen.setMaximumSize(new Dimension(w * scale, h * scale));
        screen.setMinimumSize(new Dimension(w * scale, h * scale));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(screen, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        screen.createBufferStrategy(2);
        screen.requestFocusInWindow();
        bs = screen.getBufferStrategy();
        g = bs.getDrawGraphics();
    }

    public void update() {
        g.drawImage(image, 0, 0, screen.getWidth(), screen.getHeight(), null);
        bs.show();
    }

    public BufferedImage getImage() {
        return image;
    }

    public Canvas getScreen() {
        return screen;
    }
}
