package TerrainGeneration.Preprocessor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapColoring {

    private Color water = Color.blue;
    private Color sand = Color.yellow;
    private Color gras = Color.green;
    private Color  mountain = Color.gray;
    private Color snow = Color.white;


    public void convert(BufferedImage image) {
        int greyscale = 0;

        BufferedImage coloredMap = new BufferedImage(image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        for (int y  = 0; y < image.getWidth(); y++) {
            for (int x = 0; x < image.getHeight(); x++) {

                greyscale = image.getRGB(x,y);
                int red = (greyscale & 0x00ff0000) >> 16;
                System.out.println((image.getRGB(x,y) >> 16) & 0xFF);

                if (red >= 0 && red <= 104) coloredMap.setRGB(x, y, water.getRGB());
                else if (red >= 105 && red <= 110) coloredMap.setRGB(x, y, sand.getRGB());
                else if (red >= 111 && red <= 149) coloredMap.setRGB(x, y, gras.getRGB());
                else if (red >= 150 && red <= 200) coloredMap.setRGB(x, y, mountain.getRGB());
                else coloredMap.setRGB(x, y, snow.getRGB());
            }
        }
        try {

            File outputfile = new File("saved_colored.png");
            ImageIO.write(coloredMap, "png", outputfile);
        } catch (IOException e){
            System.err.println("Error " + e);
        }
    }

}
