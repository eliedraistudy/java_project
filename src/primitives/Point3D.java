package primitives;

public class Point3D extends Point2D
{
    /**
     * Field for the z-component
     */
    protected Coordinate _z;


    // ***************** Constructors ********************** //

    /**
     * Default constructor, initialize with the (0,0,0) coordinate
     */
    public Point3D()
    {
        super(); //call father constructor
        _z = new Coordinate(); //assign 0 to z

    }

    /**
     * Value constructor, create according to coordinates
     * @param x x-component
     * @param y y-component
     * @param z z-component
     */
    public Point3D(Coordinate x, Coordinate y, Coordinate z)
    {
        super(x, y);
        _z = new Coordinate(z);
    }

    /**
     * Value constructor, create according to double
     * @param x x-component
     * @param y y-component
     * @param z z-component
     */
    public Point3D(double x, double y, double z)
    {
        super(x, y);
        _z = new Coordinate(z);
    }


    /**
     * Copy constructor
     * Copy the value of the parameter p into the object
     * @param p the point to copy
     */
    public Point3D(Point3D p)
    {
        super(p);
        _z = new Coordinate(p._z);
    }


    // ***************** Getters/Setters ********************** //

    /**
     * Getter for z-component
     * @return z-component of the vector (coordinate)
     */
    public Coordinate getZ(){return _z;}

    /**
     * Getter for z-component value
     * @return z-component value of the vector (double)
     */
    public double getZValue() { return _z.getCoordinate(); }

    /**
     * Apply a coordinate to the _z field
     * @param z the coordinate dto assign
     */
    public void setZ(Coordinate z)
    {
        _z.setCoordinate(z);
    }

    /**
     * Apply a value to the _z field
     * @param z the coordinate to assign
     */
    public void setZ(double z)
    {
        _z.setCoordinate(z);
    }

    /**
     * Copy the fields of p into the object
     * @param p the point to copy
     */
    public void setPoint(Point3D p)
    {
        setX(p.getX());
        setY(p.getY());
        setZ(p.getZ());
    }

    // ***************** Administration ******************** //

    /**
     * Override the compare to function
     * @param p the point to compare
     * @return 0 if all coordinates equals, 1 otherwise
     */
    @Override
    public int compareTo(Point3D p)
    {
       if(getXValue() == p.getXValue() &&
               getYValue() == p.getYValue() &&
               getZValue() == p.getZValue())
           return 0;
       else return 1;
    }

    /**
     * Override the toString classical function
     * Print out the coordinate
     * @return the string representation of the coordinate
     */
    @Override
    public String toString()
    {
        return "(" + _x + ", " + _y + ", " + _z + ")";
    }

    // ***************** Operations ******************** //


    /**
     * Function to add between 2 points
     * @param p the point to add to the call-object
     */
    public void add(Point3D p)
    {
        _x.add(p._x);
        _y.add(p._y);
        _z.add(p._z);
    }

    /**
     * Function to subtract between 2 points
     * @param p the point to subtract to the call-object
     */
    public void subtract(Point3D p)
    {
        _x.subtract(p._x);
        _y.subtract(p._y);
        _z.subtract(p._z);
    }



    /**
     * Return the distance between 2 3D-points
     * @param p the point to calculate the distance to
     * @return the distance between them (double)
     */
    public double distance(Point3D p)
    {
        //  create a point of substraction
        Point3D sub = new Point3D(this);
        sub.subtract(p);


        double X = Math.pow(sub.getXValue(),2);
        double Y = Math.pow(sub.getYValue(),2);
        double Z = Math.pow(sub.getZValue(),2);

        return Math.sqrt(X+Y+Z);

    }
}
