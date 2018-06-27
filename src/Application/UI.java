package Application;

import elements.*;
import geometries.Geometry;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import java.awt.*;
import java.io.Console;
import java.util.Scanner;



public class UI {

    public static void main(String[] args){
        welcome();
        chooseTest();
        quit();
    }

    private static void chooseTest(){

        System.out.print("\nPlease choose a test:\n" +
                "1) Recursive Test\n" +
                "2) Shadow Test\n" +
                "3) Animal Test\n" +
                "Your choice: ");

        int choice = UserSelect.inputInt();
        String file = inputFile();


        switch(choice){
            case 1: UserSelect.recursiveTest(file); break;
            case 2: UserSelect.shadowTest(file); break;
            case 3: UserSelect.horseTest(file); break;
            default: break;
        }



    }

    public static String inputFile(){
        System.out.print("Please enter your file's name: ");
        return new Scanner(System.in).nextLine();
    }




    public static void quit(){
        System.out.println("\n\n===========================\n" +
                "Thank you for using our app\n" +
                "===========================\n\n");
    }

    public static void welcome(){
        System.out.println(
                "\n\n======================================\n" +
                        "Welcome to Picture Builder Application\n" +
                        "======================================\n");
    }



}
