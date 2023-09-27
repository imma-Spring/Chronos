package com.chronos.physics;

import com.chronos.body.Body;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Represents an event of collision between bodies.
 */
public class CollisionEvent {

    // List of bodies involved in the collision event
    private final List<Body> bodies;

    /**
     * Constructs a CollisionEvent with the specified list of bodies.
     *
     * @param bodies List of bodies involved in the collision event.
     */
    public CollisionEvent(List<Body> bodies) {
        this.bodies = bodies;
    }

    /**
     * Gets the index of the first occurrence of a body with the given name.
     *
     * @param name The name of the body to search for.
     * @return An Optional containing the index of the body if found; otherwise, empty.
     */
    public Optional<Integer> getCollision(String name) {
        for (int i = 0; i < bodies.size(); i++) {
            if (bodies.get(i).getName().equals(name))
                return Optional.of(i);
        }
        return Optional.empty();
    }

    /**
     * Checks if a body with the given name is involved in the collision event.
     *
     * @param name The name of the body to check for collision.
     * @return True if a body with the given name is involved; otherwise, false.
     */
    public boolean isCollision(String name) {
        return bodies.stream().anyMatch(body -> body.getName().equals(name));
    }

    /**
     * Checks if a body with the given name is not involved in the collision event.
     *
     * @param name The name of the body to check for collision.
     * @return True if no body with the given name is involved; otherwise, false.
     */
    public boolean isNotCollision(String name) {
        return bodies.stream().noneMatch(body -> body.getName().equals(name));
    }

    /**
     * Checks if a body of the specified type is involved in the collision event.
     *
     * @param type The class type of the body to check for collision.
     * @return True if a body of the specified type is involved; otherwise, false.
     */
    public boolean isCollision(Class<? extends Body> type) {
        return bodies.stream().anyMatch(body -> body.getClass().equals(type));
    }

    /**
     * Gets the list of bodies involved in the collision event.
     *
     * @return The list of bodies involved in the collision event.
     */
    public List<Body> getCollisions() {
        return bodies;
    }

    /**
     * Gets the list of bodies involved in the collision event of the specified type.
     *
     * @param type The class type of the bodies to retrieve.
     * @param <U>  The type of body.
     * @return An Optional containing the list of bodies if found; otherwise, empty.
     */
    @SuppressWarnings("unchecked")
    public <U extends Body> Optional<List<U>> getCollisions(Class<U> type) {
        List<U> typeBodies =
                bodies.stream().filter(body -> body.getClass().equals(type)).map(body -> (U) body).collect(Collectors.toList());
        return !typeBodies.isEmpty() ? Optional.of(typeBodies) : Optional.empty();
    }

    /**
     * Returns a string representation of the CollisionEvent.
     *
     * @return A string representation of the CollisionEvent.
     */
    @Override
    public String toString() {
        return "CollisionEvent{" +
                "bodies=" + Arrays.toString(bodies.toArray()) +
                '}';
    }
}
