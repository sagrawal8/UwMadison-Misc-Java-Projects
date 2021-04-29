import java.io.File;
import java.io.IOException;

public class MemeageTests {
  
  
  public static void main(java.lang.String[] args) throws IOException {
    // testFourBytesGetBits();
    // testFourBytesSetBits();
     //testColor();
    testImage();
    //testColorPlusChar();
  }

  public static boolean testFourBytesGetBits() {
    FourBytes obj = new FourBytes(0);
    boolean value;
    obj.setBits(4, 10, 13);
    int x = obj.getBits(4,10);
    if (x==13)
        value = true;
    else
      value = false;    
    return value;
    
    
  }
  public static boolean testFourBytesSetBits() {
    
    boolean value;
    FourBytes obj = new FourBytes(0);
    obj.setBits(3, 8, 13);
    int x = obj.getInt();
    if (x==1280)
      value = true;
    else
      value = false;    
    return value;
    
  }
  
  public static boolean testColor() {
    
    Color obj;
    boolean errorConstructor;
    boolean errorMutator;
    boolean errorAccessor;
    obj = new Color(1280);    
    int x = obj.getARGB();    
    if(x==1280)
      errorConstructor = true;
    else 
      errorConstructor = false;    
    obj.setBlue(15);
    int y = obj.getInt();
    if(y==1295)
      errorMutator = true;
    else
      errorMutator = false;    
    int z = obj.getBlue();
    if(z==15)
      errorAccessor = true;
    else
      errorAccessor = false;   
        
    return errorConstructor&&errorMutator&&errorAccessor;
      
  }
  
  public static boolean testImage() throws IOException {
    boolean error1=true;
    boolean error2=true;
    boolean error3=true;
    Image obj;
    try {
      obj = new Image(new File("testImage.png"));      
      if(obj.getHeight()==2&&obj.getWidth()==3)
        error1 = true;
      else error1 = false;
      Color obj2 = new Color(obj.getColor(1, 1));    
      if(obj2.getRed()==0&&obj2.getGreen()==255&&obj2.getBlue()==255)
        error2 = true;
      else
        error2 = false; 
    }
    catch (IOException e)
    {
      error3 = false;
    }
    
    
   return error1&&error2&&error3;
   
    
  }
  
  public static boolean testColorPlusChar() {
    
    boolean error;
    char ch = 'H';
    Color obj = new Color(1280);
    ColorPlusChar obj1 = new ColorPlusChar(obj,ch);
    if(obj1.revealChar()=='H')
      error = true;
    else error = false;
    System.out.print(error);
    return error;
    
  }
        
    
 
  
  
  
  
  
}
