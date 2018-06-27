package Application;

import elements.DirectionalLight;
import elements.LightSource;
import elements.PointLight;
import elements.SpotLight;
import geometries.Quadrangle;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

import java.awt.*;
import java.util.Scanner;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class RecursiveTest {

    private static int inputInt(){
        return new Scanner(System.in).nextInt();
    }

    private static int inputAngle(){
        System.out.print("Please enter an angle value (between 0 and 180): ");
        return inputInt();
    }

    private static Color inputColor(){
        System.out.println("Please enter RGB components for the color: ");

        System.out.print("R: ");
        int r = inputInt();

        System.out.print("G: ");
        int g = inputInt();

        System.out.print("B: ");
        int b = inputInt();

        return new Color(r,g,b);
    }

    private static double degToRad(int angle){
        return angle*Math.PI/180;
    }

    private static Point3D setPosition(int angle){
        double t = degToRad(angle);
        double y = 300;
        double x = 500*sin(t);
        double z = 500*cos(t)-500;
        return new Point3D(x,y,z);
    }

    private static Vector setDirection(int angle){
        Point3D center = new Point3D(-550, -500, -1000);
        Point3D pos = setPosition(angle);
        return new Vector(pos,center);
    }

    private static LightSource createLight(){
        System.out.println("Please input:\n" +
                "1) Directional light\n" +
                "2) Spot Light\n" +
                "3) PointLight");
        int lightChoice = inputInt();

        Color color = inputColor();
        int angle = inputAngle();

        Point3D position = new Point3D(200, 200, -150);
        Vector direction = new Vector(-2, -2, -3);


        switch (lightChoice) {
            case 1:
                return new DirectionalLight(color, direction);
            case 2:
                return new SpotLight(color, position, direction, 0, 0.00001, 0.000005);
            case 3:
                return new PointLight(color, position, 0, 0.000001, 0.000005);
        }
        return new SpotLight(color, position, direction, 0.1, 0.00001, 0.0000005);

    }

    public static Scene createScene(){


        //  create the scene
        //  with 2 spheres
        //  one light source
        Scene scene = new Scene();
        scene.setScreenDistance(300);

        //  add the sphere 1
        Sphere sphere = new Sphere(300, new Point3D(-550, -500, -1000));
        sphere.setShininess(20);
        sphere.setEmission(new Color(0, 0, 100));
        sphere.setKt(0.5);
        scene.addGeometry(sphere);

        //  add the sphere 2
        Sphere sphere2 = new Sphere(150, new Point3D(-550, -500, -1000));
        sphere2.setShininess(20);
        sphere2.setEmission(new Color(100, 20, 20));
        sphere2.setKt(0);
        scene.addGeometry(sphere2);


        //  add the mirror
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


        //  add the light
        scene.addLight(createLight());

        return scene;
    }
}
