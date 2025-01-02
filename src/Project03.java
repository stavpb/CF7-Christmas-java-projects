import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
/**
 * Reads one-by-one the characters of a txt file and stores latin letters in a 2D array where
 * 1st column contains an integer that represents the letter and 2nd column shows how many times
 * letter is found in file. Main demonstrates statistics for each character (character frequency
 * sorted by character and by frequency).
 */
public class Project03 {
    public static void main(String[] args) throws IOException {
        String filePath = "C:/Users/stavroula/Desktop/project04.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<Integer> characters = readFile(filePath);      // Creates List with all the unique latin letters of a txt file
            int count = characters.size();
            int[][] tokens = new int[count][2];     //Creates a 2D Array. Num of columns = characters' size
            int letter;
            // Populate array
            for (int i = 0; i < tokens.length; i++) {
                tokens[i][0] = characters.get(i);
                tokens[i][1] = 0;
            }
            //Read file and add 1 to character frequency, if array contains character
            while ((letter = reader.read()) != -1) {
                for (int i = 0; i < tokens.length; i++) {
                    if (letter == tokens[i][0]) {
                        tokens[i][1]++;
                    }
                }
            }
            //Sorts array by character and prints the result
            System.out.println("Sorted by character");
            Arrays.sort(tokens, Comparator.comparingInt((int[] o) -> o[0]));
            printArray(tokens);
            System.out.println();
            //Sorts array by character frequency in descending order and prints the result
            System.out.println("Sorted by character frequency");
            Arrays.sort(tokens, Comparator.comparingInt((int[] o) -> o[1]).reversed());
            printArray(tokens);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    /**
     * Prints array values, where 1st column represents a letter
     * of latin alphabet and 2nd column the frequency of each letter
     * @param arr input array
     */
    public static void printArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            char character = (char)arr[i][0];
            System.out.println( character + " " + arr[i][1]);
        }
    }
    /**
     * Reads txt file and stores the unique latin characters in ArrayList
     * @param filePath  the txt file path
     * @return          ArrayList with unique latin letters
     * @throws IOException
     */
    public static List readFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<Integer> chars = new ArrayList<Integer>();
            int character;
            while ((character = reader.read()) != -1) {
//                if (character == ' ') continue;
                if (!chars.contains(character) && isCharacter(character)) {
                    chars.add(character);
                }
            }
            return chars;
        } catch (IOException e) {
            throw e;
        }
    }
    /**
     * Checks if a number represents a latin letter
     * @param character integer that represents a character
     * @return true if character is a latin letter
     */
    public static boolean isCharacter(int character) {
        return ((character > 64 && character < 91) || (character > 96 && character < 123));
    }
}