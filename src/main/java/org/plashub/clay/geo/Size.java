package org.plashub.clay.geo;

import com.raylib.Raylib;

public class Size<T extends Number> {

    private T width;
    private T height;

    public Size(T width, T height) {
        this.width = width;
        this.height = height;
    }

    public T get_width() {
        return width;
    }

    public T get_height() {
        return height;
    }

    public void set_width(T width) {
        this.width = width;
    }

    public void set_height(T height) {
        this.height = height;
    }

    public Raylib.Vector2 rl() {
        try(Raylib.Vector2 v = new Raylib.Vector2()) {
            return v.x((Float) width).y((Float) height);
        }
    }

}
