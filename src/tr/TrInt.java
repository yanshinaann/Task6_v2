package tr;

import java.awt.*;
import java.util.ArrayList;

public interface TrInt {
    //public void triangulate() throws NotEnoughPointsException;
    public ArrayList<Triangle2D> triangulatePolygon(ArrayList<Point> points) throws NotEnoughPointsException;
}
