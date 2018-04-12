package primitives;

public class Coordinate implements Comparable<Coordinate>
{
    /**
     * Represent the value of the coordinate
     */

    //  value of the coordinate
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
     * @param coordinate represent the object to copy into the current object
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
     * @param coordinate the coordinate to assign
     */
    public void setCoordinate(Coordinate coordinate) {
        _coordinate = coordinate._coordinate;
    }

    /**
     * Set the field coordinate
     * @param c the value to assign
     */
    public void setCoordinate(double c)
    {
        _coordinate = c;
    }

    // ***************** Administration ******************** //

    /*************************************************
     * --------
     * FUNCTION
     * --------
     * compareTo
     *
     * ------------
     * PARAMETER(S)
     * ------------
     * coordinate - the coordinate to compare with
     *
     * ------------
     * RETURN VALUE
     * ------------
     * 0 if equals
     * 1 if this bigger
     * -1 if this lower
     *
     * -------
     * MEANING
     * -------
     * Check equality between 2 coordinates
     *
     * --------
     * SEE ALSO
     * --------
     * every function in which we need to check equality
     *************************************************/
    @Override
    public int compareTo(Coordinate coordinate) {
        return Double.compare(_coordinate,coordinate._coordinate);
    }

    /**
     * Function to compare between 2 coordinates
     * @param o the coordinate to compare
     * @return true if the values are equals false otherwise
     */

    /*************************************************
     * --------
     * FUNCTION
     * --------
     * equals
     *
     * ------------
     * PARAMETER(S)
     * ------------
     * object - the object to compare with
     *
     * ------------
     * RETURN VALUE
     * ------------
     * True if the coordinates values are the same
     * False otherwise
     *
     * -------
     * MEANING
     * -------
     * Check equality between 2 coordinates
     *
     * --------
     * SEE ALSO
     * --------
     * every function in which we need to check equality
     *************************************************/
    @Override
    public boolean equals(Object o){
        Coordinate c = (Coordinate)o;
        return c.getCoordinate() == getCoordinate();
    }

    // ***************** Operations ******************** //

    /**
     * Add value to an existing coordinate
     * @param coordinate the value to add
     */

    /*************************************************
     * --------
     * FUNCTION
     * --------
     * add
     *
     * ------------
     * PARAMETER(S)
     * ------------
     * coordinate - the coordinate to add to this one
     *
     * ------------
     * RETURN VALUE
     * ------------
     * void function
     *
     * -------
     * MEANING
     * -------
     * add the value of the parameter coordinate to this one
     *
     * --------
     * SEE ALSO
     * --------
     * every function in which we need to add coordinates
     *************************************************/
    public void add(Coordinate coordinate)
    {
        _coordinate += coordinate._coordinate;
    }

    /*************************************************
     * --------
     * FUNCTION
     * --------
     * subtract
     *
     * ------------
     * PARAMETER(S)
     * ------------
     * coordinate - the coordinate to subtract to this one
     *
     * ------------
     * RETURN VALUE
     * ------------
     * void function
     *
     * -------
     * MEANING
     * -------
     * subtract the value of the parameter coordinate from this one
     *
     * --------
     * SEE ALSO
     * --------
     * every function in which we need to subtract coordinate
     *************************************************/
    public void subtract (Coordinate coordinate)
    {
        _coordinate-=coordinate._coordinate;
    }


    /*************************************************
     * --------
     * FUNCTION
     * --------
     * toString
     *
     * ------------
     * PARAMETER(S)
     * ------------
     *
     * ------------
     * RETURN VALUE
     * ------------
     * string representation of the coordinate
     *
     * -------
     * MEANING
     * -------
     * get the string representation of the coordinate with 2 floating points
     *
     * --------
     * SEE ALSO
     * --------
     * every function in which we need to print out the coordinate
     *************************************************/
    @Override
    public String toString()
    {
        return String.format("%.2f", _coordinate);
    }

}

