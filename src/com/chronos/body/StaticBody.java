package com.chronos.body;

import com.chronos.render.sprites.Sprite;
import com.chronos.util.vector.Vector3;

/**
 * Represents a static body in the game world.
 */
public abstract class StaticBody extends Body {
    /**
     * Constructs a StaticBody object.
     *
     * @param sprite   The sprite associated with the body.
     * @param position The initial position of the body.
     * @param name     The name of the body.
     */
    protected StaticBody(Sprite sprite, Vector3<Float> position, String name) {
        super(sprite, position, name);
    }

    /**
     * Constructs a StaticBody object with specified coordinates.
     *
     * @param sprite The sprite associated with the body.
     * @param x      The x-coordinate of the initial position.
     * @param y      The y-coordinate of the initial position.
     * @param z      The z-coordinate of the initial position.
     * @param name   The name of the body.
     */
    protected StaticBody(Sprite sprite, float x, float y, int z, String name) {
        super(sprite, x, y, z, name);
    }

    /**
     * Gets the sprite associated with the static body.
     *
     * @return The sprite associated with the static body.
     */
    @Override
    public Sprite getSprite() {
        return sprite;
    }
}
