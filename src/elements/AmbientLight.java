package elements;

import primitives.Point3D;

import java.awt.*;
import java.util.Map;

import java.util.*;
import java.awt.Color;

public class AmbientLight extends Light implements Comparable<AmbientLight>
{
    public double _ka = 1.0;

    //region ***** CONSTRUCTORS *****

    public AmbientLight()
    {
        super();
    }

    public AmbientLight(AmbientLight Al)
    {
        _color = new Color(Al._color.getRGB());
    }

    public AmbientLight(int r, int b, int g)
    {
        _color = new Color(r,g,b); //new Color built with three numbers which are the three wave lengths
    }

    public AmbientLight(Color color)
    {
        new AmbientLight(color.getRed(),color.getGreen(), color.getBlue());
    }

    //endregion

    //region ***** GETTERS / SETTERS *****

    public double getKa()
    {
        return _ka;
    }

    public Color getColor()
    {
        return _color;
    }

    public void setColor(Color color)
    {
        _color = new Color(color.getRGB());
    }

    //endregion

    //region ***** OPERATIONS *****

    /**
     *  Function : getIntensity()
     *  Meaning : return a new color with the parameters Red,Green,Blue that are multiplied by the coefficient to get a new color
     *  Return : the new color which is the intensity
     */
    public Color getIntensity()
    {

        return new Color((int)(_ka * _color.getRed()),
                (int)(_ka * _color.getGreen()),
                (int)(_ka * _color.getBlue()));

    }

    public Color getIntensity(Point3D point)
    {
        return getIntensity();
    }

    //endregion


    //region *****ADMINISTRATION *****

    @Override
    /**
     * Function : compareTo(AmbientLight ambientlight)
     * Parameter : an ambientlight for the comparison
     * Meaning : if the color and the coefficient ka are the same return 0
     * Return : 0 if they are equal, 1 otherwise
     */
    public int compareTo(AmbientLight ambientLight)
    {
        if(this._color == ambientLight.getColor() && this._ka == ambientLight.getKa())
            return 0;
        return -1;
    }

    @Override
    public String toString()
    {
        return ("Color: "+ _color + " Factor Intensity: " + _ka);
    }

}
