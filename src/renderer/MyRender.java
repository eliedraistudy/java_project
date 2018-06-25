package renderer;


import java.awt.Color;
import java.util.*;
import java.util.Map.Entry;
import elements.LightSource;
import geometries.*;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;


public class MyRender {

    /**
     * A Render is an engine that prepare a scene and its components.
     * It also write the scene into a file with the help of ImageWriter class.
     * */

    private Scene _scene;
    private ImageWriter _imageWriter;
    private final int RECURSION_LEVEL = 3;

    // ***************** Constructors ********************** //

    /*************************************************
     * FUNCTION
     *  Render();
     *
     * PARAMETERS
     *  No parameters.
     *
     * RETURN VALUE
     *  No return value.
     *
     * MEANING
     *  A default CTOR for render class.
     *
     * See Also
     *  ---
     **************************************************/
    public MyRender(){
        _scene = new Scene();
    }

    /*************************************************
     * FUNCTION
     *  Render();
     *
     * PARAMETERS
     *  ImageWriter - an object that write a picture file.
     *  Scene - a given scene with all elements (geometries, lights, ...)
     *
     * RETURN VALUE
     *  No return value.
     *
     * MEANING
     * Constructor of the Render.
     *
     * See Also
     *  ---
     **************************************************/
    public MyRender(ImageWriter imageWriter, Scene scene) {
        _scene = new Scene(scene);
        _imageWriter = new ImageWriter(imageWriter);
    }

    // ***************** Operations ******************** //

    /*************************************************
     * FUNCTION
     *  renderImage();
     *
     * PARAMETERS
     *  No parameters.
     *
     * RETURN VALUE
     *  No return value.
     *
     * MEANING
     *  This function calculate for each point in the view
     *  screen what is its color values and write them into
     *  an image file.
     *
     * See Also
     *  ---
     **************************************************/
    public void renderImage(){
        // Scan the screen rows.
        for(int i = 0; i<_imageWriter.getHeight(); i++) {
            // For a given row, scan all the columns.
            for(int j = 0; j<_imageWriter.getWidth(); j++){

                Ray ray = _scene.getCamera().constructRayThroughPixel(_imageWriter.getNx(),
                        _imageWriter.getNy(), j, i, _scene.getScreenDistance(),
                        _imageWriter.getWidth(), _imageWriter.getHeight());

                Entry<Geometry, Point3D> intersectionPoint = findClosestIntersection(ray);

                // When there are no objects in the scene.
                if(intersectionPoint == null){
                    _imageWriter.writePixel(j,i,_scene.getBackground());
                } else{
                    _imageWriter.writePixel(j,i,calcColor(intersectionPoint.getKey(),intersectionPoint.getValue(),ray));
                }
            }
        }
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

        Map<Geometry,List<Point3D>>
                intersectionPoints = getSceneRayIntersections(ray);

        if(intersectionPoints.size() == 0)
            return null;

        Map<Geometry,Point3D> closestPoint = getClosestPoint(intersectionPoints);
        Entry<Geometry,Point3D> entry = closestPoint.entrySet().iterator().next();
        return entry;
    }

    /*************************************************
     * FUNCTION
     *  printGrid()
     *
     * PARAMETERS
     *  int -
     *
     * RETURN VALUE
     *  ImageWriter -
     *
     * MEANING
     *
     * See Also
     *  ---
     **************************************************/
    public void printGrid(int interval){

        int height = _imageWriter.getHeight();
        int width = _imageWriter.getWidth();

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){

                if (i % interval == 0 || j % interval == 0)
                    _imageWriter.writePixel(j, i, 255, 255, 255);

            }
        }
    }

    public void printGrid(int interval, Color c){

        int height = _imageWriter.getHeight();
        int width = _imageWriter.getWidth();

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){

                if (i % interval == 0 || j % interval == 0)
                    _imageWriter.writePixel(j, i, c);

            }
        }
    }

    /*************************************************
     * FUNCTION
     *  calcColor();
     *
     * PARAMETERS
     *  Geometry -
     *  Point3D -
     *  Ray -
     *
     * RETURN VALUE
     *  Color -
     *
     * MEANING
     *
     * See Also
     *
     **************************************************/
    private Color calcColor(Geometry geometry, Point3D point, Ray ray){

        return calcColor(geometry,point,ray,0);
    }

    /*************************************************
     * FUNCTION
     *  calcSpecularComp()
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
    private Color calcSpecularComp(double ks, Vector vector, Vector N, Vector D, double shininess, Color lightIntensity) {
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

    /*************************************************
     * FUNCTION
     *  calcSpecularComp()
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
    private Color calcDiffusiveComp(double kd, Vector normal, Vector l, Color lightIntensity) {
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

    /*************************************************
     * FUNCTION
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
    private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray) {
        Iterator<Geometry> geometries = _scene.getGeometriesIterator();
        Map<Geometry, List<Point3D>>
                geometryIntersections = new HashMap<>();

        while(geometries.hasNext()) {
            Geometry geometry = geometries.next();
            List<Point3D> geometryIntersectionPoints = geometry.FindIntersections(ray);
            if (!geometryIntersectionPoints.isEmpty())
                geometryIntersections.put(geometry, geometryIntersectionPoints);
        }
        return geometryIntersections;
    }

    /*************************************************
     * FUNCTION
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

    private int correctColor(int n){
        if(n>255)return 255;
        if(n<0) return 0;
        return n;
    }
    private Color subColors(Color a, Color b){
        int R = a.getRed() - (255 + b.getRed());
        int G = a.getGreen() - (255 + b.getGreen());
        int B = a.getBlue() - (255 + b.getBlue());

        R = correctColor(R);
        G = correctColor(G);
        B = correctColor(B);

        return new Color(R,G,B);
    }

    /*************************************************
     * FUNCTION
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
    public void writeToImage(){
        _imageWriter.writeToimage();
    }

    public void writeToImage(String s) { _imageWriter.writeToimage(s);}

    /*************************************************
     * FUNCTION
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
    private Color calcColor(Geometry geometry, Point3D point, Ray inRay, int level){
        /*if(level == RECURSION_LEVEL)
            return new Color(0, 0, 0);

        Color ambientLight = _scene.getAmbientLight().getIntensity();
        Color emissionLight = geometry.getEmmission();
        Color diffuseLight = new Color(0,0,0);
        Color lightSpecular = new Color(0,0,0);

        Iterator<LightSource> lights = _scene.getLightsIterator();
        while(lights.hasNext()) {
            LightSource light = lights.next();

            if (!occluded(light, point, geometry)) {

                Color lightIntensity = light.getIntensity(point);
                diffuseLight = calcDiffusiveComp(geometry.getMaterial().getKd(), geometry.getNormal(point), light.getL(point), lightIntensity);
                lightSpecular = calcSpecularComp(geometry.getMaterial().getKs(), new Vector(point, _scene.getCamera().getP0()), geometry.getNormal(point), light.getL(point), geometry.getShininess(), lightIntensity);
            }
        }

        Ray reflectedRay = constructReflectedRay(geometry.getNormal(point), point, inRay);
        Entry<Geometry, Point3D> reflectedEntry = findClosesntIntersection(reflectedRay);
        Color reflectedColor = calcColor(reflectedEntry.getKey(), reflectedEntry.getValue(), reflectedRay, level+1);

        double kr = geometry.getMaterial().getKr();
        Color reflectedLight = new Color((int)kr*reflectedColor.getRed(),(int)kr*reflectedColor.getBlue(), (int)kr*reflectedColor.getGreen());

        Ray refractedRay = constructRefractedRay(geometry, point, inRay);
        Entry<Geometry, Point3D> refractedEntry = findClosesntIntersection(refractedRay);
        Color refractedColor = calcColor(refractedEntry.getKey(), refractedEntry.getValue(), refractedRay, level+1);
        double kt = geometry.getMaterial().getKt();
        Color refractedLight = new Color((int)kt*reflectedColor.getRed(),(int)kt*reflectedColor.getBlue(), (int)kt*reflectedColor.getGreen());

        return new Color (ambientLight.getRGB() + emissionLight.getRGB() + diffuseLight.getRGB() + lightSpecular.getRGB() + reflectedLight.getRGB() + refractedLight.getRGB());*/

        if (level == RECURSION_LEVEL){
            return new Color(0, 0, 0);
        }

        Color ambientLight = _scene.getAmbientLight().getIntensity(new Point3D());
        Color emissionLight = geometry.getEmission();
        Color inherentColors = subColors(ambientLight, emissionLight);

        //Color inherentColors = addColors(ambientLight, emissionLight);

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
            reflected = new Color ((int)(reflected.getRed() * kr), (int)(reflected.getGreen() * kr),(int)(reflected.getBlue() * kr));
        }

        // Recursive call for a refracted ray
        Ray refractedRay = constructRefractedRay(geometry, point, inRay);
        Entry<Geometry, Point3D> refractedEntry = findClosestIntersection(refractedRay);
        Color refracted = new Color(0, 0, 0);
        if (refractedEntry != null){
            refracted = calcColor(refractedEntry.getKey(), refractedEntry.getValue(), refractedRay, level + 1);
            double kt = geometry.getMaterial().getKt();
            refracted = new Color ((int)(refracted.getRed() * kt), (int)(refracted.getGreen() * kt),(int)(refracted.getBlue() * kt));
        }


        //**// End of recursive calls

        Color envColors = addColors(reflected, refracted);

        Color finalColor = addColors(envColors, I0);

        return finalColor;
    }

    /*************************************************
     * FUNCTION
     *  calcSpecularComp()
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
    private Ray constructRefractedRay(Geometry geometry, Point3D point, Ray inRay){
        Vector normal = geometry.getNormal(point);
        normal.scale(-2);
        point.add(normal);

        if (geometry instanceof FlatGeometry){
            return new Ray (point, inRay.getDirection());
        } else {
            // Here, Snell's law can be implemented.
            // The refraction index of both materials had to be derived
            return new Ray (point, inRay.getDirection());
        }
    }

    /*************************************************
     * FUNCTION
     *  calcSpecularComp()
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
    private Ray constructReflectedRay(Vector normal, Point3D point, Ray inRay){
        Vector l = inRay.getDirection();
        l.normalize();

        normal.scale(-2 * l.dotProduct(normal));
        l.add(normal);

        Vector R = new Vector(l);
        R.normalize();

        point.add(normal);

        Ray reflectedRay = new Ray(point, R);

        return reflectedRay;
    }

    /*************************************************
     * FUNCTION
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
    private boolean occluded(LightSource light, Point3D point, Geometry geometry){
        Vector lightDirection = light.getL(point);
        lightDirection.scale(-1);

        Point3D geometryPoint = new Point3D(point);

        Vector epsVector = new Vector(geometry.getNormal(point));
        epsVector.scale(2);
        geometryPoint.add(epsVector);

        Ray lightRay = new Ray(geometryPoint, lightDirection);

        Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(lightRay);

        if(geometry instanceof FlatGeometry)
            intersectionPoints.remove(geometry);

        Iterator<Entry<Geometry, List<Point3D>>> entry = intersectionPoints.entrySet().iterator();
        while(entry.hasNext())
            if (entry.next().getKey().getMaterial().getKt() == 0)
                return true;
        return false;
    }
}
