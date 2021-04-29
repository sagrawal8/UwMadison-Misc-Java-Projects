import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/* Name: Shashank Agrawal
 * Email: sagrawal8@wisc.edu
 * Lecture: 001
 * My program prints a calender for any month and year. 
 * The user has 2 options. Either to use a file to input the month and year
 * or manually enter the same.
 */

public class Main {
  
  
  /*
   * Converts the 2D string array to a single string.
   * @param 2D string array
   */
  public static String charArrayToString(String [][] outputContent) {
    String output = "";
    for(int i=0;i<outputContent.length;i++)
    {    
        for(int j=0;j<outputContent[i].length;j++)
        {
          output+=(outputContent[i][j]+"\t");
        }
        output+="\n";
     }
    return output;
  }
  
  /*
   * Uses printwriter to write a string into an output file named 'output.txt'
   * @param a String s
   */
  public static void outputToFile(String s) throws IOException
  {
    FileWriter fileWriter = new FileWriter("output.txt");
    PrintWriter printWriter = new PrintWriter(fileWriter);
    printWriter.print(s);
    printWriter.close();
  }
  
  
 public static String ch;    //Used to check do while loop condition

public static void main(String[] args) throws Exception { 
            
    do {
      
      int repeat = 0;   //checks condition for user input for char variable 'ch'
      Scanner input = new Scanner(System.in); //Scanner variable
      Scanner sc;     
      
     
      System.out.print("Do you want to use a file for input? Type"
          + " 1 for Yes and 2 for No: ");
            int userInput = input.nextInt();
            if(userInput==1)
            {
              System.out.print("Enter file name: ");              
              String fileName = input.next().trim();
              System.out.println(fileName);
              File file = new File(fileName);
              try{
                  sc = new Scanner(file);  
                  repeat = 1;
                  System.out.println("1) Type 1 for Calender");
                  System.out.println("2) Type 2 to Exit");
                
                  String x = sc.next();
                  if(x.equals("1"))
                  {
                    CalendarPrinter obj = new CalendarPrinter();
                    String [][] outputCharArray = obj.CalenderPrint(sc); 
                    String outputString = charArrayToString(outputCharArray);
                    outputToFile(outputString);
                  }

                  if(x.equals("2"))
                  {
                    System.out.println("Thank you for playing.");
                    break;
                  }
                  System.out.print("Type 'e' or 'E' to exit and anything else to start again: ");
                  if(repeat == 1)
                    ch=sc.next();
                }
              catch(IOException e) {
                System.out.println("Enter valid file name");
                continue;
              }
              catch(Exception e) {
                System.out.println("Inputs from file not valid");
              }
            }
            
            else {
              System.out.println("1) Type 1 for Calender");
              System.out.println("2) Type 3 to Exit");
            
              int x = input.nextInt();
              if(x==1)
              {
                CalendarPrinter obj = new CalendarPrinter();                  
                String [][] outputCharArray = obj.CalenderPrint(input); 
                String outputString = charArrayToString(outputCharArray);
                outputToFile(outputString);
              }             
              if(x==2)
              {
                System.out.println("Thank you for playing.");
                break;
              } 
              System.out.print("Type 'e' or 'E' to exit and anything else to start again: ");
              if(repeat==0)
                ch = input.next();
              
            }          
    
  if(ch.equals("e")||ch.equals("E"))
    {
      System.out.println("Thank you, have a nice day.");
      break;
    }
  }while(!"e".equals(ch)||!"E".equals(ch));
  }


}
