package Application;

import elements.DirectionalLight;
import elements.LightSource;
import elements.PointLight;
import elements.SpotLight;
import geometries.Geometry;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import java.awt.*;
import java.util.Scanner;

public class UserSelect {

    public static int inputInt(){
        return new Scanner(System.in).nextInt();
    }


    public static String inputFile(){
        System.out.print("Please enter your file's name: ");
        return new Scanner(System.in).nextLine();
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

    private static int inputRecursionLevel(){
        System.out.print("Please enter the recursion level: ");
        return inputInt();
    }


    private static LightSource createLight(){
        System.out.println("Please input:\n" +
                "1) Directional light\n" +
                "2) Spot Light\n" +
                "3) PointLight");
        int lightChoice = inputInt();

        Color color = inputColor();
        int angle = inputAngle();

        Point3D position = setPosition(angle);
        Vector direction = setDirection(angle);


        switch(lightChoice){
            case 1: return new DirectionalLight(color, direction);
            case 2: return new SpotLight(color,position,direction,0.1, 0.00001, 0.0000005);
            case 3: return new PointLight(color,position,0.1, 0.00001, 0.0000005);
        }
        return new SpotLight(color,position,direction,0.1, 0.00001, 0.0000005);

    }

    private static double degToRad(int angle){
        return angle*Math.PI/180;
    }

    private static Point3D setPosition(int angle) {

        double t = degToRad(angle);
        double y = 200;
        double x = 2000 * Math.cos(t) - 150 * Math.sin(t);
        double z = -150 * Math.cos(t) - 2000 * Math.sin(t);

        return new Point3D(200, 200, -150);
    }

    private static Vector setDirection(int angle){
        Point3D start = setPosition(angle);

        double t = degToRad(angle);
        double y = -200;
        double x = 200;
        double z = -150;
        return new Vector(-2,-2,-3);
    }

    public static void recursiveTest(String fileName){


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

        ImageWriter imageWriter = new ImageWriter(fileName, 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.setRecursionLevel(inputRecursionLevel());
        System.out.println("Rendering...");
        render.renderImage();
        render.writeToImage("/results/UIResults/RecursiveTests/");
        System.out.println("Rendering successful\n" +
                "Your file is in /results/UIResults/RecursiveTests");
    }


    public static void shadowTest(String fileName){

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

        scene.addLight(createLight());


        ImageWriter imageWriter = new ImageWriter(fileName, 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);


        System.out.println("Rendering...");
        render.renderImage();
        render.writeToImage("/results/UIResults/ShadowTests/");
        System.out.println("Rendering successful\n" +
                "Your file is in /results/UIResults/ShadowTests");
    }



    private static Point3D P(int x, int y)
    {
        return new Point3D(y,x,-50);
    }

    public static void horseTest(String fileName){
        Scene scene = new Scene();
        scene.setScreenDistance(50);

        Geometry[] shapes = {
                new Triangle(P(-330, -196),P(-330, -216),P(-310, -216)),
                new Triangle(P(-330, -196),P(-310, -216),P(-290, -206)),
                new Triangle(P(-310, -216),P(-290, -206),P(-280, -216)),

                new Triangle(P(-290, -206),P(-280, -216),P(-270, -202)),
                new Triangle(P(-280, -216),P(-270, -202),P(-268, -214)),
                new Triangle(P(-270, -202),P(-268, -214),P(-246, -210)),
                new Triangle(P(-270, -202),P(-250, -198),P(-246, -210)),
                new Triangle(P(-208, -192),P(-250, -198),P(-246, -210)),
                new Triangle(P(-208, -192),P(-210, -206),P(-246, -210)),
                new Triangle(P(-208, -192),P(-210, -206),P(-194, -208)),
                new Triangle(P(-208, -192),P(-176, -188),P(-194, -208)),
                new Triangle(P(-176, -188),P(-164, -212),P(-194, -208)),
                new Triangle(P(-176, -188),P(-164, -212),P(-142, -180)),
                new Triangle(P(-120, -152),P(-164, -212),P(-132, -192)),
                new Triangle(P(-140, -210),P(-160, -210),P(-132, -192)),
                new Triangle(P(-140, -210),P(-100, -204),P(-132, -192)),
                new Triangle(P(-120, -152),P(-100, -204),P(-132, -192)),
                new Triangle(P(-128, -128),P(-100, -204),P(-82, -156)),
                new Triangle(P(-128, -128),P(-144, -140),P(-120, -152)),
                new Triangle(P(-150, -162),P(-144, -140),P(-120, -152)),
                new Triangle(P(-150, -162),P(-144, -140),P(-178, -164)),
                new Triangle(P(-178, -146),P(-144, -140),P(-178, -164)),
                new Triangle(P(-178, -146),P(-194, -162),P(-178, -164)),
                new Triangle(P(-178, -146),P(-194, -162),P(-202, -148)),
                new Triangle(P(-216, -160),P(-194, -162),P(-202, -148)),
                new Triangle(P(-216, -160),P(-234, -148),P(-202, -148)),
                new Triangle(P(-216, -160),P(-234, -148),P(-250, -158)),
                new Triangle(P(-266, -146),P(-234, -148),P(-250, -158)),
                new Triangle(P(-266, -146),P(-290, -158),P(-250, -158)),
                new Triangle(P(-266, -146),P(-290, -158),P(-292, -144)),
                new Triangle(P(-314, -160),P(-290, -158),P(-292, -144)),
                new Triangle(P(-314, -160),P(-320, -140),P(-292, -144)),
                new Triangle(P(-314, -160),P(-320, -140),P(-322, -158)),
                new Triangle(P(-330, -136),P(-320, -140),P(-322, -158)),
                new Triangle(P(-100, -202),P(-84, -156),P(-65, -172)),
                new Triangle(P(-100, -202),P(-42, -198),P(-65, -172)),
                new Triangle(P(-85, -156),P(-42, -198),P(0, -136)),
                new Triangle(P(-85, -156),P(-50, -92),P(0, -136)),
                new Triangle(P(-85, -156),P(-50, -92),P(-128, -128)),
                new Triangle(P(-96, -92),P(-50, -92),P(-128, -128)),
                new Triangle(P(-42, -198),P(0, -136),P(32, -166)),
                new Triangle(P(-42, -198),P(18, -182),P(32, -166)),
                new Triangle(P(-96, -92),P(-50, -56),P(-16, -92)),
                new Triangle(P(32, -166),P(-50, -92),P(-16, -92)),
                new Triangle(P(32, -166),P(18, -182),P(56, -176)),
                new Triangle(P(44, -198),P(18, -182),P(56, -176)),
                new Triangle(P(44, -198),P(84, -182),P(56, -176)),
                new Triangle(P(32, -166),P(-16, -92),P(60, -92)),
                new Triangle(P(46, -60),P(-16, -92),P(60, -92)),
                new Triangle(P(46, -60),P(-16, -92),P(-26, -38)),
                new Triangle(P(-50, -56),P(-16, -92),P(-26, -38)),
                new Triangle(P(46, -60),P(16, -6),P(-26, -38)),
                new Triangle(P(54, -26),P(16, -6),P(60, -92)),
                new Triangle(P(54, -26),P(16, -6),P(52, 20)),
                new Triangle(P(-50, -56),P(-14, 52),P(52, 20)),
                new Triangle(P(-50, -56),P(-14, 52),P(-76, 8)),
                new Triangle(P(52, 20),P(-14, 52),P(0, 58)),
                new Triangle(P(52, 20),P(36, 78),P(0, 58)),
                new Triangle(P(52, 20),P(36, 78),P(84, 54)),
                new Triangle(P(-76, 8),P(-14, 52),P(-82, 48)),
                new Triangle(P(-42, 120),P(-14, 52),P(-82, 48)),
                new Triangle(P(-42, 120),P(-14, 52),P(-18, 132)),
                new Triangle(P(36, 78),P(-14, 52),P(-18, 132)),
                new Triangle(P(-82, 48),P(-42, 120),P(-102, 106)),
                new Triangle(P(-82, 48),P(-118, 64),P(-102, 106)),
                new Triangle(P(-140, 74),P(-118, 64),P(-102, 106)),
                new Triangle(P(-140, 74),P(-140, 108),P(-102, 106)),
                new Triangle(P(-140, 74),P(-140, 108),P(-158, 80)),
                new Triangle(P(-140, 108),P(-160, 108),P(-158, 80)),
                new Triangle(P(-210, 96),P(-160, 108),P(-158, 80)),
                new Triangle(P(-210, 96),P(-160, 108),P(-226, 114)),
                new Triangle(P(-210, 96),P(-254, 108),P(-226, 114)),
                new Triangle(P(-276, 124),P(-254, 108),P(-226, 114)),
                new Triangle(P(-276, 124),P(-254, 108),P(-276, 110)),
                new Triangle(P(-276, 124),P(-316, 112),P(-276, 110)),
                new Triangle(P(-276, 124),P(-316, 112),P(-300, 130)),
                new Triangle(P(-320, 130),P(-316, 112),P(-300, 130)),
                new Triangle(P(-102, 106),P(-42, 120),P(-120, 128)),
                new Triangle(P(-110, 158),P(-42, 120),P(-120, 128)),
                new Triangle(P(-110, 158),P(-136, 152),P(-120, 128)),
                new Triangle(P(-110, 158),P(-136, 152),P(-136, 170)),
                new Triangle(P(-158, 160),P(-136, 152),P(-136, 170)),
                new Triangle(P(-158, 160),P(-170, 174),P(-136, 170)),
                new Triangle(P(-158, 160),P(-170, 174),P(-194, 162)),
                new Triangle(P(-194, 174),P(-170, 174),P(-194, 162)),
                new Triangle(P(-194, 174),P(-216, 162),P(-194, 162)),
                new Triangle(P(-194, 174),P(-216, 162),P(-216, 172)),
                new Triangle(P(-230, 160),P(-216, 162),P(-216, 172)),
                new Triangle(P(-230, 160),P(-234, 170),P(-216, 172)),
                new Triangle(P(-230, 160),P(-234, 170),P(-254, 154)),
                new Triangle(P(-254, 154),P(-234, 170),P(-250, 166)),
                new Triangle(P(-254, 154),P(-276, 156),P(-250, 166)),
                new Triangle(P(-254, 154),P(-276, 156),P(-276, 140)),
                new Triangle(P(-18, 132),P(36, 78),P(32, 164)),
                new Triangle(P(84, 134),P(36, 78),P(32, 164)),
                new Triangle(P(84, 134),P(32, 164),P(82, 164)),
                new Triangle(P(84, 134),P(36, 78),P(80, 54)),
                new Triangle(P(82, 164),P(116, 152),P(80, 54)),
                new Triangle(P(80, 54),P(116, 152),P(140, 94)),
                new Triangle(P(178, 112),P(116, 152),P(140, 94)),
                new Triangle(P(140, 48),P(80, 54),P(140, 94)),
                new Triangle(P(172, 72),P(178, 112),P(140, 94)),
                new Triangle(P(172, 72),P(140, 94),P(140, 48)),
                new Triangle(P(172, 72),P(140, 48),P(168, 46)),
                new Triangle(P(178, 112),P(196, 72),P(168, 46)),
                new Triangle(P(200, 52),P(196, 72),P(168, 46)),
                new Triangle(P(178, 112),P(196, 72),P(216, 122)),
                new Triangle(P(228, 98),P(196, 72),P(216, 122)),
                new Triangle(P(228, 98),P(196, 72),P(222, 62)),
                new Triangle(P(200, 52),P(196, 72),P(222, 62)),
                new Triangle(P(228, 98),P(234, 70),P(222, 62)),
                new Triangle(P(178, 112),P(184, 132),P(216, 122)),
                new Triangle(P(184, 132),P(204, 152),P(216, 122)),
                new Triangle(P(184, 132),P(204, 152),P(178, 156)),
                new Triangle(P(180, 156),P(204, 152),P(212, 168)),
                new Triangle(P(178, 156),P(200, 164),P(180, 174)),
                new Triangle(P(208, 188),P(200, 164),P(180, 174)),
                new Triangle(P(208, 188),P(190, 204),P(180, 174)),
                new Triangle(P(208, 188),P(200, 164),P(218, 172)),
                new Triangle(P(204, 152),P(218, 172),P(226, 160)),
                new Triangle(P(204, 152),P(226, 138),P(226, 160)),
                new Triangle(P(204, 152),P(226, 138),P(216, 122)),
                new Triangle(P(236, 152),P(226, 138),P(226, 160)),
                new Triangle(P(236, 152),P(216, 122),P(242, 118)),
                new Triangle(P(228, 98),P(216, 122),P(242, 118)),
                new Triangle(P(236, 152),P(254, 134),P(242, 118)),
                new Triangle(P(228, 98),P(254, 134),P(256, 88)),
                new Triangle(P(228, 98),P(234, 70),P(256, 88)),
                new Triangle(P(228, 98),P(260, 110),P(256, 88)),
                new Triangle(P(250, 108),P(260, 110),P(254, 134)),
                new Triangle(P(222, 62),P(256, 64),P(256, 88)),
                new Triangle(P(222, 62),P(256, 64),P(232, 46)),
                new Triangle(P(260, 48),P(256, 64),P(232, 46)),
                new Triangle(P(260, 48),P(244, 36),P(232, 46)),
                new Triangle(P(260, 48),P(260, 28),P(244, 36)),






        };

        Color[] colors = {
                new Color(255,128,128),
                new Color(255,193,37),
                new Color(154,205,50),
                new Color(107,142,35),
                new Color(139,69,19),
                new Color(255,193,37),
                new Color(165,42,42),
                new Color(255,0,0),
                new Color(0,100,0),
                new Color(143,188,143),
                new Color(0,100,0),
                new Color(32,178,170),
                new Color(205,198,115),
                new Color(255,128,128),
                new Color(32,178,170),
                new Color(139,99,108),
                new Color(85,26,139),
                new Color(255,193,37),
                new Color(85,26,139),
                new Color(79,148,205),
                new Color(255,0,0),
                new Color(165,42,42),
                new Color(255,128,128),
                new Color(85,26,139),
                new Color(79,148,205),
                new Color(0,100,0),
                new Color(32,178,170),
                new Color(143,188,143),
                new Color(255,0,0),
                new Color(79,148,205),
                new Color(54,100,139),
                new Color(0,100,0),
                new Color(143,188,143),
                new Color(0,100,0),
                new Color(255,193,37),
                //   new Color(238,180,34),
                new Color(143,188,143),
                new Color(122,139,139),
                new Color(143,188,143),
                new Color(0,100,0),
                new Color(85,26,139),
                new Color(238,220,130),
                new Color(238,180,34),
                new Color(255,128,128),
                new Color(238,220,130),
                new Color(238,220,130),
                new Color(139,139,0),
                new Color(205,205,0),
                new Color(238,220,130),
                new Color(205,205,0),
                new Color(139,139,0),
                new Color(255,128,128),
                new Color(0,100,0),
                new Color(143,188,143),
                new Color(238,220,130),
                new Color(238,180,34),
                new Color(85,26,139),
                new Color(139,99,170),
                new Color(255,128,128),
                new Color(238,220,130),
                new Color(79,148,205),
                new Color(54,100,139),
                new Color(107,142,35),
                new Color(139,139,0),
                new Color(255,193,37),
                new Color(139,69,19),
                new Color(54,100,139),
                new Color(107,142,35),
                new Color(139,139,0),
                new Color(0,100,0),
                new Color(143,188,143),
                new Color(79,148,205),
                new Color(54,100,139),
                new Color(255,193,37),
                new Color(139,69,19),
                new Color(85,26,139),
                new Color(107,142,35),
                new Color(255,128,128),
                new Color(85,26,139),
                new Color(139,99,170),
                new Color(143,188,143),
                new Color(0,100,0),
                new Color(79,148,205),
                new Color(54,100,139),
                new Color(255,0,0),
                new Color(165,42,42),
                new Color(85,26,139),
                new Color(139,99,170),
                new Color(143,188,143),
                new Color(0,100,0),
                new Color(79,148,205),
                new Color(54,100,139),
                new Color(255,128,128),
                new Color(85,26,139),
                new Color(0,100,0),
                new Color(255,0,0),
                new Color(165,42,42),
                new Color(255,193,37),
                new Color(255,128,128),
                new Color(122,139,139),
                new Color(0,100,0),
                new Color(143,188,143),
                new Color(79,148,205),
                new Color(107,142,35),
                new Color(139,139,0),
                new Color(238,180,34),
                new Color(238,220,130),
                new Color(85,26,139),
                new Color(139,99,170),
                new Color(107,142,35),
                new Color(139,139,0),
                new Color(0,100,0),
                new Color(238,220,130),
                new Color(238,180,34),
                new Color(255,0,0),
                new Color(165,42,42),
                new Color(85,26,139),
                new Color(107,142,35),
                new Color(85,26,139),
                new Color(107,142,35),
                new Color(0,100,0),
                new Color(54,100,139),
                new Color(79,148,205),
                new Color(107,142,35),
                new Color(165,42,42),
                new Color(255,0,0),
                new Color(139,99,170),
                new Color(54,100,139),
                new Color(107,142,35),
                new Color(54,100,139),
                new Color(255,128,128),
                new Color(238,220,130),
                new Color(107,142,35),
                new Color(0,100,0),
                new Color(85,26,139),
                new Color(139,99,170),

        };

        for (int i = 0; i < shapes.length; i++)
        {
            shapes[i].setEmission(colors[i]);
            scene.addGeometry(shapes[i]);
        }

        ImageWriter imageWriter =
                new ImageWriter(fileName,
                        900, 900,
                        900, 900);

        Render render = new Render(imageWriter, scene);



        System.out.println("Rendering...");
        render.renderImage();
        render.writeToImage("/results/UIResults/AnimalTests/");
        System.out.println("Rendering successful\n" +
                "Your file is in /results/UIResults/AnimalTests");
    }

    private static Triangle invertYX(Geometry t){
        Triangle n = (Triangle)t;
        return new Triangle(n.getP2(),n.getP1(),n.getP3());
    }
}
