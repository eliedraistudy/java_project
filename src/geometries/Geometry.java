package geometries;


import javafx.scene.paint.Color;
import primitives.Material;

public class Geometry
{
    //******************* FIELDS *******************//
    private Material _material;
    private double _nShininess;
    private Color _emission;



    //******************* CONSTRUCTORS *******************//

    /**
     * Default consructor
     */
    public Geometry()
    {
        _material = new Material();
        _nShininess = 1;
        _emission = new Color(0,0,1,1.0);
    }


}
