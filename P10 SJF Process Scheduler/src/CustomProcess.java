////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           Process Scheduler
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
public class CustomProcess implements Comparable<CustomProcess> {
  
  private static int nextProcessId = 1; // stores the id to be assigned to the next process
  //to be created
  private final int PROCESS_ID; // unique identifier for this process
  private int burstTime; // time required by this process for CPU executio
  
  /**
   * constructor for the CustomProcess class 
   * initializes burst time given the argument
   * checks if burst time is a positive integer
   * ProcessID is initialized using the value of nextProcessID
   * increments the value of nextProcessID by 1
   * @param burstTime
   *
   */
  
  public CustomProcess(int burstTime) {
    if(burstTime<=0) {
      throw new IllegalArgumentException("burst time is less than 1");
    }
      this.burstTime = burstTime;
      PROCESS_ID = nextProcessId;
      nextProcessId++;
  }
  
  /**
  * Returns a String representation of this CustomProcess
  * @return a string representation of this CustomProcess
  */
  public String toString() {
  return "p" + this.PROCESS_ID + "(" + this.burstTime + ")";
  }
  
  
  
  /**
   * Returns -1,1 or 0 depending on whether the burstTime of the argument is
   * greater or lesser to the burstTime of the object calling this method.
   * If burstTimes and Process_ID are equal, 0 is returned.
   */
  @Override  
public int compareTo(CustomProcess other) {
    if(burstTime<other.burstTime)
    {
      return -1;
    }
    else if(burstTime>other.burstTime)
    {
      return 1;
    }
    else {
      if(PROCESS_ID<other.PROCESS_ID)
      {
        return -1;
      }
      else if(PROCESS_ID>other.PROCESS_ID)
      {
        return 1;
      }
      else return 0;
  }
  }
  /**
   * accessor method for ProcessID
   * @return Process_ID
   */
  public int getProcessId() {
    return PROCESS_ID;
  }
  /**
   * accessor method for burstTime
   * @return burstTime
   */
  public int getBurstTime() {
    return burstTime;
  }  
   
}
