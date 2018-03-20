package primitives;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {


    /**
     * Test the default constructor and get method
     */
    @Test
    public void getCoordinate1() {
        Coordinate x = new Coordinate();
        assertEquals(0,x.getCoordinate(),0);
    }

    /**
     * Test the value constructor and get method
     */
    @Test
    public void getCoordinate2() {
        Coordinate x = new Coordinate(2);
        assertEquals(2,x.getCoordinate(),0);
    }

    /**
     * Test copy constructor and get method
     */
    @Test
    public void getCoordinate3() {
        Coordinate x = new Coordinate(3);
        Coordinate y = new Coordinate(x);
        assertEquals(3,y.getCoordinate(),0);
    }

    /**
     * Test the double value setter
     */
    @Test
    public void setCoordinate() {
        Coordinate x = new Coordinate();
        x.setCoordinate(3);
        assertEquals(3,x.getCoordinate(), 0);
    }

    /**
     * Test the coordinate value setter
     */
    @Test
    public void setCoordinate1() {
        Coordinate x = new Coordinate();
        Coordinate y = new Coordinate(1);
        x.setCoordinate(y);
        assertEquals(1,x.getCoordinate(),0);
    }

    /**
     * Test compare to for equal case
     */
    @Test
    public void compareTo1() {
        Coordinate x = new Coordinate();
        Coordinate y = new Coordinate();
        assertEquals(0, x.compareTo(y), 0);

    }

    /**
     * Test compare to for x bigger than y
     */
    @Test
    public void compareTo2() {
        Coordinate x = new Coordinate(1);
        Coordinate y = new Coordinate();
        assertEquals(1, x.compareTo(y), 0);

    }

    /**
     * Test compare to for x smaller than y
     */
    @Test
    public void compareTo3() {
        Coordinate x = new Coordinate(1);
        Coordinate y = new Coordinate(2);
        assertEquals(-1, x.compareTo(y), 0);

    }

    /**
     * Test for adding 2 coordinates
     */
    @Test
    public void add() {
        Coordinate x = new Coordinate(1);
        Coordinate y = new Coordinate(2);
        x.add(y);
        assertEquals(3, x.getCoordinate(), 0);
    }

    /**
     * Test for subtract coordinate to an other
     */
    @Test
    public void subtract() {
        Coordinate x = new Coordinate(1);
        Coordinate y = new Coordinate(2);
        x.subtract(y);
        assertEquals(-1, x.getCoordinate(), 0);
    }

    /**
     * Test for to string representation with 2 floating points
     */
    @Test
    public void testToString() {
        Coordinate x = new Coordinate(2.5456);
        assertEquals("2.55", x.toString());
    }
}