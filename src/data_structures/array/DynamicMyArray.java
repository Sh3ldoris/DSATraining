package data_structures.array;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class represents the simple implementation of Dynamic array
 */
public class DynamicMyArray<T> implements MyArray<T> {
    static final Logger LOGGER = Logger.getLogger(DynamicMyArray.class.getSimpleName());
    private static final int INITIAL_ARRAY_SIZE = 100;
    private int length;
    private T[] data;

    public static void main(String[] args) {
        final MyArray<String> myArray = new DynamicMyArray<>();
        myArray.push("Hey");
        myArray.push("Friend");
        myArray.push("What's up");
        myArray.push("How are you");
        myArray.delete(1);

        LOGGER.log(Level.INFO, "The size of the array is: {0}", myArray.size());
        LOGGER.log(Level.INFO, "The element on the {0} position is: {1}", new Object[]{0, myArray.get(0)});
        LOGGER.log(Level.INFO, "The element on the {0} position is: {1}", new Object[]{1, myArray.get(1)});
    }

    public DynamicMyArray() {
        this.length = 0;
        this.data = (T[]) new Object[DynamicMyArray.INITIAL_ARRAY_SIZE];
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public T get(final int index) {
        // O(1) time complexity
        // Check the valid input
        isIndexValid(index);
        // Return the element on the index
        return data[index];
    }

    @Override
    public T push(final T element) {
        // O(1) time complexity, but doesn't cover the reallocation scenario (O(n))
        // Checks if there is available space in the array
        if (length > data.length) {
            // If not increase the size of the array storage
            reAllocatedDataArray(length + DynamicMyArray.INITIAL_ARRAY_SIZE);
        }
        // Insert the element to the end of the array and increase a length
        data[length++] = element;
        // Return the stored element
        return element;
    }

    @Override
    public T pop() {
        // O(1) time complexity
        // Checks if there are any data
        if (length == 0) {
            throw new IllegalArgumentException("Cannot remove from the EMPTY array");
        }

        // Get the last element
        final T elementToBeRemoved = data[length - 1];
        data[length - 1] = null;
        // Decrease length
        length--;
        // Return the deleted element
        return elementToBeRemoved;
    }

    @Override
    public T delete(final int index) {
        // O(n) time complexity
        // Checks the valid index
        isIndexValid(index);
        // Take the element to be deleted
        final T itemToBeDeleted = data[index];
        // Reorder the data array and remove the element on the index
        for (int i = index; i < data.length - 1; i++) {
            data[i] = data[i + 1];
        }
        // Decrease the length
        length--;
        // Return the deleted item
        return itemToBeDeleted;
    }

    /**
     * Reallocates the data array and copy the items from the old to the new array
     * @param newLength - new length of the data array
     */
    private void reAllocatedDataArray(final int newLength) {
        T[] increasedDataArray = (T[]) new Object[newLength];
        // We can do a manual array copy
        // But more readable is using build in function
        System.arraycopy(data, 0, increasedDataArray, 0, data.length);
    }

    /**
     * Checks if the index can be used to access the element
     * @param index to be checked
     */
    private void isIndexValid(final int index) {
        if (index < 0 || index >= length) {
            throw new IllegalArgumentException("The index is out of bounds");
        }
    }
}
