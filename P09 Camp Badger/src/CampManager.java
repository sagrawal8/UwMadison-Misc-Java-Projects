import java.util.Iterator;
////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           Badger Camp
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
public class CampManager{
  private CamperBST campers;
  private final static String [] CABIN_NAMES = new String[]{
  "Otter Overpass", "Wolverine Woodland", "Badger Bunkhouse"};
  
  public CampManager() {
    campers = new CamperBST();
  }
  public void printStatistics() {
    System.out.println("Total Number of Campers are: "+ campers.size());
  }
  public Iterator<Camper> traverse(String order) {
    
    return campers.traverse(order);
  }
  
  
  // assigns cabin to new camper and calls insert method.
  public void enrollCamper(Camper newCamper) {
    int age = newCamper.getAge();
    if(age==8||age==9)
    {
      newCamper.assignCabin(CABIN_NAMES[0]);      
    }
    else if(age>=10&&age<=12)
    {
      newCamper.assignCabin(CABIN_NAMES[1]);      
    }
    else if(age==13||age==14)
    {
      newCamper.assignCabin(CABIN_NAMES[2]);
    }
    campers.insert(newCamper);
  }
  // calls delete method.
  public void unenrollCamper(Camper delCamper) throws java.util.NoSuchElementException{
    campers.delete(delCamper);
  }
}
