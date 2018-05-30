package geometries;

import primitives.Vector;
import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

public class Plane extends Geometry implements FlatGeometry, Comparable<Plane>
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

    public Plane (Vector normal, Point3D q) throws Exception {
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

    /*************************************************
     * --------
     * FUNCTION
     * --------
     * FindIntersections
     *
     * ------------
     * PARAMETER(S)
     * ------------
     * Ray - the ray which intersect with the plane
     *
     * ------------
     * RETURN VALUE
     * ------------
     * A list which contains the intersections between the ray and the plane
     *
     * -------
     * MEANING
     * -------
     * By given:
     * Ray: P = P0 + tV
     * Plane: N.(P-Q0) = 0
     *
     * solve:
     * t = (N.Q0 - N.P0)/ N.V
     * which give the point
     *
     * --------
     * SEE ALSO
     * --------
     *************************************************/
    @Override
    public List<Point3D> FindIntersections(Ray ray) {

        //  the return list
        List<Point3D> list = new ArrayList<Point3D>();

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


    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p>
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     * <p>
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     * <p>
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     * <p>
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p>
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Plane o) {
        return o._normal.compareTo(_normal) + o._Q.compareTo(_Q);
    }
}
