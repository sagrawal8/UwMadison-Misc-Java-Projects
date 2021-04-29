import java.util.Iterator;

public class AsciiArtTester {
  public static boolean runAsciiArtTestSuite() {
  
    return(testStackPushPeek()&&testConstructorAndDraw()&&testUndo());
  }
  
  public static void main(String args[]) {
    testConstructorAndDraw();
  }
//tests the push and peek functions of the stack.
  public static boolean testStackPushPeek(){ 
    
    boolean errorConstructor = true;
    boolean errorPush1 = true;
    boolean errorPush2 = true;
    
    
    DrawingStack obj = new DrawingStack();
    try {
      if(obj.peek()!=null&&obj.size()!=0)
        errorPush1 = true;
    }
    catch(java.util.EmptyStackException e)
    {
      errorConstructor = false;
      if(obj.size()!=0)
        errorConstructor = true;
    }    
         
    obj.push(new DrawingChange(0,0,'a','f'));
    DrawingChange obj1 = obj.peek();
    if (obj1.row==0&&obj1.col==0&&obj1.prevChar=='a'&&obj1.newChar=='f')
      errorPush1 = false;
    
    obj.push(new DrawingChange(0,1,'c','q'));
    DrawingChange obj2 = obj.peek();
    if (obj2.row==0&&obj2.col==1&&obj2.prevChar=='c'&&obj2.newChar=='q')
      errorPush2 = false;
    
      
    
    
    System.out.println("errorConstructor=" + errorConstructor);
    System.out.println("errorPush1=" + errorPush1);
    System.out.println("errorPush2=" + errorPush2);
    
    
    
    return !(errorConstructor||errorPush1||errorPush2);
    
  }
  
  public static boolean testObjectsEqual(DrawingChange a, DrawingChange b) {
    if(a.row==b.row&&a.col==b.col&&a.prevChar==b.prevChar&&a.newChar==b.newChar)
      return true;
    else return false;
  }
  //tests the constructor and draw functions of canvas.java
  public static boolean testConstructorAndDraw() {
    
    boolean errorConstructorDimensions = false;
    boolean errorEmptyStack = true;
    boolean errorIterator = true;
    boolean errorWrongDimensions = true;
    boolean errorDraw = false;
    Canvas obj = new Canvas(3,3);
    if(obj.getHeight()!=3||obj.getWidth()!=3)
    {
      errorConstructorDimensions = true;
    }
    if(obj.getUndoStack().isEmpty()||obj.getRedoStack().isEmpty())
    {
      errorEmptyStack = false;
    }    
    try {
      obj.draw(-1, 0, 'c');      
    }
    catch(Exception e) {
      errorWrongDimensions = false;
    }
    obj.draw(1, 0, 'c');
    int redoStackSize = obj.getRedoStack().size();
    char [][] charArray = obj.getArray();
    if(charArray[1][0]!='c')
    {
      errorDraw = true;
    }
    
    DrawingStack undoStack = obj.getUndoStack();
    DrawingChange objPeek = undoStack.peek();
    if (objPeek.row!=1&&objPeek.col!=0&&objPeek.prevChar!='\0'&&objPeek.newChar!='c')
    {
      errorDraw = true;
    }    
    
    if(redoStackSize!=0)
      errorDraw = true;
    
    Iterator<DrawingChange> x = undoStack.iterator();
    int count = 0;
    while(x.hasNext())
    {
      count++;
      x.next();
    }
    if(count==1)
    {
      errorIterator = false;
    }
   
    
    return !(errorIterator||errorConstructorDimensions||errorEmptyStack||errorWrongDimensions||errorDraw);
  
  }

  
  //tests the undo function of canvas.java
  public static boolean testUndo() {
    boolean errorUndoDetails = false;
    boolean errorUndo;
    Canvas obj = new Canvas(3,3);
    obj.draw(1, 0, 'c');
    obj.draw(2, 2, 'd');
    int undoStackSize = obj.getUndoStack().size();
    int redoStackSize = obj.getRedoStack().size();
    DrawingChange poppedUndoObj = obj.getUndoStack().peek();   
    errorUndo = obj.undo();
    DrawingChange pushedRedoObj = obj.getRedoStack().peek();
    if(errorUndo = true)
    {
      if(undoStackSize-1!=obj.getUndoStack().size())
      {
        errorUndoDetails = true;
      }
      if(redoStackSize+1!=obj.getRedoStack().size())
      {
        errorUndoDetails = true;
      }
      
      if(obj.getArray()[poppedUndoObj.row][poppedUndoObj.col] != poppedUndoObj.prevChar)
      {
        errorUndoDetails = true;
      }
      if(poppedUndoObj.row!=pushedRedoObj.row||poppedUndoObj.col!=pushedRedoObj.col||poppedUndoObj.prevChar!=pushedRedoObj.prevChar||poppedUndoObj.newChar!=pushedRedoObj.newChar)
      {
        errorUndoDetails = true;
      }
        
    }
    
    return !(errorUndoDetails&&errorUndo);
    
  }
    
    
}
  

