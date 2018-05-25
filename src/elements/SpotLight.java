package elements;

import primitives.Point3D;
import primitives.Vector;

import java.awt.*;

public class SpotLight extends PointLight {

    private Vector _dir;

    public SpotLight(Color c, Point3D pos, double kl, double kc, double kq, Vector v){
        super(c,pos,kl,kc,kq);
        _dir = new Vector(v.normalVector());
    }

    @Override
    public Color getIntensity(Point3D p) {

        double d = _position.distance(p);
        double Il = 1 / (_Kc + _Kl*d + _Kq*d*d);
        double dot = _dir.dotProduct(new Vector(_position,p).normalVector());

        return new Color(
                (int)(_color.getRed()*Il*dot),
                (int)(_color.getGreen()*Il*dot),
                (int)(_color.getBlue()*Il*dot)
        );
    }
}
