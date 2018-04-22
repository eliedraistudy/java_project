package geometries;

import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

public class TriangleTest {

    @Test
    public void findIntersections() {
        Ray r = new Ray(
                new Point3D(0,0,0),
                new Vector(0.9,1.9,4.9));
        Triangle t = new Triangle(
                new Point3D(4,-4,4),
                new Point3D(-3,3,5),
                new Point3D(5,2,5));

        List<Point3D> lp = t.findIntersections(r);
        assertEquals(lp.get(0), new Point3D(0.9,1.9,4.9));
    }

    @Test
    public void checkInside() {
        Point3D p = new Point3D(0.9,1.9,4.9);
        Triangle t = new Triangle(
                new Point3D(4,-4,4),
                new Point3D(-3,3,5),
                new Point3D(5,2,5));
        assertTrue(t.checkInside(p));
    }

    @Test
    public void getNormal() {
    }
}