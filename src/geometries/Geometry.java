package geometries;


import java.awt.Color;
import primitives.*;
import java.util.List;


public abstract class Geometry
{
    //******************* FIELDS *******************//
    private Material _material = new Material();
    private double _nShininess = 1;
    private Color _emission = new Color(1,0,0);




    //******************* Abstract methods *******************//
    public abstract List<Point3D> FindIntersections (Ray ray);
    public abstract Vector getNormal(Point3D point);

    //******************* GETTERS/SETTERS *******************//
    public double   getShininess(){ return _nShininess; }
    public Material getMaterial() { return _material; }
    public Color getEmission() { return _emission; }

    public void setShininess(double n) { _nShininess = n; }
    public void setMaterial(Material m) { _material = m; }
    public void setEmission(Color e) { _emission = e; }

    public void setKs(double ks) { _material.setKs(ks); }
    public void setKd(double kd) { _material.setKd(kd); }
    public void setKr(double kr) { _material.setKr(kr); }
    public void setKt(double kt) { _material.setKd(kt); }


}
