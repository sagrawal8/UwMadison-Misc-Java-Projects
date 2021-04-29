import java.util.*;
public class Canvas {

  private final int width; // width of the canvas
  private final int height; // height of the canvas
  private char [][] drawingArray; // 2D character array to store the drawing
  private final DrawingStack undoStack; // store previous changes for undo
  private final DrawingStack redoStack; // store undone changes for redo
//Constructor creates a new canvas which is initially blank (use the default value
//for char type or you can use spaces)
 
  public int getWidth() {
   return width;
 }
 
 public int getHeight() {
   return height;
 }
 
 public char[][] getArray(){
   return drawingArray;
 }
 
 public DrawingStack getRedoStack() {
   return redoStack;
 }
 
 public DrawingStack getUndoStack() {
   return undoStack;
 }
 //constructor creates a new canvas with WidthxHeight dimensions.
 public Canvas(int width, int height) {
  if(width<=0||height<=0)
    throw new IllegalArgumentException("width or height is less than or equal to 0");
  this.width = width;
  this.height = height;
  drawingArray = new char[height][width];  
  undoStack = new DrawingStack();
  redoStack = new DrawingStack();  

}
   //Draw a character at the given position drawingArray[row][col]
//This method should throw an IllegalArgumentException if the drawing
//position is outside the canvas
//If that position is already marked with a different character, overwrite it.
//After making a new change, add a matching DrawingChange to the undoStack
//so that we can undo if needed.
//After making a new change, the redoStack should be empty (meaning that
//you should clear the redoStack if it is not already empty).
   public void draw(int row, int col, char c) {
     if(row>height||col>width)
     {
       throw new IllegalArgumentException("row or col are out of array bounds");
     }
     char prevChar = drawingArray[row][col];
     drawingArray[row][col] = c;
     undoStack.push(new DrawingChange(row,col,prevChar,c));
     if(!redoStack.isEmpty())
     for(int i=0; i<redoStack.size(); i++)
     {
       redoStack.pop();
     }
  
   }   
   //Undo the most recent drawing change.
 //Return true if successful. False otherwise.
   //An undone DrawingChange should be popped off the undoStack.
   //An undone DrawingChange should be added to the redoStack so that
   //we can redo if needed.
   //The content of the drawingArray should be updated accordingly to this change.
   public boolean undo() {
     
     boolean undo = true;
     try {
       DrawingChange poppedObj = undoStack.pop();
       redoStack.push(poppedObj);
       drawingArray[poppedObj.row][poppedObj.col] = poppedObj.prevChar;
     }
     catch(Exception e) { 
       undo = false;
       return undo;
     }
     return undo;
     
     
   }
   //Redo the most recent undone drawing change.
 //Return true if successful. False otherwise.
   //A redone DrawingChange should be popped off the redoStack.
   //A redone DrawingChange should be added (back) to the undoStack so that
   //we can undo again if needed.
   //The content of the drawingArray should be updated accordingly to this change.
   public boolean redo() {
     boolean redo = true;
     try {
       DrawingChange poppedObj = redoStack.pop();
       undoStack.push(poppedObj);
       drawingArray[poppedObj.row][poppedObj.col] = poppedObj.newChar;
     }
     catch(Exception e) {
       redo = false;
       return redo;
     }
     return redo;
     
   }
   //Return a printable string version of the Canvas.
   /* Format example:
    * [_ is blank. Use System.lineSeparator() to put a newline character between rows]
      * X___X
      * _X_X_
      * __X__
      * _X_X_
      * X___X
      */
   public String toString() {
     
     String s="";
     for(int i = 0; i<=width-1; i++)
     {
       for(int j=0; j<=height-1; j++)
       {
         if(drawingArray[i][j]=='\0')
         {
           s=s+" ";
         }
         else s=s+drawingArray[i][j];
       }
       s=s+System.lineSeparator();
     }
     return s;
     
}
}
