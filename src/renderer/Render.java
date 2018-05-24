package renderer;


import java.awt.Color;
import java.util.*;
import java.util.Map.Entry;

import elements.Light;
import elements.LightSource;
import geometries.Geometry;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
public class Render
{
    private Scene _scene;
    private ImageWriter _imageWriter;
    private final int RECURSION_LEVEL = 3;

    // ***************** Constructors ********************** //
    public Render(ImageWriter imageWriter, Scene scene){
        _scene = scene;
        _imageWriter = imageWriter;
    }

    // ***************** Operations ******************** //

    /*************************************************
     * --------
     * FUNCTION
     * --------
     * render the image, main class function
     *
     * ------------
     * PARAMETER(S)
     * ------------
     *
     * ------------
     * RETURN VALUE
     * ------------
     * void function
     *
     * -------
     * MEANING
     * -------
     * Calculate the intersections between each pixel on the screen and the geometries of the scene
     * then color the corresponding pixel in accordance of the geometry emission color and the ambient light color
     *
     * --------
     * SEE ALSO
     * --------
     *************************************************/
    public void renderImage(){

        //  for each point of the screen,
        // construct the ray and check intersections
        for (int i = 0; i < _imageWriter.getHeight(); i++) {
            for (int j = 0; j < _imageWriter.getWidth(); j++) {

                //  construct the ray through pixel
                Ray ray = _scene.
                        getCamera().
                        constructRayThroughPixel(
                                _imageWriter.getNx(),
                                _imageWriter.getNy(),
                                i, j,
                                _scene.getScreenDistance(),
                                _imageWriter.getWidth(),
                                _imageWriter.getHeight());

                //  create a map of intersections for each geometry
                Map<Geometry,List<Point3D>> hm = getSceneRayIntersections(ray);

                //  if no intersections, color according to background
                if(noIntersections(hm))
                    _imageWriter.writePixel(j,i,_scene.getBackground());
                else{
                    //  get the closest point
                    Map<Geometry, Point3D> m = getClosestPoint(ray, hm);
                    Geometry g = m.entrySet().iterator().next().getKey();
                    Point3D p = m.entrySet().iterator().next().getValue();

                    //  color the corresponding pixel
                    _imageWriter.writePixel(
                            j,i,
                            addColors(
                                    g.getEmission(),
                                    _scene.getAmbientLight().getIntensity()));
                }
                

            }

        }

    }

    /**
     * Function to check if a specific ray makes any intersections
     * @param map
     * @return
     */
    private boolean noIntersections(Map<Geometry,List<Point3D>> map){
        for (Map.Entry<Geometry,List<Point3D>> it: map.entrySet()) {
            List<Point3D> l = it.getValue();
            if(!l.isEmpty())
                return false;
        }
        return true;
    }



    /*************************************************
     * --------
     * FUNCTION
     * --------
     * printGrid
     *
     * ------------
     * PARAMETER(S)
     * ------------
     * int - interval
     *
     * ------------
     * RETURN VALUE
     * ------------
     * void function
     *
     * -------
     * MEANING
     * -------
     * print the grid on the picture
     *
     * --------
     * SEE ALSO
     * --------
     *************************************************/
    public void printGrid(int interval){
        for (int i = 0; i < _imageWriter.getHeight(); i++) {
            for (int j = 0; j < _imageWriter.getWidth(); j++) {

                if(i%50 == 0 || j%50 == 0)
                    _imageWriter.writePixel(j,i,Color.BLACK);
            }
        }
    }



    public void writeToImage(){
        _imageWriter.writeToimage();
    }

    public void writeToImage(String file){
        _imageWriter.writeToimage(file);
    }



    private Color calcColor(Geometry geometry, Point3D point, Ray ray){
        return
                addColors(geometry.getEmission(), _scene.getAmbientLight().getColor());
    }

    private Color calcColor(Geometry geometry, Point3D point, Ray inRay, int level){
        return null;
    }


    // Recursive
    private Ray constructRefractedRay(Geometry geometry, Point3D point, Ray inRay){
        return null;
    }

    private Ray constructReflectedRay(Vector normal, Point3D point, Ray inRay){
       return null;
    }

    private boolean occluded(LightSource light, Point3D point, Geometry geometry){
        return true;
    }


    private Color calcSpecularComp(
            double ks,
            Vector v,
            Vector normal,
            Vector l,
            double shininess,
            Color lightIntensity){
        return null;
    }


    private Color calcDiffusiveComp(
            double kd,
            Vector normal,
            Vector l,
            Color lightIntensity){
        return null;

    }


    /*************************************************
     * --------
     * FUNCTION
     * --------
     * findClosestIntersection
     *
     * ------------
     * PARAMETER(S)
     * ------------
     * Ray - the ray from which we calculate the distance
     * List<Point3D> - the list of points
     *
     * ------------
     * RETURN VALUE
     * ------------
     * Point3D - the point which is closest to the ray start
     *
     * -------
     * MEANING
     * -------
     * Loop on the list to find the minimal distance and return a copy of this point
     *
     * --------
     * SEE ALSO
     * --------
     *************************************************/
    private Point3D findClosestIntersection(
            Ray ray, // the ray to calculate distance from
            List<Point3D> l) {// the list of point{

        Point3D ret = l.get(0);
        for(Point3D p: l){
            if(ray.getPOO().distance(p)<= ray.getPOO().distance(ret))
                ret = p;
        }
        return new Point3D(ret);
    }


    private Map<Geometry, Point3D> getClosestPoint(
            Ray ray,
            Map<Geometry, List<Point3D>> intersectionPoints) {

        Point3D p;
        Point3D help;
        Geometry g;

        //  get the first entry
        Entry<Geometry, List<Point3D>> entry =
                intersectionPoints.entrySet().iterator().next();

        g = entry.getKey();
        p = findClosestIntersection(ray, entry.getValue());

        for (Map.Entry<Geometry, List<Point3D>> e : intersectionPoints.entrySet()) {
            help = findClosestIntersection(ray, e.getValue());
            if (ray.getPOO().distance(help) <= ray.getPOO().distance(p)) {
                g = e.getKey();
                p = help;
            }


        }

        Map m = new HashMap<>();
        m.put(g, p);
        return m;
    }



    /**
     * Create a map for each scene geometry the corresponding intersections points
     * @param ray
     * @return
     */
    private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray){
        Map<Geometry, List<Point3D>> hm = new HashMap<>();

        Iterator it = _scene.getGeometriesIterator();

        //  pass through all the geometries
        //  construct the intersections for each of them
        while(it.hasNext()){
            Geometry g = (Geometry)it.next();
            List<Point3D> intersections = g.FindIntersections(ray);
            //  only gives geometries which have intersections
            if(!intersections.isEmpty())
                hm.put(g,intersections);
        }

        return hm;
    }


    /**
     * Adding between 2 colors
     * @param a
     * @param b
     * @return
     */
    private Color addColors(Color c0, Color c1){
        double totalAlpha = c0.getAlpha() + c1.getAlpha();
        double weight0 = c0.getAlpha() / totalAlpha;
        double weight1 = c1.getAlpha() / totalAlpha;

        double r = weight0 * c0.getRed() + weight1 * c1.getRed();
        double g = weight0 * c0.getGreen() + weight1 * c1.getGreen();
        double b = weight0 * c0.getBlue() + weight1 * c1.getBlue();
        double a = Math.max(c0.getAlpha(), c1.getAlpha());

        return new Color((int) r, (int) g, (int) b, (int) a);

        //return
    }
}