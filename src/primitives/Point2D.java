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
        _x = new Coordinate(0);
        _y = new Coordinate(0);
    }

    /**
     * Value constructor
     * Initialize the fields with the value with given parameters
     * @param x the x coordinate to copy
     * @param y the y coordinate to copy
     */
    public Point2D(Coordinate x, Coordinate y) {
        _x.setCoordinate(x.getCoordinate());
        _y.setCoordinate(y.getCoordinate());
    }

    /**
     * Copy constructor
     * Initialize the vector with the vector given in parameter
     * @param point2D the vector to copy
     */
    public Point2D(Point2D point2D) {
        _x = new Coordinate(point2D._x.getCoordinate());
        _y = new Coordinate(point2D._y.getCoordinate());
    }

    // ***************** Getters/Setters ********************** //

    /**
     * Get the x coordinate
     * @return x coordinate
     */
    public Coordinate getX() { return _x; }
    public double getXValue() {return _x.getCoordinate(); }

    /**
     * Get the y coordinate
     * @return y coordinate
     */
    public Coordinate getY() {
        return _y;
    }
    public double getYValue() {return _y.getCoordinate(); }

    /**
     * Assign the value given in parameter to x
     * @param _x the value to assign
     */
    public void setX(Coordinate _x) {
        this._x.setCoordinate(_x.getCoordinate());
    }

    /**
     * Assign the value given in parameter to y
     * @param _y the value to assign
     */
    public void setY(Coordinate _y) { this._y.setCoordinate(_y.getCoordinate()); }

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
