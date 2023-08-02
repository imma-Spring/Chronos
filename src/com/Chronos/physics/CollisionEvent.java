package com.Chronos.physics;

import com.Chronos.body.Body;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CollisionEvent {
    private final List<Body> bodies;
    public CollisionEvent(List<Body> bodies) {
        this.bodies = bodies;
    }

    public Optional<Integer> getCollision(String name) {
        for (int i = 0; i < bodies.size(); i++) {
            if (bodies.get(i).getName().equals(name))
                return Optional.of(i);
        }
        return Optional.empty();
    }

    public boolean isCollision(String name) {
        for (Body body : bodies) {
            if (body.getName().equals(name))
                return true;
        }
        return false;
    }

    public boolean isCollision(Class<? extends Body> type) {
        for (Body body : bodies) {
            if (body.getClass().equals(type))
                return true;
        }
        return false;
    }

    public List<Body> getCollisions() {
        return bodies;
    }

    @SuppressWarnings("unchecked")
    public <U extends Body> Optional<List<U>> getCollisions(Class<U> type) {
        List<U> typeBodies = new ArrayList<>();
        for (Body body : bodies) {
            if (body.getClass().equals(type))
                typeBodies.add((U) body);
        }
        if (typeBodies.size() > 0)
            return Optional.of(typeBodies);
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "CollisionEvent{" +
                "bodies=" + Arrays.toString(bodies.toArray()) +
                '}';
    }
}
