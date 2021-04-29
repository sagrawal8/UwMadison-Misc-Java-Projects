package cs5004.tictactoe;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Tic tac toe Controller class.
 */
public class TicTacToeConsoleController implements TicTacToeController {
  private final Readable in;
  private final Appendable out;

  /**
   * Constructor for console.
   * @param in1 Readable.
   * @param out1  Appendable.
   */
  public TicTacToeConsoleController(Readable in1, Appendable out1) {
    if (in1 == null || out1 == null) {
      throw new IllegalArgumentException("in or out is null");
    }
    this.in = in1;
    this.out = out1;
  }

  @Override
  public void playGame(TicTacToe m) {
    if (m == null) {
      throw new IllegalArgumentException("TicTacToe obj cant be null");
    }
    Scanner scan = new Scanner(in);
    try {
      out.append(m.toString()).append("\n");
      out.append("Enter move for " + m.getTurn().toString()).append(":\n");
      Integer rowInput = null;
      Integer columnInput = null;
      String token = "";
      while (!m.isGameOver()) {
        token = scan.next();
        if (token.equalsIgnoreCase("q")) {
          break;
        }
        try {
          int var = Integer.parseInt(token);
          if (rowInput == null) {
            rowInput = var;
          } else {
            columnInput = var;
            m.move(rowInput - 1, columnInput - 1);
            if (m.isGameOver()) {
              out.append(m.toString()).append("\n");
              out.append("Game is over! ");
              if (m.getWinner() != null) {
                out.append(m.getWinner().toString() + "wins!\n");
              } else {
                out.append("Tie game.\n");
              }
              break;
            }
            out.append(m.toString()).append("\n");
            out.append("Enter a move for " + m.getTurn().toString()).append(":\n");
            rowInput = columnInput = null;
          }
        } catch (NumberFormatException e) {
          out.append("Invalid Number" + token + "\n");
        } catch (IllegalArgumentException e) {
          out.append("Invalid move" + rowInput + "," + columnInput + "\n");
          rowInput = columnInput = null;
        }
      }
      if (!m.isGameOver() && token.equalsIgnoreCase("q")) {
        out.append("Game quit! Ending game state:\n" + m.toString() + "\n");
      } else if (!m.isGameOver()) {
        throw new IllegalStateException("No more inputs.");
      }
    } catch (IOException e) {
      throw new IllegalStateException("Failure to Append.");
    } catch (NoSuchElementException e) {
      throw new IllegalStateException("Failure to Read.");
    }
    scan.close();
  }
}
