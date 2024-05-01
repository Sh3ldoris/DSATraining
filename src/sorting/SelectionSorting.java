package sorting;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Selection sorting looks for the smallest item and then place the item at the beginning
 * Time complexity: Time: O(n^2)
 * Space complexity: Time: O(1)
 */
public class SelectionSorting {
    private static final Logger LOGGER = Logger.getLogger(SelectionSorting.class.getSimpleName());
    public static void main(String[] args) {
        int[] numbers = new int[] {0, 5, 7, 1, 3, 8, 2};
        SelectionSorting.sort(numbers);
        LOGGER.log(Level.INFO, "Sorted: {0}", Arrays.toString(numbers));
    }

    /**
     * Sort the input nubers with the Bubble sort
     */
    public static void sort(int[] numbers) {
        // Temporary help item
        int min, minIndex;
        for (int i = 0; i < numbers.length - 1; i++) {
            min = numbers[i]; // Set the starting min of the iteration
            minIndex = i;
            // Look for the actual minimum of the iteration
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] < min) {
                    min = numbers[j];
                    minIndex = j;
                }
            }

            // Swap the minimum with the i position
            numbers[minIndex] = numbers[i];
            numbers[i] = min;
        }
    }
}
