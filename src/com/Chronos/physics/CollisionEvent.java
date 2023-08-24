package com.Chronos.physics;

import com.Chronos.body.Body;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return bodies.stream().anyMatch(body -> body.getName().equals(name));
    }

    public boolean isCollision(Class<? extends Body> type) {
        return bodies.stream().anyMatch(body -> body.getClass().equals(type));
    }

    public List<Body> getCollisions() {
        return bodies;
    }

    @SuppressWarnings("unchecked")
    public <U extends Body> Optional<List<U>> getCollisions(Class<U> type) {
        List<U> typeBodies =
                bodies.stream().filter(body -> body.getClass().equals(type)).map(body -> (U) body).collect(Collectors.toList());
        return typeBodies.size() > 0 ? Optional.of(typeBodies) : Optional.empty();
    }

    @Override
    public String toString() {
        return "CollisionEvent{" +
                "bodies=" + Arrays.toString(bodies.toArray()) +
                '}';
    }
}
