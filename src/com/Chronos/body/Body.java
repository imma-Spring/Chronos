package com.Chronos.body;

import com.Chronos.render.Sprite;
import com.Chronos.util.vector.Vector2;
import com.Chronos.util.vector.Vector3;

public abstract class Body {
    private final String name;
    private Vector3<Integer> position;
    private Sprite sprite;
    protected Body(Sprite sprite, Vector3<Integer> position, String name) {
        this.sprite = sprite;
        this.position = position;
        this.name = name;
    }

    public final String getName() {
        return name;
    }
    public final Vector3<Integer> getPosition() {
        return position;
    }

    public abstract void start();
    public abstract void update();
}
