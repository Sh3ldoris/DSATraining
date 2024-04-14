package data_structures.stack;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple implementation of a subset of Stack operations
 * Using built-in LinkedList
 * @param <T> type of stored items
 */
public class MyStackEasyImpl<T> implements MyStack<T> {
    static final Logger LOGGER = Logger.getLogger(MyStackEasyImpl.class.getSimpleName());
    private final LinkedList<T> list;
    private int length;

    public MyStackEasyImpl() {
        this.list = new LinkedList<>();
        this.length = 0;
    }

    public static void main(String[] args) {
        final MyStack<String> stack = new MyStackEasyImpl<>();
        stack.push("Google.com");
        stack.push("YouTube.com");
        stack.push("MyWeb.com");

        LOGGER.log(Level.INFO, "Last viewed site: {0}", stack.peek());
        LOGGER.log(Level.INFO, "Pop: {0}", stack.pop());
        LOGGER.log(Level.INFO, "Pop: {0}", stack.pop());
        LOGGER.log(Level.INFO, "Pop: {0}", stack.pop());
        LOGGER.log(Level.INFO, "Is stack empty: {0}", stack.empty());
    }

    @Override
    public T peek() {
        // Checks if there are any items
        if (length == 0) {
            throw new NoSuchElementException("The stack is empty, cannot peek!");
        }
        // Get the top item
        return list.getLast();
    }

    @Override
    public T push(T value) {
        // Check the input
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("Value cannot be null!");
        }
        // Store the given item
        list.add(value);
        // Increase size
        length++;
        // Return the stored item
        return value;
    }

    @Override
    public T pop() {
        // Check if the there are any items
        if (length == 0) {
            throw new NoSuchElementException("The stack is empty, cannot pop!");
        }
        // Decrease the size
        length--;
        // Remove the top item
        return list.removeLast();
    }

    @Override
    public boolean empty() {
        return length == 0;
    }
}
