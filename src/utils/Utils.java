package utils;

import tr.Triangle2D;
import tr.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static void drawPolygon(ArrayList<Point> points, Graphics2D g) {

        for (int p = 0; p < points.size(); p++) { // Пробегаемся по всем точкам
            Point p1 = points.get(p % points.size());
            Point p2 = points.get((p + 1) % points.size());
            g.setColor(Color.BLACK);
            g.drawLine(p1.x, p1.y, p2.x, p2.y); //Линия между двумя точками
        }
    }

    public static void drawTrianglesss(ArrayList<Triangle2D> triangle2DS, Graphics2D g) {
        int arrx[] = new int[triangle2DS.size() * 3];
        int arry[] = new int[triangle2DS.size() * 3];

        // Пробегаемся по всем точкам
        int n = 1;
//        for (int i = 0; i < triangle2DS.size(); i++) {
//            for (int j = 0; j < triangle2DS.size() * 3; j += n+1) {
//                n = j;
//                arrx[j] = (int) triangle2DS.get(i).a.x;
//                arry[j] = (int) triangle2DS.get(i).a.y;
//                n = j + 1;
//                arrx[n] = (int) triangle2DS.get(i).b.x;
//                arry[n] = (int) triangle2DS.get(i).b.y;
//                n = j + 2;
//                arrx[n] = (int) triangle2DS.get(i).c.x;
//                arry[n] = (int) triangle2DS.get(i).c.y;
//            }
//
//        }
        n = 0;
        for (int i = 0; i < triangle2DS.size(); i++) {
            arrx[n] = (int) triangle2DS.get(i).a.x;
            arry[n] = (int) triangle2DS.get(i).a.y;
            arrx[1 + n] = (int) triangle2DS.get(i).b.x;
            arry[1 + n] = (int) triangle2DS.get(i).b.y;
            arrx[2 + n] = (int) triangle2DS.get(i).c.x;
            arry[2 + n] = (int) triangle2DS.get(i).c.y;
            n += 3;
        }


        g.setColor(Color.BLACK);
        g.drawPolygon(arrx, arry, arrx.length); //Линии между точками

    }

    //контур и заполненеие треугольников

    public static void drawTriangles(ArrayList<Polygon> triangles, Graphics2D g) {
        //Проход по всем элементам массива треугольников
        for (int index = 0; index < triangles.size(); index++) {
            Polygon triangle = triangles.get(index);

            //Контур
            g.setColor(Color.BLACK);
            g.drawPolygon(triangle);

            //Заливка
            g.setColor(Color.GRAY);
            g.fillPolygon(triangle);
        }

    }

    public static ArrayList<Point> vectorToPoint(java.util.List<Vector2D> listVector2D) {
        ArrayList<Point> arrayList = new ArrayList<>();
        for (int i = 0; i < listVector2D.size(); i++)
            arrayList.add(i, new Point(listVector2D.get(i)));
        return arrayList;
    }

    public static java.util.List<Vector2D> pointToVector(ArrayList<Point> pointArrayList) {
        List<Vector2D> vector2DList = new ArrayList<>();
        for (int i = 0; i < pointArrayList.size(); i++)
            vector2DList.add(i, new Vector2D(pointArrayList.get(i).x, pointArrayList.get(i).y));
        return vector2DList;
    }
}
