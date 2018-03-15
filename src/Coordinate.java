public class Coordinate implements Comparable<Coordinate>
{
    /**
     * Represent the value of the coordinate
     */
    private double _coordinate;

    // ***************** Constructors ********************** //

    /**
     * Default constructor, initialize with 0
     */
    public Coordinate() {
        _coordinate = 0;
    }

    /**
     * Value constructor, initialize with the parameter coordinate
     * @param coordinate double which represent the value to assign to the object
     */
    public Coordinate(double coordinate) {
        _coordinate = coordinate;
    }

    /**
     * Copy constructor, initialize with the parameter coordinate
     * @param coordinate Coordinate which represent the value to assign to the object
     */
    public Coordinate(Coordinate coordinate) {
        _coordinate = coordinate._coordinate;
    }

    // ***************** Getters/Setters ********************** //

    /**
     * Getter to the value coordinate
     * @return the value of the field _coordinate
     */
    public double getCoordinate() {
        return _coordinate;
    }

    /**
     * Set the field coordinate
     * @param coordinate the value to assign to the coordinate
     */
    public void setCoordinate(double coordinate) {
        _coordinate = coordinate;
    }

    // ***************** Administration ******************** //

    /**
     * Compare 2 coordinates
     * @param coordinate the coordinate to compare to
     * @return 0 if equals, 1 if the object bigger and -1 if coordinate bigger
     */
    public int compareTo(Coordinate coordinate)
    {
        return Double.compare(_coordinate,coordinate._coordinate);
    }

    // ***************** Operations ******************** //

    /**
     * Add value to an existing coordinate
     * @param coordinate the value to add
     */
    public void add(Coordinate coordinate)
    {
        _coordinate+= coordinate._coordinate;
    }


    /**
     * Substract value to an existing coordinate
     * @param coordinate the value to substract
     */
    public void subtract (Coordinate coordinate)
    {
        _coordinate-=coordinate._coordinate;
    }
}

