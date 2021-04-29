import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
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
public class CampEnrollmentApp {

  public static void main(String[] args) throws IOException, java.util.NoSuchElementException {
    CampManager manager = new CampManager();
    try {
      List <String> fileLines = Files.readAllLines(Paths.get("./sim.txt"));
      for(String lines : fileLines) 
      {
        String linesTrimmed = lines.trim();
        String[] arr = linesTrimmed.split(" ");
        if (arr[0].equals("S"))
        {
          manager.printStatistics();
        }
        else if(arr[0].equals("E"))
        {
          Camper newCamper = new Camper(arr[2],arr[1],Integer.parseInt(arr[3]));                        
          manager.enrollCamper(newCamper);
        }
        else if(arr[0].equals("R"))
        {
          Camper newCamper = new Camper(arr[2],arr[1],9);                        
          try{
            manager.unenrollCamper(newCamper);  
          }
          catch(java.util.NoSuchElementException e) {
            System.out.println("That camper not enrolled.");
          }
        }
        else if(arr[0].equals("T"))
        {
          manager.traverse(arr[1]);
        }
      }
    }
    catch(IOException e) {
      System.out.print("File not found");
    }
    catch(IllegalArgumentException e)
    {
      System.out.println("Camper is too old or young to be at Camp Badger.");
    }
    
    
  }

}
