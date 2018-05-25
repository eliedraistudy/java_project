package elements;

import primitives.Point3D;
import primitives.Vector;

import java.awt.*;

public class DirectionalLight extends Light {

    private Vector _direction;

    public DirectionalLight(Color c, Vector direction){
        super(c);
        _direction = new Vector(direction);
    }

    @Override
    public Color getIntensity(Point3D p) {
        return _color;
    }
}
