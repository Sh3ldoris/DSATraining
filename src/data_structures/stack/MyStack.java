package data_structures.stack;

/**
 * Simple stack operations
 * @param <T> type of stored items
 */
public interface MyStack<T> {

    /**
     * Look at the top item without removing it
     * @return the top item
     */
    T peek();

    /**
     * Push given item to the top of the stack
     * @param value inserted data
     * @return inserted data
     */
    T push(T value);

    /**
     * Remove and return the top item
     * @return the removed top item
     */
    T pop();

    /**
     * Check if the stack is empty
     * @return true if stack is empty, otherwise false
     */
    boolean empty();
}
