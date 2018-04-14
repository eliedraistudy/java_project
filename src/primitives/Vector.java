package primitives;

public class Vector implements Comparable<Vector>
{
    /**
     * Represent the head of the vector
     */
    private Point3D _head;

    // ***************** Constructors ********************** //

    /**
     * Default constructor
     * Initialize with null vector
     */
    public Vector(){_head = new Point3D();}

    /**
     * Value constructor
     * Initialize the _head with the parameter enterred
     * @param head value of the head field
     */
    public Vector(Point3D head){ _head = new Point3D(head); }

    /**
     * Copy constructor
     * Initialize the object with the parameter enterred
     * @param vector the vector to copy
     */
    public Vector(Vector vector){_head = new Point3D(vector._head);}

    /**
     * Value constructor
     * Initialize the values of the _head according to the parameters
     * @param xHead x component of the vector
     * @param yHead y component of the vector
     * @param zHead z component of the vector
     */
    public Vector(double xHead, double yHead, double zHead)
    {
        _head = new Point3D(xHead,yHead,zHead);
    }

    /**
     * Create a vector which the base is the head of p1 and the head is head of p2
     * Do substract p2 - p1
     * @param p1 head of vector 1
     * @param p2 head of vector 2
     */
    public Vector(Point3D p1, Point3D p2)
    {
        Vector v = new Vector(p2);
        v.subtract(new Vector(p1));
        _head = new Point3D(v.getHead());
    }

    // ***************** Getters/Setters ********************** //

    /**
     * Getter of the field _head
     * @return the value of _head
     */
    public Point3D getHead(){ return _head; }


    /**
     * Assign point3D value to _head
     * @param head the value to assign
     */
    public void setHead(Point3D head){ _head = new Point3D(head); }

    /**
     * Assign value to the head
     * @param x x component
     * @param y y component
     * @param z z component
     */
    public void setHead(double x, double y, double z) { _head = new Point3D(x, y, z); }

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
     * Vector - the vector to compare to the current one
     *
     * ------------
     * RETURN VALUE
     * ------------
     * 1 if the current vector is different than the other one
     * 0 if they are equals
     *
     * -------
     * MEANING
     * -------
     * Implement comparison between vectors
     *
     * --------
     * SEE ALSO
     * --------
     * Point3D.compareTot
     *************************************************/
    @Override
    public int compareTo(Vector vector) // 0 if equal 1 if not equal
    {
        return _head.compareTo(vector._head);
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
     * Object - the vector to compare to the current one
     *
     * ------------
     * RETURN VALUE
     * ------------
     * True if they are equals
     * False otherwise
     *
     * -------
     * MEANING
     * -------
     * Implement comparison between vectors
     *
     * --------
     * SEE ALSO
     * --------
     * Point3D.compareTot
     *************************************************/
    @Override
    public boolean equals(Object o){

        double this_x = getHead().getX().getCoordinate();
        double this_y = getHead().getY().getCoordinate();
        double this_z = getHead().getZ().getCoordinate();

        double x = getHead().getX().getCoordinate();
        double y = getHead().getY().getCoordinate();
        double z = getHead().getZ().getCoordinate();

        return this_x == x && this_y == y && this_z == z;
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
     * The string representation of the vector with 2 floating points for each coordinates
     * "(x.00, y.00, z.00)"
     *
     * -------
     * MEANING
     * -------
     * Got the string representation of the vector
     *
     * --------
     * SEE ALSO
     * --------
     *************************************************/
    @Override
    public String toString()
    {
        return _head.toString();
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
     * Vector - the vector to add to the current one
     *
     * ------------
     * RETURN VALUE
     * ------------
     * void function
     *
     * -------
     * MEANING
     * -------
     * add to each coordinate of the current vector, the corresponding coordinate of the vector parameter
     *
     * --------
     * SEE ALSO
     * --------
     *************************************************/
    public void add (Vector vector)
    {
        _head.add(vector);
    }

    /*************************************************
     * --------
     * FUNCTION
     * --------
     * add
     *
     * ------------
     * PARAMETER(S)
     * ------------
     * Vector - the vector to subtract to the current one
     *
     * ------------
     * RETURN VALUE
     * ------------
     * void function
     *
     * -------
     * MEANING
     * -------
     * Function to subtract a vector to an existing one
     *
     * --------
     * SEE ALSO
     * --------
     *************************************************/
    public void subtract (Vector vector)
    {
        _head.subtract(vector);
    }

    /*************************************************
     * --------
     * FUNCTION
     * --------
     * scale
     *
     * ------------
     * PARAMETER(S)
     * ------------
     * double - a real number for multiplication
     *
     * ------------
     * RETURN VALUE
     * ------------
     * void function
     *
     * -------
     * MEANING
     * -------
     * Multiply each coordinate in the vector by the real number given in parameter
     *
     * --------
     * SEE ALSO
     * --------
     * geometries.FlatGeometry.intersections
     *************************************************/
    public void scale(double s)
    {
        double xx = _head.getX().getCoordinate()*s,
                yy = _head.getY().getCoordinate()*s,
                zz = _head.getZ().getCoordinate()*s;

        _head = new Point3D(xx,yy,zz);
    }

    /*************************************************
     * --------
     * FUNCTION
     * --------
     * crossProduct
     *
     * ------------
     * PARAMETER(S)
     * ------------
     * Vector - the vector to perform cross product with
     *
     * ------------
     * RETURN VALUE
     * ------------
     * a new vector corresponding to the operation
     *
     * -------
     * MEANING
     * -------
     * By given :
     * our current vector w = (wx, wy, wz)
     * the parameter vector v = (vx, vy, vz)
     *
     * return a new vector :
     * V = (wy*vz - wz*vy, wz*vx - wx*vz, wx*vy - wy*vx)
     *
     * --------
     * SEE ALSO
     * --------
     *************************************************/
    public Vector crossProduct(Vector vector) {

        //  coordinate of the current object
        double x = getHead().getX().getCoordinate();
        double y = getHead().getY().getCoordinate();
        double z = getHead().getZ().getCoordinate();

        //  coordinate of the vector
        double vx = vector.getHead().getX().getCoordinate();
        double vy = vector.getHead().getY().getCoordinate();
        double vz = vector.getHead().getZ().getCoordinate();

        //  x,y,z component
        double x_component = y*vz - z*vy;
        double y_component = z*vx - x*vz;
        double z_component = x*vy-y*vx;

        return new Vector(x_component, y_component,z_component);
    }

    /*************************************************
     * --------
     * FUNCTION
     * --------
     * length
     *
     * ------------
     * PARAMETER(S)
     * ------------
     *
     * ------------
     * RETURN VALUE
     * ------------
     * return the value of the vector size
     *
     * -------
     * MEANING
     * -------
     * calculate the vector's length according with its parameters ant the formula :
     * ret = sqrt(x*x + y*y + z*z)
     *
     * --------
     * SEE ALSO
     * --------
     *************************************************/
    public double length()
    {
        double x = _head.getX().getCoordinate();
        double y = _head.getY().getCoordinate();
        double z = _head.getZ().getCoordinate();


        return Math.sqrt(x*x+y*y+z*z);
    }

    /*************************************************
     * --------
     * FUNCTION
     * --------
     * normalize
     *
     * ------------
     * PARAMETER(S)
     * ------------
     *
     * ------------
     * RETURN VALUE
     * ------------
     * void function
     *
     * -------
     * MEANING
     * -------
     * Normalize the vector to give it a length of 1 by dividing each coordinate with its old length
     * Check first if the vector is no null vector
     *
     * --------
     * SEE ALSO
     * --------
     * Vector.normalVector
     *************************************************/
    public void normalize() throws Exception
    {
        //  give to the _head field the values of the corresponding normal vector
        _head = new Point3D(normalVector()._head);
    }

    /*************************************************
     * --------
     * FUNCTION
     * --------
     * normalVector
     *
     * ------------
     * PARAMETER(S)
     * ------------
     *
     * ------------
     * RETURN VALUE
     * ------------
     * A vector with same direction of the current one but with a length of 1
     *
     * -------
     * MEANING
     * -------
     * Like in Vector.normalize, return a vector with same direction of the current one but with a length of 1
     * Throws exception if null vector
     *
     * --------
     * SEE ALSO
     * --------
     *************************************************/
    public Vector normalVector() throws Exception
    {
        if(length() == 0) throw new Exception("Error, null vector");

        //  divide each coordinates value by the current length
        return new Vector(
                _head._x.getCoordinate()/length(),
                _head._y.getCoordinate()/length(),
                _head._z.getCoordinate()/length());
    }

    /*************************************************
     * --------
     * FUNCTION
     * --------
     * dotProduct
     *
     * ------------
     * PARAMETER(S)
     * ------------
     * Vector - the vector to perform with the dot product
     *
     * ------------
     * RETURN VALUE
     * ------------
     * return the double value of the dot product
     *
     * -------
     * MEANING
     * -------
     * By given :
     * v = (vx, vy, vz)
     * w = (wx, wy, wz)
     *
     * return vx*wx + vy*wy + vz*wz
     *
     * --------
     * SEE ALSO
     * --------
     *************************************************/
    public double dotProduct(Vector vector)
    {
        //  perform multiplication between each corresponding coordinates

        //  X - coordinates
        double x = getHead().getX().getCoordinate()*
                vector.getHead().getX().getCoordinate();

        //  Y - coordinates
        double y = getHead().getY().getCoordinate()*
                vector.getHead().getY().getCoordinate();

        //  Z - coordinates
        double z = getHead().getZ().getCoordinate()*
                vector.getHead().getZ().getCoordinate();

        return x+y+z;
    }

    /**
     * Check if a vector is null
     * @return return true if the vector has coordinates (0,0,0)
     */
    public boolean isNull()
    {
        return equals(Null());
    }

    /**
     * Static function to get the null vector
     * @return an instance of null vector
     */
    public static Vector Null() { return new Vector(); }
}
