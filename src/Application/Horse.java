package Application;

import geometries.Geometry;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.awt.*;

public class Horse extends Geometry {

    private static Color createRandomColor(){

        Random rand = new Random();
        /*
        return new Color(
                rand.nextInt(256),
                rand.nextInt(256),
                rand.nextInt(256)
        );*/
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        return new Color(r,g,b);
    }

    private static Point3D P(int x, int y)
    {
        return new Point3D(y,x,-50);
    }

    private Geometry[] shapes = {
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


    public Horse(){
        for (int i = 0; i < shapes.length; i++) {
            shapes[i].setEmission(createRandomColor());
        }
    }


    @Override
    public List<Point3D> FindIntersections(Ray ray) {
        List<Point3D> ret = new ArrayList<>();
        List<Point3D> help;
        for (int i = 0; i < shapes.length; i++) {
            help = shapes[i].FindIntersections(ray);
            ret.addAll(help);
        }
        return ret;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return shapes[0].getNormal(point);
    }
}
