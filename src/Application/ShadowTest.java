package Application;

import elements.*;
import geometries.Quadrangle;
import geometries.Sphere;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

import java.awt.*;
import java.util.Scanner;

import static java.lang.Math.*;

public class ShadowTest {

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
        double y = 500;
        double x = 600*sin(t);
        double z = 600*cos(t)-600;
        return new Point3D(x,y,z);
    }

    private static Vector setDirection(int angle){
        Point3D center = new Point3D(0,-200,-500);
        Point3D pos = setPosition(angle);
        return new Vector(pos,center);
    }

    public static LightSource createLight() {
        System.out.println("Please input:\n" +
                "1) Directional light\n" +
                "2) Spot Light\n" +
                "3) PointLight");
        int lightChoice = inputInt();

        Color color = inputColor();
        int angle = inputAngle();

        Point3D position = setPosition(angle);
        Vector direction = setDirection(angle);


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


    public static Scene createScene() {
        Scene scene = new Scene();

        /*
        scene.setCamera(
                new Camera(
                    new Point3D(0,300,0),
                    new Vector(0,1,-1),
                    new Vector(1,0,0)
        ));
        */

        double y = -600;
        double size = 2000;
        double z = -2000;

        //  add the support
        Quadrangle q = new Quadrangle(
                new Point3D(-size,y,-100),
                new Point3D(-size,y,z),
                new Point3D(size,y,z),
                new Point3D(size,y,-100)

        );
        q.setShininess(20);
        q.setKd(0.3);
        q.setKs(0.3);
        scene.addGeometry(q);

        //  add the sphere
        Point3D center = new Point3D(0,-200,-500);
        Sphere sphere = new Sphere(300, new Point3D(center));
        sphere.setShininess(30);
        sphere.setEmission(new Color(0, 0, 100));
        scene.addGeometry(sphere);

        scene.addLight(createLight());


        return scene;
    }
}
