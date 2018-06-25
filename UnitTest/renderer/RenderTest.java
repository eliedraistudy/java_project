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
    public void SpecularDiffusive() {

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
                        new Color(200, 100, 100),
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
    public void shadowTest() {

        Scene scene = new Scene();

        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
        sphere.setShininess(20);
        sphere.setEmission(new Color(0, 0, 100));

        scene.addGeometry(sphere);

        Triangle triangle1 = new Triangle(new Point3D(3500, 3500, -2000),
                new Point3D(-3500, -3500, -1000),
                new Point3D(3500, -3500, -2000));

        Triangle triangle2 = new Triangle(new Point3D(3500, 3500, -2000),
                new Point3D(-3500, 3500, -1000),
                new Point3D(-3500, -3500, -1000));

        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);

        scene.addLight(
                new SpotLight(
                        new Color(255, 100, 100),
                        new Point3D(200, 200, -100),
                        new Vector(-2, -2, -3),
                        0,
                        0.000001,
                        0.0000005));


        ImageWriter imageWriter = new ImageWriter("Shadow test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }

    @Test
    public void recursiveTest() {
        Scene scene = new Scene();
        scene.setScreenDistance(300);

        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
        sphere.setShininess(100);
        sphere.setEmission(new Color(0, 0, 100));
        sphere.setKt(0.5);
        sphere.setKs(1);
        sphere.setKd(0.5);
        scene.addGeometry(sphere);

        Sphere sphere2 = new Sphere(250, new Point3D(0.0, 0.0, -1000));
        sphere2.setShininess(20);
        sphere2.setEmission(new Color(100, 20, 20));
        sphere2.setKt(0);
        scene.addGeometry(sphere2);


        scene.addLight(
                new SpotLight(
                        new Color(255, 100, 100), new Point3D(200, -200, -150),
                new Vector(-2, 2, -3), 0.1, 0.00001, 0.000005));
        /*
        scene.addLight(
                new PointLight(
                    new Color(255,100,100),
                        new Point3D(-200, -200, -150),
                        0.1, 0.00001, 0.000005));
                        */

        ImageWriter imageWriter = new ImageWriter("Recursive Test 1", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }


    @Test
    public void recursiveTest2() {

        Scene scene = new Scene();
        scene.setScreenDistance(300);

        Sphere sphere = new Sphere(300, new Point3D(-550, -500, -1000));
        sphere.setShininess(20);
        sphere.setEmission(new Color(0, 0, 100));
        sphere.setKt(0.5);
        scene.addGeometry(sphere);


        Sphere sphere2 = new Sphere(150, new Point3D(-550, -500, -1000));
        sphere2.setShininess(20);
        sphere2.setEmission(new Color(100, 20, 20));
        sphere2.setKt(0);
        scene.addGeometry(sphere2);


        Triangle triangle = new Triangle(new Point3D(1500, -1500, -1500),
                new Point3D(-1500, 1500, -1500),
                new Point3D(200, 200, -375));

        Triangle triangle2 = new Triangle(new Point3D(1500, -1500, -1500),
                new Point3D(-1500, 1500, -1500),
                new Point3D(-1500, -1500, -1500));

        triangle.setEmission(new Color(20, 20, 20));
        triangle2.setEmission(new Color(20, 20, 20));
        triangle.setKr(1);
        triangle.setKd(0);
        triangle.setKs(0);
        triangle2.setKr(0.5);
        triangle2.setKd(0);
        triangle2.setKs(0);

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);


        scene.addLight(
                new SpotLight(
                        new Color(255, 100, 100),
                        new Point3D(200, 200, -150),
                        new Vector(-2, -2, -3),
                        0, 0.00001, 0.000005));

/*
        scene.addLight(
                new PointLight(
                    new Color(255,100,100),
                        new Point3D(200, 200, -150),
                        0.1, 0.00001, 0.000005));*/


        ImageWriter imageWriter = new ImageWriter("Recursive Test 2", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }

    @Test
    public void recursiveTest3() {

        Scene scene = new Scene();
        scene.setScreenDistance(300);

        Sphere sphere = new Sphere(300, new Point3D(0, 0, -1000));
        sphere.setShininess(20);
        sphere.setEmission(new Color(0, 0, 100));
        sphere.setKt(0.5);
        scene.addGeometry(sphere);

        Sphere sphere2 = new Sphere(150, new Point3D(0, 0, -1000));
        sphere2.setShininess(20);
        sphere2.setEmission(new Color(100, 20, 20));
        sphere2.setKt(0);
        scene.addGeometry(sphere2);

        Triangle triangle = new Triangle(new Point3D(2000, -1000, -1500),
                new Point3D(-1000, 2000, -1500),
                new Point3D(700, 700, -375));

        Triangle triangle2 = new Triangle(new Point3D(2000, -1000, -1500),
                new Point3D(-1000, 2000, -1500),
                new Point3D(-1000, -1000, -1500));

        triangle.setEmission(new Color(20, 20, 20));
        triangle2.setEmission(new Color(20, 20, 20));
        triangle.setKr(1);
        triangle2.setKr(0.5);
        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -150),
                new Vector(-2, -2, -3), 0, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("Recursive Test 3", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
}





