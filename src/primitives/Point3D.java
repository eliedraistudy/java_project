package primitives;

public class Point3D extends Point2D
{
    protected Coordinate _z;
    // ***************** Constructors ********************** //
    public Point3D()
    {
        super(new Coordinate(0), new Coordinate(0));
        _z = new Coordinate(0);

    }
    public Point3D(Coordinate x, Coordinate y, Coordinate z)
    {
        super(new Coordinate(x.getCoordinate()), new Coordinate(y.getCoordinate()));
        _z = new Coordinate(z.getCoordinate());
    }

    public Point3D(double x, double y, double z)
    {
        super(new Coordinate(x), new Coordinate(y));
        _z = new Coordinate(z);
    }
    public Point3D(Point3D point3D)
    {
        super(point3D._x, point3D._y);
        _z = new Coordinate(point3D._z.getCoordinate());
    }
    // ***************** Getters/Setters ********************** //
    public Coordinate getZ(){return _z;}
    public double getZValue() { return _z.getCoordinate(); }

    public void setZ(Coordinate z){_z.setCoordinate(z.getCoordinate());}

    public void setPoint(Point3D p) { setX(p.getX()); setY(p.getY()); setZ(p.getZ()); }

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

    public String toString()
    {
        return "(" + _x + ", " + _y + ", " + _z + ")";
    }

    // ***************** Operations ******************** //


   // public void add(primitives.Vector vector);
    //public void subtract(primitives.Vector vector);
    public double distance(Point3D point)
    {
        double powerOfsubtraction_X = Math.pow((this._x.getCoordinate() - point._x.getCoordinate()),2);
        double powerOfsubtraction_Y = Math.pow(this._y.getCoordinate() - point._y.getCoordinate(),2);
        double powerOfsubtraction_Z = Math.pow(this._z.getCoordinate() - point._z.getCoordinate(),2);
        return Math.sqrt(powerOfsubtraction_X + powerOfsubtraction_Y + powerOfsubtraction_Z);
    }
}
