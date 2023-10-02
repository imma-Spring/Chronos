package com.chronos.render.game.animation;

import com.chronos.exceptions.ImageNotFoundException;
import com.chronos.render.SpriteSource;
import com.chronos.render.game.Sprite;
import com.chronos.render.game.Tilemap;

import java.util.HashMap;

public class AnimationHolder implements SpriteSource {
    private HashMap<String, Animation> animationHashMap;

    private Animation currentAnimation;
    public AnimationHolder() {
        animationHashMap = new HashMap<>();
    }

    public boolean addAnimation(String name, Animation animation) {
        if (animationHashMap.containsKey(name)) return false;
        if (animationHashMap.isEmpty())
            currentAnimation = animation;
        animationHashMap.put(name, animation);

        return true;
    }

    public boolean setCurrentAnimation(String name) {
        if (!animationHashMap.containsKey(name)) return false;
        currentAnimation = animationHashMap.get(name);
        currentAnimation.reset();
        return true;
    }

    @Override
    public Sprite getSprite() {
        return currentAnimation.getSprite();
    }
}
