package com.Chronos.engine;

import com.Chronos.body.Body;
import com.Chronos.render.Screen;
import com.Chronos.util.vector.Vector2;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public abstract class Chronos extends JFrame {
    public static final double MAX_FRAME_RATE = (double) 1 / 60;
    private final Screen screen;
    List<Body> bodies;
    protected Chronos(String game, int w, int h) {
        super(game);
        bodies = new LinkedList<>();
        screen = new Screen(w, h);
        add(screen);
        setResizable(false);
        pack();
        setVisible(true);
        start();
    }
    protected Chronos(String game, int dim) {
        this(game, dim, dim);
    }
    protected Chronos(String game, Vector2<Integer> dim) {
        this(game, dim.x, dim.y);
    }
    protected Chronos(String game) {
        this(game, 100);
    }
    public abstract void addBodies();
    protected void addBody(Body b) {
        for (int i = 0; i < bodies.size(); i++) {
            if (bodies.get(i).getPosition().z.compareTo(b.getPosition().z) < 0) {
                bodies.add(i, b);
                return;
            }
        }
        bodies.add(b);
    }

    private void start() {
        addBodies();
        Thread update = new Thread(this::update);
        Thread physics = new Thread(this::physics);
        Thread render = new Thread(this::render);
        update.start();
    }

    private void update() {
        double startTime = System.nanoTime();
        double currentTime = 0;
        double passedTime = 0;
        double totalTime = 0;
        for(;;) {
            currentTime = System.nanoTime();
            passedTime = startTime - currentTime;
            startTime = currentTime;
            totalTime += passedTime;
            if ((totalTime / 10000) >= MAX_FRAME_RATE) {
                totalTime = 0;
                onCollisionEnter();
                onCollisionExit();
                onCollision();
                updateBodies();
            }
        }
    }

    private void physics() {
        double startTime = System.nanoTime();
        double currentTime = 0;
        double passedTime = 0;
        double totalTime = 0;
        for(;;) {
            currentTime = System.nanoTime();
            passedTime = startTime - currentTime;
            startTime = currentTime;
            totalTime += passedTime;
            if ((totalTime / 10000) >= MAX_FRAME_RATE) {
                totalTime = 0;
                collision();
            }
        }
    }

    private void updateBodies() {

    }

    private void collision() {

    }

    private void onCollisionEnter() {

    }

    private void onCollisionExit() {

    }
    private void onCollision() {

    }

    private void render() {
        double startTime = System.nanoTime();
        double currentTime = 0;
        double passedTime = 0;
        double totalTime = 0;
        for(;;) {
            currentTime = System.nanoTime();
            passedTime = startTime - currentTime;
            startTime = currentTime;
            totalTime += passedTime;
            if ((totalTime / 10000) >= MAX_FRAME_RATE) {
                totalTime = 0;
                renderBackground();
                renderBodies();
            }
        }
    }

    private void renderBackground() {

    }

    private void renderBodies() {

    }
}
