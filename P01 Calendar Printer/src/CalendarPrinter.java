import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math; 

public class CalendarPrinter {
  
  private final static String[] DAYS_OF_WEEK = {"MON", "TUE", "WED", "THU", "FRI",
      "SAT", "SUN"};
  private final static String[] MONTHS_OF_YEAR = {"JAN", "FEB", "MAR", "APR", "MAY",
      "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

  /**
  * Calculates the number of centuries (rounded down) that is represented by
  * the specified year (ie. the integer part of year/100).
  * @param year to compute the century of (based on the Gregorian Calendar AD)
  * String must contain the digits of a single non-negative int for year.
  * @return number of centuries in the specified year
  */
  public static int getCentury(String year)
  {
    return(Integer.parseInt(year)/100);
  }
  
  /**
  * Calculates the number of years between the specified year, and the first
  * year in the specified year's century. This number is always between 0 - 99.
  * @param year to compute the year within century of (Gregorian Calendar AD)
  * String must contain the digits of a single non-negative int for year.
  * @return number of years since first year in the current century
  */
  public static int getYearWithinCentury(String year) 
  {
    int century = getCentury(year);
    return (Integer.parseInt(year)-((century)*100));
  }
  
  /**
  * This method computes whether the specified year is a leap year or not.
  * @param yearString is the year that is being checked for leap-year-ness
  * String must contain the digits of a single non-negative int for year.
  * @return true when the specified year is a leap year, and false otherwise
  */
  public static boolean getIsLeapYear(String yearString) 
  {
    boolean value=true;
    int year = Integer.parseInt(yearString);
    
    if(year % 4 != 0)
    {
      value=false;
    }
      else if (year % 100!=0)
      {
        value = true;
      }
      else if(year % 400 != 0)
        {
        value=false;
        }
    
    else value=true;
    
    return value;
    
    
  }
  
  /**
  * Converts the name or abbreviation for any month into the index of that
  * month's abbreviation within MONTHS_OF_YEAR. Matches the specified month
  * based only on the first three characters, and is case in-sensitive.
  * @param month which may or may not be abbreviated to 3 or more characters
  * @return the index within MONTHS_OF_YEAR that a match is found at
  * and returns -1, when no match is found
  */
  public static int getMonthIndex(String month) 
  {
    String upperCaseMonth = month.toUpperCase();
    
    int index = -1;
    for(int i=0;i<MONTHS_OF_YEAR.length;i++)
    {
      if(upperCaseMonth.startsWith(MONTHS_OF_YEAR[i]))
        {
          index=i;
          break;
        }
        
    }
   
   
    return (index);
    
    
  }
  
  /**
  * Calculates the number of days in the specified month, while taking into
  * consideration whether or not the specified year is a leap year.
  * @param month which may or may not be abbreviated to 3 or more characters
  * @param year of month that days are being counted for (Gregorian Calendar AD)
  * String must contain the digits of a single non-negative int for year.
  * @return the number of days in the specified month (between 28-31)
  */
  public static int getNumberOfDaysInMonth(String month, String year) 
  {
    int days [] = {31,28,31,30,31,30,31,31,30,31,30,31};
    int indexOfMonth = getMonthIndex(month);
    if(indexOfMonth==1 && getIsLeapYear(year)==true)
      {
        return 29;
      }
    else if (indexOfMonth==1 && getIsLeapYear(year)==false)
    {
      return 28;
    }
    else return days[indexOfMonth];
  }
  
  /**
  * Calculates the index of the first day of the week in a specified month.
  * The index returned corresponds to position of this first day of the week
  * within the DAYS_OF_WEEK class field.
  * @param month which may or may not be abbreviated to 3 or more characters
  * @param year of month to determine the first day from (Gregorian Calendar AD)
  * String must contain the digits of a single non-negative int for year.
  * @return index within DAYS_OF_WEEK of specified month's first day
  */
  public static int getFirstDayOfWeekInMonth(String month, String year) 
  {
    
    int m = getMonthIndex(month)+1;
    if(m==1)
      m=13;
    if(m==2)
      m=14;
        
    int k = getYearWithinCentury(year); 
    if(m==13||m==14)
      {
        if(k==0)
          k=99;
        else k-=1;
      }
    
    int j = getCentury(year);
    if(m==13||m==14)
      {
        if(getYearWithinCentury(year)==0)
        j = getCentury(year)-1;
      }   
    
    int dayOfWeek = -1;
    dayOfWeek = (1 + (int)((13.0/5.0)*(m+1)) + k + (int)(k/4.0) + (int)(j/4.0) + (5*j))%7;
    
    if(dayOfWeek==0)
      dayOfWeek=5;
    else if(dayOfWeek==1)
      dayOfWeek=6;
    else dayOfWeek-=2;
    
    
    
    return dayOfWeek;
    
  
  }
  
  
  /**
  * Creates and initializes a 2D String array to reflect the specified month.
  * The first row of this array [0] should contain labels representing the days
  * of the week, starting with Monday, as abbreviated in DAYS_OF_WEEK. Every
  * later row should contain dates under the corresponding days of week.
  * Entries with no corresponding date in the current month should be filled
  * with a single period. There should not be any extra rows that are either
  * blank, unused, or completely filled with periods.
  * For example, the contents for September of 2019 should look as follows,
  * where each horizontal row is stored in different array within the 2d result:
  *
  * MON TUE WED THU FRI SAT SUN
  * . . . . . . 1
  * 2 3 4 5 6 7 8
  * 9 10 11 12 13 14 15
  * 16 17 18 19 20 21 22
  * 23 24 25 26 27 28 29
  * 30 . . . . . .
  *
  * @param month which may or may not be abbreviated to 3 or more characters
  * @param year of month generate calendar for (Gregorian Calendar AD)
  * String must contain the digits of a single non-negative int for year.
  * @return 2d array of strings depicting the contents of a calendar
  */
  public static String[][] generateCalendar(String month, String year){
    
    String[][] calender = new String [7][7]; 
    
    calender[0] = DAYS_OF_WEEK;
    int row = 1;
    int indexOfFirstDayInMonth = getFirstDayOfWeekInMonth(month,year);
    for(int i = 0; i < indexOfFirstDayInMonth; i++)
      calender[row][i]=".";
    int k = 1;                        //day of the month  
    int n = 1;                        //row counter  
    int m = indexOfFirstDayInMonth;   //column counter
    
    
    while(k <= getNumberOfDaysInMonth(month,year))
    {
      if(m==7)
      {
        m=0;
        n++;        
      }
      calender[n][m] = Integer.toString(k);
      k++;
      m++;
    }
    
    for(int i=0;i<calender.length;i++)
    {
      for(int j=0; j< calender[0].length;j++)
      {
        if(calender[i][j]==null)
        {
          calender[i][j]=".";
        }
      }
    }
    return calender; 
     
    
  }
  
  public static String[][] CalenderPrint(Scanner input) {
    System.out.println("Welcome to the Calender Printer.");
    System.out.println("================================");    
    String s = "";
    int flag = 1;
    String month="";
    while(flag==1)
    {
      System.out.println("Enter first 3 letters in uppercase of month to print: ");
      month = input.next(); 
      for(int i = 0 ; i < MONTHS_OF_YEAR.length; i++)
      {
        if (month.equals(MONTHS_OF_YEAR[i]))
            flag = 0;        
      }
      if(flag!=0)
      {
        System.out.println("Month is not valid");
      }
      
    }
      s = month + "\n"; 
        
//    if(input.hasNextLine());
//    input.nextLine();
    
    System.out.println("Enter the year to print: ");
    String year = input.next(); 
    s+= year + "\n";
    
    
     
    String[][] calendar = generateCalendar(month,year);
    
    for(int i=0;i<calendar.length;i++)
    {    
        for(int j=0;j<calendar[i].length;j++)
        {
          System.out.print(calendar[i][j]+"\t");
        }
        System.out.println();
     }      
    
  System.out.println("================================");
  return calendar;
  }
    
 
}
 
  
  

