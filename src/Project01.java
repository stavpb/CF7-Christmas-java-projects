import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 * Reads file and stores integers from 1-49 in array. Produces all possible
 * 6-number combinations that
 * 1) have maximum 4 even numbers
 * 2) have maximum 4 odd numbers
 * 3) have maximum 2 contiguous numbers
 * 4) have maximum 3 numbers with the same ending
 * 5) have maximum 3 numbers that are in the same ten
 * In the end, it creates a txt file and saves the combinations
 */
public class Project01 {
    public static void main(String[] args) throws IOException {
        String inPath = "C:/Users/stavroula/Desktop/numbers.txt";
        String outPath = "C:/Users/stavroula/Desktop/lotto.txt";
        try (Scanner in = new Scanner(new File(inPath));
             PrintStream ps = new PrintStream(outPath, StandardCharsets.UTF_8)){
            final int LOTTO_SIZE = 6;
            int pivot = 0;
            int[] result = new int[LOTTO_SIZE];
            int window;
            ArrayList<Integer> numbers = new ArrayList<>();
            String line;
            String[] tokens;
            // Checks txt file and stores numbers in ArrayList
            while (in.hasNextLine()) {
                line = in.nextLine();
                tokens = (line.split("\\s+"));
                for (String token : tokens) {
                    try {
                        int number = Integer.parseInt(token);
                        numbers.add(number);
                    } catch (NumberFormatException e) {
                        e.getMessage();
                    }
                }
            }
            // Creates new array and stores the numbers contained in txt file
            pivot = numbers.size();
            int[] lottoNumbers = new int[pivot];
            for (int i = 0; i < pivot; i++) {
                lottoNumbers[i] = numbers.get(i);
            }
            Arrays.sort(lottoNumbers);
            window = pivot - LOTTO_SIZE;
            for (int i = 0; i <= window; i++) {
                for (int j = i + 1; j <= window + 1; j++) {
                    for (int k = j + 1; k <= window + 2; k++) {
                        for (int l = k + 1; l <= window + 3; l++) {
                            for (int m = l + 1; m <= window + 4; m++) {
                                for (int n = m + 1; n <= window + 5; n++) {
                                    result[0] = lottoNumbers[i];
                                    result[1] = lottoNumbers[j];
                                    result[2] = lottoNumbers[k];
                                    result[3] = lottoNumbers[l];
                                    result[4] = lottoNumbers[m];
                                    result[5] = lottoNumbers[n];
                                    if (!isEven(result) && !isOdd(result) && !isSameTen(result)
                                            && !isSameEnding(result) && !isContiguous(result)) {
                                        ps.printf("%d %d %d %d %d %d\n",
                                                result[0], result[1], result[2], result[3], result[4], result[5]);
                                        System.out.printf("%d %d %d %d %d %d\n",
                                                result[0], result[1], result[2], result[3], result[4], result[5]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Checks if there are more than 4 even numbers
     * @param arr array of numbers
     * @return true if there are more than 4 even numbers
     */
    public static boolean isEven(int[] arr) {
        int count = 0;
        for (int el : arr) {
            if (el % 2 == 0) count++;
        }
        return count > 4;
    }
    /**
     * Checks if there are more than 4 odo numbers
     * @param arr array of numbers
     * @return true if there are more than 4 odo numbers
     */
    public static boolean isOdd(int[] arr) {
        int count = 0;
        for (int el : arr) {
            if (el % 2 != 0) count++;
        }
        return count > 4;
    }
    /**
     * Checks if there are more than two contiguous numbers
     * @param arr input array
     * @return true if there are more than two contiguous numbers
     */
    public static boolean isContiguous(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == (arr[j] + 1) || arr[i] == (arr[j] - 1)) count++;
            }
        }
        System.out.println(count);
        return count > 1;
    }
    /**
     * Checks if there are more than 3 numbers that have the same ending
     * @param arr input array
     * @return true if there are more than 3 numbers that have the same ending
     */
    public static boolean isSameEnding(int[] arr) {
        int count = 0;
        int[] endingArray = new int[10];
        Arrays.fill(endingArray, 0);
        for (int el : arr) {
            int ending = el % 10;
            endingArray[ending]++;
        }
        for (int end : endingArray) {
            if (end > 3) return true;
        }
        return false;
    }
    /**
     * Checks if there are more than 3 numbers that are in the same ten
     * @param arr input array
     * @return true if there are more than 3 numbers that are in the same ten
     */
    public static boolean isSameTen(int[] arr) {
        int count = 0;
        int[] decadeArray = new int[10];
        Arrays.fill(decadeArray, 0);
        for (int el : arr) {
            int decade = el / 10;
            decadeArray[decade]++;
        }
        for (int dec : decadeArray) {
            if (dec > 3) return true;
        }
        return false;
    }
    /**
     * Prints elements of array
     * @param arr input array
     */
    public static void printArray(int[] arr) {
        for (int el : arr) {
            System.out.print(el + " ");
        }
    }
}
