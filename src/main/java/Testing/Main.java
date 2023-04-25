package Testing;

import TerrainGeneration.Preprocessor.MapColoring;
import TerrainGeneration.Preprocessor.PerlinNoise3D;

import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {


        PerlinNoise3D perlinNoise3D = new PerlinNoise3D(600, 600, 8);
        BufferedImage randomMap = new BufferedImage(perlinNoise3D.getWidth(),
                perlinNoise3D.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        MapColoring coloring = new MapColoring();

        perlinNoise3D.getNoiseImage(randomMap);
        perlinNoise3D.saveImage(randomMap);
        BufferedImage coloredMap = new BufferedImage(perlinNoise3D.getWidth(), perlinNoise3D.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        coloring.convert(randomMap);

    }
}
