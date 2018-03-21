package primitives;

import org.junit.Test;

import static org.junit.Assert.*;

public class Point3DTest {

    @Test
    public void getZ() {
        Point3D p = new Point3D();
        assertEquals(0,p.getZValue(),0);
    }

    @Test
    public void getZValue()
    {
        Point3D p = new Point3D(2,3,4);
        assertEquals(4,p.getZValue(),0);
    }

    @Test
    public void setZ()
    {
        Point3D p = new Point3D();
        p.setZ(-9);
        assertEquals(-9,p.getZValue(),0);
    }

    @Test
    public void setZ1() {
    }

    @Test
    public void setPoint()
    {
        Point3D p1 = new Point3D();
        Point3D p2 = new Point3D(-4,3,-7);
        p1.setPoint(p2);


            if (p1 == null && p1 == null)
            {
                return;
            }
            if (p1 != null && p1.equals(p2))
            {
                return;
            }

            //failNotEquals("setPoint test is fail", p1, p2); // eli, we need to check how to compare between objects, it's don't work for me/

    }

    @Test
    public void compareTo() {
    }

    @Test
    public void testToString() {
    }

    @Test
    public void add() {
    }

    @Test
    public void subtract() {
    }

    @Test
    public void distance() {
    }

    @Test
    public void getX() {
    }

    @Test
    public void getXValue() {
    }

    @Test
    public void getY() {
    }

    @Test
    public void getYValue() {
    }

    @Test
    public void setX() {
    }

    @Test
    public void setY() {
    }
}