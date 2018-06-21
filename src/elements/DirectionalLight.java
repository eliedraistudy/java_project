package elements;

import primitives.Point3D;
import primitives.Vector;

import java.awt.*;

public class DirectionalLight extends Light implements LightSource
{
    private Vector _direction;

    //region ***** CONSTRUCTORS *****

    public DirectionalLight(Color c, Vector v)
    {
        _color = new Color(c.getRGB());
        _direction = new Vector(v);
    }

    //endregion

    //region ***** GETTERS / SETTERS *****

    public Vector get_direction()
    {
        return _direction;
    }

    public void setDirection(Vector direction)
    {
        _direction = new Vector(direction);
    }

    @Override
    public  Color getIntensity(Point3D point)
    {
        return _color;
    }

    @Override
    public Vector getL(Point3D point)
    {
        Point3D direction = new Point3D(_direction.getHead());
        return new Vector(direction,point);
    }

    //endregion
}