package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * A class to describe symmetrical objects (with a constant radius)
 */
public abstract class RadialGeometry extends Geometry
{
    /**
     * Field for radius
     */
    protected double _radius;

    // ***************** Constructors ********************** //

    /**
     * Default constructor, initialize radius with 0
     */
    public RadialGeometry() { _radius = 0; }

    /**
     * Value constructor, assign the value to the _radius
     * @param radius the value to assign
     */
    public RadialGeometry(double radius) { _radius = radius; }

    /**
     * Copy constructor
     * @param rg the value to copy
     */
    public RadialGeometry(RadialGeometry rg) {
        _radius = rg._radius;
    }

    // ***************** Getters / Setters ********************** //

    /**
     * Getter for the field radius
     * @return the value of the radius
     */
    public double getRadius()  { return _radius; }

    /**
     * Set the radius to a specific value
     * @param radius the value to assign
     */
    public void  setRadius(double radius) { _radius = radius; }


    // ***************** Abstract functions ********************** //
    @Override
    public abstract List<Point3D> findIntersections(Ray ray);

    @Override
    public abstract Vector getNormal(Point3D p);
}
