import java.util.Scanner;
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
public class ProcessScheduler {
  private int currentTime; // stores the current time after the last run
  private int numProcessesRun; // stores the number of processes run so far
  private WaitingProcessQueue queue; // this processing unit’s queue
  
  /**
   * initializes queue to be a new queue
   * assigns currentTime to 0 and numProcesses run to 0.
   */
  public ProcessScheduler() {
    queue = new WaitingProcessQueue();
    currentTime = 0;
    numProcessesRun = 0;
  }
  
  /**
   * is called when a new process is inserted into the queue.
   * @param process
   */
  public void scheduleProcess(CustomProcess process) {
    queue.insert(process);    
  }
  
  /**
   * removesBest is called until the queue is empty. A string representation of
   * the elements are returned giving information on when the process starts and
   * is completed.
   * @return Strings containing a log of when the processes start and are completed.
   */
  public String run() {
    String str = "";
    str = "Starting " + queue.size() + " process\n\n";
    while(!queue.isEmpty()) {      
      CustomProcess obj = queue.removeBest();
      str = str + "Time " + currentTime + " : Process ID " + obj.getProcessId()+ " Starting.\n";
      currentTime+= obj.getBurstTime();
      numProcessesRun++;
      str = str+ "Time " + currentTime + " : Process ID " + obj.getProcessId()+ " Completed.\n";
      
    }
    str = str + "\nTime " + currentTime + ": All scheduled processes completed.\n";
    return str;
  }
  
  /**
   * Accessor method of getNumProcessesRun
   * @return numProcessesRun
   */
  private int getNumProcessesRun() {
    return numProcessesRun;
  }
  
  /**
   * Accessor method of getCurrentTime
   * @return currentTime
   */
  private int getCurrentTime() {
    return currentTime;
  }
  
  /**
   * Takes a scanner as input and displays the menu for the program. It calls
   * scheduleProcess or Run depending on the user input. The user can also choose
   * to quit the program.
   * @param scn
   */
  private static void driver(Scanner scn) {
    boolean isTerminated = false;
    ProcessScheduler schedule = new ProcessScheduler(); 
    System.out.print("========== Welcome to the SJF Process Scheduler App ======== \n\n");
    while (!isTerminated) {           
      System.out.print(ProcessScheduler.PROMPT_MENU());
      String cmd = scn.nextLine().trim();
      String[] str = cmd.split(" ");
      if(str[0].equals("schedule")||str[0].equals("s"))
      {
        if(isInt(str[1])) {
          CustomProcess obj = new CustomProcess(Integer.parseInt(str[1]));
          System.out.print("Process ID " + obj.getProcessId() + " scheduled. Burst Time = " + obj.getBurstTime() + "\n");
          schedule.scheduleProcess(obj);
          
        }
        else {
          System.out.print("WARNING: burst time MUST be an integer!\n\n");
          }
      }
      
      else if(cmd.equals("run")||cmd.equals("r")) {
        String log = schedule.run();
        System.out.print(log);
      }
      else if(cmd.equals("quit")||cmd.equals("q")) {
        System.out.print(schedule.getNumProcessesRun() + " processes run in " + schedule.getCurrentTime() + " units of time!\n" +
            "Thank you for using our scheduler!\n" + "Goodbye!\n");
        isTerminated = true;
      }
      else System.out.print("WARNING: Please enter a valid command!\n\n");
      
    }
  }
  
  public static void main(String args[]) {
    Scanner scn = new Scanner(System.in);    
    driver(scn);
  }
  
  /**
   * private static method that displays the commands available to the user.
   * @return A string representation of the available commands.
   */
  private static String PROMPT_MENU() {
    String str = "\nEnter command:\n"+"[schedule <burstTime>] or [s <burstTime>]\n" +
   "[run] or [r]\n" + "[quit] or [q]\n\n";  
    return str;
  }
  
  /**
   * public static method that checks whether the user input is an integer.
   * @param str
   * @return true if integer, false if not.
   */
  public static boolean isInt(String str)
  {
      try
      {
          Integer.parseInt(str);
          return true;
      } catch (NumberFormatException e)
      {
          return false;
      }
  }
  

}
