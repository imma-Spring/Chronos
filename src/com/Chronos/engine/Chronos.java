package com.Chronos.engine;

import com.Chronos.body.Body;
import com.Chronos.input.Input;
import com.Chronos.render.Screen;
import com.Chronos.render.Window;
import com.Chronos.render.sprites.Sprite;
import com.Chronos.util.vector.Vector2;

import java.util.*;
import java.util.stream.IntStream;

public abstract class Chronos {
    private boolean god = false;
    public static final double MAX_FRAME_RATE = 1.0 / 60.0;
    private final int w, h, scale;
    private final Window window;
    private final Screen screen;
    private final List<Body> bodies;

    private Sprite background;

    protected Chronos(String game, int w, int h, int scale, int backgroundColor) {
        this.w = w;
        this.h = h;
        this.scale = scale;
        window = new Window(game, w, h, scale);
        screen = new Screen(w, h, backgroundColor, this);
        bodies = Collections.synchronizedList(new LinkedList<>());
        new Input(this);
        start();
    }

    protected Chronos(String game, int dim, int scale, int backgroundColor) {
        this(game, dim, dim, scale, backgroundColor);
    }

    protected Chronos(String game, Vector2<Integer> dim, int scale, int backgroundColor) {
        this(game, dim.x, dim.y, scale, backgroundColor);
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
        for (int i = 0; i < bodies.size(); i++)
            if (bodies.get(i).getPosition().z.compareTo(b.getPosition().z) > 0) {
                bodies.add(i, b);
                b.start();
                return;
            }
        b.start();
        bodies.add(b);
    }

    private void start() {
        addBodies();
        Thread update = new Thread(this::updateGame);
        update.start();
    }

    private void updateGame() {
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
                update();
                remove();
                Signal.sendSignal(Signal.reset);
                render = true;
                if (god) return;
            }
            if (!render) try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
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
        IntStream.range(0, bodies.size()).filter(i -> bodies.get(i).destroyed).forEach(i -> bodies.remove(bodies.get(i)));
    }

    private void updateBodies(float dt) {
        bodies.stream().filter(body -> !body.destroyed).forEach(body -> body.update(dt));
    }

    private void collision() {
        IntStream.range(0, bodies.size()).forEachOrdered(i -> bodies.get(i).hitbox.collisionEvents(bodies, i));
    }

    private void onCollisionEnter() {
        bodies.stream().filter(b -> b.hitbox.collisionEvents[1] != null
                && !b.destroyed).forEachOrdered(b -> b.onCollisionEnter(b.hitbox.collisionEvents[1]));
    }

    private void onCollisionExit() {
        bodies.stream().filter(b -> b.hitbox.collisionEvents[2] != null
                && !b.destroyed).forEachOrdered(b -> b.onCollisionExit(b.hitbox.collisionEvents[2]));
    }

    private void onCollision() {
        bodies.stream().filter(b -> b.hitbox.collisionEvents[0] != null
                && !b.destroyed).forEachOrdered(b -> b.onCollision(b.hitbox.collisionEvents[0]));
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
        if (background != null) screen.drawSprite(background);
        else screen.clear();
    }

    private void renderBodies() {
        bodies.forEach(b -> screen.drawSprite(b.getSprite(), b.getPosition().toVec2().center(b.getSprite().getSize(), false)));
    }

    protected List<Body> getBodies() {
        return bodies;
    }

    public abstract void update();

    protected void endProgram(int code) {
        System.out.printf("Program ended with code of %d%n", code);
        god = true;
    }

    protected Optional<Body> getBody(String name) {
        for (Body b : bodies) {
            if (b.getName().equals(name))
                return Optional.of(b);
        }
        return Optional.empty();
    }

    protected Optional<Body> getBody(Class<? extends Body> type) {
        for (Body b : bodies) {
            if (b.getClass().equals(type))
                return Optional.of(b);
        }
        return Optional.empty();
    }

    protected int count(Class<? extends Body> type) {
        return (int) bodies.stream().filter(b -> b.getClass().equals(type)).count();
    }

    protected void setBackground(Sprite background) {
        this.background = background;
    }
}
