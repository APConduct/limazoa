package org.plashub.clay.geo;

import org.plashub.clay.Paintable;

public interface Shape<T extends Number> extends Paintable {
    void move(T x, T y);
    void move(Point<T> point);
    void resize(T x, T y);
    void resize(Size<T> size);

    T area();



    void paint();

}
