public class Coordinate implements Comparable<Coordinate>
{
    private double _coordinate;

    // ***************** Constructors ********************** //
    public Coordinate() {
        _coordinate = 0;
    }

    public Coordinate(double coordinate) {
        _coordinate = coordinate;
    }

    public Coordinate(Coordinate coordinate) {
        _coordinate = coordinate._coordinate;
    }

    // ***************** Getters/Setters ********************** //
    public double getCoordinate() {
        return _coordinate;
    }

    public void setCoordinate(double coordinate) {
        _coordinate = coordinate;
    }

    // ***************** Administration ******************** //
    public int compareTo(Coordinate coordinate)
    {
        return Double.compare(_coordinate,coordinate._coordinate);
    }

    // ***************** Operations ******************** //
    public void add(Coordinate coordinate)
    {
        _coordinate+= coordinate._coordinate;
    }


    public void subtract (Coordinate coordinate)
    {
        _coordinate-=coordinate._coordinate;
    }
}
