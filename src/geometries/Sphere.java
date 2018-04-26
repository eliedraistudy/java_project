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
    public Vector getNormal(Point3D point){ return null; }

    @Override
    public List<Point3D> FindIntersections(Ray ray) {
        ArrayList<Point3D> intersectionsPoints = new ArrayList<Point3D>();

        Vector tempVector;
        Point3D tempPoint;
        Point3D p1,p2;
        Point3D p0 = new Point3D(ray.getPOO());
        Point3D o = new Point3D(this._center);
        Vector v = new Vector(ray.getDirection());
        v.normalize();
        double r = this._radius;

        tempVector = new Vector(o);
        tempVector.subtract(new Vector(p0));
        Vector L = new Vector(tempVector);

        tempVector = new Vector(v);
        tempVector.dotProduct(L);
        double tm = tempVector.length();

        double d = Math.sqrt(Math.pow(L.length(),2) - Math.pow(tm,2));

        if (d>r)
            return intersectionsPoints;

        double th = Math.sqrt(Math.pow(r,2) - Math.pow(d,2));
        double t1 = tm - th;
        double t2 = tm + th;

        tempPoint = new Point3D(p0);
        tempVector = new Vector(v);
        tempVector.scale(t1);
        tempPoint.add(tempVector);
        p1 = new Point3D(tempPoint);

        intersectionsPoints.add(p1);
        if (r == d)
            return intersectionsPoints;

        tempPoint = new Point3D(p0);
        tempVector = new Vector(v);
        tempVector.scale(t2);
        tempPoint.add(tempVector);
        p2 = new Point3D(tempPoint);

        intersectionsPoints.add(p2);
        return intersectionsPoints;

    }

}
