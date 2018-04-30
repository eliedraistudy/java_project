package geometries;

import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SphereTest {

    @Test
    public void getCenter() {
    }

    @Test
    public void setCenter() {
    }

    @Test
    public void getNormal() {
    }

    @Test
    public void findIntersections()
    {
        Sphere s = new Sphere(4,new Point3D(9,9,9));
        List<Point3D> arrayList = s.FindIntersections(new Ray(new Point3D(1,2,3), new Vector(5,3,3)));

    }
}