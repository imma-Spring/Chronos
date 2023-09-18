package com.Chronos.physics;

import com.Chronos.body.Body;
import com.Chronos.util.vector.Vector2;
import com.Chronos.util.vector.Vector3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Hitbox {
    public Vector3<Float> position;
    public Vector2<Integer> size;
    private CollisionEvent last;
    public CollisionEvent[] collisionEvents;

    public Hitbox(float x, float y, int z, int w, int h) {
        this(new Vector3<>(x, y, (float) z), new Vector2<>(w, h));
    }

    public Hitbox(float x, float y, int w, int h) {
        this(x, y, 0, w, h);
    }

    public Hitbox(float x, float y, int z, Vector2<Integer> size) {
        this(new Vector3<>(x, y, (float) z), size);
    }

    public Hitbox(float x, float y, Vector2<Integer> size) {
        this(x, y, 0, size);
    }

    public Hitbox(Vector3<Float> position, int w, int h) {
        this(position, new Vector2<>(w, h));
    }

    public Hitbox(Vector3<Float> position, Vector2<Integer> size) {
        this.position = position;
        this.size = size;
        last = null;
        collisionEvents = new CollisionEvent[3];
    }

    public void collisionEvents(List<Body> bodies, int index) {
        List<Body> collisions = new ArrayList<>();
        float top = position.y - (size.y / 2f);
        float bottom = position.y + (size.y / 2f);
        float left = position.x - (size.x / 2f);
        float right = position.x + (size.x / 2f);
        for (int i = 0; i < bodies.size(); i++) {
            if (i == index) continue;
            var bHitbox = bodies.get(i).hitbox;
            float topOfTarget = bHitbox.position.y - (bHitbox.size.y / 2f);
            float bottomOfTarget = bHitbox.position.y + (bHitbox.size.y / 2f);
            float leftOfTarget = bHitbox.position.x - (bHitbox.size.x / 2f);
            float rightOfTarget = bHitbox.position.x + (bHitbox.size.x / 2f);
            if ((topOfTarget < top && bottomOfTarget > bottom && leftOfTarget < left && rightOfTarget > right)
                    || (topOfTarget < top && bottomOfTarget > bottom && leftOfTarget >= left && rightOfTarget <= right)
                    || (topOfTarget >= top && bottomOfTarget <= bottom && leftOfTarget < left && rightOfTarget > right)
                    || (topOfTarget >= top && bottomOfTarget <= bottom && leftOfTarget >= left && rightOfTarget <= right)
                    || (topOfTarget < top && bottomOfTarget <= bottom && (leftOfTarget <= right && rightOfTarget >= left) && bottomOfTarget > top)
                    || (topOfTarget >= top && bottomOfTarget > bottom && (leftOfTarget <= right && rightOfTarget >= left) && topOfTarget < bottom)
                    || (leftOfTarget < left && rightOfTarget <= right && (bottomOfTarget >= top && topOfTarget <= bottomOfTarget) && rightOfTarget > left)
                    || (leftOfTarget >= left && rightOfTarget > right && (bottomOfTarget >= top && topOfTarget <= bottomOfTarget) && leftOfTarget < right)) {
                collisions.add(bodies.get(i));
            }
        }
        collisionEvents[0] = new CollisionEvent(collisions);
        List<Body> enter = new ArrayList<>(), exit = new ArrayList<>();
        if (last != null) {
            for (Body collision : collisions) {
                if (!last.isCollision(collision.getName()))
                    enter.add(collision);
            }

            for (Body collision : last.getCollisions()) {
                if (!collisionEvents[0].isCollision(collision.getName()))
                    exit.add(collision);
            }
        } else {
            enter = collisions;
        }
        last = collisionEvents[0];
        collisionEvents[1] = new CollisionEvent(enter);
        collisionEvents[2] = new CollisionEvent(exit);
    }

    public void setPosition(Vector3<Float> position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hitbox hitbox)) return false;
        return Objects.equals(position, hitbox.position) && Objects.equals(size, hitbox.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, size);
    }

    @Override
    public String toString() {
        return "Hitbox{" +
                "position=" + position +
                ", size=" + size +
                ", centered= " + position.toVec2().center(size.convert(0f), false) +
                '}';
    }
}
