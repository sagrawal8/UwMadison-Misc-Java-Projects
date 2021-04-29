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
public class Color extends FourBytes {

  
  public Color(int argb) {
    super(argb);    
    
  }
  public Color(int alpha, int red, int green, int blue) {
    super(0);
    setAlpha(alpha);
    setRed(red);
    setGreen(green);
    setBlue(blue);
    
    
  }
  public Color(Color other) {
    
    super(other.getARGB());
    
  }
  public int getARGB() {
    
    int x = super.getInt();
    return x;
    
  }
  public int getAlpha() {
    
    int x = super.getBits(8, 24);
    return x;
    
  }
  public int getRed() {
    
    int x = super.getBits(8, 16);
    return x;
    
  }
  public int getGreen() {
    
    int x = super.getBits(8, 8);
    return x;
    
  }
  public int getBlue() {
    
    int x = super.getBits(8, 0);
    return x;
    
  }
  public void setARGB(int argb) {
    
    super.setBits(24, 24, argb);
    
  }
  public void setAlpha(int alpha) {
    
    super.setBits(8, 24, alpha);
    
  }
  public void setRed(int red) {
    
    super.setBits(8, 16, red);
    
  }
  public void setGreen(int green) {
    
    super.setBits(8, 8, green);
    
  }
  public void setBlue(int blue) {
    
    super.setBits(8, 0, blue);
    
  }


}
