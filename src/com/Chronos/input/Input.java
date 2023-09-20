package com.Chronos.input;

import com.Chronos.engine.Chronos;

import java.awt.event.*;

public final class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
    private final Chronos engine;

    private static final int NUM_KEYS = 256;
    private static final boolean[] keys = new boolean[NUM_KEYS];
    private static final boolean[] keysLast = new boolean[NUM_KEYS];

    private static final int NUM_BUTTONS = 5;
    private static final boolean[] buttons = new boolean[NUM_BUTTONS];
    private static final boolean[] buttonsLast = new boolean[NUM_BUTTONS];
    private static int mouseX, mouseY;
    private static int scroll;
    public Input(Chronos engine) {
        this.engine = engine;
        mouseX = 0;
        mouseY = 0;
        scroll = 0;

        engine.getWindow().getScreen().addKeyListener(this);
        engine.getWindow().getScreen().addMouseMotionListener(this);
        engine.getWindow().getScreen().addMouseListener(this);
        engine.getWindow().getScreen().addMouseWheelListener(this);
    }

    public void update() {
        scroll = 0;

        System.arraycopy(keys, 0, keysLast, 0, NUM_KEYS);

        System.arraycopy(buttons, 0, buttonsLast, 0, NUM_BUTTONS);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = (int)(e.getX() / engine.getScale());
        mouseY = (int)(e.getY() / engine.getScale());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = (int)(e.getX() / engine.getScale());
        mouseY = (int)(e.getY() / engine.getScale());
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scroll = e.getWheelRotation();
    }

    public static boolean isKeyPressed(int keyCode) {
        try {
            return keys[keyCode];
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Key does not exist");
        }
    }

    public static boolean isKeyUp(int keyCode) {
        try {
            return !keys[keyCode] && keysLast[keyCode];
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Key does not exist");
        }
    }

    public static boolean isKeyDown(int keyCode) {
        try {
            return keys[keyCode] && !keysLast[keyCode];
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Key does not exist");
        }
    }

    public static boolean isButtonPressed(int button) {
        try {
            return buttons[button];
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Key does not exist");
        }
    }

    public static boolean isButtonUp(int button) {
        try {
            return !buttons[button] && buttonsLast[button];
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Key does not exist");
        }
    }

    public static boolean isButtonDown(int button) {
        try {
            return buttons[button] && !buttonsLast[button];
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Key does not exist");
        }
    }

    public static int getMouseX() {
        return mouseX;
    }

    public static int getMouseY() {
        return mouseY;
    }

    public static int getScroll() {
        return scroll;
    }
}
