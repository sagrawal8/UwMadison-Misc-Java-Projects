import processing.core.PApplet;

public class Point {
  private int positionX;
  private int positionY;
  private static final int POINT_DIAMETER = 8;
  public Point(int x, int y) {
    positionX = x;
    positionY = y;
  }
  public int getX()
  {
    return positionX;
  }
  public int getY()
  {
    return positionY;
  }
  public void SetPosition(int x, int y)
  {
    this.positionX = x;
    this.positionY = y;
  }
  public void draw(PApplet drawTo) {  // draw a white circle at this point’s position
    drawTo.fill(-1);
    drawTo.circle(positionX, positionY, POINT_DIAMETER);
    
  }
//returns true when the position x, y is within the circle drawn to visualize this point, otherwise returns false
  public boolean isOver(int x, int y) {
    double distance;
    boolean flag = false;
    distance = Math.sqrt((float)(Math.pow(x-positionX, 2)-Math.pow(y-positionY, 2)));
    if(distance<POINT_DIAMETER/2)
      flag = true;
    else flag = false;
    
    return flag;    
    
  }
}
