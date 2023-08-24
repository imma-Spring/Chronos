package com.Chronos.body;

import com.Chronos.physics.CollisionEvent;
import com.Chronos.physics.Hitbox;
import com.Chronos.render.sprites.Sprite;
import com.Chronos.util.vector.Vector3;

import java.util.Objects;

public abstract class Body {
    protected final String name;
    public Hitbox hitbox;
    protected Vector3<Float> position;
    protected Sprite sprite;
    public boolean destroyed;

    protected Body(Sprite sprite, Vector3<Float> position, String name) {
        this.sprite = sprite;
        this.position = position;
        this.name = name;
        destroyed = false;
    }

    protected Body(Sprite sprite, float x, float y, int z, String name) {
        this(sprite, new Vector3<>(x, y, (float) z), name);
    }

    public final String getName() {
        return name;
    }

    public final Vector3<Integer> getPosition() {
        return new Vector3<>(position.x.intValue(), position.y.intValue(), position.z.intValue());
    }

    public abstract void start();

    public abstract void update(float dt);

    public abstract Sprite getSprite();

    public void onCollisionEnter(CollisionEvent collisionEvent) { }

    public void onCollisionExit(CollisionEvent collisionEvent) { }

    public void onCollision(CollisionEvent collisionEvent) { }

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
