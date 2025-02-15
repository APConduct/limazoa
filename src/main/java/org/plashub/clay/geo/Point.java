package org.plashub.clay.geo;
import com.raylib.Raylib;

public class Point<T extends Number> {
    private T x;
    private T y;

    public Point(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public T get_x() {
        return x;
    }

    public T get_y() {
        return y;
    }

    public void set_x(T x) {
        this.x = x;
    }

    public void set_y(T y) {
        this.y = y;
    }

    public Raylib.Vector2 rl() {
        try(Raylib.Vector2 v = new Raylib.Vector2()) {
            return v.x((Float) x).y((Float) y);
        }
    }
}


