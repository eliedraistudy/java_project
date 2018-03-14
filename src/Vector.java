public class Vector
{
    private Point3D _head;
    // ***************** Constructors ********************** //
    public Vector(){_head = new Point3D();}
    public Vector(Point3D head){this._head = new Point3D(head);}
    public Vector(Vector vector){_head = new Point3D(vector._head);}
    public Vector(double xHead, double yHead, double zHead){_head = new Point3D(xHead,yHead,zHead);}
    public Vector(Point3D p1, Point3D p2)
    {
        double x = p1.getX().getCoordinate() + p2.getX().getCoordinate();
        double y = p1.getY().getCoordinate() + p2.getY().getCoordinate();
        double z = p1.getZ().getCoordinate() + p2.getZ().getCoordinate();
        this._head = new Point3D(x,y,z);
    }
    // ***************** Getters/Setters ********************** //
    public Point3D getHead(){return _head;}
    public void setHead(Point3D head){this._head = new Point3D(head);}
    // ***************** Administration ******************** //
    public int compareTo(Vector vector) // 0 if equal 1 if not equal
    {
        double xOfInputVector = vector._head.getX().getCoordinate();
        double yOfInputVector = vector._head.getY().getCoordinate();
        double zOfInputVector = vector._head.getZ().getCoordinate();
        double SizeOfInputVector = Math.sqrt(Math.pow(xOfInputVector,2) + Math.pow(yOfInputVector,2) + Math.pow(zOfInputVector,2));

        double xOfThisVector = this._head.getX().getCoordinate();
        double yOfThisVector = this._head.getY().getCoordinate();
        double zOfThisVector = this._head.getZ().getCoordinate();
        double SizeOfThisVector = Math.sqrt(Math.pow(xOfThisVector,2) + Math.pow(yOfThisVector,2) + Math.pow(zOfThisVector,2));

        if (SizeOfThisVector > SizeOfInputVector)
            return 1;
        if (SizeOfThisVector < SizeOfInputVector)
            return -1;

        return 0; // if vectors are equal

    }
    public String toString() // lhazek 2 sfrot after the dot
    {
        return "(" + _head.getX().getCoordinate() + "," + _head.getY().getCoordinate() + ","
                + _head.getZ().getCoordinate() + ")";
    }


    // ***************** Operations ******************** //
    public void add (Vector vector )
    {
        _head._x.setCoordinate(_head._x.getCoordinate() + vector._head._x.getCoordinate());
        _head._y.setCoordinate(_head._y.getCoordinate() + vector._head._y.getCoordinate());
        _head._z.setCoordinate(_head.getZ().getCoordinate() + vector._head.getZ().getCoordinate());
    }
    public void subtract (Vector vector);
    public void scale(double scalingFactor);
    public Vector crossProduct(Vector vector)
    {
        double x_component = this._head._y.getCoordinate()*vector._head._z.getCoordinate()
                - this._head._z.getCoordinate()*vector._head._y.getCoordinate();

        double y_component = this._head._z.getCoordinate()*vector._head._x.getCoordinate()
                - this._head._x.getCoordinate()*vector._head._z.getCoordinate();

        double z_component = this._head._x.getCoordinate()*vector._head._y.getCoordinate()
                - this._head._y.getCoordinate()*vector._head._x.getCoordinate();

        return new Vector(x_component,y_component,z_component);
    }
    public double length()
    {
        double powerOf_X = Math.pow(_head._x.getCoordinate(),2);
        double powerOf_Y = Math.pow(_head._y.getCoordinate(),2);
        double powerOf_Z = Math.pow(_head._z.getCoordinate(),2);

        return Math.sqrt(powerOf_X + powerOf_Y + powerOf_Z);
    }
    public void normalize(); // Throws exception if length = 0
    public double dotProduct(Vector vector)
    {
        double x = this._head._x.getCoordinate()* vector._head._x.getCoordinate();
        double y = this._head._y.getCoordinate()* vector._head._y.getCoordinate();
        double z = this._head._z.getCoordinate()* vector._head._z.getCoordinate();

        return x+y+z;
    }

}
