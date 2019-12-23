import model.TriangleInt;
import model.Triangulation;
import tr.*;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class View extends JPanel implements KeyListener {

    private Point selected;
    private boolean isTriang;
    private boolean isGray;
    private boolean isRed;
    private final int dx = 4, dy = 4;

    private TriangleInt triangle = new Triangulation();
    private TriangleInt delaunayTriangulator = new DelaunayTriangulator();
    private ArrayList<Point> points = new ArrayList<>();
    private ArrayList<Vector2D> linkedList = new ArrayList<>();

    private ArrayList triangles = new ArrayList<>();
    ArrayList<Triangle2D> triangle2D = new ArrayList<>();

    public View() {
        setPreferredSize(new Dimension(600, 480));
        MouseHandler mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        addKeyListener(this);

    }

    //Отрисовываем на экране
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Utils.drawPolygon(points, (Graphics2D) g);
        if (isGray) Utils.drawTriangles(triangles, (Graphics2D) g);
        else Utils.drawTrianglesss(triangle2D, (Graphics2D) g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        ArrayList<Point> startPoints = new ArrayList<>(points);
        if (e.getKeyChar() == '1')
            isGray = true;
        else if (e.getKeyChar() == '2')
            isRed = true;
        else if ((e.getKeyChar() == 't') && (isGray)) {
            isTriang = true;
            triangles = triangle.triangulatePolygon(startPoints);
            // Вызов триангляции

        } else if ((e.getKeyChar() == 't') && (isRed)) {
            isTriang = true;
            // ArrayList startPoints = new ArrayList<>(points);
            if (points.size() > 0) {
                linkedList = (ArrayList<Vector2D>) Utils.pointToVector(startPoints);
                Utils.vectorToPoint(linkedList);
                delaunayTriangulator = new DelaunayTriangulator(linkedList);
                if (linkedList.size() >= 3) {
                   // delaunayTriangulator.triangulatePolygon(startPoints);
                    // ArrayList<Triangle2D> triangle2D = new ArrayList<>();
                    try {
                        triangle2D = (ArrayList<Triangle2D>) delaunayTriangulator.triangulatePolygone(startPoints);
                    } catch (NotEnoughPointsException ex) {
                        ex.printStackTrace();
                    }
                    // Utils.drawTrianglesss(triangle2D, (Graphics2D) g);
                }
               //points.clear();
            }
        } else if (e.getKeyChar() == 'c') {
            triangles.clear();
            triangle2D.clear();
            points.clear();
            isTriang = false;
            isRed = false;
            isGray = false;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private class MouseHandler extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            selected = getClosest(e.getX(), e.getY(), dx, dy);
            if (selected == null && e.getButton() == MouseEvent.BUTTON1) {
                if (!isTriang) {
                    selected = getClosest(e.getX(), e.getY(), dx, dy);
                    points.add(new Point(e.getX(), e.getY()));
                }
            } else if (selected != null && e.getButton() == MouseEvent.BUTTON3) {
                if (!isTriang) {
                    points.remove(selected);
                    repaint();
                }
            }

            repaint();

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (!isTriang) {
                if (selected == null)
                    return;
                selected.x = e.getX();
                selected.y = e.getY();
                repaint();
            } else {
                if (selected == null)
                    return;
                selected.x = e.getX();
                selected.y = e.getY();
                ArrayList<Point> startPoints = new ArrayList<>(points);
                if (isGray) {
                    triangles = triangle.triangulatePolygon(startPoints);
                } else {
                    try {
                        triangle2D = delaunayTriangulator.triangulatePolygone(points);
                    } catch (NotEnoughPointsException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            repaint();
        }

    }


    Point getClosest(int x, int y, int dx, int dy) {
        Point closest = null;
        for (Point point : points) {
            if (Math.abs(x - point.x) < dx
                    && Math.abs(y - point.y) < dy
            ) {
                closest = point;
            }
        }
        return closest;
    }


}