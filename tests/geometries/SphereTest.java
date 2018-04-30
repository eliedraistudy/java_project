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
        Sphere s = new Sphere(180,new Point3D(200,50,0));
        List<Point3D> arrayList = s.FindIntersections(
                new Ray(
                        new Point3D(0,0,0),
                        new Vector(100,80,15)
                )
        );

    }
}