package elements;

import primitives.Point3D;

import java.awt.*;
import java.util.Map;


public class AmbientLight extends Light {

    // ***************** Fields ********************** //
    private double _Ka = 1.0;

    // ***************** Constructors ********************** //

    public AmbientLight(){}
    public AmbientLight(AmbientLight aLight){
        super(aLight);
        _Ka = aLight._Ka;
    }
    public AmbientLight(Color c){
        super(c);
        _Ka = 1.0;
    }

    public AmbientLight(int r, int g, int b){
        super(new Color(r,g,b));
    }

    public AmbientLight(Color c, double Ka){
        super(c);
        _Ka = Ka;
    }
    public AmbientLight(Map<String, String> attributes){}

    // ***************** Getters/Setters ********************** //
    public Color getColor(){ return _color; }
    public void  setColor(Color color) { _color = new Color(color.getRGB()); }
    public double getKa() { return _Ka; }


    /*************************************************
     * --------
     * FUNCTION
     * --------
     * get ambient light intensity
     *
     * ------------
     * PARAMETER(S)
     * ------------
     *
     * ------------
     * RETURN VALUE
     * ------------
     * The new color by multiplicate the R,G,B fields by the intensity Ka
     *
     * -------
     * MEANING
     * -------
     *
     * --------
     * SEE ALSO
     * --------
     *************************************************/
    @Override
    public Color getIntensity(Point3D p){
        return new Color(
                (int)(_color.getRed()*_Ka),
                (int)(_color.getGreen()*_Ka),
                (int)(_color.getBlue()*_Ka));
    }


    /*************************************************
     * --------
     * FUNCTION
     * --------
     * equals
     *
     * ------------
     * PARAMETER(S)
     * ------------
     *  Object - o, the light to compare with
     *
     * ------------
     * RETURN VALUE
     * ------------
     * True if 2 ambient lights are equals
     * False otherwise
     *
     * -------
     * MEANING
     * -------
     *
     * --------
     * SEE ALSO
     * --------
     *************************************************/
    @Override
    public boolean equals(Object o){
        AmbientLight a = (AmbientLight)o;
        return a.getColor().equals(getColor()) && _Ka == a.getKa();
    }
}

