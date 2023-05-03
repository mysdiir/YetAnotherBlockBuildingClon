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

With his created noise generator, Perlin won the 1997 the Academy Award for Technical Achievment and set the path for procedural landscape generation untill today.

But let's take a look at the algorithm itself, understand the basic problem and find out how Perlin Noise works.

As I said before when you generate a random generated noise, the values have no relationship as shown in the following graphic

![image](https://user-images.githubusercontent.com/70364903/235259572-7aeae6d6-8106-4e64-8639-6ed7ea6dc3a2.png)

To explain Perlin Noise indepth is way too much for that little devblog but I'll try to explain at the most basic concepts:


The main function of Perlin noise is set a random generated values in a relationship to each other, like you can see in the graphic below.


![image](https://user-images.githubusercontent.com/70364903/236028686-bb2846b5-fe88-4d23-af7d-7064d06b1c4b.png)

Perlin noise consits out of three basic concepts:
- Grid definition
- Dot product
- Interpolation

Lets take a look at each of these basic concepts:

#### Grid definition
Imagine an empty picture with resolution 100 x 100 px. First of all we need to implement a grid system, that consists of certain pixels.
The bigger the grid is, the more detailes are shown on the output image, like from a completely blury image to a more detailed version of it.

![image](https://user-images.githubusercontent.com/70364903/236035983-ed8946c0-e671-4a81-b9ba-33853c9954ed.png)
1x1 Grid

![image](https://user-images.githubusercontent.com/70364903/236036076-5666e82a-ee09-4ef1-a7c8-b550f5f48779.png)
32 x 32 grid

This grid system has the task to generate a gradient vector on each corner point, that points into random directions. Technically choosing this gradient vector doesn't happen with random generating numbers function (like in Java `Math.random()`), instead a random number table (basicallly an array where random numbers are stored, desinged by Perlin) is used here.
To be honest, at the moment of writing this, I don't fully understand why the table has to be used, as far as I read, it has something to do with preventing an overflow, but I'll keep on researching ;).

The second vectors we need are distance vector that basically points from every corder to the pixel where we want to place a grey value.
Those two vectors are the essential part to calculate the second basic concept of Perlin noise: the Dot Product.



