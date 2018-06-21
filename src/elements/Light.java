package elements;

import primitives.*;
import primitives.Vector;
import java.util.*;
import java.awt.Color;

public abstract class Light
{
    protected Color _color = new Color(0,0,0);

    //region ***** CONSTRUCTOR *****

    public Light() {
        _color = Color.BLACK;
    }

    public Light(Color c)
    {
        _color = new Color(c.getRGB());
    }

    public Light(Light l){
        _color = l._color;
    }

    //endregion

    //region ***** GETTERS / SETTERS *****

    public Color GetIntensity()
    {
        return _color;
    }


    //endregion
}
