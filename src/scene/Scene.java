package scene;

import elements.AmbientLight;
import elements.Camera;
import elements.Light;
import geometries.Geometry;
import elements.LightSource;


import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Scene{

    //************** FIELDS **************//
    String _sceneName;
    Color _background;
    AmbientLight _ambientLight;
    ArrayList<Geometry> _geometries;
    Camera _camera;
    double _screenDistance;
    ArrayList<LightSource> _lights;



    //************** CONSTRUCTOR(S) **************//
    public Scene()
    {
        this._geometries = new ArrayList<Geometry>();
        this._lights= new ArrayList<LightSource>();
        this._background = new Color(Color.BLACK.getRGB());// Check if is true to do it.
        this._ambientLight = new AmbientLight(Color.GRAY);
        this._camera = new Camera();
        this._sceneName = "scene";
        this._screenDistance = 100;

    }
    public Scene (Scene scene)
    {
        this._screenDistance = scene._screenDistance;
        this._sceneName = new String(scene._sceneName);
        this._camera = new Camera(scene._camera);
        this._ambientLight = new AmbientLight(scene._ambientLight);
        this._background = new Color(scene._background.getRGB());
        this._geometries = new ArrayList<Geometry>(scene._geometries);
        this._lights= new ArrayList<LightSource>(scene._lights);


    }
    public Scene(AmbientLight aLight, Color background, Camera camera, double screenDistance)
    {
        this._ambientLight = new AmbientLight(aLight);
        this._background = new Color(background.getRGB());
        this._camera = new Camera(camera);
        this._screenDistance = screenDistance;
        this._sceneName = "scene";
        this._geometries = new ArrayList<Geometry>();
        this._lights= new ArrayList<LightSource>();
    }

    //************** GETTERS/SETTERS **************//
    public Color getBackground(){return this._background;}

    public AmbientLight getAmbientLight(){return this._ambientLight;}

    public Camera getCamera(){return this._camera;}

    public String getSceneName(){return this._sceneName;}

    public double getScreenDistance(){return this._screenDistance;}

    public void setBackground(Color _background){

        this._background = new Color(_background.getRGB());
    }
    public void setAmbientLight(AmbientLight ambientLight){
        this._ambientLight = new AmbientLight(ambientLight);
    }

    public void setCamera(Camera camera){

        this._camera = new Camera(camera);
    }

    public void setSceneName(String sceneNAme){

        this._sceneName = new String(sceneNAme);
    }

    public void setScreenDistance(double screenDistance){
        this._screenDistance = screenDistance;
    }

    //************** METHODS **************//


    /**
     * Add a new geometry to the current list of geometry
     * @param geometry
     */
    public void addGeometry(Geometry geometry)
    {
        this._geometries.add(geometry); // Check if need new
    }

    /**
     * Get an iterator over all the geometries
     * @return
     */
    public Iterator<Geometry> getGeometriesIterator() {

        return this._geometries.iterator();
    } //Check if need new


    /**
     * Add an other Light source to the current scene
     * @param light
     */
    public void addLight(LightSource light) {

        this._lights.add(light);
    }


    /**
     * Get an iterator on the lightsource list
     * @return
     */
    public Iterator<LightSource> getLightsIterator() {
        return this._lights.iterator();
    }
}

