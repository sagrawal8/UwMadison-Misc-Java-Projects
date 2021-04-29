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
public class ProcessSchedulerTester {
  public static void main(String args[]) {
    testInsertWaitingProcessQueue();
    testRemoveBestWaitingProcessQueue();
  }
  //checks the correctness of the insert operation
  //implemented in the WaitingProcessQueue class
  public static boolean testInsertWaitingProcessQueue(){
       boolean error = false; 
    WaitingProcessQueue obj = new WaitingProcessQueue();
    obj.insert(new CustomProcess(10));
    obj.insert(new CustomProcess(2));
    obj.insert(new CustomProcess(5));
    obj.insert(new CustomProcess(3));
    obj.insert(new CustomProcess(1));
    
    if("p5(1) p2(2) p3(5) p1(10) p4(3)".equals(obj.toString()))
      error = true;
      else {
        error = false;
      }
    System.out.print(error);
    
    return error;
  }
  //checks the correctness of the removeBest operation
  //implemented in the WaitingProcessQueue class
  public static boolean testRemoveBestWaitingProcessQueue(){
    boolean error = false; 
    WaitingProcessQueue obj = new WaitingProcessQueue();
    obj.insert(new CustomProcess(5));
    obj.insert(new CustomProcess(4));
    obj.insert(new CustomProcess(10));
      
    CustomProcess obj2 = obj.removeBest();
    if(obj2.getBurstTime()==4)
      error = true;
    obj2 = obj.removeBest();
    if(obj2.getBurstTime()==5)
      error = true;
    obj2 = obj.removeBest();
    if(obj2.getBurstTime()==10)
      error = true;
    return error;
  }
}
