import java.util.Arrays;
import java.util.Scanner;
/**
 * It finds the contiguous sub-array with the largest sum using
 * Kadane's algorithm and the following case {-2, 1, -3, 4, -1, 2, 1, -5, 4},
 * the maximum sum sub-array is {4, -1, 2, 1} with a sum of 6.
 */
public class Project02 {
    public static void main(String[] args) {
        try {
            int[] array = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
            int n = array.length - 1;
            getMaxSumAndEndPos(array, n);
            int max_sum = getMaxSumAndEndPos(array, n)[0];      //One for loop [time complexity=O(n)]
            int end_pos = getMaxSumAndEndPos(array, n)[1];      //One for loop [time complexity=O(n)]
            int start_pos = getStartPos(array, max_sum, end_pos);
            int[] subArray = Arrays.copyOfRange(array, start_pos, end_pos + 1);
            System.out.println("Subarray with max sum: " + Arrays.toString(subArray));
            System.out.println("Max sum of subarray: " + max_sum);
        } catch (Exception e) {
            System.out.println("An error occurred. Exit app...");
        }
    }
    /**
     * Computes the maximum sub-array sum for the sub-array ending at index n.
     * @param arr   initial array
     * @param n     number of positions of array (length-1)
     * @return      the maximum sum of sub-array
     */
    public static int getMax (int[] arr, int n) {
        if (n == 0) {
            return arr[0];
        } else {
            return Math.max(getMax(arr,n-1) + arr[n], arr[n]);
        }
    }
    /**
     * Finds the largest sum of sub-array and the ending position of sub-array
     * @param arr   initial array
     * @param n     number of positions of array (length-1)
     * @return      two-position array with the maximum sum and the ending position of sub-array
     */
    public static int[] getMaxSumAndEndPos(int[] arr, int n) {
        int localMax;
        int globalMax = arr[0];
        int end = 0;
        int[] max_array = new int [2];
        for (int i = n; i >= 0; i--) {
            localMax = getMax(arr, i);
            if (localMax > globalMax) {
                globalMax = localMax;
                end = i;
            }
        }
        max_array[0] = globalMax;
        max_array[1] = end;
        return max_array;
    }
    /**
     * Finds the starting position of sub-array
     * @param arr       initial array
     * @param max_sum   maximum sum of sub-array
     * @param end_pos   ending position of sub-array
     * @return          starting position of sub-array
     */
    public static int getStartPos (int[] arr, int max_sum, int end_pos) {
        int sum = 0;
        int start_pos = end_pos;
        for (int i = end_pos; i >= 0; i--) {
            sum += arr[i];
            if (sum == max_sum) {
                start_pos = i;
                break;
            }
        }
        return start_pos;
    }
}
