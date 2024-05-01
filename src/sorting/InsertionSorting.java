package sorting;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Insertion sort iterates the list and creates a new portion of list (sorted) by swapping the lowest
 * item from the unsorted part of the list
 * https://www.geeksforgeeks.org/insertion-sort/
 * The best Time: O(n), the worst Time: O(n^2)
 * Space: O(1)
 */
public class InsertionSorting {
    private static final Logger LOGGER = Logger.getLogger(InsertionSorting.class.getSimpleName());
    public static void main(String[] args) {
        int[] numbers = new int[] {0, 5, 7, 1, 3, 8, 2};
        InsertionSorting.sort(numbers);
        LOGGER.log(Level.INFO, "Sorted: {0}", Arrays.toString(numbers));
    }

    /**
     * Sort the input nubers with the Bubble sort
     */
    public static void sort(int[] unsorted) {
        int n = unsorted.length;
        for (int i = 1; i < n; ++i) {
            int key = unsorted[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && unsorted[j] > key) {
                unsorted[j + 1] = unsorted[j];
                j = j - 1;
            }
            unsorted[j + 1] = key;
        }
    }
}
