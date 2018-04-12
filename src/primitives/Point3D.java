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

    /**
     * Override the compare to function
     * @param p the point to compare
     * @return 0 if all coordinates equals, 1 otherwise
     */
    @Override
    public int compareTo(Point3D p)
    {
        if (getX().compareTo(p.getX()) == 0 &&
                getY().compareTo(p.getY()) == 0 &&
                getZ().compareTo(p.getZ()) == 0)
           return 0;
       else return 1;
    }

    /**
     * Override the equal method for comparison
     * @param o the object to check equality with
     * @return true if all fields are the same, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        Point3D p = (Point3D)o;
        return p.getX().equals(getX()) &&
                p.getY().equals(getY()) &&
                p.getZ().equals((getZ()));
    }


    /**
     * Override the toString classical function
     * Print out the coordinate
     * @return the string representation of the coordinate
     */
    @Override
    public String toString()
    {
        return "(" + _x.toString() + ", " + _y.toString() + ", " + _z.toString() + ")";
    }

    // ***************** Operations ******************** //

    /**
     * Function to add a vector to a point
     * @param v the vector to add to the call-object
     */
    public void add(Vector v)
    {
        //  add for each coordinate the corresponding coordinate in the vector
        //  for example : x_new = _x + v._x
        _x.add(v.getHead().getX());
        _y.add(v.getHead().getY());
        _z.add(v.getHead().getZ());
    }

    /**
     * Function to subtract between 2 points
     * @param v the vector to subtract to the call-object
     */
    public void subtract(Vector v)
    {
        //  subtract for each coordinate the corresponding coordinate in the vector
        //  for example : x_new = _x - v._x
        _x.subtract(v.getHead().getX());
        _y.subtract(v.getHead().getY());
        _z.subtract(v.getHead().getZ());
    }



    /**
     * Return the distance between 2 3D-points
     * @param p the point to calculate the distance to
     * @return the distance between them (double)
     */
    public double distance(Point3D p)
    {
        //  create a point of subtraction
        Point3D sub = new Point3D(this);

        //  create the relative point and calculate the distance according
        sub.subtract(new Vector(p));


        double X = Math.pow(sub.getX().getCoordinate(),2);
        double Y = Math.pow(sub.getY().getCoordinate(),2);
        double Z = Math.pow(sub.getZ().getCoordinate(),2);

        return Math.sqrt(X+Y+Z);

    }
}
