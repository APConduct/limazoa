package org.plashub.clay.geo;

import com.raylib.Raylib;

import static com.raylib.Colors.BLANK;
import static com.raylib.Colors.RED;
import static com.raylib.Raylib.*;

public class Rect<T extends Number> implements Shape<T> {
    private Point<T> origin;
    private Size<T> size;
    Color color = BLANK;

    public Rect(Point<T> origin, Size<T> size) {
        this.origin = origin;
        this.size = size;
    }

    public Rect(T x, T y, T width, T height) {
        this.origin = new Point<>(x, y);
        this.size = new Size<>(width, height);
    }

    public Rect(T x, T y, T width, T height, Color color) {
        this.origin = new Point<>(x, y);
        this.size = new Size<>(width, height);
        this.color = color;
    }

    public Rect<T> of_x(T x) {
        return new Rect<>(x, origin.get_y(), size.get_width(), size.get_height());
    }

    public Rect<T> of_y(T y) {
        return new Rect<>(origin.get_x(), y, size.get_width(), size.get_height());
    }

    public Rect<T> of_width(T width) {
        return new Rect<>(origin.get_x(), origin.get_y(), width, size.get_height());
    }

    public Rect<T> of_height(T height) {
        return new Rect<>(origin.get_x(), origin.get_y(), size.get_width(), height);
    }

    public Rect<T> of_size(Size<T> size) {
        return new Rect<>(origin.get_x(), origin.get_y(), size.get_width(), size.get_height());
    }

    public Rect<T> at(Point<T> origin) {
        return new Rect<>(origin.get_x(), origin.get_y(), size.get_width(), size.get_height());
    }

    public Point<T> get_origin() {
        return origin;
    }

    public Size<T> get_size() {
        return size;
    }

    public void set_origin(Point<T> origin) {
        this.origin = origin;
    }

    public void set_size(Size<T> size) {
        this.size = size;
    }

    public Raylib.Rectangle rl() {
        try(Raylib.Rectangle r = new Raylib.Rectangle()) {
            return r.x((Float) origin.get_x()).y((Float) origin.get_y()).width((Float) size.get_width()).height((Float) size.get_height());
        }
    }

    public T top () {
        return (T) origin.get_y();
    }

    @SuppressWarnings("unchecked")
    private T convert_to_t(Number n) {
        return switch (origin.get_x()) {
            case Integer i -> (T) Integer.valueOf(n.intValue());
            case Double v -> (T) Double.valueOf(n.doubleValue());
            case Float v -> (T) Float.valueOf(n.floatValue());
            case Long l -> (T) Long.valueOf(n.longValue());
            case Short i -> (T) Short.valueOf(n.shortValue());
            case Byte b -> (T) Byte.valueOf(n.byteValue());
            case null, default -> throw new UnsupportedOperationException("Unsupported type");
        };
    }

    T right () {
        return convert_to_t(origin.get_x().doubleValue() + size.get_width().doubleValue());
    }

    T bottom () {
        return convert_to_t(origin.get_y().doubleValue() + size.get_height().doubleValue());
    }

    T left () {
        return origin.get_x();
    }

    public boolean contains(Point<T> p) {
        return p.get_x().doubleValue() >= origin.get_x().doubleValue() && p.get_x().doubleValue() <= right().doubleValue() && p.get_y().doubleValue() >= origin.get_y().doubleValue() && p.get_y().doubleValue() <= bottom().doubleValue();
    }

    public boolean contains(Rect<T> r) {
        return r.left().doubleValue() >= left().doubleValue() && r.right().doubleValue() <= right().doubleValue() && r.top().doubleValue() >= top().doubleValue() && r.bottom().doubleValue() <= bottom().doubleValue();
    }

    public boolean intersects(Rect<T> r) {
        return r.left().doubleValue() < right().doubleValue() && r.right().doubleValue() > left().doubleValue() && r.top().doubleValue() < bottom().doubleValue() && r.bottom().doubleValue() > top().doubleValue();
    }

    public Rect<T> intersection(Rect<T> r) {
        if (!intersects(r)) {
            return null;
        }
        T x = r.left().doubleValue() > left().doubleValue() ? r.left() : left();
        T y = r.top().doubleValue() > top().doubleValue() ? r.top() : top();
        T width = r.right().doubleValue() < right().doubleValue() ? convert_to_t(r.right().doubleValue() - x.doubleValue()) : convert_to_t(right().doubleValue() - x.doubleValue());
        T height = r.bottom().doubleValue() < bottom().doubleValue() ? convert_to_t(r.bottom().doubleValue() - y.doubleValue()) : convert_to_t(bottom().doubleValue() - y.doubleValue());
        return new Rect<>(x, y, width, height);
    }

    public Rect<T> union(Rect<T> r) {
        T x = r.left().doubleValue() < left().doubleValue() ? r.left() : left();
        T y = r.top().doubleValue() < top().doubleValue() ? r.top() : top();
        T width = r.right().doubleValue() > right().doubleValue() ? convert_to_t(r.right().doubleValue() - x.doubleValue()) : convert_to_t(right().doubleValue() - x.doubleValue());
        T height = r.bottom().doubleValue() > bottom().doubleValue() ? convert_to_t(r.bottom().doubleValue() - y.doubleValue()) : convert_to_t(bottom().doubleValue() - y.doubleValue());
        return new Rect<>(x, y, width, height);
    }

    @Override
    public void paint() {
        DrawRectangleRec(rl(), RED);
    }

    @Override
    public T area() {
        return convert_to_t(size.get_width().doubleValue() * size.get_height().doubleValue());
    }

    @Override
    public void move(T x, T y) {
        origin.set_x(x);
        origin.set_y(y);
    }

    @Override
    public void move(Point<T> point) {
        origin = point;
    }

    @Override
    public void resize(T x, T y) {
        size.set_width(x);
        size.set_height(y);
    }

    @Override
    public void resize(Size<T> size) {
        this.size = size;
    }



}
