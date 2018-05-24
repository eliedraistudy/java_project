package geometries;
import primitives.Ray;
import primitives.Point3D;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class Sphere extends RadialGeometry
{
    private Point3D _center;

    // ***************** Constructors ********************** //
    public Sphere(){
        this._radius = 0;
        this._center = new Point3D();
    }

    public Sphere (Sphere sphere) {
        this._radius = sphere._radius;
        this._center = new Point3D(sphere._center);
    }

    public Sphere(double radius, Point3D center) {
        this._radius = radius;
        this._center = new Point3D(center);
    }


    // ***************** Getters/Setters ********************** //
    public Point3D getCenter() {
        return this._center;
    }
    public void setCenter(Point3D center) {
        this._center = new Point3D(center);
    }


    // ***************** Operations ******************** //

    @Override
    public Vector getNormal(Point3D point) {
        return new Vector(point,this._center).normalVector();
    }


    /*************************************************
     * --------
     * FUNCTION
     * --------
     * FindIntersections
     *
     * ------------
     * PARAMETER(S)
     * ------------
     * Ray - the ray which intersect with the sphere
     *
     * ------------
     * RETURN VALUE
     * ------------
     * A list which contains the intersections between the ray and the sphere
     *
     * -------
     * MEANING
     * -------
     * By given:
     * L = sphere.center - p0
     * tm = ray.direction DOT L
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
        ArrayList<Point3D> intersectionsPoints = new ArrayList<Point3D>();

        //  points to return
        Point3D p1,p2;

        //  start of the ray
        Point3D p0 = new Point3D(ray.getPOO());
        //  center of the sphere
        Point3D o = new Point3D(this._center);
        //  ray direction
        Vector v = new Vector(ray.getDirection()).normalVector();
        double r = this._radius;

        //  get L the vector from ray's start to sphere's center
        //  perform new vector with parameter o - p0 (o.subtract(p0))
        Vector L = new Vector(o.subtract_return(p0));

        //  get the projection of L on v (unit vector)
        double tm = v.dotProduct(L); // to check


        double d = Math.sqrt(Math.pow(L.length(),2.0) - Math.pow(tm,2.0));

        //  if d > r, then no intersections, return empty list
        if (d>r)
            return intersectionsPoints;

        //  else get th, the length we need to add/remove from tm to get our points
        double th = Math.sqrt(Math.pow(r,2) - Math.pow(d,2));

        //  p1 is the first intersection point
        double t1 = tm - th;
        double t2 = tm + th;

        //  if both of the value are negative, don(t consider them
        if(t1 < 0 && t2 < 0)
            return intersectionsPoints;

        //  if t1>0 add the point to the list
        if(t1 > 0)
            intersectionsPoints.add(new Point3D(p0.add_return(v.scale_return(t1))));

        //  if t1 == t2 end the process and return the list with one value
        if(t1 == t2)
            return intersectionsPoints;

        //  if t2 > 0 add the point to the list
        if(t2 > 0)
            intersectionsPoints.add(new Point3D(p0.add_return(v.scale_return(t2))));

        //  return the list
        return intersectionsPoints;

    }



}
