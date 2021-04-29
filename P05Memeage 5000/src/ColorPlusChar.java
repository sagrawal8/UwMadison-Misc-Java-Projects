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
public class ColorPlusChar extends Color{

  public ColorPlusChar(Color color, char character) {
    super(color);
    
    hideChar(character);
  }
  public ColorPlusChar(Color color) {
    super(color);
    
  } 
  // stores 8-bit character within the least significant bits of color components
  public void hideChar(char character) {
    FourBytes obj = new FourBytes(character);
    int blue = obj.getBits(2, 0);
    int green = obj.getBits(2, 2);
    int red = obj.getBits(2, 4);
    int alpha = obj.getBits(2, 6);
    
    
    super.setBits(2,0,blue);
    super.setBits(2,8,green);
    super.setBits(2,16,red);
    super.setBits(2,24,alpha);    
    
  }
  // retrieves 8-bit character from the least significant bits of color components
  public char revealChar() {
    
    FourBytes obj = new FourBytes('\0');
    obj.setBits(2, 0, getBits(2,0));
    obj.setBits(2, 2, getBits(2, 8));
    obj.setBits(2, 4, getBits(2,16));
    obj.setBits(2, 6, getBits(2,24));
    
    
    return obj.getChar();
    
    
  }
  
}
