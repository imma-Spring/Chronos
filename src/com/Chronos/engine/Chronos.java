package com.Chronos.engine;

import com.Chronos.body.Body;
import com.Chronos.input.Input;
import com.Chronos.render.Screen;
import com.Chronos.render.Window;
import com.Chronos.util.vector.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public abstract class Chronos {
    public static final double MAX_FRAME_RATE = 1.0 / 60.0;
    private final int w, h, scale;
    private final Window window;
    private Screen screen;
    private List<Body> bodies;

    private Body background;

    protected Chronos(String game, int w, int h, int scale, int backgroundColor) {
        this.w = w;
        this.h = h;
        this.scale = scale;
        window = new Window(game, w, h, scale);
        screen = new Screen(w, h, backgroundColor, this);
        bodies = new ArrayList<>();
        new Input(this);
        start();
    }

    protected Chronos(String game, int dim, int scale, int backgroundColor) {
        this(game, dim, dim, scale, backgroundColor);
    }

    protected Chronos(String game, Vector2<Integer> dim, int scale, int backgroundColor) {
        this(game, dim.x, dim.y);
    }

    protected Chronos(String game, int dim, int backgroundColor) {
        this(game, dim, dim, 1, backgroundColor);
    }

    protected Chronos(String game, Vector2<Integer> dim, int backgroundColor) {
        this(game, dim.x, dim.y, backgroundColor);
    }

    protected Chronos(String game, int backgroundColor) {
        this(game, 100, 1, backgroundColor);
    }

    public abstract void addBodies();

    protected void addBody(Body b) {
        for (int i = 0; i < bodies.size(); i++) {
            if (bodies.get(i).getPosition().z.compareTo(b.getPosition().z) > 0) {
                bodies.add(i, b);
                b.start();
                return;
            }
        }
        b.start();
        bodies.add(b);
    }

    private void start() {
        addBodies();
        Thread update = new Thread(this::update);
        update.start();
    }

    private void update() {
        boolean render = false;
        double startTime = System.nanoTime() / 10e8;
        double currentTime = 0;
        double passedTime = 0;
        double totalTime = 0;
        for (; ; ) {
            currentTime = System.nanoTime() / 10e8;
            passedTime = currentTime - startTime;
            startTime = currentTime;
            totalTime += passedTime;
            while (totalTime >= MAX_FRAME_RATE) {
                totalTime -= MAX_FRAME_RATE;
                collision();
                onCollisionEnter();
                onCollisionExit();
                onCollision();
                updateBodies((float) passedTime * 10);
                remove();
                render = true;
            }
            if (!render) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                window.update();
                renderBackground();
                renderBodies();
            }
            render = false;
        }
    }

    private void remove() {
        for (int i = 0; i < bodies.size(); i++) {
            if (bodies.get(i).destroyed) bodies.remove(bodies.get(i));
        }
    }

    private void updateBodies(float dt) {
        for (Body body : bodies) {
            if (!body.destroyed)
                body.update(dt);
        }
    }

    private void collision() {
        for (int i = 0; i < bodies.size(); i++) {
            bodies.get(i).hitbox.collisionEvents(bodies, i);
        }
    }

    private void onCollisionEnter() {
        for (Body b : bodies) {
            if (b.hitbox.collisionEvents[1] != null && !b.destroyed) {
                b.onCollisionEnter(b.hitbox.collisionEvents[1]);
            }
        }
    }

    private void onCollisionExit() {
        for (Body b : bodies) {
            if (b.hitbox.collisionEvents[2] != null && !b.destroyed) {
                b.onCollisionExit(b.hitbox.collisionEvents[2]);
            }
        }
    }

    private void onCollision() {
        for (Body b : bodies) {
            if (b.hitbox.collisionEvents[0] != null && !b.destroyed) {
                b.onCollision(b.hitbox.collisionEvents[0]);
            }
        }
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public int getScale() {
        return scale;
    }

    public Window getWindow() {
        return window;
    }

    private void renderBackground() {
        if (background == null) {
            screen.clear();
        } else {
            //screen.render(background)
        }
    }

    private void renderBodies() {
        for (Body b :
                bodies) {
            screen.drawSprite(b.getSprite(), b.getPosition().toVec2().center(b.getSprite().getSize(), false));
        }
    }

    protected List<Body> getBodies() {
        return bodies;
    }
}
