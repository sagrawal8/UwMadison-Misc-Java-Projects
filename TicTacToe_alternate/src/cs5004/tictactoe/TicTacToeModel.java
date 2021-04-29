package cs5004.tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Implementation of TicTacToe Interface.
 */
public class TicTacToeModel implements TicTacToe {
  // add your implementation here
  private Player[][] board;
  private int turn;
  Player winner;

  /**
   * Constructor for TicTacToeModel.
   */
  public TicTacToeModel() {
    board = new Player[3][3];
    turn = 0;
    winner = null;
  }

  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard())
            .map(row -> " " + Arrays.stream(row)
                    .map(p -> p == null ? " " : p.toString())
                    .collect(Collectors.joining(" | ")))
            .collect(Collectors.joining("\n-----------\n"));
  }

  @Override
  public void move(int r, int c) {
    if (isGameOver()) {
      throw new IllegalStateException("Game is over.");
    }
    testOutOfBounds(r, c);

    if (board[r][c] != null) {
      throw new IllegalArgumentException("Position occupied. Select new position");
    } else {
      board[r][c] = getTurn();
      turn++;
    }
  }

  @Override
  public Player getTurn() {
    if (turn % 2 == 0) {
      return Player.X;
    } else {
      return Player.O;
    }
  }

  @Override
  public boolean isGameOver() {
    boolean flag = false;
    for (Player name : Player.values()) {
      if (board[0][0] == name && board[0][1] == name && board[0][2] == name) {
        flag = true;
      } else if (board[1][0] == name && board[1][1] == name && board[1][2] == name) {
        flag = true;
      } else if (board[2][0] == name && board[2][1] == name && board[2][2] == name) {
        flag = true;
      } else if (board[0][0] == name && board[1][0] == name && board[2][0] == name) {
        flag = true;
      } else if (board[0][1] == name && board[1][1] == name && board[2][1] == name) {
        flag = true;
      } else if (board[0][2] == name && board[1][2] == name && board[2][2] == name) {
        flag = true;
      } else if (board[0][0] == name && board[1][1] == name && board[2][2] == name) {
        flag = true;
      } else if (board[2][0] == name && board[1][1] == name && board[0][2] == name) {
        flag = true;
      }
      if (flag) {
        winner = name;
        break;
      }
    }
    if (!flag && turn > 8) {
      flag = true;
    }
    return flag;
  }

  @Override
  public Player getWinner() {
    isGameOver();
    Player winnerCopy = winner;
    return winnerCopy;
  }

  //From StackOverflow 'Copy a 2D java array'
  //Wanted something different from a nested for loop.
  @Override
  public Player[][] getBoard() {
    return Arrays.stream(board).map((Player[] row) -> row.clone())
            .toArray((int length) -> new Player[length][]);
  }

  @Override
  public Player getMarkAt(int r, int c) {
    testOutOfBounds(r, c);
    Player mark = board[r][c];
    return mark;
  }

  private void testOutOfBounds(int r, int c) {
    if (r < 0 || r > 2) {
      throw new IllegalArgumentException("Row is invalid.");
    } else if (c < 0 || c > 2) {
      throw new IllegalArgumentException("Column is invalid.");
    }
  }
}
