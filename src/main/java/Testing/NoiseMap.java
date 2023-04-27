package Testing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NoiseMap {

    public static void main(String[] args) {
        int width = 600;
        int height = 600;

        double min = 0;
        double max = 255;

        BufferedImage map = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rng = (int) (min + (int)(Math.random() * ((max - min) + 1)));
                int gray = (rng << 16) + (rng << 8) + rng;
                map.setRGB(x,y, gray);
            }
        }

        try {

            File outputfile = new File("noise.png");
            ImageIO.write(map, "png", outputfile);
        } catch (IOException e){
            System.err.println("Error " + e);
        }
    }
}
