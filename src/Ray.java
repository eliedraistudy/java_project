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

    public Ray(Point3D poo, Vector direction);


    // ***************** Getters/Setters ********************** //
    public void setPOO(Point3D _POO);
    public void setDirection(Vector _direction);
    public Vector  getDirection();
    public Point3D getPOO();
}
