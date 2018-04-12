package primitives;

public abstract class Point2D implements Comparable<Point2D>
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

    /*************************************************
     * --------
     * FUNCTION
     * --------
     * compareTo
     *
     * ------------
     * PARAMETER(S)
     * ------------
     * Point2D - the point to compare to this one
     *
     * ------------
     * RETURN VALUE
     * ------------
     * 0 if equal points
     * 1 otherwise
     *
     * -------
     * MEANING
     * -------
     * compare between 2 points
     *
     * --------
     * SEE ALSO
     * --------
     * every function in which we need to add vectors
     *************************************************/
    @Override
    public int compareTo(Point2D point2D) {
        if (this._x.getCoordinate() == point2D._x.getCoordinate()
                && this._y.getCoordinate() == point2D._y.getCoordinate())
            return 0;
        else
            return 1;
    }

    //  abstract function to inheritance
    public abstract int compareTo(Point3D point3D);
}
