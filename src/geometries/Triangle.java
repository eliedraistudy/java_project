package geometries;


import primitives.*;

import java.util.ArrayList;
import java.util.List;

public class Triangle extends Geometry implements FlatGeometry, Comparable<Triangle> {

    //**************** FIELDS ****************//
    Point3D _p1;
    Point3D _p2;
    Point3D _p3;

    //**************** CONSTRUCTOR ****************//
    public Triangle(Point3D p1, Point3D p2, Point3D p3){
        _p1 = new Point3D(p1);
        _p2 = new Point3D(p2);
        _p3 = new Point3D(p3);
    }

    //**************** GETTERS/SETTERS ****************//

    public Point3D getP1() { return _p1; }
    public void setP1(Point3D p){ _p1 = new Point3D(p); }

    public Point3D getP2() { return _p2; }
    public void setP2(Point3D p){ _p2 = new Point3D(p); }

    public Point3D getP3() { return _p3; }
    public void setP3(Point3D p){ _p3 = new Point3D(p); }

    //**************** METHODS ****************//

    /*************************************************
     * --------
     * FUNCTION
     * --------
     * getPlane
     *
     * ------------
     * PARAMETER(S)
     * ------------
     *
     * ------------
     * RETURN VALUE
     * ------------
     * Plane
     *
     * -------
     * MEANING
     * -------
     * Return the plane which contains the current triangle
     * Make cross product between Vector(p1,p2) and Vector(p2,p3)
     * return a plane with the calculated normal and POO _p1
     *
     * --------
     * SEE ALSO
     * --------
     * Plane.findIntersection
     *************************************************/
    public Plane getPlane() throws Exception{

        //  no matter which point in parameter
        //  all normal will be the same
        Vector n = getNormal(_p1);

        //  create a plane with the calculated normal and the application point p1
        return new Plane(n, _p1);
    }

    /*************************************************
     * --------
     * FUNCTION
     * --------
     * findIntersection
     *
     * ------------
     * PARAMETER(S)
     * ------------
     * Ray
     *
     * ------------
     * RETURN VALUE
     * ------------
     * List<3D>
     *
     * -------
     * MEANING
     * -------
     * Return the list which contains the intersection point between triangle and ray
     * First of all get the intersection point with the plane
     * Then check if the point is inside the triangle with the method checkInside
     * If no, return empty list
     *
     * --------
     * SEE ALSO
     * --------
     * Triangle.checkInside
     *************************************************/
    @Override
    public List<Point3D> FindIntersections(Ray ray) {

        //  the list to return, start empty
        List<Point3D> list = new ArrayList<Point3D>();

        Plane p;
        //  get the triangle plane
        try{
            p = getPlane();

            //  if the plane list is empty, no intersection
            if(p.FindIntersections(ray).isEmpty())
                return list;

            //  get the point to check if inside the triangle
            Point3D p_to_check = new Point3D(p.FindIntersections(ray).get(0));

            //  check if the point inside the triangle
            //  if so, add the point to the list
            if(checkInside(p_to_check))
                list.add(p_to_check);
        }
        catch (Exception e){
        }

        return list;

    }

    /*************************************************
     * --------
     * FUNCTION
     * --------
     * checkInside
     *
     * ------------
     * PARAMETER(S)
     * ------------
     *
     * ------------
     * RETURN VALUE
     * ------------
     * boolean
     *
     * -------
     * MEANING
     * -------
     * Check if a point inside the triangle's side
     * get normal with cross product for each side
     * then calculate dot product between each normal and the point to check
     * if all with same sign return true
     * else if not or if one of them is 0 return false
     *
     * --------
     * SEE ALSO
     * --------
     * Triangle.same_sign
     *************************************************/
    public boolean checkInside(Point3D p){
        //  get the cross product for each triangle side
        Vector n1 = (new Vector(_p1)).crossProduct(new Vector(_p2)).normalVector();
        Vector n2 = (new Vector(_p2)).crossProduct(new Vector(_p3)).normalVector();
        Vector n3 = (new Vector(_p3)).crossProduct(new Vector(_p1)).normalVector();

        //  get the dot product
        double a = n1.dotProduct(new Vector(p));
        double b = n2.dotProduct(new Vector(p));
        double c = n3.dotProduct(new Vector(p));

        if(a == 0 || b == 0 || c == 0)
            return false;

        return same_sign(a,b,c);
    }

    /*************************************************
     * --------
     * FUNCTION
     * --------
     * same_sign
     *
     * ------------
     * PARAMETER(S)
     * ------------
     *  double a
     *  double b
     *  double c
     *
     * ------------
     * RETURN VALUE
     * ------------
     * boolean
     *
     * -------
     * MEANING
     * -------
     * check if 3 variables are of the same sign
     *
     * --------
     * SEE ALSO
     * --------
     * Triangle.checkInside
     *************************************************/
    private boolean same_sign(double a, double b, double c){
        return (a>0 && b>0 && c>0) || (a<0 && b<0 && c<0);
    }


    @Override
    public Vector getNormal(Point3D point){
        Vector v1 = new Vector(_p1, _p2);
        Vector v2 = new Vector(_p1, _p3);
        Vector n = v1.crossProduct(v2);
        n.normalize();
        n.scale(-1);
        return n;
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
    public int compareTo(Triangle o) {
        return o._p1.compareTo(_p1) +
                o._p2.compareTo(_p2) +
                o._p3.compareTo(_p3);
    }
}
