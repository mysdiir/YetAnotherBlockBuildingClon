# Yet another block building clone

## What is it about?

Well, while preparing for my final exam I started playing Minecraft,just to clear my mind and find a little relaxation. But then some strange thought came into my mind: How does Minecraft work? 
And there it was: the itching feeling in my fingers and so was born the idea to recreate Minecraft for myself.

This repo will won't contain the final game as a playable version, moresoever this is a little blog where I write down my progress of creating it.
Have fun while reading ;)

## Map generation
### Creating a hightmap

```Java

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
    
```
![image.png](https://user-images.githubusercontent.com/70364903/234199370-d672e529-912f-4518-b9d2-4c1925ba52ed.png))
