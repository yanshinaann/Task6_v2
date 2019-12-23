package math;

import java.awt.*;

public class Vector2 extends Point {

    public double x;
    public double y;


    public Vector2(double x, double y) { // конструктор с аргументами x, y
        this.x = x;
        this.y = y;
    }

    public double cross(Vector2 v) {
        return x * v.y - y * v.x;
    } //Векторное произвдение

}