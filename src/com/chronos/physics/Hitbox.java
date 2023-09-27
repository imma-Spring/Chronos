package com.chronos.physics;

import com.chronos.body.Body;
import com.chronos.util.vector.Vector2;
import com.chronos.util.vector.Vector3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Represents a hitbox for collision detection.
 */
public class Hitbox {

    // Position of the hitbox in 3D space
    public Vector3<Float> position;

    // Size of the hitbox in 2D space
    public Vector2<Integer> size;

    // Stores the last collision event for reference
    private CollisionEvent last;

    // Stores collision events (current, enter, exit)
    public CollisionEvent[] collisionEvents;

    /**
     * Constructs a Hitbox with the specified position and size.
     *
     * @param position The position of the hitbox.
     * @param size     The size of the hitbox.
     */
    public Hitbox(Vector3<Float> position, Vector2<Integer> size) {
        this.position = position;
        this.size = size;
        last = null;
        collisionEvents = new CollisionEvent[3];
    }

    /**
     * Calculates and updates collision events with other bodies.
     *
     * @param bodies The list of bodies to check for collision.
     * @param index  The index of the body associated with this hitbox.
     */
    /**
     * Calculates and updates collision events with other bodies.
     *
     * @param bodies The list of bodies to check for collision.
     * @param index  The index of the body associated with this hitbox.
     */
    public void collisionEvents(List<Body> bodies, int index) {
        // Calculate the position boundaries of this hitbox
        float top = position.y - (size.y / 2f);
        float bottom = position.y + (size.y / 2f);
        float left = position.x - (size.x / 2f);
        float right = position.x + (size.x / 2f);

        // Initialize a list to store bodies that collide with this hitbox
        List<Body> collisions = new ArrayList<>();

        // Iterate through the list of bodies
        for (int i = 0; i < bodies.size(); i++) {
            // Skip checking collision with itself
            if (i == index) continue;

            // Get the hitbox of the current body
            var bHitbox = bodies.get(i).hitbox;

            // Calculate position boundaries of the current body's hitbox
            float topOfTarget = bHitbox.position.y - (bHitbox.size.y / 2f);
            float bottomOfTarget = bHitbox.position.y + (bHitbox.size.y / 2f);
            float leftOfTarget = bHitbox.position.x - (bHitbox.size.x / 2f);
            float rightOfTarget = bHitbox.position.x + (bHitbox.size.x / 2f);

            // Check for collision between this hitbox and the current body's hitbox
            if ((topOfTarget < top && bottomOfTarget > bottom && leftOfTarget < left && rightOfTarget > right)
                    || (topOfTarget < top && bottomOfTarget > bottom && leftOfTarget >= left && rightOfTarget <= right)
                    || (topOfTarget >= top && bottomOfTarget <= bottom && leftOfTarget < left && rightOfTarget > right)
                    || (topOfTarget >= top && bottomOfTarget <= bottom && leftOfTarget >= left && rightOfTarget <= right)
                    || (topOfTarget < top && bottomOfTarget <= bottom && (leftOfTarget <= right && rightOfTarget >= left) && bottomOfTarget > top)
                    || (topOfTarget >= top && bottomOfTarget > bottom && (leftOfTarget <= right && rightOfTarget >= left) && topOfTarget < bottom)
                    || (leftOfTarget < left && rightOfTarget <= right && (bottomOfTarget >= top && topOfTarget <= bottomOfTarget) && rightOfTarget > left)
                    || (leftOfTarget >= left && rightOfTarget > right && (bottomOfTarget >= top && topOfTarget <= bottomOfTarget) && leftOfTarget < right)) {
                // Collision detected, add the body to the collisions list
                collisions.add(bodies.get(i));
            }
        }

        // Create a CollisionEvent containing the list of colliding bodies
        collisionEvents[0] = new CollisionEvent(collisions);

        // Initialize lists to track bodies entering and exiting collision with this hitbox
        List<Body> enter = new ArrayList<>();
        List<Body> exit = new ArrayList<>();

        if (last != null) {
            // Compare with previous collision event to determine entering and exiting bodies
            for (Body collision : collisions) {
                if (!last.isCollision(collision.getName()))
                    enter.add(collision);
            }

            for (Body collision : last.getCollisions()) {
                if (!collisionEvents[0].isCollision(collision.getName()))
                    exit.add(collision);
            }
        } else {
            // If there was no previous collision event, all colliding bodies are considered entering
            enter = collisions;
        }

        // Update collision events for entering and exiting bodies
        last = collisionEvents[0];
        collisionEvents[1] = new CollisionEvent(enter);
        collisionEvents[2] = new CollisionEvent(exit);
    }

    /**
     * Sets the position of the hitbox.
     *
     * @param position The new position of the hitbox.
     */
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
