import java.util.LinkedList;
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

public class LinkedListMegaBlock {

  private LinkedMegaBlock head; // head of this list
  private LinkedMegaBlock tail; // tail of this list
  private int size; // size of this list (total number of megablocks stored in this list)
  private int redCount; // number of RED megablocks stored in this list
  private int yellowCount; // number of YELLOW megablocks stored in this list
  private int blueCount; // number of BLUE megablocks stored in this list

  //initializes all variables and sets head to null
  public LinkedListMegaBlock() {
    new LinkedList<LinkedMegaBlock>();
    size = 0;
    redCount = 0;
    yellowCount = 0;
    blueCount = 0;
    head = null;

  }

  //checks if list is empty by checking head and tail with null
  public boolean isEmpty() {
    if (head == null && tail == null) {
      return true;
    } else {
      return false;
    }
  }

  //returns the size (getter)
  public int size() {
    return size;
  }

  //clears the list by resetting all variables (similar to constructor)
  public void clear() {

    head = null;
    tail = null;
    size = 0;
    redCount = 0;
    yellowCount = 0;
    blueCount = 0;

  }

  //adds blueBlock, checks if blue is first node in addition to adding it. increments size and bluecount
  public void addBlue(MegaBlock blueBlock) {

    if (blueBlock == null) {
      throw new IllegalArgumentException("blue block is null");
    }
    if (blueBlock.getColor() != Color.BLUE) {
      throw new IllegalArgumentException("blue block is not blue");
    }
    LinkedMegaBlock obj1 = new LinkedMegaBlock(blueBlock);
    if (head != null) {
      tail.setNext(obj1);
      tail = obj1;

    } else {
      head = obj1;
      tail = obj1;
    }

    size++;
    blueCount++;

  }

  //adds redBlock, checks if red is the only node in addition to adding it. increments size and bluecount
  public void addRed(MegaBlock redBlock) {
    LinkedMegaBlock obj1;
    if (redBlock == null) {
      throw new IllegalArgumentException("red block is null");
    }
    if (redBlock.getColor() != Color.RED) {
      throw new IllegalArgumentException("red block is not red");
    }
    if (head == null) {
      obj1 = new LinkedMegaBlock(redBlock);
    } else {
      obj1 = new LinkedMegaBlock(redBlock, head);
    }

    if (tail != null) {
      head = obj1;
    } else {

      head = obj1;
      tail = obj1;
    }

    size++;
    redCount++;
  }

  //checks if yellowBlock is the only node in the list. checks the value of index and gets the corresponding next.
  public void addYellow(int index, MegaBlock yellowBlock) {

    if (yellowBlock == null) {
      throw new IllegalArgumentException("Yellow block is null");
    }
    if (yellowBlock.getColor() != Color.YELLOW) {
      throw new IllegalArgumentException("Yellow block is not yellow");
    }
    LinkedMegaBlock obj1 = new LinkedMegaBlock(yellowBlock);
    if (index < redCount || index > size - blueCount) {
      throw new IndexOutOfBoundsException("index out of range for yellow blocks");
    }

    if (head == null) {
      head = obj1;
      tail = obj1;
    } else if (index != size && index != 0) {
      LinkedMegaBlock nextObj = getLinkedBlock(index);

      obj1.setNext(nextObj);
      LinkedMegaBlock prevObj = getLinkedBlock(index - 1);

      prevObj.setNext(obj1);

    } else if (index == size) {
      LinkedMegaBlock prevObj = getLinkedBlock(index - 1);

      prevObj.setNext(obj1);
      tail = obj1;
    } else if (index == 0) {
      LinkedMegaBlock nextObj = getLinkedBlock(index);
      obj1.setNext(nextObj);
      head = obj1;
    }

    size++;
    yellowCount++;

  }

  //gets the LinkedMegaBlock for the node at 'index' using a for loop 

  private LinkedMegaBlock getLinkedBlock(int index) {
    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException("index out of range");
    }
    LinkedMegaBlock obj = null;
    if (head != null) {
      obj = head;
      for (int i = 0; i < index; i++) {
        obj = obj.getNext();
      }
    }
    return obj;
  }

  //similar as previous method but returns the Megablock.
  public MegaBlock get(int index) {

    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException("index out of range");
    }
    LinkedMegaBlock obj = null;
    if (head != null) {
      obj = head;
      for (int i = 0; i < index; i++) {
        if (obj.getNext() == null) {
          return null;
        }
        obj = obj.getNext();
      }
    }
    return obj.getBlock();
  }

  //sets Megablock by replacing a block of same color at 'index'
  public MegaBlock set(int index, MegaBlock block) {

    if (block == null || !block.equals(get(index))) {
      throw new IllegalArgumentException("object is null or object is not equal to current block");
    }
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException("index out of range");
    }

    MegaBlock obj = get(index);
    obj.setLabel(block.getLabel());
    return obj;

  }

  //removes first red block from the head and changes head.
  public MegaBlock removeRed() {
    MegaBlock obj = get(0);
    if (obj.getColor().equals(Color.RED)) {
      if (size != 1) {
        head = getLinkedBlock(1);
      } else {
        head = null;
        tail = null;
      }

      size--;
      redCount--;
      return obj;
    } else {
      throw new java.util.NoSuchElementException("No Red Mega Blocks");
    }

  }

  //removes blue block from the end and changes the tail
  public MegaBlock removeBlue() {
    MegaBlock obj = get(size - 1);

    if (obj.getColor().equals(Color.BLUE)) {
      if (size - 1 != 0) {
        tail = getLinkedBlock(size - 2);
        tail.setNext(null);
      } else {
        tail = null;
        head = null;
      }

      size--;
      blueCount--;
      return obj;
    } else {
      throw new java.util.NoSuchElementException("No Blue Mega Blocks");
    }

  }

  //removes yellow block and changes the next of the block before it to the block after it.
  public MegaBlock removeYellow(int index) {
    if (index < redCount || index > size - blueCount) {
      throw new IndexOutOfBoundsException("index out of range for yellow blocks");
    }
    MegaBlock obj = get(index);
    if (!obj.getColor().equals(Color.YELLOW)) {
      throw new java.util.NoSuchElementException("No Yellow Mega Blocks");
    } else {
      if (index - 1 >= 0 && index + 1 != size) {
        LinkedMegaBlock objPrev = getLinkedBlock(index - 1);
        objPrev.setNext(getLinkedBlock(index + 1));
      } else if (index + 1 == size) {
        LinkedMegaBlock objPrev = getLinkedBlock(index - 1);
        objPrev.setNext(null);
        tail = objPrev;
      } else if (index == 0) {
        head = getLinkedBlock(index + 1);
      }

      size--;
      yellowCount--;
      return obj;

    }
  }

  //next 3 methods return the counts of the diff colors.

  public int getRedCount() {
    return redCount;
  }

  public int getYellowCount() {
    return yellowCount;
  }

  public int getBlueCount() {
    return blueCount;
  }


  public java.lang.String toString() {
    String str = "";
    if (head == null) {

      return (str);
    } else {
      LinkedMegaBlock obj = head;
      str = str + obj.toString();
      if (size != 1) {
        do {
          obj = obj.getNext();
          str = str + obj.toString();
        } while (obj.getNext() != null);
      } else if (size == 1) {
        str += "END";
      }
    }
    return str;
  }
}
 
 


  
  


  
  
  
  
  
 

