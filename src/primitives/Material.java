package primitives;

public class Material
{
    private double _Kd; // Diffusion attenuation coefficient
    private double _Ks; // Specular attenuation coefficient
    private double _Kr; // Reflection coefficient (1 for mirror)
    private double _Kt; // Refraction coefficient (1 for transparent)
    private double _n;  // Refraction index

    // ***************** Constructors ********************** //

    /**
     * Default constructor
     */
    public Material()
    {
        _Kd = 1;
        _Ks = 1;
        _Kr = 0;
        _Kt = 0;
        _n  = 1;
    }

    /**
     * Copy constructor
     * Initialize according to the given parameter
     * @param material the material to copy
     */
    public Material(Material material)
    {
        _Kd = material._Kd;
        _Kr = material._Kr;
        _Ks = material._Ks;
        _Kt = material._Kt;
        _n = material._n;
    }


    // ***************** Getters/Setters ********************** //

    /**
     * Getter for diffusion attenuation coefficient
     * @return value of _kd
     */
    public double getKd() { return _Kd; }

    /**
     * Getter for specular attenuation coefficient
     * @return value of _ks
     */
    public double getKs() { return _Ks; }

    /**
     * Getter for reflection coefficient
     * @return value of _kr
     */
    public double getKr() { return _Kr; }

    /**
     * Getter for refraction coefficient
     * @return value of _kt
     */
    public double getKt() { return _Kt; }

    /**
     * Getter for refraction index
     * @return value of _n
     */
    public double getN() { return _n; }

    /**
     * Setter for diffusion attenuation coefficient
     * @param Kd value to set
     */
    public void setKd(double Kd) { _Kd = Kd; }

    /**
     * Setter for specular attenuation coefficient
     * @param Ks value to set
     */
    public void setKs(double Ks) { _Ks = Ks; }

    /**
     * Setter for reflection coefficient
     * @param Kr value to set
     */
    public void setKr(double Kr) { _Kr = Kr; }

    /**
     * Setter for refraction coefficient
     * @param Kt value to set
     */
    public void setKt(double Kt) { _Kt = Kt; }

    /**
     * Setter for refraction index
     * @param n value to set
     */
    public void setN (double n) { _n = n; }
}
