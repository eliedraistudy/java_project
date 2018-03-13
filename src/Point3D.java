public class Point3D extends Point2D
{
    private Coordinate _z;
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
    public void setZ(Coordinate _z){this._z = _z;}
    // ***************** Administration ******************** //
   // public int compareTo(Point3D point3D);
    public String toString()
    {
        return "(" + _x + "," + _y + ")";
    }
    // ***************** Operations ******************** //
   // public void add(Vector vector);
    //public void subtract(Vector vector);
    //public double distance(Point3D point); // lasot pitgoras
}
