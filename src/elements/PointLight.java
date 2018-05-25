package elements;

import primitives.Point3D;

import java.awt.*;

public class PointLight extends Light {

    protected Point3D _position;
    protected double _Kl, _Kc, _Kq;

    public PointLight(Color c, Point3D pos, double kl, double kc, double kq){
        super(c);
        _position = new Point3D(pos);
        _Kl = kl;
        _Kc = kc;
        _Kq = kq;
    }

    @Override
    public Color getIntensity(Point3D p) {

        double d = _position.distance(p);
        double Il = 1 / (_Kc + _Kl*d + _Kq*d*d);
        return new Color(
                (int)(_color.getRed()*Il),
                (int)(_color.getGreen()*Il),
                (int)(_color.getBlue()*Il)
        );
    }


}
