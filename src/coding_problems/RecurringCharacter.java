package coding_problems;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecurringCharacter<T> {
    // Given an array of characters or numbers
    // Return the first recurring character in the array
    // Input: [1.2.3.7.1.5.3.2]
    // Return: 1
    // If does not contain recurring character return null
    static final Logger LOGGER = Logger.getLogger(RecurringCharacter.class.getSimpleName());

    public static void main(String[] args) {
        // Initialize array of items
        final Integer[] items = new Integer[] {1, 2, 5, 143, 43, 5 };

        // Execute algorithm
        final RecurringCharacter<Integer> recurringCharacter = new RecurringCharacter<>();
        final Integer recurringItem = recurringCharacter.findFirstRecurred(items);

        // Log the resul
        LOGGER.log(
                Level.INFO,
                "In the items: {0} the first recurring item is: {1}",
                new Object[] {Arrays.toString(items), recurringItem}
        );
    }

    /**
     * Iterates through given array of T objects and find the first repeated object
     * @return first recurring item
     */
    private T findFirstRecurred(final T[] items) {
        // Time complexity is on average O(n)
        // Check the input
        if (Objects.isNull(items) || items.length == 0) {
            return null;
        }
        // Store the occurrences into Set
        final Set<T> occurrences = new HashSet<>();
        // Iterate through items
        for (T item: items) {
            if (Objects.isNull(item)) continue;

            if (occurrences.contains(item)) {
                return item;
            }

            occurrences.add(item);
        }

        return null;
    }
}
