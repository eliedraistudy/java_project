public class Point2D
{
    
    protected Coordinate _x;
    protected Coordinate _y;

    // ***************** Constructors ********************** //
    public Point2D() {
        _x = new Coordinate(0);
        _y = new Coordinate(0);
    }

    public Point2D(Coordinate x, Coordinate y) {
        _x.setCoordinate(x.getCoordinate());
        _y.setCoordinate(y.getCoordinate());
    }

    public Point2D(Point2D point2D) {
        _x = new Coordinate(point2D._x.getCoordinate());
        _y = new Coordinate(point2D._y.getCoordinate());
    }

    // ***************** Getters/Setters ********************** //
    public Coordinate getX() {
        return _x;

    }

    public Coordinate getY() {
        return _y;
    }

    public void setX(Coordinate _x) {
        this._x = _x;
    }

    public void setY(Coordinate _y) {
        this._y = _y;
    }

    // ***************** Administration ******************** //
    public int compareTo(Point2D point2D) // return 0 if equal and 1 id not equal
    {
        //if (this._x.getCoordinate() == point2D._x.getCoordinate() && this._y.getCoordinate() == point2D._y.getCoordinate())

        double SizeOfInputVector = Math.sqrt(point2D._x.getCoordinate()*point2D._x.getCoordinate() + point2D._y.getCoordinate()*point2D._y.getCoordinate());
        double SizeOfThisVector = Math.sqrt(_x.getCoordinate()*_x.getCoordinate() + _y.getCoordinate()*_y.getCoordinate());

        return Double.compare(SizeOfThisVector,SizeOfInputVector);
    }

}
