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
    public void emmissionTest() {


        Scene scene = new Scene();
        scene.setAmbientLight(new AmbientLight(Color.BLACK));
        scene.setScreenDistance(50);

        Sphere sphere = new Sphere(100, new Point3D(0.0, 0.0, -50));
        Triangle triangle1 = new Triangle(new Point3D(150, 0, -50),
                new Point3D(0, 150, -50),
                new Point3D(150, 150, -50));

        Triangle triangle2 = new Triangle(new Point3D(150, 0, -50),
                new Point3D(0, -150, -50),
                new Point3D(150, -150, -50));

        Triangle triangle3 = new Triangle(new Point3D(-150, 0, -50),
                new Point3D(0, 150, -50),
                new Point3D(-150, 150, -50));

        Triangle triangle4 = new Triangle(new Point3D(-150, 0, -50),
                new Point3D(0, -150, -50),
                new Point3D(-150, -150, -50));

        sphere.setEmmission(new Color(255, 54, 37));
        triangle1.setEmmission(Color.BLUE);
        triangle2.setEmmission(Color.YELLOW);
        triangle3.setEmmission(Color.GREEN);
        triangle4.setEmmission(Color.PINK);

        scene.addGeometry(sphere);
        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);

        ImageWriter imageWriter =
                new ImageWriter(
                        "Emmission test",
                        500, 500,
                        500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.printGrid(50);
        render.writeToImage("/results/RenderResults/");
    }
}


