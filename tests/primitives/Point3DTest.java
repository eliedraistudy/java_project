package primitives;

import javafx.scene.effect.Light;
import org.junit.Test;

import static org.junit.Assert.*;

public class Point3DTest {

    @Test
    public void getZ() {
        Point3D p = new Point3D();
        assertEquals(0,p.getZ().getCoordinate(),0);
    }

    @Test
    public void setZ()
    {
        Point3D p = new Point3D();
        p.setZ(-9);
        assertEquals(-9,p.getZ().getCoordinate(),0);
    }


    @Test
    public void setPoint()
    {
        Point3D p1 = new Point3D();
        Point3D p2 = new Point3D(-4,3,-7);
        p1.setPoint(p2);
        assertEquals(0, p1.compareTo(p2),0);
    }

    /**
     * tests if two object references do not point to the same object
     */
    @Test
    public void setPoint2()
    {
        Point3D p1 = new Point3D();
        Point3D p2 = new Point3D(-4,3,-7);
        p1.setPoint(p2);

        assertNotSame(p1,p2);
    }

    @Test
    public void compareTo()
    {
        Point3D p1 = new Point3D(3,-4,5);
        Point3D p2 = new Point3D(3,-4,5);
        assertEquals(p1.compareTo(p2), 0,0);
    }

    @Test
    public void testToString()
    {
        Point3D p = new Point3D(3.22456,5.01234,6.9876);
        assertEquals(p.toString(), "(3.22, 5.01, 6.99)");
    }

    @Test
    public void add()
    {
        Point3D p1 = new Point3D(-3,5,1);
        Vector v = new Vector(7,-8,0);
        p1.add(v);
        assertEquals(p1, new Point3D(-3+7, 5-8, 1+0));
    }

    @Test
    public void subtract() {
        Point3D p1 = new Point3D(-3,5,1);
        Vector v = new Vector(7,-8,0);
        p1.subtract(v);
        assertEquals(p1, new Point3D(-3-7, 5+8, 1-0));
    }

    @Test
    public void distance()
    {
        Point3D p = new Point3D(-2,2,-1);
        double d = p.distance(new Point3D());
        assertEquals(3,d,0);
    }

    @Test
    public void getX()
    {
        Point3D p = new Point3D(-2,2,-1);
        assertEquals(-2,p.getX().getCoordinate(),0);
    }

    @Test
    public void getXValue()
    {
        Point3D p = new Point3D(-32.667,2,-1);
        assertEquals(-32.667,p.getX().getCoordinate(),0);
    }

    @Test
    public void getY()
    {
        Point3D p = new Point3D(-32.667,2.234,-1);
        assertEquals(2.234,p.getY().getCoordinate(),0);
    }

    @Test
    public void getYValue()
    {
        Point3D p = new Point3D(-32.667,2.234,-1);
        assertEquals(2.234,p.getY().getCoordinate(),0);
    }

    @Test
    public void setX()
    {
        Point3D p = new Point3D(-32.667,2.234,-1);
        p.setX(new Coordinate(10.27));
        assertEquals(10.27,p.getX().getCoordinate(),0);
    }

    @Test
    public void setY()
    {
        Point3D p = new Point3D(-32.667,2.234,-1);
        p.setY(new Coordinate(10.27));
        assertEquals(10.27,p.getY().getCoordinate(),0);
    }
}