package renderer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageWriter {

    private int _imageWidth;
    private int _imageHeight;

    private int _Ny, _Nx;

    final String PROJECT_PATH = System.getProperty("user.dir");

    private BufferedImage _image;

    private String _imageName;

    public BufferedImage getImage() {
        return _image;
    }

    // ***************** Constructors ********************** //

    public ImageWriter(String imageName, int width, int height, int Ny, int Nx){

        _Nx = Nx;
        _Ny = Ny;

        _imageWidth = width;
        _imageHeight = height;

        _imageName = imageName;

        _image = new BufferedImage(
                _imageWidth, _imageHeight, BufferedImage.TYPE_INT_RGB);;
    }

    public ImageWriter (ImageWriter imageWriter){
        _Nx = imageWriter._Nx;
        _Ny = imageWriter._Ny;

        _imageWidth = imageWriter.getWidth();
        _imageHeight = imageWriter.getHeight();

        _imageName = imageWriter._imageName;

        _image = new BufferedImage(
                _imageWidth, _imageHeight, BufferedImage.TYPE_INT_RGB);;
    }

    // ***************** Getters/Setters ********************** //

    public int getWidth()  { return _imageWidth;  }
    public int getHeight() { return _imageHeight; }

    public int getNy() { return _Ny; }
    public int getNx() { return _Nx; }

    public void setNy(int _Ny) { this._Ny = _Ny; }
    public void setNx(int _Nx) { this._Nx = _Nx; }

    // ***************** Operations ******************** //


    /*************************************************
     * --------
     * FUNCTION
     * --------
     * writeToImage
     *
     * ------------
     * PARAMETER(S)
     * ------------
     *
     * ------------
     * RETURN VALUE
     * ------------
     *
     * -------
     * MEANING
     * -------
     * write the image to the default file
     *
     * --------
     * SEE ALSO
     * --------
     *************************************************/
    public void writeToimage(){

        File ouFile = new File(PROJECT_PATH + "/results/" + _imageName + ".jpg");

        try {
            ImageIO.write(_image, "jpg", ouFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*************************************************
     * --------
     * FUNCTION
     * --------
     * writeToImage
     *
     * ------------
     * PARAMETER(S)
     * ------------
     * String - the string representation of the path where to write
     *
     * ------------
     * RETURN VALUE
     * ------------
     *
     * -------
     * MEANING
     * -------
     * write the image to the string path file
     *
     * --------
     * SEE ALSO
     * --------
     *************************************************/
    public void writeToimage(String file_path){
        File ouFile = new File(PROJECT_PATH + file_path + _imageName + ".jpg");

        try {
            ImageIO.write(_image, "jpg", ouFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*************************************************
     * --------
     * FUNCTION
     * --------
     * writePixel
     *
     * ------------
     * PARAMETER(S)
     * ------------
     * int - the x coordinate of the pixel
     * int - the y coordinate of the pixel
     * int - red composant
     * int - green composant
     * int - blue composant
     *
     * ------------
     * RETURN VALUE
     * ------------
     * void
     *
     * -------
     * MEANING
     * -------
     * write a specific pixel
     *
     * --------
     * SEE ALSO
     * --------
     *************************************************/
    public void writePixel(int xIndex, int yIndex, int r, int g, int b){

        int rgb = new Color(r, g, b).getRGB();
        _image.setRGB(xIndex, yIndex, rgb);
    }

    public void writePixel(int xIndex, int yIndex, int[] rgbArray){

        int rgb = new Color(rgbArray[0], rgbArray[1], rgbArray[2]).getRGB();
        _image.setRGB(xIndex, yIndex, rgb);

    }

    public void writePixel(int xIndex, int yIndex, Color color){

        _image.setRGB(xIndex, yIndex, color.getRGB());

    }


}
