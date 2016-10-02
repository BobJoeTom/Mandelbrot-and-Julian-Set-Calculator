package io.gitbuh.bobjoetom;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Brenden on 7/24/2016.
 */
public class Point {
    float x;
    float y;
    float tint;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getTint() {
        return tint;
    }

    public void setTint(float tint) {
        this.tint = tint;
    }

    public Point(float x, float y, float tint) {
        this.x = x;
        this.y = y;
        this.tint = tint;
    }
}
