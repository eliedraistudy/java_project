package renderer;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.Random;
import org.junit.Test;

public class ImageWriterTest {

    @Test
    public void writeImageTest(){

        ImageWriter imageWriter = new ImageWriter("Image writer test", 500, 500, 1, 1);
        Random rand = new Random();
        for (int i = 0; i < imageWriter.getHeight(); i++){
            for (int j = 0; j < imageWriter.getWidth(); j++)
            {

                if (i % 25 == 0 || j % 25 == 0 /*|| i == j */|| i == 499 || j == 499 /*||i == 500 - j*/)
                    imageWriter.writePixel(j, i, 0, 0, 0);  // Black
                else
                if(i >= 200 && i <= 300 && j >= 200 && j <= 300)
                    imageWriter.writePixel(j, i, 255, 120, 0); // Red
                else
                if(i >= 150 && i <= 350 && j >= 150 && j <= 350)
                    imageWriter.writePixel(j, i, 0, 255, 0);  // Green
                else
                if(i >= 100 && i <= 400 && j >= 100 && j <= 400)
                    imageWriter.writePixel(j, i, 0, 0, 255); // Blue
                else
                if(i >= 50 && i <= 450 && j >= 50 && j <= 450)
                    imageWriter.writePixel(j, i, 255, 255, 0); // Yellow
                else
                    imageWriter.writePixel(j, i, rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)); // Random
            }
        }

        imageWriter.writeToimage("/results/Flags/");

    }

    @Test
    public void ImageWriterFlagFrance(){
        ImageWriter imageWriter =
                new ImageWriter("FranceFlag", 600, 400, 1, 1);

        for (int i = 0; i < imageWriter.getHeight(); i++) {
            for (int j = 0; j < imageWriter.getWidth(); j++) {
                if(j<=imageWriter.getWidth()/3)
                    imageWriter.writePixel(j, i, new Color(0,0,128));

                else if (j<=2*imageWriter.getWidth()/3)
                    imageWriter.writePixel(j, i, 255, 255, 255);

                else
                    imageWriter.writePixel(j,i, 255,0,0);
            }
        }
        imageWriter.writeToimage("/results/Flags/");
    }

    @Test
    public void ImageWriterFlagIsrael(){
        ImageWriter imageWriter =
                new ImageWriter("IsraelFlag", 600, 400, 1, 1);
        Color blue = new Color(0,0,128);
        Color white = Color.WHITE;

        for (int i = 0; i < imageWriter.getHeight(); i++) {
            for (int j = 0; j < imageWriter.getWidth(); j++) {

                //  create the stripes
                if(i<=40 || i>360 || (i<=300 && i>100))
                    imageWriter.writePixel(j,i, white);
                else if(i<=100 || i> 300)
                    imageWriter.writePixel(j,i, blue);

                //  create the star
                //  localize the square at the center
                int centerX = imageWriter.getWidth()/2;
                int centerY = imageWriter.getHeight()/2;
                int radius = 75;
                if((i<= centerY + radius && i>=centerY - radius) &&
                        (j<= centerX + radius && j>=centerX - radius)){
                    imageWriter.writePixel(j,i, blue);
                }
            }
        }

        imageWriter.writeToimage("/results/Flags/");
    }

    @Test
    public void ImageWriterFlagJapan(){
        ImageWriter imageWriter =
                new ImageWriter("JapanFlag", 600, 400, 1, 1);
        Color red = Color.RED;
        Color white = Color.WHITE;

        for (int i = 0; i < imageWriter.getHeight(); i++) {
            for (int j = 0; j < imageWriter.getWidth(); j++) {



                //  create the star
                int centerX = imageWriter.getWidth()/2;
                int centerY = imageWriter.getHeight()/2;
                if(Math.pow(i-centerY,2) + Math.pow(j-centerX,2) <= 100*100)
                    imageWriter.writePixel(j,i, red);
                else
                    imageWriter.writePixel(j,i, white);
            }
        }

        imageWriter.writeToimage();
    }

    @Test
    public void ImageWriterFlagUSA(){
        int height = 494;
        int width = (int)(height * 1.9);
        ImageWriter imageWriter =
                new ImageWriter("USA flag", width, height, 1, 1);
        Color red = new Color(178,34,34);
        Color white = Color.WHITE;
        Color blue = new Color(0,0,128);

        //  color background
        int stripe_size = (int)(height / 13);
        for (int i = 0; i < imageWriter.getHeight(); i++) {
            for (int j = 0; j < imageWriter.getWidth(); j++) {
                if(i%(2*stripe_size)<=stripe_size)
                    imageWriter.writePixel(j,i,red);
                else imageWriter.writePixel(j,i,white);
            }
        }

        // color blue square
        for (int i = 0; i < imageWriter.getHeight(); i++) {
            for (int j = 0; j < imageWriter.getWidth(); j++) {
                if(i<=7*stripe_size && j<=(int)(0.4*width))
                    imageWriter.writePixel(j,i,blue);
            }
        }

        //  create stars

        imageWriter.writeToimage("/results/Flags/");
    }


    private void ColorLine(ImageWriter image,int i, int j_start, int j_end, Color c){
        for (int j = j_start; j < j_end; j++) {
            image.writePixel(j,i,c);
        }
    }

    @Test
    public void WriteStar(){
        ImageWriter imageWriter =
                new ImageWriter("triangle flag", 500, 500, 1, 1);
        CreateStar(imageWriter,500,0,0,Color.BLUE);
        imageWriter.writeToimage("/results/Flags/");
    }


    private void CreateStar(ImageWriter image,
                               int square_size,
                               int i_start,
                               int j_start,
                            Color c){

        int middle = square_size/2;
        int starting_j, ending_j;

        for(int i = 0; i<=square_size; i++, i_start++){
            for(int j = 0; j<=square_size; j++){

                if(i<=(2*(double)square_size)/3) {
                    starting_j = j_start + middle - (i / 2);
                    ending_j = j_start + middle + (i / 2);
                    ColorLine(image, i_start, starting_j, ending_j, c);
                }
            }
        }


        i_start-=2;
        for(int i = square_size; i>0; i--, i_start--){
            for(int j = 0; j<=square_size; j++){
                if(i>=((double)square_size)/3) {
                    starting_j = j_start + middle - (square_size - i/ 2);
                    ending_j = j_start + middle + (square_size - i / 2);
                    ColorLine(image, i_start, starting_j, ending_j, c);
                }
            }
        }
    }


}
