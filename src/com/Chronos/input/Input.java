package com.Chronos.input;

import com.Chronos.engine.Chronos;
import com.Chronos.util.vector.Vector2;

import java.awt.event.*;

public class Input implements MouseWheelListener, MouseMotionListener, KeyListener, MouseListener {
    private static Vector2<Integer> mousePosition = new Vector2<>(0);
    private static boolean mouseOnScreen = true, mousePressed;
    public static final int NUM_KEYS = 256;
    private static boolean[] keys = new boolean[NUM_KEYS];
    private static boolean[] keysLast = new boolean[NUM_KEYS];
    public static final int NUM_BUTTONS = 256;
    private static boolean[] buttons = new boolean[NUM_BUTTONS];
    private static boolean[] buttonsLast = new boolean[NUM_BUTTONS];
    private static int scroll = 0;
    private int scale;
    public Input(Chronos c) {
        c.getWindow().getScreen().addKeyListener(this);
        c.getWindow().getScreen().addMouseWheelListener(this);
        c.getWindow().getScreen().addMouseMotionListener(this);
        c.getWindow().getScreen().addMouseListener(this);
        scale = c.getScale();
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePosition = new Vector2<>(e.getX(), e.getY());
        mousePosition.divide(scale);
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePosition = new Vector2<>(e.getX(), e.getY());
        mousePosition.divide(scale);
        mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mousePosition = new Vector2<>(e.getX(), e.getY());
        mousePosition.divide(scale);
        mouseOnScreen = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mousePosition = new Vector2<>(e.getX(), e.getY());
        mousePosition.divide(scale);
        mouseOnScreen = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePosition = new Vector2<>(e.getX(), e.getY());
        mousePosition.divide(scale);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition = new Vector2<>(e.getX(), e.getY());
        mousePosition.divide(scale);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }

    public static Vector2<Integer> getMousePosition() {
        return mousePosition;
    }

    public static boolean isMouseOnScreen() {
        return mouseOnScreen;
    }

    public static boolean isMousePressed() {
        return mousePressed;
    }
}
