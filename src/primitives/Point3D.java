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

    /*************************************************
     * --------
     * FUNCTION
     * --------
     * compareTo
     *
     * ------------
     * PARAMETER(S)
     * ------------
     * Point3D - the point to compare to this one
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
     * compare between 2 3D-points
     *
     * --------
     * SEE ALSO
     * --------
     * every function in which we need to compare points
     *************************************************/
    @Override
    public int compareTo(Point3D p)
    {
        if (getX().compareTo(p.getX()) == 0 &&
                getY().compareTo(p.getY()) == 0 &&
                getZ().compareTo(p.getZ()) == 0)
           return 0;
       else return 1;
    }

    /*************************************************
     * --------
     * FUNCTION
     * --------
     * equals
     *
     * ------------
     * PARAMETER(S)
     * ------------
     * Object - the point to compare to this one
     *
     * ------------
     * RETURN VALUE
     * ------------
     * True if equal points
     * False otherwise
     *
     * -------
     * MEANING
     * -------
     * compare between 2 3D-points
     *
     * --------
     * SEE ALSO
     * --------
     * every function in which we need to compare points
     *************************************************/
    @Override
    public boolean equals(Object o) {
        Point3D p = (Point3D)o;
        return p.getX().equals(getX()) &&
                p.getY().equals(getY()) &&
                p.getZ().equals((getZ()));
    }


    /*************************************************
     * --------
     * FUNCTION
     * --------
     * toString
     *
     * ------------
     * PARAMETER(S)
     * ------------
     *
     * ------------
     * RETURN VALUE
     * ------------
     * string representation of the point
     *
     * -------
     * MEANING
     * -------
     * get the string representation of the point
     *
     * --------
     * SEE ALSO
     * --------
     * every function in which we need to print out points
     *************************************************/
    @Override
    public String toString()
    {
        return "(" + _x.toString() + ", " + _y.toString() + ", " + _z.toString() + ")";
    }

    // ***************** Operations ******************** //

    /*************************************************
     * --------
     * FUNCTION
     * --------
     * add
     *
     * ------------
     * PARAMETER(S)
     * ------------
     * Vector - the vector to add to the current point
     *
     * ------------
     * RETURN VALUE
     * ------------
     * void function
     *
     * -------
     * MEANING
     * -------
     * By given a 3D-point p, add to it the translation with the vector given in parameters
     *
     * --------
     * SEE ALSO
     * --------
     * Point3D.subtract
     *************************************************/
    public void add(Vector v)
    {
        //  add for each coordinate the corresponding coordinate in the vector
        //  for example : x_new = _x + v._x
        _x.add(v.getHead().getX());
        _y.add(v.getHead().getY());
        _z.add(v.getHead().getZ());
    }

    /*************************************************
     * --------
     * FUNCTION
     * --------
     * subtract
     *
     * ------------
     * PARAMETER(S)
     * ------------
     * Vector - the vector to subtract to the current point
     *
     * ------------
     * RETURN VALUE
     * ------------
     * void function
     *
     * -------
     * MEANING
     * -------
     * By given a 3D-point p, add to it the translation with the inverse
     * of the vector given in parameter
     *
     * --------
     * SEE ALSO
     * --------
     * Point3D.add
     * Vector.scale
     *************************************************/
    public void subtract(Vector v)
    {
        //  invert the vector v
        v.scale(-1);
        //  add the invert of v which is the same than subtract v itself
        add(v);
    }



    /*************************************************
     * --------
     * FUNCTION
     * --------
     * distance
     *
     * ------------
     * PARAMETER(S)
     * ------------
     * Point3D - the point to calculate the distance from
     *
     * ------------
     * RETURN VALUE
     * ------------
     * double - distance between 2 points
     *
     * -------
     * MEANING
     * -------
     * Calculate the distance between 2 3D points
     *
     * --------
     * SEE ALSO
     * --------
     * Vector.length
     *************************************************/
    public double distance(Point3D p)
    {
        //  get the vector from a point to an other
        Vector v = new Vector(this, p);
        //return this vector's length
        return v.length();
    }
}
