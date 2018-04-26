package elements;

import java.awt.*;
import primitives.*;

public interface LightSource {

    Color getIntensity(Point3D point);
    Vector getL(Point3D point); // light to point vector
}
