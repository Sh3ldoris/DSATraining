package coding_problems;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class contains solutions for the public official Google's interview video
 * The video can be found <a href="https://www.youtube.com/watch?v=XKu_SEDAykw">here</a>
 */
public class GoogleInterview {
    // You are given a collection of numbers
    // Take that collection and find the matching pair that is equal of a sum that will be given
    // Output for the example would be only true/false that there is a matching pair

    // Examples:
        // 1. [1,2,3,9], sum=8
        // 2. [1,2,4,4], sum=8

    // 1st Solution:
        // - numbers are sorted
        // - negative numbers can be present

    // 2nd Solution:
        // - numbers are unsorted
        // - negatives can be present
    static final Logger LOGGER = Logger.getLogger(GoogleInterview.class.getSimpleName());

    public static void main(String[] args) {
        // Initialize inputs
        int[] sortedNumbers = {1, 2, 3, 9};
        int[] unsortedNumbers = {4, 2, 4, 5};
        int sum = 8;

        // Run the solutions
        final GoogleInterview interview = new GoogleInterview();
        final boolean hasPairWithSumInSortedArray = interview.hasPairWithSumSorted(sortedNumbers, sum);
        final boolean hasPairWithSumInUnsortedArray = interview.hasPairWithSumUnsorted(unsortedNumbers, sum);

        // Log the result
        printResult(hasPairWithSumInSortedArray, sum);
        printResult(hasPairWithSumInUnsortedArray, sum);

        // We can add additional unit tests:
            // - if the algorithm can handle negatives and positive sum
            // - if the algorithm can handle negatives and negative sum
            // - if the algorithm can handle positives and negative sum
            // - if the algorithm throws exception if the numbers are null or empty
    }

    private static void printResult(final boolean result, final int sum) {
        if (result) {
            LOGGER.log(Level.INFO, "The array HAS the pair matching the sum of {0}", sum);
        } else {
            LOGGER.log(Level.INFO, "The array HAS NOT the pair matching the sum of {0}", sum);
        }
    }

    /**
     * Searches for 2 number, that sum of those is equals to the given sum
     * @param numbers - sorted array of number that is searched
     * @param sum - searched sum of 2 numbers
     * @return boolean of the presence of the matching pair
     */
    public boolean hasPairWithSumSorted(final int[] numbers, final int sum) {
        // Naive solution - O(n^2)
            // nested for loops 1. for i from 0 to numbers.length, 2. for j from i + 1 to number.length

        // Solution - O(n) linear time complexity, O(2) => O(1) space complexity
            // Find the init pair (the first - lowest number and the last - greatest number)
            // move towards the middle and sum the number
            // end if the indexes are crossed each self

        // Input validation:
        //1. If there is actual array given
        validateInputArray(numbers);

        //2. if the first number in the sorted array is greater than given sum -> result will be false
        if (numbers[0] > sum) {
            return false;
        }

        int leftIndex = 0;
        int rightIndex = numbers.length - 1;

        while(leftIndex < rightIndex) {
            if (numbers[leftIndex] + numbers[rightIndex] == sum) {
                return  true;
            }

            if (numbers[rightIndex] > sum) {
                rightIndex--;
            } else {
                leftIndex++;
            }
        }

        return false;
    }

    /**
     * Searches for 2 number, that sum of those is equals to the given sum
     * @param numbers - unsorted array of number that is searched
     * @param sum - searched sum of 2 numbers
     * @return boolean of the presence of the matching pair
     */
    public boolean hasPairWithSumUnsorted(final int[] numbers, final int sum) {
        // Solution - O(n) time complexity and also space complexity
            // Iterate once through the numbers
            // Each iteration looks back if we haven't passed the difference for the sum - numbers[i]
            // Use HashSet for an O(1) look-up and store the already passed numbers
        validateInputArray(numbers);

        //2. if the first number in the sorted array is greater than given sum -> result will be false

        Set<Integer> complements = new HashSet<>();
        for (int number : numbers) {
            if (complements.contains(number)) {
                return true;
            }

            complements.add(sum - number);
        }

        return false;
    }

    /**
     * Validates the inputs for the pair sum algorithm
     * @param numbers - input for the algorithm
     * @throws IllegalArgumentException if the numbers are null or empty
     */
    private void validateInputArray(final int[] numbers) throws IllegalArgumentException {
        if (Objects.isNull(numbers) || numbers.length == 0) {
            throw new IllegalArgumentException("Numbers cannot be null or empty!");
        }
    }
}
