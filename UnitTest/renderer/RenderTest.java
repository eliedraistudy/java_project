package renderer;

import elements.AmbientLight;
import elements.LightSource;
import elements.PointLight;
import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

import java.awt.*;

public class RenderTest {


    @Test
    public void SpecularDiffusive(){

        Scene scene = new Scene();
        scene.setScreenDistance(200);
        //scene.setAmbientLight(new AmbientLight(Color.GRAY, 0.3));

        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
        sphere.setShininess(70);
        sphere.setEmission(new Color(100, 100, 200));
        scene.addGeometry(sphere);

        /*Triangle triangle = new Triangle(new Point3D(-125, -225, -260),
                new Point3D(-225, -125, -260),
                new Point3D(-225, -225, -270));

        triangle.setEmission(new Color (0, 0, 100));
        triangle.setShininess(4);
        scene.addGeometry(triangle);*/

        scene.addLight(
                new SpotLight(
                        new Color(200,100,100),
                        new Point3D(-200, -200, -150),
                        new Vector(0, 0, -3),
                        0.02, 0.001, 0.000001
                        )
        );

        ImageWriter imageWriter = new ImageWriter("Spot test 2", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage("/results/Render2/");
    }


    @Test
    public void shadowTest(){

        Scene scene = new Scene();

        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
        sphere.setShininess(20);
        sphere.setEmission(new Color(0, 0, 100));

        scene.addGeometry(sphere);

        Triangle triangle1 = new Triangle(new Point3D(  3500,  3500, -2000),
                new Point3D( -3500, -3500, -1000),
                new Point3D(  3500, -3500, -2000));

        Triangle triangle2 = new Triangle(new Point3D(  3500,  3500, -2000),
                new Point3D( -3500,  3500, -1000),
                new Point3D( -3500, -3500, -1000));

        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);

        scene.addLight(
                new SpotLight(
                        new Color(100, 100, 100),
                        new Point3D(500, 0, -100),
                        new Vector(-1, 0, 0),
                        0,
                        0.000001,
                        0.0000005));


        ImageWriter imageWriter = new ImageWriter("Shadow test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }
}



