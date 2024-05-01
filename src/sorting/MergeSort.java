package sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Merge sort takes and divide the list of data by halves until the single items is present (O(logn)) in the half
 * then it goes back and compare that halves (O(n))
 * Time: O(nLogn)
 * Space: O(n)
 */
public class MergeSort {
    private static final Logger LOGGER = Logger.getLogger(MergeSort.class.getSimpleName());
    public static void main(String[] args) {
        int[] numbers = new int[] {0, 5, 7, 1, 3, 8, 2};
        final int[] sorted = MergeSort.sort(numbers);
        LOGGER.log(Level.INFO, "Sorted: {0}", Arrays.toString(sorted));
    }

    /**
     * Sort the input nubers with the Bubble sort
     */
    public static int[] sort(int[] numbers) {
        if (numbers.length == 1) {
            return numbers;
        }
        // Split the array into the halves
        final int middle = numbers.length / 2;
        int[] left = Arrays.copyOfRange(numbers, 0, middle);
        int[] right = Arrays.copyOfRange(numbers, middle, numbers.length);

//        LOGGER.log(Level.INFO, "Left: {0}", Arrays.toString(left));
//        LOGGER.log(Level.INFO, "Right: {0}", Arrays.toString(right));

        // Return the merged sort(left) + sort(right)
        return merge(sort(left), sort(right));
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int index = 0;
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] < right[rightIndex]) {
                // If the left value of current left index is less
                result[index++] = left[leftIndex++];
            } else {
                // If the right value of current index is less
                result[index++] = right[rightIndex++];
            }
        }

        while (leftIndex < left.length) {
            result[index++] = left[leftIndex++];
        }

        while (rightIndex < right.length) {
            result[index++] = right[rightIndex++];
        }
        return result;
    }
}
