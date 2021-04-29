import java.util.ArrayList;
import processing.core.PApplet;

public class KaleidoscopePen {
  
private int numberOfTrianglePens;
  private ArrayList<TrianglePen> trianglePenArray;
  public KaleidoscopePen(PApplet drawTo, int numberOfTrianglePens) {
   trianglePenArray= new ArrayList<>(numberOfTrianglePens);
   this.numberOfTrianglePens=numberOfTrianglePens;
    for(int i=0;i<numberOfTrianglePens;i++)
    {      
      if(i==0)
      {
      trianglePenArray.add(new TrianglePen(drawTo,true));
      }
      else trianglePenArray.add(new TrianglePen(drawTo,false));
    }
  
  }
  
  /**
  * Rotates a position around the center of an 800x600 screen by the specified
  * amount, and then returns an array containing the resulting position.
  * @param x position of the point to be rotated (0 to 800 pixels)
  * @param y position of the point to be rotated (0 to 600 pixels)
  * @param angle amount of rotation to apply (angle in radians units: 0 to 2*PI)
  * @return the rotated position array: x @ index 0, y @ index 1
  */
  private static int[] rotate(int x, int y, double angle) {
   x -= 400; // translate center of screen to origin (0,0)
   y -= 300;
   int[] rotatedPosition = new int[] { // rotate around origin
   (int)(x * Math.cos(angle) - y * Math.sin(angle)),
   (int)(x * Math.sin(angle) + y * Math.cos(angle)) };
   rotatedPosition[0] += 400; // return to center of screen
   rotatedPosition[1] += 300;
   return rotatedPosition;
  }
 
   
  private TrianglePen obj;
  public void update(int mouseX, int mouseY, boolean mousePressed, char keyPressed) {
    
    for(int i=0;i<trianglePenArray.size();i++)
    {
      obj = trianglePenArray.get(i);
      double angle = i*2*Math.PI/numberOfTrianglePens;
      int [] rotation = rotate (mouseX,mouseY,angle);
      obj.update(rotation[0], rotation[1], mousePressed, keyPressed);
    }
  }
  
}
