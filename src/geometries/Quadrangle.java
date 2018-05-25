package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class Quadrangle extends Geometry implements FlatGeometry {

    private Triangle _tri1;
    private Triangle _tri2;

    // ***************** Constructors ********************** //
    public Quadrangle(Point3D P1, Point3D P2, Point3D P3, Point3D P4){
        _tri1 = new Triangle(P1, P2, P4);
        _tri2 = new Triangle(P2, P3, P4);
    }


    // ***************** Methods ********************** //
    @Override
    public Vector getNormal(Point3D p){
        if(_tri1.checkInside(p))
            return _tri1.getNormal(p);
        else if (_tri2.checkInside(p))
            return _tri2.getNormal(p);
        return null;
    }

    @Override
    public List<Point3D> FindIntersections(Ray ray){
        List<Point3D> l1 = _tri1.FindIntersections(ray);
        List<Point3D> l2 = _tri2.FindIntersections(ray);

        List<Point3D> l = new ArrayList<>();
        l.addAll(l1);
        l.addAll(l2);
        return l;
    }

}
