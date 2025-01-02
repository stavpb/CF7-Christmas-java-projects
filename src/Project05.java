import java.util.Scanner;
/**
 * Manages bookings and cancellations for theater seats of 30 rows
 * and 12 columns. Name of each seat consists of a letter (column) and
 * a number (row). Ex. C2 seat is placed at 2nd row and 3rd column. Main:
 * - Prints choices
 * - If choice is 1 or 2, requests seat from user and then
 *   proceeds with booking or cancellation accordingly
 * - If choice is 3, prints the seats array
 * - If choice is 4, app exit.
 */
public class Project05 {
    public static Scanner in = new Scanner(System.in);
    public static boolean[][] isBooked = new boolean[30][12];
    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("---Theater management---");
                System.out.println("1. Book seat");
                System.out.println("2. Cancel seat");
                System.out.println("3. Print seats");
                System.out.println("4. Exit");
                System.out.println("Choose an option\n");
                int choice = checkInt();
                switch (choice) {
                    case 1:
                        book(getColumn(), getRow());
                        break;
                    case 2:
                        cancel(getColumn(), getRow());
                        break;
                    case 3:
                        printSeats();
                        break;
                    case 4:
                        System.out.println("Thank you for using Theater Management. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option. Please choose between 1-4.\n");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid column name. Please enter a char.\n");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid seat. Row must be 1-30. Column must be A-L.\n");
            } catch (Exception e) {
                System.out.println("An error occurred.\n");
            }
        }
    }
    /**
     * Requests column name from user
     * @return column letter
     * @throws IllegalArgumentException if input is not char
     * @throws IndexOutOfBoundsException if input isn't between A-L
     */
    public static char getColumn()  {
        System.out.println("Enter column letter: ");
        String input = in.next();
        try {
            if (!Character.isLetter(input.charAt(0)) || input.length() != 1) {
                throw new IllegalArgumentException("Column not valid");
            }
            char column = input.charAt(0);
            try {
                if (column < 'A' || column > 'L') {
                    throw new IndexOutOfBoundsException("Column must be between A-L");
                }
                return column;
            } catch (IndexOutOfBoundsException e) {
                throw e;
            }
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
    /**
     * Requests row number from user
     * @return row number
     * @throws IndexOutOfBoundsException if number isn't between 1-30
     */
    public static int getRow() throws IndexOutOfBoundsException {
        System.out.println("Enter row number: ");
        int row = checkInt();
        try {
            if (row < 1 || row > 30) {
                throw new IndexOutOfBoundsException("Row only between 1-30");
            }
            return row;
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }
    /**
     * Checks if input is integer
     * @return integer
     */
    public static int checkInt() {
        while (!in.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer\n");
            in.nextLine();
        }
        return in.nextInt();
    }
    /**
     * Checks if a seat is booked. If not, it proceeds to booking.
     * @param column    column letter of seat
     * @param row       row number of seat
     */
    public static void book(char column, int row) {
        int col_num = column - 'A';
        int row_num = row - 1;       //Seats start from 1 but arrays' first element is 0
        if (isBooked[row_num][col_num]) {
            System.out.println("The seat is already booked\n");
            return;
        }
        isBooked[row_num][col_num] = true;
        System.out.println("Seat " + column + row + " has been successfully booked.\n");
    }
    /**
     * Checks if a seat is booked, and proceeds to cancellation.
     * @param column    column letter of seat
     * @param row       row number of seat
     */
    public static void cancel(char column, int row) {
        int col_num = column - 'A';
        int row_num = row - 1;       //Seats start from 1 but arrays' first element is 0
        if (isBooked[row_num][col_num]) {
            isBooked[row_num][col_num] = false;
            System.out.println("" + column + row + " booking has been cancelled.\n");
        } else {
            System.out.println("Cancellation of " + column + row + " seat failed. The seat is not booked.\n");
        }
    }
    /**
     * Prints theater seats. True if seat is booked, false if not.
     */
    public static void printSeats() {
        for (boolean[] row : isBooked) {
            for (boolean el : row) {
                System.out.printf("%-5s ", el);
            }
            System.out.println();
        }
    }
}