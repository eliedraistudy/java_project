package elements;

import geometries.*;
import primitives.*;
import org.junit.Test;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import java.awt.*;

public class LightingTest
{
    @Test
    public void emissionTest() {


        Scene scene = new Scene();
        scene.setScreenDistance(100);

        Sphere sphere = new Sphere(50, new Point3D(0.0, 0.0, -100));
        Triangle triangle1 = new Triangle(
                new Point3D(150, 0, -100),
                new Point3D(0, 150, -100),
                new Point3D(150, 150, -100));

        Triangle triangle2 = new Triangle(new Point3D(150, 0, -100),
                new Point3D(0, -150, -100),
                new Point3D(150, -150, -100));

        Triangle triangle3 = new Triangle(
                new Point3D(-150, 0, -100),
                new Point3D(0, 150, -100),
                new Point3D(-150, 150, -100));

        Triangle triangle4 = new Triangle(
                new Point3D(-150, 0, -100),
                new Point3D(0, -150, -100),
                new Point3D(-150, -150, -100));

        sphere.setEmission(new Color(255, 54, 37));
        triangle1.setEmission(Color.BLUE);
        triangle2.setEmission(Color.BLUE);
        triangle3.setEmission(Color.BLUE);
        triangle4.setEmission(Color.BLUE);

        scene.addGeometry(sphere);
        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);

        ImageWriter imageWriter =
                new ImageWriter(
                        "Emission test2 - blue",
                        501, 501,
                        501, 501);

        Render render = new Render(imageWriter, scene);

        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.LIGHT_GRAY, 1.0));
        render.renderImage();
        render.printGrid(50, Color.WHITE);
        render.writeToImage("/results/RenderResults/");
    }
}


