package com.chronos.body;

import com.chronos.physics.CollisionEvent;
import com.chronos.physics.Hitbox;
import com.chronos.render.SpriteSource;
import com.chronos.render.game.Sprite;
import com.chronos.util.vector.Vector3;

import java.util.Objects;

/**
 * Represents a physical body in the game world.
 */
public abstract class Body {
    protected final String name;  // Name of the body
    public Hitbox hitbox;  // The hitbox of the body
    protected Vector3<Float> position;  // The position of the body
    protected SpriteSource sprite;  // The sprite representing the body
    public boolean destroyed;  // Flag indicating if the body is destroyed

    /**
     * Constructs a Body object.
     *
     * @param sprite   The sprite associated with the body.
     * @param position The initial position of the body.
     * @param name     The name of the body.
     */
    protected Body(SpriteSource sprite, Vector3<Float> position, String name) {
        this.sprite = sprite;
        this.position = position;
        this.name = name;
        destroyed = false;
    }

    /**
     * Constructs a Body object with specified coordinates.
     *
     * @param sprite The sprite associated with the body.
     * @param x      The x-coordinate of the initial position.
     * @param y      The y-coordinate of the initial position.
     * @param z      The z-coordinate of the initial position.
     * @param name   The name of the body.
     */
    protected Body(SpriteSource sprite, float x, float y, int z, String name) {
        this(sprite, new Vector3<>(x, y, (float) z), name);
    }

    /**
     * Gets the name of the body.
     *
     * @return The name of the body.
     */
    public final String getName() {
        return name;
    }

    /**
     * Gets the position of the body as integer coordinates.
     *
     * @return The position as integer coordinates.
     */
    public final Vector3<Integer> getPosition() {
        return new Vector3<>(position.x.intValue(), position.y.intValue(), position.z.intValue());
    }

    /**
     * Called when the body starts in the game world.
     */
    public abstract void start();

    /**
     * Updates the body's state over time.
     *
     * @param dt The time step for the update.
     */
    public abstract void update(float dt);

    /**
     * Gets the sprite associated with the body.
     *
     * @return The sprite associated with the body.
     */
    public abstract Sprite getSprite();

    /**
     * Called when a collision event occurs.
     *
     * @param collisionEvent The collision event object.
     */
    public void onCollisionEnter(CollisionEvent collisionEvent) {
    }

    /**
     * Called when a collision event ends.
     *
     * @param collisionEvent The collision event object.
     */
    public void onCollisionExit(CollisionEvent collisionEvent) {
    }

    /**
     * Called during a collision event.
     *
     * @param collisionEvent The collision event object.
     */
    public void onCollision(CollisionEvent collisionEvent) {
    }

    /**
     * Marks the body as destroyed.
     */
    public void destroy() {
        destroyed = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Body body)) return false;
        return destroyed == body.destroyed && Objects.equals(getName(), body.getName())
                && Objects.equals(hitbox, body.hitbox)
                && Objects.equals(getPosition(), body.getPosition())
                && Objects.equals(getSprite(), body.getSprite());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), hitbox, getPosition(), getSprite(), destroyed);
    }
}
