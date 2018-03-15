package primitives;

public class Vector
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
    public Vector(Point3D head){this._head = new Point3D(head);}

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
        double x = - p1.getX().getCoordinate() + p2.getX().getCoordinate();
        double y = - p1.getY().getCoordinate() + p2.getY().getCoordinate();
        double z = - p1.getZ().getCoordinate() + p2.getZ().getCoordinate();
        this._head = new Point3D(x,y,z);
    }

    // ***************** Getters/Setters ********************** //

    /**
     * Getter of the field _head
     * @return the value of _head
     */
    public Point3D getHead(){ return _head; }

    /**
     * Assign value to _head
     * @param head the value to assign
     */
    public void setHead(Point3D head){ this._head = new Point3D(head); }

    // ***************** Administration ******************** //


    /**
     * Override the compare to function in object
     * Compare between 2 vectors according to their length
     * @param vector the vector2 to compare
     * @return -1 if this vector is smaller, 1 if bigger and 0 if equals
     */
    public int compareTo(Vector vector) // 0 if equal 1 if not equal
    {

        if (length() > vector.length())
            return 1;
        else if (length() < vector.length())
            return -1;

        else return 0; // if vectors are equal

    }

    /**
     * Override of toString
     * @return the string representation of the vector (3 coordinates)
     */
    public String toString() // lhazek 2 sfrot after the dot
    {
        return "(" + _head.getX().getCoordinate() + "," + _head.getY().getCoordinate() + ","
                + _head.getZ().getCoordinate() + ")";
    }


    // ***************** Operations ******************** //

    /**
     * Function to add a vector to an existing one
     * @param vector the vector to add
     */
    public void add (Vector vector )
    {
        _head._x.setCoordinate(_head._x.getCoordinate() + vector._head._x.getCoordinate());
        _head._y.setCoordinate(_head._y.getCoordinate() + vector._head._y.getCoordinate());
        _head._z.setCoordinate(_head._z.getCoordinate() + vector._head.getZ().getCoordinate());
    }

    /**
     * Function to substract a vector to an existing one
     * @param vector
     */
    public void subtract (Vector vector)
    {
        _head._x.setCoordinate(_head._x.getCoordinate() - vector._head._x.getCoordinate());
        _head._y.setCoordinate(_head._y.getCoordinate() - vector._head._y.getCoordinate());
        _head._z.setCoordinate(_head._z.getCoordinate() - vector._head.getZ().getCoordinate());
    }

    /**
     * Function to apply scalar multiplication to a vector
     * The parameter scaling factor multiplies each coordinate of the vector
     * @param scalingFactor multiplies each coordinate of the vector
     */
    public void scale(double scalingFactor)
    {
        _head._x.setCoordinate(_head._x.getCoordinate() * scalingFactor);
        _head._y.setCoordinate(_head._y.getCoordinate() * scalingFactor);
        _head._z.setCoordinate(_head._z.getCoordinate() * scalingFactor);
    }

    /**
     * Apply dotProduct between 2 vectors
     * @param vector the vector to do the cross product with
     * @return the resulting vector of the dot product
     */
    public Vector crossProduct(Vector vector)
    {
        double x_component =
                this._head._y.getCoordinate()*vector._head._z.getCoordinate()
                - this._head._z.getCoordinate()*vector._head._y.getCoordinate();

        double y_component =
                this._head._z.getCoordinate()*vector._head._x.getCoordinate()
                - this._head._x.getCoordinate()*vector._head._z.getCoordinate();

        double z_component =
                this._head._x.getCoordinate()*vector._head._y.getCoordinate()
                - this._head._y.getCoordinate()*vector._head._x.getCoordinate();

        return new Vector(x_component,y_component,z_component);
    }

    /**
     * Find the length of the vector
     * @return
     */
    public double length()
    {
        double powerOf_X = Math.pow(_head._x.getCoordinate(),2);
        double powerOf_Y = Math.pow(_head._y.getCoordinate(),2);
        double powerOf_Z = Math.pow(_head._z.getCoordinate(),2);

        return Math.sqrt(powerOf_X + powerOf_Y + powerOf_Z);
    }

    /**
     * Normalize the vector to be with length of 1
     * @throws Exception
     * SEE ALSO function normalVector()
     */
    public void normalize() throws Exception
    {
        if(length() == 0) throw new Exception("Error, null vector");

        this._head = new Point3D(normalVector()._head);
    }

    /**
     * Get the vector which correspond to this one but with length of 1
     * @return unit_vector
     * @throws Exception
     * SEE ALSO function normalize()
     */
    public Vector normalVector() throws Exception
    {
        if(length() == 0) throw new Exception();

        return new Vector(
                _head._x.getCoordinate()/length(),
                _head._y.getCoordinate()/length(),
                _head._z.getCoordinate()/length());
    }

    /**
     * Apply dot product
     * @param vector the vector to do the dot product with
     * @return value of dot product
     */
    public double dotProduct(Vector vector)
    {
        double x = this._head._x.getCoordinate()* vector._head._x.getCoordinate();
        double y = this._head._y.getCoordinate()* vector._head._y.getCoordinate();
        double z = this._head._z.getCoordinate()* vector._head._z.getCoordinate();

        return x+y+z;
    }

}