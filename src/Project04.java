import java.util.Scanner;
/**
 * Tic-Tac-Toe game. Main:
 * - Requests starting player from user
 * - Requests where player wants to place its symbol in tic-tac-toe
 * - Checks if position is occupied and handles the situation accordingly
 * - Prints tic-tac-toe after each player's move
 * - Checks for win
 * - Checks for draw
 * - Switches player
 * - Repeats process till the end of the game
 */
public class Project04 {
    public static void main(String[] args) {
        try {
            char currentPlayer = readPlayer();     //Choose player
            char[][] tictactoe = new char[3][3];
            while (true) {
                //Player chooses the position of his move
                System.out.println("PLAYER " + currentPlayer + " PLAYS");
                System.out.println("In which row you want to place " + currentPlayer + "?");
                int r = getPosition();
                System.out.println("In which column you want to place " + currentPlayer + "?");
                int c = getPosition();
                if (tictactoe[r][c] == 0) {
                    tictactoe[r][c] = currentPlayer;
                } else {
                    System.out.println("This position is occupied. Try again.\n");
                    continue;
                }
                System.out.println();
                printTicTacToe(tictactoe);
                System.out.println();
                //Checks for win or draw
                if (isWin(tictactoe, currentPlayer)) break;
                if (isDraw(tictactoe)) {
                    System.out.println("It's a Draw! No one wins.");
                    break;
                }
                //Updates player
                currentPlayer = switchPlayer(currentPlayer);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Character must be either 'X' or 'O'");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Position is not valid. Must be between 1-3. Exit...");
        } catch (Exception e) {
            System.out.println("Error occurred. Exit...");
        }
    }
    /**
     * Reads the symbol of first player in the game from std input
     * @return the symbol that user wrote on stdin
     * @throws IllegalArgumentException in case char is neither 'X' nor 'O'
     */
    public static char readPlayer() throws IllegalArgumentException {
        Scanner in = new Scanner(System.in);
        System.out.println("Which player are you? 'X' or 'O'?");
        char player = in.nextLine().charAt(0);
        try {
            if (player != 'X' && player != 'O') {
                throw new IllegalArgumentException("Character must be either 'X' or 'O'");
            }
            return player;
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
    /**
     * Reads user's input and makes sure input is integer
     * @return position where player desires to place his symbol
     * @throws IndexOutOfBoundsException in case position is out of tic-tac-toe bounds
     */
    public static int getPosition() throws IndexOutOfBoundsException {
        Scanner in = new Scanner(System.in);
        while (!in.hasNextInt()) {
            System.out.println("Please enter an integer");
            in.nextLine();
        }
        int position = in.nextInt() - 1;
        try {
            if (position < 0 || position > 2) {
                throw new IndexOutOfBoundsException("Position only between 1-3");
            }
            return position;
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }
    /**
     * Prints elements of two-dimensional tic-tac-toe array
     * @param arr tic-tac-toe array
     */
    public static void printTicTacToe(char[][] arr) {
        for (char[] row : arr) {
            for (char el : row) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }
    /**
     * Checks if player has won the game
     * @param arr       tic-tac-toe array
     * @param player    player 'X' or 'O'
     * @return          true if player has won, false if not
     */
    public static boolean isWin(char[][] arr, char player) {
        //Check rows
        for (int i = 0; i <= 2; i++) {
            if (arr[i][0] == arr[i][1] && arr[i][1] == arr[i][2] && arr[i][0] == player) {
                return getWinResult(player);
            }
        }
        //Check columns
        for (int i = 0; i <= 2; i++) {
            if (arr[0][i] == arr[1][i] && arr[1][i] == arr[2][i] && arr[0][i] == player) {
                return getWinResult(player);
            }
        }
        //Check diagonals
        if (arr[0][0] == arr[1][1] && arr[1][1] == arr[2][2] && arr[0][0] == player) {
            return getWinResult(player);
        }
        if (arr[2][0] == arr[1][1] && arr[1][1] == arr[0][2] && arr[2][0] == player) {
            return getWinResult(player);
        }
        return false;
    }
    /**
     * Prints a message for win and returns true
     * @param player player 'X' or 'O'
     * @return       true
     */
    public static boolean getWinResult (char player) {
        System.out.println("Player " + player + " wins!");
        return true;
    }
    /**
     * Checks if players are even
     * @param arr tic-tac-toe array
     * @return    true if there's draw, false if not
     */
    public static boolean isDraw(char[][] arr) {
        for (char[] row : arr) {
            for (char el : row) {
                if (el == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Switches between the two players
     * @param   currentPlayer symbol of current player
     * @return  char with the symbol of next player
     */
    public static char switchPlayer(char currentPlayer){
        return (currentPlayer == 'X') ? 'O' : 'X';
    }
}