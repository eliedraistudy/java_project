package elements;

import java.awt.*;

public abstract class Light {

    // ***************** Fields ********************** //
    protected Color _color;

    // ***************** Constructors ********************** //
    public Light(){}
    public Light (Color color){ _color = new Color(color.getRGB()); }

    // ***************** Getters/Setters ********************** //
    public abstract Color getIntensity();
}
