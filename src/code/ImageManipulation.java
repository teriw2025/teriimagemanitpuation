package code;

import image.APImage;
import image.Pixel;

import java.awt.*;
import java.io.File;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
        APImage myImage = new APImage("cyberpunk2077.jpg");
        myImage.draw();
        grayScale("cyberpunk2077.jpg");
        blackAndWhite("cyberpunk2077.jpg");
        edgeDetection("cyberpunk2077.jpg", 20);
    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String pathOfFile) {
        APImage image = new APImage(pathOfFile);

        int height = image.getHeight();
        int width = image.getWidth();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                Pixel pix = image.getPixel(x, y);
                int avg = getAverageColour(pix);

                Pixel p = new Pixel(avg, avg, avg);
                image.setPixel(x, y, p);
            }
        }
        image.draw();
    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
       int red = pixel.getRed();
       int blue = pixel.getBlue();
       int green = pixel.getGreen();

       return ((red + blue + green)/3);
    }

    /** CHALLENGE TWO: Black and White
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     *
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String pathOfFile) {
        APImage image = new APImage(pathOfFile);

        int height = image.getHeight();
        int width = image.getWidth();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                Pixel pix = image.getPixel(x, y);
                int avg = getAverageColour(pix);

                if(avg < 128){
                    Pixel p = new Pixel(0, 0, 0);
                    image.setPixel(x, y, p);
                }

                else if(avg >= 128){
                    Pixel p = new Pixel(255, 255, 255);
                    image.setPixel(x, y, p);
                }
            }
        }
        image.draw();
    }

    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
    public static void edgeDetection(String pathToFile, int threshold) {
        APImage image = new APImage(pathToFile);

        int height = image.getHeight();
        int width = image.getWidth();

        for (int x = width - 1; x > 0; x--){
            for (int y = height - 1; y > 0; y--){

                Pixel pix1 = image.getPixel(x, y);
                int avg1 = getAverageColour(pix1);

                Pixel pix2 = image.getPixel(x-1, y);
                int avg2 = getAverageColour(pix2);

                Pixel pix3 = image.getPixel(x, y-1);
                int avg3 = getAverageColour(pix3);

                int diff_12 = java.lang.Math.abs(avg1 - avg2);
                int diff_13 = java.lang.Math.abs(avg1 - avg3);

                Pixel p;
                if(diff_12 >= threshold || diff_13 >= threshold) {
                    p = new Pixel(0, 0, 0);
                }
                else{
                    p = new Pixel(255, 255, 255);
                }
                image.setPixel(x, y, p);
            }
        }
        image.draw();
    }

//    /** CHALLENGE Four: Reflect Image
//     *
//     * INPUT: the complete path file name of the image
//     * OUTPUT: the image reflected about the y-axis
//     *
//     */
//    public static void reflectImage(String pathToFile) {
//
//    }
//
//    /** CHALLENGE Five: Rotate Image
//     *
//     * INPUT: the complete path file name of the image
//     * OUTPUT: the image rotated 90 degrees CLOCKWISE
//     *
//     *  */
//    public static void rotateImage(String pathToFile) {
//
//    }

}
