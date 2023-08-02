package com.Chronos.physics;

import com.Chronos.body.Body;
import com.Chronos.util.vector.Vector2;
import com.Chronos.util.vector.Vector3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Hitbox {
    public Vector3<Float> position;
    public Vector2<Float> size;
    private CollisionEvent last;
    public CollisionEvent[] collisionEvents;

    public Hitbox(float x, float y, int z, float w, float h) {
        this(new Vector3<>(x, y, (float) z), new Vector2<>(w, h));
    }

    public Hitbox(float x, float y, float w, float h) {
        this(x, y, 0, w, h);
    }

    public Hitbox(float x, float y, int z, Vector2<Float> size) {
        this(new Vector3<>(x, y, (float) z), size);
    }

    public Hitbox(float x, float y, Vector2<Float> size) {
        this(x, y, 0, size);
    }

    public Hitbox(Vector3<Float> position, float w, float h) {
        this(position, new Vector2<>(w, h));
    }

    public Hitbox(Vector3<Float> position, Vector2<Float> size) {
        this.position = position;
        this.size = size;
        last = null;
        collisionEvents = new CollisionEvent[3];
    }

    public void collisionEvents(List<Body> bodies, int index) {
        Vector2<Float> center = position.toVec2().center(size, false);
        float top = center.y + (size.y / 2);
        float bottom = center.y - (size.y / 2);
        float right = center.x + (size.x / 2);
        float left = center.x - (size.x / 2);
        CollisionEvent current;
        CollisionEvent enter;
        CollisionEvent exit;
        List<Body> hit = new ArrayList<>();
        for (Body b : bodies) {
            Hitbox h = b.hitbox;
            if (b.getName().equals(bodies.get(index).getName()))
                break;
            if (!h.position.z.equals(position.z))
                break;
            Vector2<Float> centeroftarget = h.position.toVec2().center(h.size, false);
            float topoftarget = centeroftarget.y + (h.size.y / 2);
            float bottomoftarget = centeroftarget.y - (h.size.y / 2);
            float rightoftarget = centeroftarget.x + (h.size.x / 2);
            float leftoftarget = centeroftarget.x - (h.size.x / 2);
//            detecting if a hitbox intersects another
            if (bottom < topoftarget && top > topoftarget && center.x < leftoftarget && center.x > rightoftarget)
                hit.add(b);
            else if (bottom < bottomoftarget && top > bottomoftarget && center.x < leftoftarget
                    && center.x > rightoftarget) hit.add(b);
            else if (left < rightoftarget && right > rightoftarget && center.y < topoftarget
                    && center.y > bottomoftarget) hit.add(b);
            else if (left < leftoftarget && right > leftoftarget && center.y < topoftarget
                    && center.y > bottomoftarget) hit.add(b);
            else if (left < leftoftarget && right > leftoftarget && bottom < topoftarget && top > topoftarget)
                hit.add(b);
            else if (left < rightoftarget && right > rightoftarget && bottom < topoftarget && top > topoftarget)
                hit.add(b);
            else if (left < leftoftarget && right > leftoftarget && bottom < bottomoftarget && top > bottomoftarget)
                hit.add(b);
            else if (left < rightoftarget && right > rightoftarget && bottom < bottomoftarget
                    && top > bottomoftarget) hit.add(b);
        }
        current = new CollisionEvent(hit);
        hit = new ArrayList<>();
        if (last != null){
            var prev = last.getCollisions();
            for (Body b : current.getCollisions()) {
                if (!prev.contains(b))
                    hit.add(b);
            }
            enter = new CollisionEvent(hit);
            hit = new ArrayList<>();
            var cur = current.getCollisions();
            for (Body b : prev) {
                if (!cur.contains(b))
                    hit.add(b);
            }
        } else {
            hit = current.getCollisions();
            enter = new CollisionEvent(hit);
            hit = new ArrayList<>();
        }
        exit = new CollisionEvent(hit);
        last = current;
        collisionEvents = new CollisionEvent[] {current, enter, exit};
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
                ", centered= " + position.toVec2().center(size, false) +
                '}';
    }
}
