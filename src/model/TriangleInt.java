package model;

import tr.NotEnoughPointsException;
import tr.Triangle2D;

import java.awt.*;
import java.util.ArrayList;

public interface TriangleInt {
    public ArrayList<Triangle2D> triangulatePolygone(ArrayList<Point> points) throws NotEnoughPointsException;

    ArrayList<Polygon> triangulatePolygon(ArrayList<Point> points);
}
