package com.Chronos.physics;

import com.Chronos.util.vector.Vector2;
import com.Chronos.util.vector.Vector3;

public class Raycast {
    private Vector2<Float> start, end;
    public Raycast(float start_x, float start_y, float end_x, float end_y) {
        this(new Vector2<>(start_x, start_y), new Vector2<>(end_x, end_y));
    }

    public Raycast(Vector2<Float> start, float end_x, float end_y) {
        this(start, new Vector2<>(end_x, end_y));
    }

    public Raycast(float start_x, float start_y, Vector2<Float> end) {
        this(new Vector2<>(start_x, start_y), end);
    }

    public Raycast(Vector2<Float> start, Vector2<Float> end) {
        this.start = start;
        this.end = end;
    }
}
