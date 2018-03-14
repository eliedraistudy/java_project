public class Ray
{
    // Point of origin
    private Point3D _POO;

    // Ray direction
    private Vector _direction;


    // ***************** Constructors ********************** //
    public Ray()
    {
        _POO = new Point3D();
        _direction = new Vector();
    }

    public Ray(Ray ray)
    {
        _POO = new Point3D(ray._POO);
        _direction = new Vector(ray._direction);
    }

    public Ray(Point3D poo, Vector direction)
    {
        _POO = new Point3D(poo);
        _direction = new Vector(direction);
    }


    // ***************** Getters/Setters ********************** //
    public void setPOO(Point3D POO) {_POO = POO; }
    public void setDirection(Vector direction) { _direction = direction;}
    public Vector  getDirection() { return _direction; }
    public Point3D getPOO() { return _POO; }
}
