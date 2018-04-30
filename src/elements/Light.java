package elements;

import java.awt.*;

public abstract class Light {

    // ***************** Fields ********************** //
    protected Color _color;

    // ***************** Constructors ********************** //
    public Light(){_color = new Color(255,255,255);}
    public Light (Color color){ _color = new Color(color.getRGB()); }
    public Light(Light l) { _color = new Color(l._color.getRGB());}

    // ***************** Getters/Setters ********************** //
    public abstract Color getIntensity();
}
