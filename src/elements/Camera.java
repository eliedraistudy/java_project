package elements;

import primitives.*;

public class Camera {

    // ***************** Fields ********************** //

    //Eye point of the camer
    private Point3D _P0;
    private Vector _vUp;
    private Vector _vTo;

    //Should be calculated as the cross product if vUp and vTo
    private Vector _vRight;

    // ***************** Constructors ********************** //
    public Camera(){ _vRight = _vUp.crossProduct(_vTo); }

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
        return "_P0: " + _P0.toString() +
                "\n_vUp: " + _vUp.toString() +
                "\n_vTo: " + _vTo.toString() +
                "\n_vRight: " + _vRight.toString();
    }

    public Ray constructRayThroughPixel (int Nx,   int Ny,
                                         double x, double y,
                                         double screenDist,
                                         double screenWidth,
                                         double screenHeight){
        Point3D Pc = new Point3D(_P0);
        Vector v = new Vector(_vTo);
        v.scale(screenDist);
        Pc.add(v);

        double rx = screenWidth/Nx;
        double ry = screenHeight/Ny;

        Vector helpRight = new Vector(_vRight);
        Vector helpUp = new Vector(_vUp);

        helpRight.scale((x-Nx/2)*rx+rx/2);
        helpUp.scale((x-Ny/2)*ry+ry/2);
        Point3D P = Pc;
        P.add(helpRight);
        P.subtract(helpUp);
        return new Ray(_P0, new Vector(_P0,P).normalVector());
    }
}
