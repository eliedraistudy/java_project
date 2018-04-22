package geometries;

import primitives.Vector;
import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

public class Plane extends Geometry implements FlatGeometry
{

    // ***************** Fields ********************** //
    private Vector _normal;
    private Point3D _Q;

    // ***************** Constructors ********************** //

    public Plane() {
        _normal = new Vector();
        _Q = new Point3D();
    }


    public Plane (Plane plane) throws ArithmeticException {
        //assign the normal vector to be unitary
        _normal = new Vector(plane._normal.normalVector());
        _Q = new Point3D(plane._Q);
    }


    public Plane (Vector normal, Point3D q) throws Exception
    {
        _normal = new Vector(normal).normalVector();

        if(_normal.isNull())
            throw new Exception("ERROR, can't create a plane with null normal vector");

        _Q = new Point3D(q);
    }

    // ***************** Getters/Setters ********************** //

    public Vector getNormal() { return _normal; }
    public Point3D getQ() { return _Q; }

    public void setNormal(Vector normal) throws ArithmeticException{
            _normal = new Vector(normal.normalVector());
    }
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
        //  don't consider it
        if(t<0)
            return list;

        //  use a ray function
        list.add(ray.getPoint(t));

        return list;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return getNormal();
    }



}
