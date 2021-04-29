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
  
public class LinkedMegaBlock {

  private MegaBlock block; // data field of this linkedMegaBlock
  private LinkedMegaBlock next; // link to the next linkedMegaBlock
  
  
  //initializes megablock only as next isnt provided in parameters
  
  public LinkedMegaBlock(MegaBlock block){
    this.block = block;
    this.next = null;
  }
  //initializes block and next as both are provided
  public LinkedMegaBlock(MegaBlock block,
      LinkedMegaBlock next) {
    this.block = block;
    this.next = next;
    
  }
  
  //returns block(accessor method)
  public MegaBlock getBlock() {
    return block;
  }
  
  //sets block (mutator)
  public void setBlock(MegaBlock block) {
    this.block = block;
  }
  
  //gets the next value as its private(accessor)
  public LinkedMegaBlock getNext() {
    return next;
  }
  //sets the next as its private (mutator)
  public void setNext(LinkedMegaBlock next) {
    this.next = next;
  }
  //returns the block with an arrow after it or an arrow after it and the text "END" if its the last block
  public java.lang.String toString(){
    String blockStr="";
    if(next != null)
      blockStr = block.toString() + " -> ";
    else 
      blockStr = block.toString() + " -> END";
    
    return blockStr;
    
  }
}
