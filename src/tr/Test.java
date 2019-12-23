package tr;
import java.util.List;

public class Test {


    public void testThatTriangleSoupIsInitiallyEmpty() {
        TriangleSoup soup = new TriangleSoup();
        List<Triangle2D> triangles = soup.getTriangles();

    }


    public void testAddTriangle() {
        TriangleSoup soup = new TriangleSoup();
        soup.add(new Triangle2D(new Vector2D(0, 0), new Vector2D(1, 0), new Vector2D(1, 1)));

        List<Triangle2D> triangles = soup.getTriangles();

    }


    public void testRemoveTriangle() {
        TriangleSoup soup = new TriangleSoup();
        Triangle2D triangle = new Triangle2D(new Vector2D(0, 0), new Vector2D(1, 0), new Vector2D(1, 1));

        soup.add(triangle);
        soup.remove(triangle);

        List<Triangle2D> triangles = soup.getTriangles();

    }

}