package org.plashub.clay.geo;

import com.raylib.Raylib;

import static com.raylib.Colors.BLANK;

public class Line<T extends Number> {
    private Point<T> start;
    private Point<T> end;
    T thickness;

    Raylib.Color color = BLANK;

    public Line(Point<T> start, Point<T> end) {
        this.start = start;
        this.end = end;
    }

    public Line(T x1, T y1, T x2, T y2) {
        this.start = new Point<>(x1, y1);
        this.end = new Point<>(x2, y2);
    }

    public Line(T x1, T y1, T x2, T y2, T thickness) {
        this.start = new Point<>(x1, y1);
        this.end = new Point<>(x2, y2);
        this.thickness = thickness;
    }

    public Line(Point<T> start, Point<T> end, T thickness) {
        this.start = start;
        this.end = end;
        this.thickness = thickness;
    }

    public Line<T> of_start(Point<T> start) {
        return new Line<>(start, end);
    }

    public Line<T> of_end(Point<T> end) {
        return new Line<>(start, end);
    }

    public Line<T> of_thickness(T thickness) {
        return new Line<>(start, end, thickness);
    }

    public Line<T> of_color(Raylib.Color color) {
        return new Line<>(start, end, thickness);
    }

    public Point<T> get_start() {
        return start;
    }

    public Point<T> get_end() {
        return end;
    }

    public T get_thickness() {
        return thickness;
    }

    public Raylib.Color get_color() {
        return color;
    }

    public void set_start(Point<T> start) {
        this.start = start;
    }

    public void set_end(Point<T> end) {
        this.end = end;
    }

    public void set_thickness(T thickness) {
        this.thickness = thickness;
    }

    public void set_color(Raylib.Color color) {
        this.color = color;
    }



}
