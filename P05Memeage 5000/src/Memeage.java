import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Memeage extends Image {
////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           Memeage 5000
//Files:           (a list of all source files used by that program)
//Course:          CS300
//
//Author:          Shashank Agrawal
//Email:           sagrawal8@wisc.edu
//Lecturer's Name: Gary Dahl
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//Partner Name:    (name of your pair programming partner)
//Partner Email:   (email address of your programming partner)
//Partner Lecturer's Name: (name of your partner's lecturer)
//
//VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//___ Write-up states that pair programming is allowed for this assignment.
//___ We have both read and understand the course Pair Programming Policy.
//___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully 
//acknowledge and credit those sources of help here.  Instructors and TAs do 
//not need to be credited here, but tutors, friends, relatives, room mates, 
//strangers, and others do.  If you received no outside help from either type
//of source, then please explicitly indicate NONE.
//
//Persons:         (identify each person and describe their help in detail)
//Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE /////////////////////////////// 
  
  public Memeage(File file) throws IOException{
    super(file);
  }
  public Memeage(File file, String meme) throws IOException, IllegalArgumentException{
    super(file);
    setMeme(meme);
  }
  public void setMeme(String meme) throws IllegalArgumentException{
    char[] chars = meme.toCharArray();
    int count = 0;
    int i;
    int j = 0;
    
    if(chars.length>((super.getWidth()*super.getHeight())-1))
    {
      throw new IllegalArgumentException("characters exceed colors");
    }
  
      
    for(i = 0; i<super.getHeight();i++ )
    {
      for(j = 0; j<super.getWidth(); j++)
      {
        if(chars[count]>127)
        {
          throw new IllegalArgumentException("Character not ASCII");
        }
        ColorPlusChar obj = new ColorPlusChar(super.getColor(j,i),chars[count]);
        super.setColor(j,i,obj);
        if(count++>=chars.length)
        {
          break;
        }
        count++;
      }
      if(count++>=chars.length)
      {
        break;
      }
    }
    ColorPlusChar obj2 = new ColorPlusChar(super.getColor(j,i),'\0');
    super.setColor(j, i, obj2);
    
  }
  public String getMeme() throws IllegalStateException{
  String s = ""; 
  boolean nullChar = false;
  for(int i = 0; i < super.getHeight();i++ )
  {
    for(int j = 0; j<super.getWidth();j++)
    {
      ColorPlusChar obj = new ColorPlusChar(super.getColor(j,i));
      char ch = obj.revealChar();
      if(nullChar == false)
        if(ch=='\0')
        {
          nullChar=true;
          break;
        }
      if(ch>127)
      {
        throw new IllegalStateException("chracter not ASCII");
      }
      s=s+ch;
    }
    if(nullChar == true)
      break;
  }
  return s;
  
  
  }

}
