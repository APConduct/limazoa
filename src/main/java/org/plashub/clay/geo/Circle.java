package org.plashub.clay.geo;

import com.raylib.Raylib;

import static com.raylib.Colors.BLANK;

public class Circle<T extends Number> implements Shape<T> {

    private T radius;
    private Point<T> center;
    private Raylib.Color color = BLANK;

    public Circle(T radius) {
        this.radius = radius;
    }

    public Circle(T radius, Point<T> center) {
        this.radius = radius;
        this.center = center;
    }

    public Circle(T radius, Point<T> center, Raylib.Color color) {
        this.radius = radius;
        this.center = center;
        this.color = color;
    }

    public Circle<T> of_radius(T radius) {
        return new Circle<>(radius, center, color);
    }

    public Circle<T> at(Point<T> center) {
        return new Circle<>(radius, center, color);
    }

    public Point<T> get_center() {
        return center;
    }

    public T get_radius() {
        return radius;
    }

    public void set_radius(T radius) {
        this.radius = radius;
    }

    public void set_center(Point<T> center) {
        this.center = center;
    }


    @SuppressWarnings("unchecked")
    private T convert_to_t(Number n) {
        return switch (get_radius()) {
            case Integer i -> (T) Integer.valueOf(n.intValue());
            case Double v -> (T) Double.valueOf(n.doubleValue());
            case Float v -> (T) Float.valueOf(n.floatValue());
            case Long l -> (T) Long.valueOf(n.longValue());
            case Short i -> (T) Short.valueOf(n.shortValue());
            case Byte b -> (T) Byte.valueOf(n.byteValue());
            case null, default -> throw new UnsupportedOperationException("Unsupported type");
        };
    }


    @Override
    public void paint() {
        try(Raylib.Vector2 v = center.rl()) {
            Raylib.DrawCircleV(v, radius.floatValue(), color);
        }
    }

    public void paint_lines() {
        try(Raylib.Vector2 v = center.rl()) {
            Raylib.DrawCircleLinesV(v, radius.floatValue(), color);
        }
    }

    @Override
    public T area() {
        return convert_to_t(Math.PI * Math.pow(radius.doubleValue(), 2));
    }

    @Override
    public void move(T x, T y) {
        this.center = new Point<>(x, y);
    }

    @Override
    public void move(Point<T> point) {
        this.center = point;
    }

    @Override
    public void resize(T x, T y) {
        this.radius = x;
    }

    @Override
    public void resize(Size<T> size) {
        this.radius = size.get_width();
    }


    public Raylib.Color get_color() {
        return color;
    }

    public void set_color(Raylib.Color color) {
        this.color = color;
    }

    public boolean is_inside(Point<T> point) {
        return Math.pow(point.get_x().doubleValue() - center.get_x().doubleValue(), 2) + Math.pow(point.get_y().doubleValue() - center.get_y().doubleValue(), 2) <= Math.pow(radius.doubleValue(), 2);
    }

    public boolean is_inside(Circle<T> circle) {
        return Math.pow(circle.get_center().get_x().doubleValue() - center.get_x().doubleValue(), 2) + Math.pow(circle.get_center().get_y().doubleValue() - center.get_y().doubleValue(), 2) <= Math.pow(radius.doubleValue() - circle.get_radius().doubleValue(), 2);
    }

    public boolean intersects(Circle<T> circle) {
        return Math.pow(circle.get_center().get_x().doubleValue() - center.get_x().doubleValue(), 2) + Math.pow(circle.get_center().get_y().doubleValue() - center.get_y().doubleValue(), 2) <= Math.pow(radius.doubleValue() + circle.get_radius().doubleValue(), 2);
    }

    public Circle<T> intersection(Circle<T> circle) {
        if (!intersects(circle)) {
            return null;
        }
        double d = Math.sqrt(Math.pow(circle.get_center().get_x().doubleValue() - center.get_x().doubleValue(), 2) + Math.pow(circle.get_center().get_y().doubleValue() - center.get_y().doubleValue(), 2));
        double a = (Math.pow(radius.doubleValue(), 2) - Math.pow(circle.get_radius().doubleValue(), 2) + Math.pow(d, 2)) / (2 * d);
        double h = Math.sqrt(Math.pow(radius.doubleValue(), 2) - Math.pow(a, 2));
        double x = center.get_x().doubleValue() + a * (circle.get_center().get_x().doubleValue() - center.get_x().doubleValue()) / d;
        double y = center.get_y().doubleValue() + a * (circle.get_center().get_y().doubleValue() - center.get_y().doubleValue()) / d;
        return new Circle<>(convert_to_t(h), new Point<>(convert_to_t(x), convert_to_t(y)));
    }

    public Circle<T> union(Circle<T> circle) {
        double d = Math.sqrt(Math.pow(circle.get_center().get_x().doubleValue() - center.get_x().doubleValue(), 2) + Math.pow(circle.get_center().get_y().doubleValue() - center.get_y().doubleValue(), 2));
        double r = (radius.doubleValue() + circle.get_radius().doubleValue() + d) / 2;
        double x = center.get_x().doubleValue() + r * (circle.get_center().get_x().doubleValue() - center.get_x().doubleValue()) / d;
        double y = center.get_y().doubleValue() + r * (circle.get_center().get_y().doubleValue() - center.get_y().doubleValue()) / d;
        return new Circle<>(convert_to_t(r), new Point<>(convert_to_t(x), convert_to_t(y)));
    }


    public boolean contains(Circle<T> circle) {
        return Math.pow(circle.get_center().get_x().doubleValue() - center.get_x().doubleValue(), 2) + Math.pow(circle.get_center().get_y().doubleValue() - center.get_y().doubleValue(), 2) + Math.pow(circle.get_radius().doubleValue() - radius.doubleValue(), 2) <= Math.pow(radius.doubleValue(), 2);
    }
}
