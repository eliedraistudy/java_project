package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;


public class Cylinder extends RadialGeometry {

    /*
     * We define a Cylinder with a point and an axis vector.
     * */
    private Point3D _axisPoint;
    private Vector _axisDirection;

    // ***************** Constructors ********************** //

    /*************************************************
     * FUNCTION
     *  Cylinder()
     *
     * PARAMETERS
     *  No parameters.
     *
     * RETURN VALUE
     *  No return value.
     *
     * MEANING
     *  A default constructor.
     *
     * See Also
     *  ---
     **************************************************/
    public Cylinder(){
        super(0.0);
        this.setAxisPoint(new Point3D());
        this.setAxisDirection(new Vector());
    }

    /*************************************************
     * FUNCTION
     *  Cylinder()
     *
     * PARAMETERS
     *  Cylinder - a given cylinder.
     *
     * RETURN VALUE
     *  No return value.
     *
     * MEANING
     *  A CTOR from a given cylinder.
     *
     * See Also
     *  ---
     **************************************************/
    public Cylinder(Cylinder cylinder){
        this.setRadius(cylinder.getRadius());
        this.setAxisPoint(cylinder.getAxisPoint());
        this.setAxisDirection(cylinder.getAxisDirection());
    }

    /*************************************************
     * FUNCTION
     *  Cylinder()
     *
     * PARAMETERS
     *  double - a given radius.
     *  Point3D - a given point.
     *  Vector - a given axis vector.
     *
     * RETURN VALUE
     *  No return value.
     *
     * MEANING
     *  A CTOR from given radius, point 3D and an axis vector.
     *
     * See Also
     *  ---
     **************************************************/
    public Cylinder(double radius, Point3D axisPoint, Vector axisDirection)
    {
        super(radius);
        this.setAxisPoint(axisPoint);
        this.setAxisDirection(axisDirection);
    }

    // ***************** Getters/Setters ********************** //

    /*************************************************
     * FUNCTION
     *  getAxisPoint()
     *
     * PARAMETERS
     *  No parameters.
     *
     * RETURN VALUE
     *  No return value.
     *
     * MEANING
     *  A getter for the Cylinder axis point.
     *
     * See Also
     *  Called by the constructors.
     *
     **************************************************/
    public Point3D getAxisPoint(){return this._axisPoint;}

    /*************************************************
     * FUNCTION
     *  getAxisDirection()
     *
     * PARAMETERS
     *  No parameters.
     *
     * RETURN VALUE
     *  No return value.
     *
     * MEANING
     *  A getter for the Cylinder axis vector direction.
     *
     * See Also
     *  Called by the constructors.
     *
     **************************************************/
    public Vector getAxisDirection(){return this._axisDirection;}

    /*************************************************
     * FUNCTION
     *  setAxisPoint()
     *
     * PARAMETERS
     *  point3D - a given Cylinder axis point.
     *
     * RETURN VALUE
     *  No return value.
     *
     * MEANING
     *  A setter for the Cylinder axis point.
     *
     * See Also
     *  Called by the constructors.
     *
     **************************************************/
    public void setAxisPoint(Point3D axisPoint)
    {
        this._axisPoint = new Point3D(axisPoint);
    }

    /*************************************************
     * FUNCTION
     *  setAxisDirection()
     *
     * PARAMETERS
     *  Vector - a given direction vector.
     *
     * RETURN VALUE
     *  No return value.
     *
     * MEANING
     *  A setter for the Cylinder axis vector direction.
     *
     * See Also
     *  Called by the constructors.
     *
     **************************************************/
    public void setAxisDirection(Vector axisDirection){ this._axisDirection = new Vector(axisDirection); }

    // ***************** Operations ******************** //

    /*************************************************
     * FUNCTION
     *  FindIntersections()
     *
     * PARAMETERS
     *  Ray - a camera ray.
     *
     * RETURN VALUE
     *  ArrayList - all intersection points with the Cylinder.
     *
     * MEANING
     *  Finds all the intersections between the camera rays and a Cylinder.
     *
     * See Also
     *  ---
     **************************************************/
    public ArrayList<Point3D> FindIntersections(Ray ray){
        ArrayList<Point3D> intersections = new ArrayList<Point3D>();
        Point3D E = ray.getPOO();
        Vector D = ray.getDirection();
        Double a,b,c;

        a = ((Math.pow(D.getHead().getX().getCoordinate(),2) + Math.pow(D.getHead().getY().getCoordinate(),2)));
        b = 2*(E.getX().getCoordinate()*D.getHead().getX().getCoordinate()) + 2*(E.getY().getCoordinate()*D.getHead().getY().getCoordinate());
        c = Math.pow(E.getX().getCoordinate(),2) + Math.pow(E.getY().getCoordinate(),2) - _radius;

        double T1 = (-b + Math.sqrt(Math.pow(b,2)-4*a*c))/2*a;
        double T2 = (-b - Math.sqrt(Math.pow(b,2)-4*a*c))/2*a;

        if (T1 >= 0)
            intersections.add(ray.getRayPointByT(E,D,T1));
        if (T2 >= 0)
            intersections.add(ray.getRayPointByT(E,D,T2));

        return intersections;
    }

    /*************************************************
     * // TODO: complete getNormal() comment
     * FUNCTION
     *  getNormal()
     *
     * PARAMETERS
     *  Point3D -
     *
     * RETURN VALUE
     *  Vector -
     *
     * MEANING
     *
     * See Also
     *
     **************************************************/
    public Vector getNormal(Point3D point){
        // TODO: implement getNormal() method and ask for the signification of a cylinder normal.
        return new Vector();
    }
}
