package renderer;


import elements.AmbientLight;
import elements.Camera;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

import java.awt.*;

public class RendererTest {


    @Test
    public void TestRenderer()
    {
        Scene s = new Scene(
                new AmbientLight(Color.WHITE, 1),

                Color.BLACK,

                new Camera(
                        new Point3D(0.0, 0.0, 0.0),
                        new Vector(0.0, 1.0, 0.0),
                        new Vector(0.0, 0.0, -1.0)),

                10
        );

        Triangle t = new Triangle(new Point3D(0, 1, -12),
            new Point3D(1, -1, -12),
            new Point3D(-1, -1, -12));

        t.setEmmission(Color.RED);
        s.addGeometry(t);


        ImageWriter image = new ImageWriter(
                "test1", 500, 500, 1, 1
        );

        Render r = new Render(image, s);
        r.renderImage();
        r.writeToImage();
    }
}
