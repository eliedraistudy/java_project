package primitives;

import org.junit.Test;

import static org.junit.Assert.*;

public class VectorTest {

    @Test
    public void getHead()
    {
        Vector v = new Vector(1,2,3);
        assertEquals(v.getHead().compareTo(new Point3D(1,2,3)),0,0);
    }

    @Test
    public void getX()
    {
        Vector v = new Vector(-10.345,2,3);
        assertEquals(v.getX(), -10.345, 0 );

    }

    @Test
    public void getY()
    {
        Vector v = new Vector(-10.345,2.4567778,3);
        assertEquals(v.getY(), 2.4567778, 0 );
    }

    @Test
    public void getZ()
    {
        Vector v = new Vector(-10.345,2.4567778,0.345);
        assertEquals(v.getZ(), 0.345, 0 );
    }

    @Test
    public void setHead()
    {
        Point3D p = new Point3D(-10.345,2.4567778,0.345);
        Vector v = new Vector();
        v.setHead(p);

        assertEquals(v.getHead().compareTo(p),0,0);

    }

    @Test
    public void setHead1()
    {
        Vector v = new Vector();
        v.setHead(34.67,-32.5447,0.112);
        assertEquals(v.getHead().compareTo(new Point3D(34.67,-32.5447,0.112)),0,0);
    }

    @Test
    public void compareTo()
    {

    }

    @Test
    public void toStringTest() {
    }

    @Test
    public void add() {
    }

    @Test
    public void subtract() {
    }

    @Test
    public void scale() {
    }

    @Test
    public void crossProduct() {
    }

    @Test
    public void length() {
    }

    @Test
    public void normalize() {
    }

    @Test
    public void normalVector() {
    }

    @Test
    public void dotProduct() {
    }

    @Test
    public void isNull() {
    }

    @Test
    public void nullTest()

    {
    }
}