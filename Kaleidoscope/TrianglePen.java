import java.util.ArrayList;
import processing.core.PApplet;

public class TrianglePen {
  private boolean mouseWasPressed = false; // mousePressed from previous update() call
  private char keyWasPressed = '\0'; // keyPressed from previous update() call
  private PApplet processing; // PApplet object that represents
//the graphic display window
  private boolean showPoints;
  private int colorIndex;
  private ArrayList<Point> circles;
  private ArrayList<Triangle> triangles;
  private int index = 0;
  private int circlesIndex;
  boolean flag = false;
  private Point currentPoint=null;
  public TrianglePen(PApplet processing, boolean showPoints){
    circles = new ArrayList<Point>();
    triangles = new ArrayList<Triangle>();
    this.processing = processing;
    this.showPoints=showPoints;
  }
  private void handleMousePress(int mouseX, int mouseY) {
    
    Point obj;
    int x;
    int y;
    if(circles.size()!=0)
    {
      for(int i=0;i<circles.size();i++)
      {
        obj=circles.get(i);
        x = obj.getX();
        y = obj.getY();
        currentPoint = new Point(x,y);
        if(currentPoint.isOver(mouseX,mouseY))
        {
          flag=true;
          circlesIndex = i;
        }
        else currentPoint=null;
      }
    }
    
    if(flag==false)
    {
      Point o = new Point(mouseX, mouseY);
      circles.add(o);
      
      if((circles.size()) % 3 == 0 && circles.size() != 0)
      { 
        System.out.println();
        Point X = circles.get(index);
        Point Y = circles.get(index+1);
        Point Z = circles.get(index+2);
        triangles.add(new Triangle(X,Y,Z,colorIndex));
        index+=3;      
      }
    }
  }
  private void handleMouseRelease(int mouseX, int mouseY) {
    currentPoint=null;
    flag=false;
  }
  private void handleMouseDrag(int mouseX, int mouseY) {
    if(flag==true)
    {      
      circles.set(circlesIndex,new Point(mouseX,mouseY));
      int quotient=0;
      int remainder=0;
      quotient=circlesIndex/3;
      remainder=circlesIndex%3;
      Triangle obj = null;
      try {      obj = triangles.get(quotient);
      if(remainder==0)
      {
        obj.setX(new Point(mouseX,mouseY));
      }
      else if(remainder==1)
      {
        obj.setY(new Point(mouseX,mouseY));
      }
      else if(remainder==2)
      {
        obj.setZ(new Point(mouseX,mouseY));
      }
      }
      catch(IndexOutOfBoundsException e) {      
      }
    }
    
  }
  private void handleKeyPress(int mouseX, int mouseY, char KeyPressed) {
    Triangle o;
    Point X;
    Point Y;
    Point Z;
    int x1;
    int y1;
    int x2;
    int y2;
    int x3;
    int y3;
    if(KeyPressed>='0'&&KeyPressed<='7')
    {
      for(int i=0; i < triangles.size(); i++)
      {
        o = triangles.get(i);
        X = o.getX();
        x1 = X.getX();
        y1 = X.getY();
        Y = o.getY();
        x2 = Y.getX();
        y2 = Y.getY();
        Z = o.getZ();
        x3 = Z.getX();
        y3 = Z.getY();
        if(o.getIsPointInTriangle(mouseX,mouseY,x1,y1,x2,y2,x3,y3))
            {
              o.setColor((int)(KeyPressed-'0'));              
            }
      }      
    }    
  }
  private void draw() {
    if(showPoints==true)
    {
      for(int i=0; i<circles.size();i++)
      {
        circles.get(i).draw(processing);
      }
    }
    
    for(int i=0; i<triangles.size();i++)
    {
      triangles.get(i).draw(processing);
    }
  }
  public void update(int mouseX, int mouseY, boolean mousePressed, char keyPressed){
    if(mousePressed != mouseWasPressed) {
      if(mousePressed) handleMousePress(mouseX, mouseY);
      else handleMouseRelease(mouseX, mouseY);
     }
     if(mousePressed) handleMouseDrag(mouseX, mouseY);
     mouseWasPressed = mousePressed;
     // process keyboard-based user input
     if(keyPressed != keyWasPressed) handleKeyPress(mouseX, mouseY, keyPressed);
     keyWasPressed = keyPressed;
     // draw everything in its current state
     draw();
  }
}
