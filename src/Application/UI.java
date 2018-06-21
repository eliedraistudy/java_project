package Application;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometry;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import java.io.Console;
import java.util.Scanner;



public class UI {

    public static void main(String[] args){
        welcome();
        int choice = -1;
        do {
            choice = input();
            switch(choice){
                case 1: spherePicture(); break;
                case 2: animalPicture(); break;
                case 0: quit(); break;
                default: System.out.println("Error in input");
            }
        } while(choice != 0);

    }

    public static void quit(){
        System.out.println("\n\n===========================\n" +
                "Thank you for using our app\n" +
                "===========================\n");
    }

    public static void welcome(){
        System.out.println(
                "======================================\n" +
                        "Welcome to Picture Builder Application\n" +
                        "======================================\n");
    }

    public static int input(){
        int in = -1;
        Scanner inp = new Scanner(System.in);

        System.out.println("\n\nPlease select an action to perform: ");
        System.out.println("\t1)\tSphere Picture (press 1)");
        System.out.println("\t2)\tAnimal Picture (press 2)");
        System.out.println("To quit press 0\n\n");

        while(in != 0 && in != 1 && in !=2) {
            System.out.print("Your choice: ");
            String s = inp.nextLine();
            in = Integer.parseInt(s);
        }

        return in;
    }


    public static void spherePicture(){
        Scene scene = new Scene();

        System.out.println("Would you like to implement your scene ? Y/N");
        String s = new Scanner(System.in).nextLine();
        if(s == "Y" || s == "y")
            scene = implementScene(scene);

        System.out.println(scene.getCamera());
        System.out.println(scene.getScreenDistance());

        //  add the sphere
        Sphere sphere = inputSphere();
        sphere = setSphere(sphere);
        System.out.println(sphere);
        scene.addGeometry(sphere);

        //  set the lights


        System.out.println("Please enter the file's name to write there the picture");
        String name = new Scanner(System.in).nextLine();

        ImageWriter image = new ImageWriter(name,501,501,501,501);
        Render render = new Render(image,scene);

        //  render it
        render.renderImage();
        //render.writeToImage("/results/UIResults/");
        System.out.println("Picture correctly encoded...\n\n");
    }



    public static Sphere setSphere(Sphere sphere){
        /*
        System.out.println("\nPlease modify the sphere, press 0 to quit\n");
        System.out.println("\t1) Set emission");
        System.out.println("\t2) Add a geometry");
        System.out.println("\t3) Add a light source");
        System.out.println("\t4) Set the ambient light");
        System.out.println("\t5) Set screen distance");

        int in;
        Scanner scan = new Scanner(System.in);
        in = Integer.parseInt(scan.nextLine());
        return in;*/

        return sphere;
    }

    public static void animalPicture(){

    }

    public static Point3D inputPoint3D(){
        Scanner in = new Scanner(System.in);
        System.out.print("X = ");
        String x = in.nextLine();
        System.out.print("Y = ");
        String y = in.nextLine();
        System.out.print("Z = ");
        String z = in.nextLine();

        double xx = Double.parseDouble(x);
        double yy = Double.parseDouble(y);
        double zz = Double.parseDouble(z);
        return new Point3D(xx,yy,zz);
    }


    public static Camera inputCamera(){
        System.out.println("P0:");
        Point3D p0 = inputPoint3D();

        System.out.println("VUp:");
        Vector vUp = new Vector(inputPoint3D());

        System.out.println("VTo:");
        Vector vTo = new Vector(inputPoint3D());

        return new Camera(p0,vUp,vTo);
    }

    public static int inputSceneChoice(){
        System.out.println("\nPlease modify the scene, press 0 to quit\n");
        System.out.println("\t1) Set the camera");
        System.out.println("\t2) Add a geometry");
        System.out.println("\t3) Add a light source");
        System.out.println("\t4) Set the ambient light");
        System.out.println("\t5) Set screen distance");

        int in;
        Scanner scan = new Scanner(System.in);
        in = Integer.parseInt(scan.nextLine());
        return in;
    }

    public static Geometry inputGeometry(){
        System.out.println("Please select a geometry: ");
        System.out.println("\t1) Sphere");
        System.out.println("\t2) Triangle");

        int i = Integer.parseInt(new Scanner(System.in).nextLine());

        switch(i){
            case 1: return inputSphere();
            case 2: return inputTriangle();
            default: return null;
        }
    }

    public static Sphere inputSphere(){
        System.out.println("input sphere's center");
        Point3D center = inputPoint3D();

        System.out.println("input sphere's radius");
        double radius = inputDistance();

        return new Sphere(radius, center);
    }

    public static Triangle inputTriangle(){
        System.out.println("input p1");
        Point3D p1 = inputPoint3D();

        System.out.println("input p2");
        Point3D p2 = inputPoint3D();

        System.out.println("input p3");
        Point3D p3 = inputPoint3D();

        return new Triangle(p1,p2,p3);
    }

    public static LightSource inputLight(){
        return null;
    }

    public static AmbientLight inputAmbient(){
        return null;
    }

    public static double inputDistance(){
        return Double.parseDouble(new Scanner(System.in).nextLine());
    }

    public static Scene implementScene(Scene scene){


        int in = -1;
        do{
            in = inputSceneChoice();
            switch(in){
                case 1: scene.setCamera(inputCamera()); break;
                case 2: scene.addGeometry(inputGeometry()); break;
                case 3: scene.addLight(inputLight()); break;
                case 4: scene.setAmbientLight(inputAmbient()); break;
                case 5: scene.setScreenDistance(inputDistance()); break;
                case 0: break;
                default: System.out.println("Error in your input");
            }
        }while(in != 0);

        return scene;
    }


}
