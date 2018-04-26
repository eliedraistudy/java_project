package elements;

import java.awt.*;
import java.util.Map;

public class AmbientLight extends Light {

    // ***************** Fields ********************** //
    private final double _Ka = 0.1;

    // ***************** Constructors ********************** //
    public AmbientLight(){}
    public AmbientLight(AmbientLight aLight){}
    public AmbientLight(int r, int g, int b){}
    public AmbientLight(Map<String, String> attributes){}

    // ***************** Getters/Setters ********************** //
    public Color getColor(){ return _color; }
    public void  setColor(Color color) { _color = new Color(color.getRGB()); }
    public double getKa() { return _Ka; }
    public Color getIntensity();
}
