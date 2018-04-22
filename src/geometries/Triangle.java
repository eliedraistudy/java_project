package geometries;


import primitives.*;

import java.util.ArrayList;
import java.util.List;

public class Triangle extends Geometry implements FlatGeometry {

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
    public List<Point3D> findIntersections(Ray ray) {

        //  the list to return, start empty
        List<Point3D> list = new ArrayList<Point3D>();

        Plane p;
        //  get the triangle plane
        try{
            p = getPlane();

            //  if the plane list is empty, no intersection
            if(p.findIntersections(ray).isEmpty())
                return list;

            //  get the point to check if inside the triangle
            Point3D p_to_check = new Point3D(p.findIntersections(ray).get(0));

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
        return v1.crossProduct(v2).normalVector();
    }
}
