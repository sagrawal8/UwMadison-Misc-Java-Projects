import java.util.Scanner;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (descriptive title of the program making use of this file)
// Files:           (a list of all source files used by that program)
// Course:          (course number, term, and year)
//
// Author:          (your name)
// Email:           (your @wisc.edu email address)
// Lecturer's Name: (name of your lecturer)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
public class CalendarTester {
  
  
  public static void main(String[] args) {    
    testGetCentury();
    testGetIsLeapYear();
    testGetYearWithinCentury();
    testGetYearWithinCentury();
    testGetMonthIndex();
    testGetNumberOfDaysInMonth();
    testGetFirstDayOfWeekInMonth();
    }

  public static boolean testGetCentury() {
    
    boolean error=false;
    if(CalendarPrinter.getCentury("2100") != 21) error=true; 
    if(CalendarPrinter.getCentury("2019") != 20) error=true;
    if(CalendarPrinter.getCentury("44444") != 444) error=true;
    return error;      
   }
  
  public static boolean testGetIsLeapYear() {
    boolean error = true;
    if(CalendarPrinter.getIsLeapYear("2100") != false) error=false;
    if(CalendarPrinter.getIsLeapYear("1998") != false) error=false;
    if(CalendarPrinter.getIsLeapYear("2000") == false) error=false;
    return error; 
    }  
   
  
    public static boolean testGetYearWithinCentury() {
      boolean error = false;
      if(CalendarPrinter.getYearWithinCentury("2111")!= 11) error = true; 
      if(CalendarPrinter.getYearWithinCentury("2011")!= 11) error = true;
      if(CalendarPrinter.getYearWithinCentury("1997")!= 97) error = true;
      return error;  
    
    }
    
    public static boolean testGetMonthIndex() {
      boolean error = false;
      if(CalendarPrinter.getMonthIndex("March")!= 2) error = true;
      if(CalendarPrinter.getMonthIndex("FEBBBB")!= 1) error = true;
      if(CalendarPrinter.getMonthIndex("bleh")!= -1) error = true;
      return error;  
    
    }
    
    
    public static boolean testGetNumberOfDaysInMonth() {
      boolean error = false;
      if(CalendarPrinter.getNumberOfDaysInMonth("March", "2020")!= 31) error = true;
      if(CalendarPrinter.getNumberOfDaysInMonth("FEBBBB", "2100")!= 28) error = true;
      if(CalendarPrinter.getNumberOfDaysInMonth("FEB", "2001")!= 28) error = true;
      return error;  
    
    }
    
    public static boolean testGetFirstDayOfWeekInMonth() {
      boolean error = false;
      if(CalendarPrinter.getFirstDayOfWeekInMonth("Jan", "2019")!= 1) error = true;
      if(CalendarPrinter.getFirstDayOfWeekInMonth("Feb", "2100")!= 0) error = true;
      if(CalendarPrinter.getFirstDayOfWeekInMonth("Feb", "2020")!= 5) error = true;
      return error; 
    
    }
    
  
  
  
}
