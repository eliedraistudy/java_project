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
        assertEquals(v.getHead().getX().getCoordinate(), -10.345, 0 );

    }

    @Test
    public void getY()
    {
        Vector v = new Vector(-10.345,2.4567778,3);
        assertEquals(v.getHead().getY().getCoordinate(), 2.4567778, 0 );
    }

    @Test
    public void getZ()
    {
        Vector v = new Vector(-10.345,2.4567778,0.345);
        assertEquals(v.getHead().getZ().getCoordinate(), 0.345, 0 );
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
        Vector v = new Vector(21.77,45.3,-98.012);
        assertEquals(v.getHead().compareTo(new Point3D(21.77,45.3,-98.012)),0,0);
    }

    @Test
    public void toStringTest()
    {
        Vector v = new Vector(3.22456,5.01234,6.9876);
        assertEquals(v.toString(), "(3.22, 5.01, 6.99)");
    }

    @Test
    public void add()
    {
        Vector v1 = new Vector(3,-4,5.223);
        Vector v2 = new Vector(0,2.33,-5.223);
        v1.add(v2);

        assertEquals(v1.compareTo(new Vector(3,-1.67,0)),0,0);
    }

    @Test
    public void subtract()
    {
        Vector v1 = new Vector(3,-4,5.223);
        Vector v2 = new Vector(0,2.33,-5.223);
        v1.subtract(v2);

        assertEquals(v1.compareTo(new Vector(3,-6.33,10.446)),0,0);
    }

    @Test
    public void scale()
    {
        Vector v = new Vector(3,-4,5.2);
        v.scale(-2.11);
        assertEquals(v.compareTo(new Vector(-6.33,8.44,-10.972)),0,0);
    }

    @Test
    public void crossProduct()
    {
        Vector v1 = new Vector(3,-2,0);
        Vector v2 = new Vector(1.1,4.2,3.034);
        Vector v3 = v1.crossProduct(v2);

        assertEquals(v3.compareTo(new Vector(-6.068, -9.102, 14.8)),0,0);
    }

    @Test
    public void length()
    {
        Vector v1 = new Vector(3.5,-2,0);
        assertEquals(v1.length(),4.031128874,0.000000001);

    }

    @Test
    public void normalize() throws Exception
    {
        Vector v1 = new Vector(-5,2,1);
        v1.normalize();

        //  test length of 1
        assertEquals(1,v1.length(),0);
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