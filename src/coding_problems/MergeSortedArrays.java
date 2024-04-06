package coding_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class consists of a solution to the simple problem
 */
public class MergeSortedArrays {
    //On the input there are given 2 arrays of string
    // Both of them are sorted
    // Task is to merge them into one array

    // Q1: Will have the arrays inner string occurrences?
    // Q2: Will be there strings or characters?

    static final Logger LOGGER = Logger.getLogger(MergeSortedArrays.class.getSimpleName());

    public static void main(String[] args) {
        // Create 2 sorted arrays of String
        String[] array1 = new String[] {"adam", "peta"};
        String[] array2 = new String[] {"meno", "nieco", "zaciatok"};

        // Execute merge
        final MergeSortedArrays mergeSortedArrays = new MergeSortedArrays();
        final String[] mergedSortedArrays = mergeSortedArrays.mergeSorted(array1, array2);

        LOGGER.log(
                Level.INFO,
                "For sorted string arrays {0} and {1} the merge result is {2}",
                new Object[]{Arrays.toString(array1), Arrays.toString(array2), Arrays.toString(mergedSortedArrays)}
        );
    }


    /**
     * Merge 2 sorted arrays that are given
     * @param array1
     * @param array2
     * @return
     * @throws IllegalArgumentException in a case that one of the arrays is not initialized
     */
    private String[] mergeSorted(final String[] array1, final String[] array2) throws IllegalArgumentException {
        // Pick the larger array's length
        // while counter is less than total length look to element from both arrays,
        // compare them and write in order.

        if (Objects.isNull(array1) || Objects.isNull(array2)) {
            throw new IllegalArgumentException("Array input cannot be null");
        }

        // The resul list of strings
        final List<String> result = new ArrayList<>();
        // Define the minimal length from arrays
        final int length = Math.min(array1.length, array2.length);

        String array1String;
        String array2String;

        // iterate both arrays and compare and set items into the result
        for (int i = 0; i < length; i++) {
            array1String = array1[i];
            array2String = array2[i];

            if (array1String.compareTo(array2String) <= 0) {
                result.add(array1String);
                result.add(array2String);
            } else {
                result.add(array2String);
                result.add(array1String);
            }
        }

        // Define the max length of arrays and add the rest of items to the result list
        final String[] largerArray = length < array1.length ? array1 : array2;
        final String[] restItems = Arrays.copyOfRange(largerArray, length, largerArray.length);
        result.addAll(Arrays.asList(restItems));

        // Return the list as an array
        return result.toArray(new String[0]);
    }
}
