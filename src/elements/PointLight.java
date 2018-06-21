package elements;

import primitives.*;
import primitives.Vector;
import java.util.*;
import java.awt.Color;


public abstract class PointLight extends Light implements LightSource
{
    protected Point3D _position;
    protected double _Kc, _Kl, _Kq;

    //region ***** CONSTRUCTORS *****

    public PointLight(Color color, Point3D position, double Kc, double Kl, double Kq)
    {
        super(color);
        _position = new Point3D(position);
        _Kc = Kc;
        _Kl = Kl;
        _Kq = Kq;
    }

    public PointLight()
    {
        super();
        _position = new Point3D();
        _Kc = 0;
        _Kl = 0;
        _Kq = 0;
    }

    //endregion

    //region ***** OPERATION ***** //

    /**
     * Function : GetIntensity
     * Parameter : point3D: the point we are enlightening
     *             dotProductDL: the coefficient if we had to multiply it; used with SpotLight GetIntensity function.
     *                           initialized to 1 if doesn't exist.
     * Meaning : We get the distance between our position and the obtained point3D.
     *           Then we calculate the colour intensity by the following formula:
     *              result = 1 / ( Kc + d*Kd + d² * Kq)
     *           We multiply the result to the whole of lightIntensity composites.
     * Return :  the result colour
     * */
    public Color GetIntensity(Point3D point3D, double dotProductDL, Color color)
    {
        double distance = new Vector(this._position, point3D).length();
        double pointLightColorIntensity = 1 * Math.abs(dotProductDL) / (this._Kc + this._Kl * distance + this._Kq * distance * distance);

        if(pointLightColorIntensity > 1)
            pointLightColorIntensity = 1;

        return new Color((int) (color.getRed() * pointLightColorIntensity),
                (int) (color.getGreen() * pointLightColorIntensity),
                (int) (color.getBlue() * pointLightColorIntensity));

    }

    @Override
    public Color getIntensity(Point3D point3D)
    {
        return GetIntensity(point3D, 1, _color);
    }

    /**
     * Function : getL(Point3D point)
     * Parameter: the point we enlighten
     * Meaning : calculate the vector between the two points : point and the PointLight position
     * Return : the vector composed by the two points
     */
    @Override
    public Vector getL(Point3D point)
    {
        return new Vector(_position, point);
    }

    //endregion
}
