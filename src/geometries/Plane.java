package geometries;

import primitives.Vector;
import primitives.Point3D;
import primitives.Ray;

import java.util.List;

public class Plane
{
    /**
     * Field for the normal vector
     */
    private Vector _normal;

    /**
     * Field for the application point
     */
    private Point3D _Q;

    // ***************** Constructors ********************** //

    /**
     * Default constructor
     *
     */
    public Plane()
    {
        _normal = new Vector();
        _Q = new Point3D();
    }

    /**
     * Copy constructor
     * @param plane
     */
    public Plane (Plane plane)
    {
        //assign the normal vector to be unitary
        try{
            _normal = new Vector(plane._normal.normalVector());
        }
        catch(Exception e) {}
        _Q = new Point3D(plane._Q);
    }

    /**
     * Value constructor
     * @param normal assign the value to the normal vector
     * @param q assign the value to the application point
     */
    public Plane (Vector normal, Point3D q)
    {
        //assign the normal vector to be unitary
        try {
            _normal = new Vector(normal).normalVector();
        }
        catch (Exception e) {}

        _Q = new Point3D(q);
    }

    // ***************** Getters/Setters ********************** //

    /**
     * Getter for the normal vector
     * @return the value of the vector
     */
    public Vector getNormal() { return _normal; }

    /**
     * Getter for the application point
     * @return the value of the application point
     */
    public Point3D getQ() { return _Q; }


    /**
     * Set the value of the normal vector
     * @param normal
     */
    public void setNormal(Vector normal)
    {
        try{
            _normal = new Vector(normal.normalVector());
        }
        catch (Exception e){}

    }

    /**
     * Set the value of the application point
     * @param d
     */
    public void setQ(Point3D d) { _Q = new Point3D(d); }


    // ***************** Operations ******************** //

    /**
     * Find intersections between a ray and the plane
     * @param ray
     * @return list of 3D pointsfor intersections, empty list if no intersection
     */
    public List<Point3D> FindIntersections(Ray ray)
    {

    }

}
