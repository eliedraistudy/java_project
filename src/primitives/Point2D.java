package primitives;

public abstract class Point2D
{

    /**
     * coordinate x
     */
    protected Coordinate _x;
    /**
     * coordinate y
     */
    protected Coordinate _y;

    // ***************** Constructors ********************** //

    /**
     * Default constructor
     * Initialize a null vector
     */
    public Point2D() {
        _x = new Coordinate();
        _y = new Coordinate();
    }

    /**
     * Value constructor
     * Initialize the fields with the value with given parameters
     * @param x the x coordinate to copy
     * @param y the y coordinate to copy
     */
    public Point2D(Coordinate x, Coordinate y) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
    }

    /**
     * Value constructor
     * Initialize the fields with the value with given parameters
     * @param x x-value coordinate
     * @param y y-value coordinate
     */
    public Point2D(double x, double y)
    {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
    }

    /**
     * Copy constructor
     * Initialize the vector with the vector given in parameter
     * @param p the vector to copy
     */
    public Point2D(Point2D p) {
        _x = new Coordinate(p.getX());
        _y = new Coordinate(p.getY());
    }

    // ***************** Getters/Setters ********************** //

    /**
     * Get the x coordinate
     * @return x coordinate (coordinate)
     */
    public Coordinate getX() { return _x; }


    /**
     * Get the y coordinate
     * @return y coordinate (coordinate)
     */
    public Coordinate getY() {
        return _y;
    }


    /**
     * Assign the value given in parameter to x
     * @param x the value to assign
     */
    public void setX(Coordinate x){ _x.setCoordinate(x); }

    /**
     * Assign the value given in parameter to y
     * @param y the value to assign
     */
    public void setY(Coordinate y) { _y.setCoordinate(y); }

    // ***************** Administration ******************** //

    /**
     * To compare between 2 point2D
     * Comparison according to the values in the fields
     * @param point2D the point2D to compare to
     * @return 1 if differents and 0 if equals
     */
    public int compareTo(Point2D point2D) // return 0 if equal and 1 id not equal
    {
        if (this._x.getCoordinate() == point2D._x.getCoordinate()
                && this._y.getCoordinate() == point2D._y.getCoordinate())
            return 0;
        else
            return 1;
    }

    // ***************** Administration ******************** //
    public abstract int compareTo(Point3D point3D);
}
