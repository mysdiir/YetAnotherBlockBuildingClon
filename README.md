# Yet another block building clone

# Table of Contents
1. [Introduction?](#introduction)
2. [Map generation](#mapgeneration)
  1 [Creating a hightmao](#Creatingahightmap)


## Introduction<a name="introduction"></a>

Well, while preparing for my final exam I started playing Minecraft, just to clear my mind and find a little relaxation. But then some strange thought came into my mind: How does Minecraft work? 
And there it was: the itching feeling in my fingers and so was born the idea to recreate Minecraft for myself.

This repo will won't contain the final game as a playable version, moresoever this is a little blog where I write down my progress of creating it.
Have fun while reading ;)

## Map generation <a name="mapgeneration"></a>
### Creating a hightmap <a name="Creatingahightmap"></a>

My first idea was to generate a noise map, made by random generated greyscale values, which are converted to a image by a `BufferedImage` Object.


Setting up the image and randomizer conditions:


```Java

  int width = 600;
  int height = 600;
  
   double min = 0;
  double max = 255;
```

Creating a new `BufferedImage` object.

```Java
  BufferedImage map = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
```

Iterate trough X and Y coordinate dimenons and set a random generated greyvalue between 0 and 255

```Java
for (int x = 0; x < width; x++) {
    for (int y = 0; y < height; y++) {
      int rng = (int) (min + (int)(Math.random() * ((max - min) + 1)));
      int gray = (rng << 16) + (rng << 8) + rng;
      map.setRGB(x,y, gray);
    }
 }
 ```
 
 
 Generate a `png` file and handle the upcoming `IOException`
 
 ```Java
 try {
   File outputfile = new File("noise.png");
   ImageIO.write(map, "png", outputfile);
 } catch (IOException e){
   System.err.println("Error " + e);
   }
 }
    
```

The result:


![Procedural generated noise map](https://user-images.githubusercontent.com/70364903/234199370-d672e529-912f-4518-b9d2-4c1925ba52ed.png)

Okay so far a heightmap was technically made but when imported into Minecrft WorldEditor (a programm that can generate custom Minecraft maps, furthermore with importing heightmaps) the result was this:


![image](https://user-images.githubusercontent.com/70364903/234211051-763dec25-9287-41a7-bd2d-9bb73b757b17.png)


### Perlin Noise

