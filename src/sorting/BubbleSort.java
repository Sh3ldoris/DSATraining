package sorting;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Bubble sort iterates and compares every number with each others
 * Time complexity: Time: O(n^2)
 * Space complexity: Time: O(1)
 */
public class BubbleSort {
    private static final Logger LOGGER = Logger.getLogger(BubbleSort.class.getSimpleName());
    public static void main(String[] args) {
        int[] numbers = new int[] {0, 5, 7, 1, 3, 8, 2};
        BubbleSort.sort(numbers);
        LOGGER.log(Level.INFO, "Sorted: {0}", Arrays.toString(numbers));
    }

    /**
     * Sort the input nubers with the Bubble sort
     */
    public static void sort(int[] numbers) {
        // Temporary help item
        int temporary;
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbers.length - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    temporary = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temporary;
                }
            }
        }
    }
}
