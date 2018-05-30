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
                    //  get the closest geometry point
                    Map<Geometry, Point3D> m = getClosestPoint(ray, hm);
                    Geometry g = m.entrySet().iterator().next().getKey();

                    //  color the corresponding pixel
                    _imageWriter.writePixel(
                            j,i,
                            calcColor(g)
                    );
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
    public void printGrid(int interval, Color c){
        for (int i = 0; i < _imageWriter.getHeight(); i++) {
            for (int j = 0; j < _imageWriter.getWidth(); j++) {

                if(i%interval == 0 || j%interval == 0)
                    _imageWriter.writePixel(j,i,c);
            }
        }
    }



    /*************************************************
     * --------
     * FUNCTION
     * --------
     * writeToImage
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
     * write the values onto the file, call the ImageWriter write to image method
     *
     * --------
     * SEE ALSO
     * --------
     *************************************************/
    public void writeToImage(){
        _imageWriter.writeToimage();
    }


    /*************************************************
     * --------
     * FUNCTION
     * --------
     * writeToImage
     *
     * ------------
     * PARAMETER(S)
     * ------------
     *
     * ------------
     * RETURN VALUE
     * ------------
     * String - the string name of the path where to write
     *
     * -------
     * MEANING
     * -------
     * same as above but also get in parameter the path to the file where to write the image
     *
     * --------
     * SEE ALSO
     * --------
     *************************************************/
    public void writeToImage(String file){
        _imageWriter.writeToimage(file);
    }



    private Color calcColor(Geometry g){
        return
                subColors(_scene.getAmbientLight().getIntensity(new Point3D()),g.getEmission() );
    }


    private Color calcColor(Geometry geometry, Point3D point, Ray ray) {

        return calcColor(geometry, point, ray, 0);
    }

    private Color calcColor(Geometry geometry, Point3D point, Ray inRay, int level){
        if (level == RECURSION_LEVEL){
            return new Color(0, 0, 0);
        }

        Color ambientLight = _scene.getAmbientLight().getIntensity(new Point3D());
        Color emissionLight = geometry.getEmission();

        Color inherentColors = addColors(ambientLight, emissionLight);

        Iterator<LightSource> lights = _scene.getLightsIterator();

        Color lightReflected = new Color(0, 0, 0);

        while (lights.hasNext()){

            LightSource light = lights.next();

            if (!occluded(light, point, geometry)){

                Color lightIntensity = light.getIntensity(point);


                Color lightDiffuse = calcDiffusiveComp(geometry.getMaterial().getKd(),
                        geometry.getNormal(point),
                        light.getL(point),
                        lightIntensity);


                Color lightSpecular = calcSpecularComp(geometry.getMaterial().getKs(),
                        new Vector(point, _scene.getCamera().get_P0()),
                        geometry.getNormal(point),
                        light.getL(point),
                        geometry.getShininess(),
                        lightIntensity);

                lightReflected = addColors(lightDiffuse, lightSpecular);
            }
        }

        Color I0 = addColors(inherentColors, lightReflected);


        //**// Recursive calls

        // Recursive call for a reflected ray
        Ray reflectedRay = constructReflectedRay(geometry.getNormal(point), point, inRay);
        Entry<Geometry, Point3D> reflectedEntry = findClosestIntersection(reflectedRay);
        Color reflected = new Color(0, 0, 0);
        if (reflectedEntry != null){
            reflected = calcColor(reflectedEntry.getKey(), reflectedEntry.getValue(), reflectedRay, level + 1);
            double kr = geometry.getMaterial().getKr();
            reflected = new Color (
                    (int)(reflected.getRed() * kr),
                    (int)(reflected.getGreen() * kr),
                    (int)(reflected.getBlue() * kr)
            );
        }

        // Recursive call for a refracted ray
        Ray refractedRay = constructRefractedRay(geometry, point, inRay);
        Entry<Geometry, Point3D> refractedEntry =
                findClosestIntersection(refractedRay);
        Color refracted = new Color(0, 0, 0);
        if (refractedEntry != null){
            refracted = calcColor(refractedEntry.getKey(), refractedEntry.getValue(), refractedRay, level + 1);
            double kt = geometry.getMaterial().getKt();
            refracted =
                    new Color (
                            (int)(refracted.getRed() * kt),
                            (int)(refracted.getGreen() * kt),
                            (int)(refracted.getBlue() * kt)
                    );
        }


        //**// End of recursive calls

        Color envColors = addColors(reflected, refracted);

        Color finalColor = addColors(envColors, I0);

        return finalColor;
    }

    /************************************************
     * FUNCTION
     *  findClosesntIntersection();
     *
     * PARAMETERS
     *  Ray - a camera ray.
     *
     * RETURN VALUE
     *  @return Entry/<Geometry,Point3D> - an Entry iterator to a Map
     *  that associates the closest intersection point of a geometry to itself.
     *
     * MEANING
     *  Find the closest point of each geometry in the scene to the screen.
     *
     * See Also
     *  The function renderImage() is calling in order to get the points
     *  it need to color
     *
     **************************************************/
    private Entry<Geometry, Point3D> findClosestIntersection(Ray ray){

        Map<Geometry,List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);

        if(intersectionPoints.size() == 0)
            return null;

        Map<Geometry,Point3D> closestPoint = getClosestPoint(intersectionPoints);
        Entry<Geometry,Point3D> entry = closestPoint.entrySet().iterator().next();
        return entry;
    }

    /*************************************************
     * FUNCTION
     *  getClosestPoint();
     *
     * PARAMETERS
     *
     * RETURN VALUE
     *
     * MEANING
     *
     * See Also
     *
     **************************************************/
    private Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> intersectionPoints) {
        Map<Geometry, Point3D> geometriesClosestPoint = new HashMap<Geometry,Point3D>();
        double max = 999999999;
        Point3D p0=_scene.getCamera().get_P0();
        double distance;

        for(Entry<Geometry,List<Point3D>> intersectionsIt : intersectionPoints.entrySet()){
            for(Point3D p : intersectionsIt.getValue())
            {
                distance=p0.distance(p);
                while(max>distance)
                {
                    geometriesClosestPoint.clear();
                    geometriesClosestPoint.put(intersectionsIt.getKey(),p);
                    max=distance;
                }
            }
        }
        return  geometriesClosestPoint;
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

    private boolean occluded(Vector l, Point3D point, Geometry geometry) {

        Vector lightDirection = l.scale_return(-1); // from point to light source
        Ray lightRay = new Ray(point, lightDirection);
        Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(lightRay);
        return !intersectionPoints.isEmpty();
    }


    private Color calcSpecularComp(
            double ks,
            Vector vector,
            Vector N,
            Vector D,
            double shininess,
            Color lightIntensity){
        vector.normalize();
        N.normalize();
        D.normalize();
        double k = 0;

        double Dtemp = D.dotProduct(N);
        N.scale(-2*Dtemp);
        D.add(N);

        Vector R = new Vector(D);

        if(vector.dotProduct(R)>0){
            k = ks*Math.pow(vector.dotProduct(R),shininess);
        }

        Color IO = new Color((int)(lightIntensity.getRed() * k),
                (int)(lightIntensity.getGreen() * k),
                (int)(lightIntensity.getBlue()   * k));

        return IO;
    }


    private Color calcDiffusiveComp(
            double kd,
            Vector normal,
            Vector l,
            Color lightIntensity){
        normal.normalize();
        l.normalize();
        double dot = normal.dotProduct(l);

        double temp = Math.abs(kd*dot);

        Color IO = new Color((int)(lightIntensity.getRed() * temp),
                (int)(lightIntensity.getGreen() * temp),
                (int)(lightIntensity.getBlue() * temp));

        return IO;

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

        Map<Geometry, Point3D> geometriesClosestPoint = new HashMap<>();
        double max = 999999999;
        Point3D p0 = _scene.getCamera().get_P0();
        double distance;

        for (Entry<Geometry, List<Point3D>> intersectionsIt : intersectionPoints.entrySet()) {
            for (Point3D p : intersectionsIt.getValue()) {
                distance = p0.distance(p);
                while (max > distance) {
                    geometriesClosestPoint.clear();
                    geometriesClosestPoint.put(intersectionsIt.getKey(), p);
                    max = distance;
                }
            }
        }
        return geometriesClosestPoint;

    }



    /**
     * Create a map for each scene geometry the corresponding intersections points
     * @param ray
     * @return
     */
    private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray){
        Iterator<Geometry> geometries = _scene.getGeometriesIterator();
        Map<Geometry, List<Point3D>> geometryIntersections = new HashMap<>();

        while(geometries.hasNext()) {
            Geometry geometry = geometries.next();
            List<Point3D> geometryIntersectionPoints = geometry.FindIntersections(ray);
            if (!geometryIntersectionPoints.isEmpty())
                geometryIntersections.put(geometry, geometryIntersectionPoints);
        }
        return geometryIntersections;
    }


    private Color subColors(Color light, Color object){
        int r = 255 - object.getRed();
        int g = 255 - object.getGreen();
        int b = 255 - object.getBlue();

        int r_ret = light.getRed() - r;
        int g_ret = light.getGreen() - g;
        int b_ret = light.getBlue() - b;

        if(r_ret<=0) r_ret = 0;
        if(g_ret<=0) g_ret = 0;
        if(b_ret<=0) b_ret = 0;

        return new Color(r_ret, g_ret, b_ret);
    }

    private Color addColors(Color a, Color b){
        int R = a.getRed() + b.getRed();
        if(R>255)
            R = 255;
        int G = a.getGreen() + b.getGreen();
        if(G>255)
            G = 255;
        int B = a.getBlue() + b.getBlue();
        if(B>255)
            B = 255;

        return new Color(R,G,B);
    }
}
