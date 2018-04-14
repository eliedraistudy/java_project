package geometries;

import primitives.*;

import java.util.List;

public interface FlatGeometry
{
    public List<Point3D> findIntersections(Ray ray);
}
