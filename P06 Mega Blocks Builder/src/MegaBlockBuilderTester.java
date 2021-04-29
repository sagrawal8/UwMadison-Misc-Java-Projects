import java.io.IOException;
////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           Megablock
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

public class MegaBlockBuilderTester {
  
  public static void main(java.lang.String[] args) throws IOException {
    //testMegaBlockEquals();
    //testMegaBlockToString();
    //testLinkedMegaBlock();
//    testLinkedMegaBlockListAddRed();
    //testLinkedListMegaBlockRemoveBlue();
  }

  public static boolean testMegaBlockEquals() {
    MegaBlock obj1 = new MegaBlock(Color.RED , '1');
    MegaBlock obj2 = new MegaBlock(Color.RED,'a');
    boolean error;
    if(obj1.equals(obj2))
      error = true;
    else error = false;    
    return error;
  }
  public static boolean testMegaBlockToString() {
    MegaBlock obj1 = new MegaBlock(Color.RED , '1');
    boolean error;
    if(obj1.toString().equals("RED 1"))
        {
        error = true;
        }
    else {
       error = false;
    }
    
    return error;
  }
  
  public static boolean testLinkedMegaBlock() {
    boolean error1; 
    boolean error2;
    
    LinkedMegaBlock obj1 = new LinkedMegaBlock(new MegaBlock(Color.BLUE, '1'));
    
    if(obj1.getBlock().equals(new MegaBlock(Color.BLUE, '1')))
      error1 = true;
    else error1 = false;
    obj1.setBlock(new MegaBlock(Color.YELLOW, '2'));
    if(obj1.toString().equals("YELLOW 2 -> "))
      error2 = true;
    else
      error2 = false;
   
    return error1&&error2;
    
  }
  
  
  public static boolean testLinkedMegaBlockListAddRed() {
    
    LinkedListMegaBlock obj = new LinkedListMegaBlock();
    MegaBlock redBlock = new MegaBlock(Color.RED,'j');    
    obj.addRed(redBlock);
    boolean error1 = true;;
    boolean error2 = true;
    if(obj.size()!=1)
      error1 = false;
    if(!obj.toString().equals("RED j -> END"))
      error2 = false;
    
    
    
    return error1&&error2;
  }
  
  public static boolean testLinkedListMegaBlockRemoveBlue() {
    boolean error1 = true;
    boolean error2 = true;
    LinkedListMegaBlock obj = new LinkedListMegaBlock();
    MegaBlock redBlock = new MegaBlock(Color.RED,'j');    
    obj.addRed(redBlock);
    MegaBlock blueBlock = new MegaBlock(Color.BLUE,'x');    
    obj.addBlue(blueBlock);
    MegaBlock yellowBlock = new MegaBlock(Color.YELLOW,'x');    
    obj.addYellow(1,yellowBlock);
    if(obj.size()!=3)
      error1 = false;
    
    System.out.println(obj.toString());
    obj.removeBlue();
    if(obj.size()!=2)
      error2 = false;
   
    if(!obj.toString().equals("RED j -> YELLOW x -> END"))
      error2 = false;
    
    return true;
    
    
  }

  
  
}
