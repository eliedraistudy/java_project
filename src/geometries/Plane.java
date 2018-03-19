package geometries;

import primitives.Vector;
import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
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
     * @return list of 3D points for intersections, empty list if no intersection
     */
    public List<Point3D> FindIntersections(Ray ray)
    {
        Vector w = new Vector(ray.getDirection());
        List<Point3D> lp = new ArrayList<Point3D>();
        if (w.dotProduct(_normal) == 0)
            return lp;

        //  solve equation of intersection
        //  ray : {x = a+Xt, y = b+Yt, z = c+Zt}
        //  plane : {Ax+By+Cz = D}
        //  A(a+Xt) + B(b+Yt) + C(c+Zt) = D
        //  t(AX + BY + CZ) = D - (Aa+Bb+Cc)
        //  t = [D - (Aa+Bb+Cc)]/(AX + BY + CZ)

        double a = ray.getPOO().getXValue();
        double b = ray.getPOO().getYValue();
        double c = ray.getPOO().getZValue();

        double X = ray.getDirection().getX();
        double Y = ray.getDirection().getY();
        double Z = ray.getDirection().getZ();

        double A = _normal.getX();
        double B = _normal.getY();
        double C = _normal.getZ();
        double D = _Q.getXValue()*_normal.getX() +
                _Q.getYValue()*_normal.getY() +
                _Q.getZValue()*_normal.getZ();

        double t = (D - (A*a+B*b+C*c))/(A*X+B*Y+C*Z);

        if(t<=0) return lp;

        //  add the point
        lp.add(new Point3D(a + t*X, b+t*Y, c+t*Z));
        return lp;

    }

    /**
     * Function to check if a specific point is part of the plane
     * @param p the point to check
     * @return true if the point is in the plane, false otherwise
     */
    public boolean contains(Point3D p)
    {
        //  get the d in equation ax + by + cz = d
        //  (a,b,c) : coordinates of normal vector
        //  (x,y,z) : coordinates of _q
        double d = _Q.getXValue()*_normal.getX() +
                _Q.getYValue()*_normal.getY() +
                _Q.getZValue()*_normal.getZ();

        //   check the equation for the point p
        return (p.getXValue()*_normal.getX() +
                p.getYValue()*_normal.getY() +
                p.getZValue()*_normal.getZ() == d);
    }


}
