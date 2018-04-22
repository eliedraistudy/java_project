package geometries;

import primitives.Vector;
import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

public class Plane implements FlatGeometry
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
    @Override
    public List<Point3D> findIntersections(Ray ray) {

        //  the return list
        List<Point3D> list = new ArrayList<>();

        //  Ray : P = P0 + tV
        //  Plane : N.(P-Q0) = 0

        Point3D P0 = new Point3D(ray.getPOO());
        Vector V = new Vector(ray.getDirection());
        Vector N = new Vector(getNormal());
        Point3D Q0 = new Point3D(getQ());


        //  here the equation to solve
        //  N.(P0 + tV - Q0) = 0
        //  t = (N.Q0 - N.P0)/ N.V

        //  check first if N, V are orthogonals which means no intersections
        if (N.dotProduct(V) == 0) return list;

        //  find the value of t
        double t = (N.dotProduct(new Vector(Q0)) - N.dotProduct(new Vector(P0)))
                /(N.dotProduct(V));

        //  if t is negative, vector behind the camera
        if(t<0) return list;

        //  use a ray function
        list.add(ray.getPoint(t));

        return list;
    }


    /*
    public Vector getPlanarVector(){
        //  normal vector : (a, b, c)
        //  new vector : (x, y, z)
        //  solution of ax + by + cz = 0

        double a = _normal.getHead().getX().getCoordinate();
        double b = _normal.getHead().getY().getCoordinate();
        double c = _normal.getHead().getZ().getCoordinate();

        //  for a != 0 : x = (-b-c)/a, y = 1, z = 1
        if (a != 0)
            return new Vector((-b-c)/a, 1,1);
        else if(b!=0)
            return new Vector(1,(-a-c)/b,1);
        else if(c != 0)
            return new Vector(1,1,(-a-b)/c);
        else return new Vector();
    }
    */


}
