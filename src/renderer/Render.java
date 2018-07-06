package renderer;

import java.awt.Color;
import java.util.*;
import java.util.List;
import java.util.Map.Entry;

import elements.Light;
import elements.LightSource;
import geometries.FlatGeometry;
import geometries.Geometry;

import primitives.*;
import primitives.Ray;
import primitives.Vector;
import renderer.ImageWriter;
import scene.Scene;

import java.awt.*;

public class Render {


    private Scene _scene;
    private ImageWriter _imageWriter;
    private int RECURSION_LEVEL = 3;


    public Render(ImageWriter imageWriter, Scene scene) {
        _scene = new Scene(scene);
        _imageWriter = new ImageWriter(imageWriter);
    }


    public void setRecursionLevel(int r){ RECURSION_LEVEL = r; }

    /**
     * Function : renderImage
     * Parameter : this function doesn't take in parameter anything
     * Meaning : pick up all of point3D from _scene and realize image representing it
     * Return : this function doesn’t return anything
     */
    public void renderImage() {


        int rX = _imageWriter.getHeight()/_imageWriter.getNx();
        int rY = _imageWriter.getWidth()/_imageWriter.getNy();

        for (int y = 0; y < _imageWriter.getNy(); y++)
        {
            for (int x = 0; x < _imageWriter.getNx(); x++)
            {
                // For a given row, scan all the columns and all the ligns
                Ray ray = _scene.getCamera().constructRayThroughPixel(
                        _imageWriter.getNx(), _imageWriter.getNy(), x, y, _scene.getScreenDistance(),
                        _imageWriter.getWidth(), _imageWriter.getHeight());

                Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);

                if (intersectionPoints.isEmpty())
                    _imageWriter.writePixel(x, y, _scene.getBackground());
                else
                {
                    Map.Entry<Geometry, Point3D> closestPoint = getClosestPoint(ray, intersectionPoints).entrySet().iterator().next();
                    _imageWriter.writePixel(x, y, calcColor(closestPoint.getKey(),closestPoint.getValue(), ray));
                }
            }
        }
    }


    /**
     * Function : printGrid
     * Parameter : interval
     * Meaning : print a grid in function of interval received
     * Return : this function doesn't return anything
     */
    public void printGrid(int interval) {
        for (int i = 1; i < _imageWriter.getNx() ; i++)
        {
            for (int j = 1; j < _imageWriter.getNy(); j++)
            {
                if( (i%interval == 0) || (j%interval == 0))
                    _imageWriter.writePixel(j, i, new Color(0,0,255));
            }
        }
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



    /**
     * Function : writeToImage
     * Parameter : this function doesn't take in parameter anything
     * Meaning : finalize the picture creation by creating the file and saving it in HDD
     * Return : this function doesn't return anything
     */
    public void writeToImage() {
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
    public void writeToImage(String file_path){
        _imageWriter.writeToimage(file_path);
    }


    /**
     * Function : occluded
     * Parameter : a vector L,a Point3D, a Geometry
     * Meaning : We want to check if the figure we want to enlight is blockd by other objects. So I make shadow rays
     *           and if there is an intersection of the ray with a figuure so there is problem and that means the figure is occluded.
     *          For that, we create a ray that we call lightRay and we find the sceneRay intersections of this ray.
     * If the list of intersections is Empty the geometry i enlight is not occluded and i will calculate the diffusion and the specular effects
     * Return : True if occluded
     *          False if the list is Empty->not occluded
     */
    private boolean occluded(Vector l, Point3D point, Geometry geometry) {
        Vector lightDirection = l.scale_return(-1);
        Vector epsilonVector = new Vector(geometry.getNormal(point)).scale_return(2);

        Point3D geometryPoint = new Point3D(point);
        geometryPoint.add(epsilonVector);

        Ray lightRay = new Ray(geometryPoint, lightDirection);

        Map<Geometry, List<Point3D>> intersectionPoints =
                getSceneRayIntersections(lightRay);

        if(geometry instanceof FlatGeometry)
            intersectionPoints.remove(geometry);

        Iterator<Entry<Geometry, List<Point3D>>> entry = intersectionPoints.entrySet().iterator();

        while (entry.hasNext())
            if (entry.next().getKey().getMaterial().getKt() == 0)
                return true;

        return false;
    }


    /**
     * Function : calcSpecularComp
     * Parameters : geometry: we use it for its ks material parameter
     *              v: vector between the point and the scene origin
     *              normal: the normal vector of the geometry
     *              Vector l: the light source vector
     *              n: the shininess parameter of the geometry
     *              lightIntensity: the diffused color
     * Meaning : We obtain a new Vector r that is calculated by the formula l-(2*l·normal)*normal.
     *           We multiply ks with the v-r dot product, all is upped to shininess power.
     *           We multiply the result to the whole of lightIntensity composites.
     * Return : the result colour
     */
    public final Color calcSpecularComp(Geometry geometry, Vector v, Vector normal, Vector l,
                                        double shininess, Color lightIntensity) {
        return calcSpecularComp(geometry.getMaterial().getKs(), v, normal, l, shininess, lightIntensity);
    }


    /**
     * Function : calcSpecularComp
     * Parameters : ks: material parameter
     *              v: vector between the point and the scene origin
     *              normal: the normal vector of the geometry
     *              Vector l: the light source vector
     *              n: the shininess parameter of the geometry
     *              lightIntensity: the diffused color
     *
     *  Meaning : We obtain a new Vector r that is calculated by the formula l-(2*l·normal)*normal.
     *            We multiply ks with the v-r dot product, all is upped to shininess power.
     *            We multiply the result to the whole of lightIntensity composites.
     *  Return : the result colour
     */


    public Color calcSpecularComp(double Ks, Vector v, Vector n,Vector l,double nShininess,Color intensity){

        //  formula is SpecularComp = Ks*(V.R)^n*I

        //  get L
        Vector L= new Vector(l);
        L.normalize();


        //  get R
        n.normalize();
        n.scale(2*(l.dotProduct(n)));
        Vector r = new Vector(L.subtract_return(n));
        r.normalize();
        v.normalize();

        // get (V.R)^n
        double vr= Math.pow(r.dotProduct(v),nShininess);
        double kvr=Math.abs(Ks*vr);


        int red= (int)(kvr*intensity.getRed()) ;
        int green= (int)(kvr*intensity.getGreen());
        int blue= (int)(kvr*intensity.getBlue());


        if(red>255) red=255;
        if(green>255) green=255;
        if(blue>255) blue=255;
        if(red<0) red=0;
        if(green<0) green=0;
        if(blue<0) blue=0;


        return new Color(red,green,blue);
    }



    /**
     * Function : calcDiffusiveComp
     * Parameters : geometry: we use it to get its kd parameter
     *              normal: normal vector of the geometry
     *              l: the lightSource vector
     *              lightIntensity: the diffused color
     * Meaning : Calculation of the diffuse color from the the material.
     *           Get a material coefficient (kd) and multiply to the dot product of normal and l, two vectors.
     *           We multiply the result to the whole of lightIntensity composites.
     * Return : the result colour
     */
    public Color calcDiffusiveComp(Geometry geometry, Vector normal, Vector l, Color lightIntensity)
    {
        return calcDiffusiveComp(geometry.getMaterial().getKd(), normal, l, lightIntensity);
    }


    /**
     * Function : calcDiffusiveComp
     * Parameters : kd: material parameter
     *              normal: normal vector of the geometry
     *              l: the lightSource vector
     *              lightIntensity: the diffused color
     * Meaning : Calculation of the diffuse color from the the material.
     *           Get a material coefficient (kd) and multiply to the dot product of normal and l, two vectors.
     *           We multiply the result to the whole of lightIntensity composites
     * Return :  the result colour
     */
    private Color calcDiffusiveComp(double kd, Vector normal, Vector l, Color lightIntensity)
    {
        normal.normalize();
        l.normalize();
        double diffusedColorIntensity = Math.abs(kd * (normal.dotProduct(l)));
        return new Color(
                (int)(diffusedColorIntensity * lightIntensity.getRed()),
                (int)(diffusedColorIntensity * lightIntensity.getGreen()),
                (int)(diffusedColorIntensity * lightIntensity.getBlue()));
    }



    /**
     * Function : getClosestPoint
     * Parameter : Ray ray, Map<Geometry, List<Point3D>> intersectionPoints
     * Meaning : compare  with the intersectionPoints content and
     *           compare which distance is the most lower from P0 (camera origin) with other geometries
     * Return : return a map associating each Geometry to the closest point from origin
     */
    private Map<Geometry, Point3D> getClosestPoint(Ray ray, Map<Geometry, List<Point3D>> intersectionPoints) {
        Map<Geometry, Point3D> geometriesClosestPoint = new HashMap<>();
        double max = Double.MAX_VALUE;
        Point3D p0 = _scene.getCamera().get_P0();
        double distance;

        for (Entry<Geometry, List<Point3D>> intersectionsIt : intersectionPoints.entrySet())
        {
            for (Point3D p : intersectionsIt.getValue())
            {
                distance = p0.distance(p);

                while (max > distance)
                {
                    geometriesClosestPoint.clear();
                    geometriesClosestPoint.put(intersectionsIt.getKey(), p);
                    max = distance;
                }
            }
        }
        return geometriesClosestPoint;
    }


    /**
     * Function : getSceneRayIntersections
     * Parameter : ray
     * Meaning : realize a list that look for all intersection point that could encounter a specific ray
     * Return : this function doesn’t return anything
     */
    private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray) {
        Iterator<Geometry> geometries = _scene.getGeometriesIterator();
        Map<Geometry, List<Point3D>> geometryIntersections = new HashMap<>();

        while(geometries.hasNext())
        {
            Geometry geometry = geometries.next();
            List<Point3D> geometryIntersectionPoints = geometry.FindIntersections(ray);

            if (!geometryIntersectionPoints.isEmpty())
                geometryIntersections.put(geometry, geometryIntersectionPoints);
        }
        return geometryIntersections;
    }


    /**
     * Function : calcColor
     * Parameter : point
     * Meaning : get the representing colour of point
     * Return : return the colour of point
     **/
    private Color calcColor(Geometry geometry, Point3D point)
    {
        /*Color color = new Color(_scene.getAmbientLight().getIntensity().getRGB());
        return addColors(color, geometry.getEmission());*/

        Color ambientLight = _scene.getAmbientLight().getIntensity(point);
        Color emissionLight = geometry.getEmission();

        Color I0 = addColors(ambientLight, emissionLight);

        Color diffuseLight = new Color(Color.BLACK.getRGB());
        Color specularLight = new Color(Color.BLACK.getRGB());

        Iterator<LightSource> lights = _scene.getLightsIterator();
        while (lights.hasNext())
        {
            LightSource light = lights.next();
            //Add to IO the calculated light contribution from each light source

            if (!occluded(light.getL(point), point, geometry))
            {
                //  add the diffusive component
                diffuseLight = addColors(diffuseLight, calcDiffusiveComp(geometry.getMaterial().getKd(),
                        geometry.getNormal(point),
                        light.getL(point),
                        light.getIntensity(point)));

                //  add the specular component
                specularLight = addColors(diffuseLight, calcSpecularComp(geometry.getMaterial().getKs(),
                        new Vector(point, _scene.getCamera().get_P0()),
                        geometry.getNormal(point),
                        light.getL(point),
                        geometry.getShininess(),
                        light.getIntensity(point)));
            }
        }

        return addColors(I0, addColors(diffuseLight, specularLight));
    }



    /**
     * Function : constructReflectedRay
     * Parameter : normal: a vector
     *             point: a Point3D in the geometry
     *             inRay: a ray from the light to the point.
     * Meaning : We want to get the new reflected ray.
     *           So we calculate the new origin point of our new ray by
     *           calculating copyPoint= point + 2*normal*(the sign of the result of the dot product between n and the direction of the ray)
     *           This is the new POO of our Ray
     *           Now we get the direction of the Ray by the formula R=D- 2*(D*N)*N
     * Return : the new Ray that we have calculated with its new POO and its new direction
     */
    private Ray constructReflectedRay(Vector normal, Point3D point, Ray inRay)
    {
        // create the scalar prod
        double scalarProductDN = normal.dotProduct(inRay.getDirection().normalVector());


        //  vector dn
        Vector vectorDN = normal.scale_return(-2 * scalarProductDN);
        Vector r = new Vector(inRay.getDirection().normalVector());
        r.add(vectorDN);

        //  newray departure
        Point3D departureNewRay = new Point3D(point);

        departureNewRay.add(vectorDN);


        return new Ray(departureNewRay, r);
    }


    /**
     * Function : constructRefractedRay
     * Parameter : geometry a Geometry to get the normal
     *            point a Point3D in the geometry
     *            inRay a ray  from the light on the point
     * Meaning : We want to get the new refracted ray.
     *           So we calculate the new point of origin of our new ray by
     *           calculating copyPoint = point + 2*normal*(the sign of the result of the dot product between n and the direction of the ray)
     *           This is the new POO of our Ray with the small direction that inRay
     * Return : The new Ray that we have calculated
     */
    private Ray constructRefractedRay(Geometry geometry, Point3D point, Ray inRay)
    {
        Vector normal = geometry.getNormal(point).normalVector(); //A corriger si besoin
        normal.scale(-2);

        point.add(normal);

        return new Ray(point, inRay.getDirection().normalVector());
    }


    /**
     * Function : calcColor
     *     * Parameters : the Geometry we enlight
     *     *          the point we are going to get the Color
     *               the ray for the calculation of the reflected rays and refracted rays
     *               the level of the reflection and refraction
     *   Meaning : We calculate the color  of the point like in the function with the parameters geometry and point only .
     *             But we also take in count the reflection of the rays and the refraction and it adds color that we take in count
     *             We get the closest intersection of the reflected ray and we do the recursive function at the max final recursion level
     *             We also take in count the physical parameters of the geometry that we sent in parameters
     *             Then finally we add the reflected colors to the colors we already calculate and get a new Color
     *    Return : the final color on the point
     */
    private Color calcColor(Geometry geometry, Point3D point, Ray inRay, int level)
    {
        if(level >=  RECURSION_LEVEL)
            return Color.BLACK;

        Color instantColor = calcColor(geometry, point);

        //**// Recursive calls

        // Recursive call for a reflected ray
        Ray reflectedRay = constructReflectedRay(geometry.getNormal(point), point, inRay);
        Entry<Geometry, Point3D> reflectedEntry = findClosesntIntersection(reflectedRay);
        Color reflected = new Color(0, 0, 0);
        if (reflectedEntry != null){
            reflected = calcColor(reflectedEntry.getKey(), reflectedEntry.getValue(), reflectedRay, level + 1);
            double kr = geometry.getMaterial().getKr();
            reflected = new Color ((int)(reflected.getRed() * kr), (int)(reflected.getGreen() * kr),(int)(reflected.getBlue() * kr));
        }

        // Recursive call for a refracted ray
        Ray refractedRay = constructRefractedRay(geometry, point, inRay);
        Entry<Geometry, Point3D> refractedEntry = findClosesntIntersection(refractedRay);
        Color refracted = new Color(0, 0, 0);
        if (refractedEntry != null){
            refracted = calcColor(refractedEntry.getKey(), refractedEntry.getValue(), refractedRay, level + 1);
            double kt = geometry.getMaterial().getKt();
            refracted = new Color ((int)(refracted.getRed() * kt), (int)(refracted.getGreen() * kt),(int)(refracted.getBlue() * kt));
        }


        //**// End of recursive calls

        Color envColors = addColors(reflected, refracted);

        Color finalColor = addColors(envColors, instantColor);

        return finalColor;

        /*
        Ray reflectedRay = constructReflectedRay(geometry.getNormal(point), point, inRay);

        Entry<Geometry,Point3D> reflectedEntry = findClosesntIntersection(reflectedRay);

        Color reflectedColor = new Color(0,0,0);
        Color colorReflectedIntensity = new Color(0,0,0);

        if (reflectedEntry != null)
        {
            reflectedColor = calcColor(reflectedEntry.getKey(), reflectedEntry.getValue(),
                    reflectedRay,level+1);

            double kr = geometry.getMaterial().getKr();
            colorReflectedIntensity = new Color (
                    (int)(kr * reflectedColor.getRed()),
                    (int)(kr * reflectedColor.getGreen()),
                    (int)(kr * reflectedColor.getBlue()));

        }




        Ray refractedRay = constructRefractedRay(geometry,point,inRay);
        Entry<Geometry,Point3D> refractedEntry = findClosesntIntersection(refractedRay);
        Color refractedColor = new Color(0,0,0);
        Color colorRefractedIntensity = new Color(0,0,0);

        if (refractedEntry != null)
        {
            refractedColor = calcColor(refractedEntry.getKey(), refractedEntry.getValue(),
                    refractedRay,level+1);

            double kr = geometry.getMaterial().getKr();
            colorRefractedIntensity = new Color (
                    (int)(kr * refractedColor.getRed()),
                    (int)(kr * refractedColor.getGreen()),
                    (int)(kr * refractedColor.getBlue()));

        }

        return addColors(instantColor, addColors(colorReflectedIntensity, colorRefractedIntensity));
    */
    }



    private Entry<Geometry, Point3D> findClosesntIntersection(Ray ray)
    {
        Map<Geometry, List<Point3D>> intersectionsPoints = getSceneRayIntersections(ray);
        if (intersectionsPoints.size() == 0)
            return null;

        Map<Geometry, Point3D> closestPoint = getClosestPoint(ray, intersectionsPoints);
        Entry<Geometry, Point3D> entry = closestPoint.entrySet().iterator().next();

        return entry;
    }


    /**
     *  Function : calcColor
     *  Parameters : geometry: the geometry we are going to enlight
     *               point: the point we want to color
     *               ray: a ray that cuts the geometry
     *  Meaning : we use the function with the level 0 . We doesn't want to call the fucntion with the parameter level which is
     *            a double from a public function(render image) because it's a bad design
     *  Return : the function with the parameter level
     */
    private Color calcColor(Geometry geometry, Point3D point, Ray ray)
    {
        return calcColor(geometry, point, ray, 0);
    }


    /**
     * Function : addColors
     * Parameter : Color a, Color b
     * Meaning : add each colour component with the corresponding other.
     * Return : return the addition of the colours
     */
    public Color addColors(Color a, Color b)
    {
        int R = a.getRed() + b.getRed();
        if (R >= 255)
            R = 255;

        int B = a.getBlue() + b.getBlue();
        if (B >= 255)
            B = 255;

        int G = a.getGreen() + b.getGreen();
        if (G >= 255)
            G = 255;

        return new Color(R,G,B);
    }
}
