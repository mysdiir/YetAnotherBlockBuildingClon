# Yet another block building clone

# Table of Contents
1. [Introduction?](#introduction)
2. [Map generation](#mapgeneration)
   1. [Creating a hightmap](#Creatingahightmap)
   2. [Perlin noise](#Perlinnoise)


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

Okay so far a heightmap was technically made but when imported into Minecraft WorldEditor (a programm that can generate custom Minecraft maps, furthermore with importing heightmaps) the result was this:


![image](https://user-images.githubusercontent.com/70364903/234211051-763dec25-9287-41a7-bd2d-9bb73b757b17.png)

Well, that was pretty the result that I expected and so I kept on searching ways to make a smoother terrain generation.

### Perlin Noise <a name="Perlinnoise"></a>


If you take a look at the terrain arround you, you will notice that mountains and hills often show a smoothly transition between lower and higher parts.
The problem with my first attempt of generating a height map with random noise generation was, that each pixel doesn't have a relationship to each other and this avoids the generation of an natural ascent.

Thanks to the <a href="https://www.youtube.com/watch?v=iW4nFygKAjw&list=PLA2Wxg-e7vbA1LC15uXM0s8p5Nhse_rNn">great video series made by Fataho</a> I started understanding the the genius behind the Perlin noise algorithm and even though I had been a lazy math student during my high school times that could barely be motivated, I started gaining a huge interest in the functionality of Perlin noise.

So let's do a little digression on Perlin noise:

After taking part at the CGI production team of Disney's movie Tron (1982) and being frustrated of the lack of natural look created by computers, Perlin descided to work on a method to make it look more natural.
