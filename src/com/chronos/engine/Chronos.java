package com.chronos.engine;

import com.chronos.body.Body;
import com.chronos.input.Input;
import com.chronos.render.Screen;
import com.chronos.render.Window;
import com.chronos.render.sprites.Sprite;
import com.chronos.util.vector.Vector2;

import java.util.*;
import java.util.stream.IntStream;

/**
 * The base class for the Chronos game engine.
 * <p>
 * This class provides the core functionality for managing game state, updating game elements,
 * and rendering the game. It also handles user input and window management.
 **/
public abstract class Chronos {
    // Constants
    public static final double MAX_FRAME_RATE = 1.0 / 60.0;
    private static int scale;
    private static int bottom;
    private static int right;
    public final Input input;
    // Game window properties
    private final int w, h;
    private final Window window;
    private final Screen screen;
    // Game state
    private final List<Body> bodies;
    private boolean god = false;
    private Sprite background;

    /**
     * Constructs a Chronos game engine with specified parameters.
     *
     * @param game            The name of the game.
     * @param w               The width of the game window.
     * @param h               The height of the game window.
     * @param scale           The scale factor of the game window.
     * @param backgroundColor The background color of the game window.
     */
    protected Chronos(String game, int w, int h, int scale, int backgroundColor) {
        // Initialize game window
        this.w = w;
        this.h = h;
        Chronos.scale = scale;
        window = new Window(game, w, h, scale);
        screen = new Screen(w, h, backgroundColor, this);

        // Initialize game state
        bodies = Collections.synchronizedList(new LinkedList<>());
        input = new Input(this);
        right = w - 1;
        bottom = h - 1;

        // Start the game loop
        start();
    }

    /**
     * Constructs a Chronos game engine with a square window of specified dimensions.
     *
     * @param game            The name of the game.
     * @param dim             The dimensions of the square game window.
     * @param scale           The scale factor of the game window.
     * @param backgroundColor The background color of the game window.
     */
    protected Chronos(String game, int dim, int scale, int backgroundColor) {
        this(game, dim, dim, scale, backgroundColor);
    }

    /**
     * Constructs a Chronos game engine with specified dimensions using a vector.
     *
     * @param game            The name of the game.
     * @param dim             A vector specifying the dimensions of the game window.
     * @param scale           The scale factor of the game window.
     * @param backgroundColor The background color of the game window.
     */
    protected Chronos(String game, Vector2<Integer> dim, int scale, int backgroundColor) {
        this(game, dim.x, dim.y, scale, backgroundColor);
    }

    /**
     * Constructs a Chronos game engine with a square window of specified dimensions and background color.
     *
     * @param game            The name of the game.
     * @param dim             The dimensions of the square game window.
     * @param backgroundColor The background color of the game window.
     */
    protected Chronos(String game, int dim, int backgroundColor) {
        this(game, dim, dim, 1, backgroundColor);
    }

    /**
     * Constructs a Chronos game engine with specified dimensions using a vector and background color.
     *
     * @param game            The name of the game.
     * @param dim             A vector specifying the dimensions of the game window.
     * @param backgroundColor The background color of the game window.
     */
    protected Chronos(String game, Vector2<Integer> dim, int backgroundColor) {
        this(game, dim.x, dim.y, backgroundColor);
    }

    /**
     * Constructs a Chronos game engine with a default square window of 100x100 and background color.
     *
     * @param game            The name of the game.
     * @param backgroundColor The background color of the game window.
     */
    protected Chronos(String game, int backgroundColor) {
        this(game, 100, 1, backgroundColor);
    }

    public static int scale() {
        return scale;
    }

    public static int TOP() {
        return 0;
    }

    public static int BOTTOM() {
        return bottom;
    }

    public static int LEFT() {
        return 0;
    }

    public static int RIGHT() {
        return right;
    }

    /**
     * Abstract method to add game bodies to the engine.
     */
    public abstract void addBodies();

    /**
     * Adds a body to the game.
     *
     * @param b The body to add.
     */
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
        Thread update = new Thread(this::updateGame);
        update.start();
    }

    /**
     * The game loop responsible for updating and rendering the game.
     * <p>
     * This method manages the main game loop, ensuring that game elements are updated
     * at a consistent frame rate and that rendering is performed efficiently.
     */
    private void updateGame() {
        boolean render = false;
        double startTime = System.nanoTime() / 10e8;
        double currentTime;
        double passedTime;
        double totalTime = 0;

        for (; ; ) {
            currentTime = System.nanoTime() / 10e8;
            passedTime = currentTime - startTime;
            startTime = currentTime;
            totalTime += passedTime;

            // Update the game at a fixed frame rate
            while (totalTime >= MAX_FRAME_RATE) {
                totalTime -= MAX_FRAME_RATE;
                collision();
                onCollisionEnter();
                onCollisionExit();
                onCollision();
                updateBodies((float) passedTime * 10);
                update();
                input.update();
                remove();
                render = true;

                if (god) return;
            }

            // If not ready to render, sleep for a short time to avoid busy-waiting
            if (!render) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                // Render the game
                window.update();
                renderBackground();
                renderBodies();
            }

            render = false;
        }
    }

    /**
     * Removes destroyed bodies from the list of active bodies.
     * <p>
     * This method iterates through the list of bodies and removes any bodies that have been
     * marked as destroyed.
     */
    private void remove() {
        int bound = bodies.size();
        for (int i = 0; i < bound; i++) {
            if (bodies.get(i).destroyed) {
                bodies.remove(bodies.get(i));
                i--;
                bound--;
            }
        }
    }

    private void updateBodies(float dt) {
        bodies.stream().filter(body -> !body.destroyed).forEach(body -> body.update(dt));
    }

    private void collision() {
        int bound = bodies.size();
        IntStream.range(0, bound).filter(i -> bodies.get(i).hitbox
                != null).forEach(i -> bodies.get(i).hitbox.collisionEvents(bodies, i));
    }

    private void onCollisionEnter() {
        for (Body b : bodies) {
            if (b.hitbox != null && !b.hitbox.collisionEvents[1].getCollisions().isEmpty() && !b.destroyed) {
                b.onCollisionEnter(b.hitbox.collisionEvents[1]);
            }
        }
    }

    private void onCollisionExit() {
        for (Body b : bodies) {
            if (b.hitbox != null && !b.hitbox.collisionEvents[2].getCollisions().isEmpty() && !b.destroyed) {
                b.onCollisionExit(b.hitbox.collisionEvents[2]);
            }
        }
    }

    private void onCollision() {
        for (Body b : bodies) {
            if (b.hitbox != null && !b.hitbox.collisionEvents[0].getCollisions().isEmpty() && !b.destroyed) {
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

    /**
     * Renders the background of the game.
     * <p>
     * This method renders the background of the game screen. If a background sprite is
     * provided, it is drawn; otherwise, the screen is cleared.
     */
    private void renderBackground() {
        if (background != null) screen.drawSprite(background);
        else screen.clear();
    }

    /**
     * Renders the game bodies.
     * <p>
     * This method iterates through the list of bodies and renders each body's sprite at its
     * specified position.
     */
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

    protected List<Body> getBodies(Class<? extends Body> type) {
        List<Body> bodies = new ArrayList<>();
        for (Body b : this.bodies) {
            if (b.getClass().equals(type))
                bodies.add(b);
        }
        return bodies;
    }

    protected int count(Class<? extends Body> type) {
        return (int) bodies.stream().filter(b -> b.getClass().equals(type)).count();
    }

    protected void setBackground(Sprite background) {
        this.background = background;
    }
}
