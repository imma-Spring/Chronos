package com.Chronos.body;

import com.Chronos.render.sprites.Sprite;
import com.Chronos.util.vector.Vector3;

public abstract class StaticBody extends Body {
    protected StaticBody(Sprite sprite, Vector3<Float> position, String name) {
        super(sprite, position, name);
    }

    protected StaticBody(Sprite sprite, float x, float y, int z, String name) {
        super(sprite, x, y, z, name);
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }
}
