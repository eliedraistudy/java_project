package primitives;

public class Ray
{
    // Point of origin
    private Point3D _POO;

    // the vector direction
    private Vector _direction;


    // ***************** Constructors ********************** //

    /**
     * Default constructor, initialize with point of origin (0,0,0) and vector (1,1,1)
     */
    public Ray()
    {
        _POO = new Point3D();
        _direction = new Vector(1,1,1);
    }

    /**
     * Copy constructor, copy instance of ray into the object
     * @param ray the ray to copy
     */
    public Ray(Ray ray)
    {
        _POO = new Point3D(ray._POO);
        _direction = new Vector(ray._direction);
    }

    /**
     * Value constructor, initialize with values
     * @param poo the point of origin
     * @param direction the director vector
     */
    public Ray(Point3D poo, Vector direction)
    {
        _POO = new Point3D(poo);
        _direction = new Vector(direction);
    }


    // ***************** Getters/Setters ********************** //

    /**
     * Setting the POO
     * @param POO the values to set
     */
    public void setPOO(Point3D POO) {_POO = POO; }

    /**
     * Setting the direction
     * @param direction
     */
    public void setDirection(Vector direction) { _direction = direction;}

    /**
     * Getting the direction vector
     * @return the direction vector
     */
    public Vector getDirection() { return _direction; }

    /**
     * Getting the point of origin
     * @return the point of origin
     */
    public Point3D getPOO() { return _POO; }

}
