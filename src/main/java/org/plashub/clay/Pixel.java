package org.plashub.clay;

import com.raylib.Raylib;
import org.plashub.clay.geo.Point;

import static com.raylib.Colors.BLANK;

public class Pixel {
    Point<Float> point;
    Raylib.Color color;

    public Pixel(Point<Float> point) {
        this.point = point;
        this.color = BLANK;
    }

    public Pixel(Float x, Float y) {
        this.point = new Point<>(x, y);
        this.color = BLANK;
    }

    public Pixel(Float x, Float y, Raylib.Color color) {
        this.point = new Point<>(x, y);
        this.color = color;
    }

    public Pixel of_x(Float x) {
        return new Pixel(x, point.get_y());
    }

    public Pixel of_y(Float y) {
        return new Pixel(point.get_x(), y);
    }

    public Pixel of_color(Raylib.Color color) {
        return new Pixel(point.get_x(), point.get_y(), color);
    }

    public Point<Float> get_point() {
        return point;
    }

    public Raylib.Color get_color() {
        return color;
    }

    public void paint() {
        Raylib.DrawPixel(point.get_x().intValue(), point.get_y().intValue(), color);
    }

}
