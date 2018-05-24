package elements;

import primitives.*;

public class Camera {

    // ***************** Fields ********************** //

    //Eye point of the camera
    private Point3D _P0;
    private Vector _vUp;
    private Vector _vTo;

    //Should be calculated as the cross product if vUp and vTo
    private Vector _vRight;

    // ***************** Constructors ********************** //
    public Camera(){
        _P0 = new Point3D(0,0,0);
        _vUp = new Vector(0.0, 1.0, 0.0);
        _vTo = new Vector(0.0, 0.0, -1.0);
        _vRight = _vUp.crossProduct(_vTo);
    }

    public Camera(Camera c){
        _P0 = new Point3D(c._P0);
        _vUp = new Vector(c._vUp).normalVector();
        _vTo = new Vector(c._vTo).normalVector();
        _vRight = _vTo.crossProduct(_vUp).normalVector();
    }

    public Camera(Point3D p, Vector vup, Vector vto){
        _P0 = new Point3D(p);
        _vUp = new Vector(vup).normalVector();
        _vTo = new Vector(vto).normalVector();
        _vRight = _vTo.crossProduct(_vUp).normalVector();
    }

    // ***************** Getters/Setters ********************** //

    public Vector get_vRight() {
        return _vRight;
    }

    public Point3D get_P0() {
        return _P0;
    }

    public Vector get_vTo() {
        return _vTo;
    }

    public Vector get_vUp() {
        return _vUp;
    }

    public void set_P0(Point3D _P0) {
        this._P0 = _P0;
    }

    public void set_vTo(Vector _vTo) {
        this._vTo = _vTo;
    }

    public void set_vUp(Vector _vUp) {
        this._vUp = _vUp;
    }

    // ***************** Methods ********************** //

    @Override
    public String toString() {
        return "Vto: " + _vTo + "\n" + "Vup: " + _vUp + "\n" + "Vright:" + _vRight + ".";
    }



    /*************************************************
     * --------
     * FUNCTION
     * --------
     * construct ray through pixel
     *
     * ------------
     * PARAMETER(S)
     * ------------
     * int - Nx, the # of pixels in width
     * int - Ny, the # of pixels in height
     * double - x, the x coordinate of the pixel
     * double - y, the y coordinate of the pixel
     * double - screenDist, the distance from the camera to the screen
     * double - screenWidth, the width size of the screen
     * double - screenHeight, the height size of the screen
     *
     * ------------
     * RETURN VALUE
     * ------------
     * The ray from the camera to the pixel on the screen
     *
     * -------
     * MEANING
     * -------
     * Allow to get the ray from the camera to a specific point on the screen
     * 1)Pc = P0 + d.vTo
     * 2)Py = Pc - ((y - Ny/2)*ry + ry/2)
     * 3)Px = Pc + ((x - Nx/2)*rx + rx/2)
     * 4) P = (Px,Pc)
     *
     * --------
     * SEE ALSO
     * --------
     *************************************************/
    public Ray constructRayThroughPixel (int Nx,   int Ny,
                                         double x, double y,
                                         double screenDist,
                                         double screenWidth,
                                         double screenHeight){


        //  Pc = P0 + d*vTo
        Point3D Pc = new Point3D(_P0);
        Vector v = new Vector(_vTo).normalVector();
        v.scale(screenDist);
        Pc.add(v);

        //  ratio screen/#pixels
        double rx = (int)screenWidth/Nx;
        double ry = (int)screenHeight/Ny;

        // Px + ((x - Nx/2)*rx + rx/2)
        double sx = (rx * (x - (double)Nx/2)) + rx/2;
        Pc.add(_vRight.normalVector().scale_return(sx));

        // Py = Pc - ((y - Ny/2)*ry + ry/2)
        double sy = (ry * (y - (double)Ny/2)) + ry/2;
        Pc.subtract(_vUp.normalVector().scale_return(sy));


        return new Ray(Pc, new Vector(_P0,Pc).normalVector());
    }
}
