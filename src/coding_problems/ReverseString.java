package coding_problems;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class consists of a solution to the simple problem
 */
public class ReverseString {
    // There is a string given
    // Reverse a string
    // "Hi, My name is Slim Shady" => "ydahS si eman yM ,iH"

    // Q: Will I be given the String or the char[]?
    // Q: Do I have to null-check the string?
    static final Logger LOGGER = Logger.getLogger(ReverseString.class.getSimpleName());

    public static void main(String[] args) {
        // Create the input
        String string = "Hi, My name is Slim Shady";

        // Call the reverse function
        final ReverseString reverseString = new ReverseString();
        String reversedString = reverseString.reverse(string);

        // Print the result
        LOGGER.log(
                Level.INFO,
                "For the string: [{0}], the reverse one is: [{1}]",
                new Object[]{string, reversedString}
        );
    }

    public String reverse(final String string) {
        // Checks if the string is nonNull, nonEmpty or just doesn't have 1 character
        if (Objects.isNull(string) || string.length() <= 1) {
            return string;
        }

        // With the String we can work as with an array
        // Tho solution can be based on the iteration from the left and right to the middle and swapping the items
        // 1. Event length of the string
            // - middle: length / 2
        // 2 Odd length of the string
            // - the middle character will not swap

        // Solution - O(n) time and space complexity

        int leftIndex = 0;
        int rightIndex = string.length() - 1;
        char[] characters = string.toCharArray();

        while (leftIndex < rightIndex) {
            char characterFromRight = characters[rightIndex];
            characters[rightIndex--] = characters[leftIndex];
            characters[leftIndex++] = characterFromRight;
        }

        return new String(characters);
    }
}
