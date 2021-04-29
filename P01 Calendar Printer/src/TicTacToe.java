import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {

    
    private static char[][] board = new char[3][3];
    private final static char x = 'X';
    private final static char o = 'O';
    private static int moveCount = 0;
    String win = null;
    
    public TicTacToe()
    {
      for(int i=0;i<3;i++)
      {
        for(int j=0;j<3;j++)
        {
          board[i][j]='_';
        }
      }
      moveCount=0;  
         
    }
    
    private static void displayCurrentBoard() {    
      System.out.println("Current Board");
      System.out.println("  1 2 3");
      for(int i = 0 ; i<3 ; i++)
      {
        System.out.print(i+1+" ");
        for(int j = 0; j<3; j++)
        {
          System.out.print(board[i][j] + " ");
          
        }
        System.out.println();
      }
    }
   
    private static void turnPlayer1(Scanner input) throws Exception {
      int row,column;
      int flag = 0;
      while(flag==0) {
      try{
        System.out.println("Player 1's turn.");
        System.out.print("Enter row to place token in: ");
        row = Integer.parseInt(input.nextLine());
        System.out.print("Enter column to place token in: ");
        column = Integer.parseInt(input.nextLine());
        if(row<1||row>3||column<1||column>3)
        {
          System.out.println("Row and column should be between 1 and 3");
          throw new InputMismatchException("Number is not between 1 and 9");
        }
        if(board[row-1][column-1]!='_')
        {
          throw new Exception ("Choose another place");
        }
        
        else {
          flag = 1;       
          board[row-1][column-1] = x;
        }
      }    
      catch(InputMismatchException e) {
        continue;
      }
      catch(Exception e) {
        continue;
      }
      }
      
    }
    private static void turnPlayer2(Scanner input) throws Exception {
      System.out.print("Enter row and column to place token in: ");
      int row,column;
      int flag = 0;
      while(flag==0) {
      try{
        System.out.println("Player 2's turn.");
        System.out.print("Enter row to place token in: ");
        row = Integer.parseInt(input.next());
        System.out.print("Enter column to place token in: ");
        column = Integer.parseInt(input.next());
        if(row<1||row>3||column<1||column>3)
          {
            System.out.println("Row and column should be between 1 and 3");
            throw new InputMismatchException();          
          }
        if(board[row-1][column-1]!='_')
        {        
          throw new Exception ("Choose another place");
        }      
        else {
          flag = 1;        
          board[row-1][column-1] = o;
        }
      }    
      catch(InputMismatchException e) {
        continue;
      }
      catch(Exception e) {
        continue;
      }
      }
      
    }
    
    private static String checkWin() {
      String flag = "";
      for(int i=0; i<8; i++)
      {      
        String win = "";
        switch (i) {
          case 0: win = Character.toString(board[0][0])+Character.toString(board[0][1])+Character.toString(board[0][2]);        
                  break;
          case 1: win = Character.toString(board[1][0])+Character.toString(board[1][1])+Character.toString(board[1][2]);                
                  break;
          case 2: win = Character.toString(board[2][0])+Character.toString(board[2][1])+Character.toString(board[2][2]);                
                  break;
          case 3: win = Character.toString(board[0][0])+Character.toString(board[1][0])+Character.toString(board[2][0]);
                  break;
          case 4: win = Character.toString(board[0][1])+Character.toString(board[1][1])+Character.toString(board[2][1]);
                  break;
          case 5: win = Character.toString(board[0][2])+Character.toString(board[1][2])+Character.toString(board[2][2]);
                  break;
          case 6: win = Character.toString(board[0][0])+Character.toString(board[1][1])+Character.toString(board[2][2]);                
                  break;
          case 7: win = Character.toString(board[2][0])+Character.toString(board[1][1])+Character.toString(board[0][2]);
                  break;
        }
        if(win.equals("XXX")||win.equals("OOO"))
          {
            flag = "Win";
            return flag;
          }      
      }
      if((!flag.equals("Win")) && moveCount == 9)
      {
        flag = "Draw"; 
        return flag;
      }
      return flag;    
    }
    
    
    public static void run(Scanner sc) throws Exception {   
      String result = "";
      String playAgain;      
      System.out.println("Welcome to TicTacToe, a 2 player short game. \n rows and "
          + "columns are numbered 1-3 starting from top and left respectively"
          + " \n Player 1 is X and Player 2 is O.");
      System.out.println("----------------");
      
      do {
        TicTacToe obj = new TicTacToe();
        displayCurrentBoard();      
        do {      
          turnPlayer1(sc);
          displayCurrentBoard();  
          moveCount++;                
          if(checkWin().equals("Win"))
            {
              System.out.println("Player 1 wins.");                            
              break;
            }
          if(moveCount==9)
          {          
            break;
          }        
          turnPlayer2(sc);
          moveCount++;          
          displayCurrentBoard();        
          if(checkWin().equals("Win"))
          {
            System.out.println("Player 2 wins.");
            break;
          }        
          
        }while(moveCount<9);
      if(moveCount == 9 && checkWin().equals("Draw"))
      {
        System.out.println("It's a Draw.");
      }
      System.out.print("Do you want to play again? Enter y or Y for yes: ");
      playAgain = sc.next();
      }while(playAgain.equals("y")||playAgain.equals("Y"));
      
      displayCurrentBoard(); 
     }
}
