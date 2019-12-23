package model;

import com.sun.javafx.scene.paint.GradientUtils;
import math.Vector2;
import tr.NotEnoughPointsException;
import tr.Triangle2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Triangulation implements TriangleInt {


    @Override
    public ArrayList<Triangle2D> triangulatePolygone(ArrayList<Point> points) throws NotEnoughPointsException {
        return null;
    }

    // треангуляция многоугольника
    @Override
    public ArrayList<Polygon> triangulatePolygon(ArrayList<Point> points) {
        ArrayList<Polygon> triangles = new ArrayList<>();
        boolean clockwise = isClockwise(points);
        int index = 0;

        while (points.size() > 2) { //Циклическая проверка для триангуляции

            Point p1 = points.get((index) % points.size());
            Point p2 = points.get((index + 1) % points.size()); //Получаем 3 вершины
            Point p3 = points.get((index + 2) % points.size());

            Vector2 v1 = new Vector2(p2.x - p1.x, p2.y - p1.y);
            Vector2 v2 = new Vector2(p3.x - p1.x, p3.y - p1.y);
            double cross = v1.cross(v2); //векторное произведение

            Polygon triangle = new Polygon(); // Добавляем вершины в треугольник
            triangle.addPoint(p1.x, p1.y);
            triangle.addPoint(p2.x, p2.y);
            triangle.addPoint(p3.x, p3.y);


            /*Если значение векторного произведения > 0, то многоугольник строился по часовой
              стрелке, проверяем на возможность существования треугольника */
            if (!clockwise && cross >= 0 && validTriangle(triangle, p1, p2, p3, points)) {
                points.remove(p2); //Исключаем вершину
                triangles.add(triangle); //Строим треугольник
                // System.out.println("По часовой");

            } else if (clockwise && cross <= 0 && validTriangle(triangle, p1, p2, p3, points)) {
                points.remove(p2);
                triangles.add(triangle);
                // System.out.println("Против часовой");
            } else {
                index++; //Для перехода на следующие точки
            }
        }
        return triangles;
    }

    //Проверка действительности треугольника
    public boolean validTriangle(Polygon triangle, Point p1, Point p2, Point p3, List<Point> points) {
        for (Point p : points) {
            if (p != p1 && p != p2 && p != p3 && triangle.contains(p)) {
                return false;
            }
        }
        return true;
    }


    // Для проверки оборота, по часовой если сумма > 0
    public boolean isClockwise(List<Point> points) {
        int sum = 0;
        for (int i = 0; i < points.size(); i++) {
            Point p1 = points.get(i);
            Point p2 = points.get((i + 1) % points.size());
            sum += (p2.x - p1.x) * (p2.y + p1.y);
        }
        return sum >= 0;
    }
}
/*сделать интерфейс для триангуляции+
на вход фигуру - на выход треугольничики+
draw в утилиту+
тянуть точку, удалять,+
ретриангуляцию при перетаскивании+
фигура - замкнутая линия+

1.мое разбиение
2.равномерное разбиение
позволяется выбирать метод разбиения

сплайн или арка или прямая линия через интерфейс - выбор

*/
