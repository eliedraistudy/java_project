package elements;


import primitives.*;
import primitives.Vector;
import java.util.*;
import java.awt.Color;

public  class SpotLight extends PointLight
{
    private Vector _direction;

    // region ***** CONSTRUCTORS *****

    public SpotLight( Color color, Point3D position,Vector direction, double Kc, double Kl, double Kq)
    {
        super(color, position, Kc, Kl, Kq);
        _direction = new Vector(direction);
    }

    //endregion

    //region ***** OPERATION *****

    /**
     * Function : GetIntensity
     * Parameter : The point we are enlightening
     * Meaning : We used the PointLight GetIntensity function,
     *           we pass over parameter the dot product result between (direction vector and positionPoint3D vector).
     * Return : the result colour
     */
    @Override
    public Color getIntensity(Point3D point3D)
    {
        Color pointColor = super.getIntensity(point3D);
        Vector vector = new Vector(getL(point3D)).normalVector();
        double dotProduct = _direction.dotProduct(vector);
        return super.GetIntensity(point3D, dotProduct, pointColor);
    }

    //endregion
}
