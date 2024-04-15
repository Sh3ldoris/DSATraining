package data_structures.queue;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyQueueImpl<T> implements MyQueue<T> {
    static final Logger LOGGER = Logger.getLogger(MyQueueImpl.class.getSimpleName());
    private LinkedList<T> list;
    private int length;

    public MyQueueImpl() {
        this.list = new LinkedList<>();
        this.length = 0;
    }

    public static void main(String[] args) {
        final MyQueue<String> queue = new MyQueueImpl<>();
        queue.enqueue("First");
        queue.enqueue("Second");
        queue.enqueue("Third");

        LOGGER.log(Level.INFO, "The first items waiting is: {0}", queue.peek());
        LOGGER.log(Level.INFO, "Dequeue 1: {0}", queue.dequeue());
        LOGGER.log(Level.INFO, "Dequeue 2: {0}", queue.dequeue());
        LOGGER.log(Level.INFO, "Dequeue 3: {0}", queue.dequeue());
        LOGGER.log(Level.INFO, "Is queue empty: {0}", queue.empty());
    }


    @Override
    public T peek() {
        // Checks if there are any items
        if (length == 0) {
            throw new NoSuchElementException("The queue is empty, cannot peek!");
        }
        // Get the latest added item
        return list.getFirst();
    }

    @Override
    public T enqueue(T value) {
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
    public T dequeue() {
        // Check if the there are any items
        if (length == 0) {
            throw new NoSuchElementException("The queue is empty, cannot dequeue!");
        }
        // Decrease the size
        length--;
        // Remove the top item
        return list.removeFirst();
    }

    @Override
    public boolean empty() {
        return length == 0;
    }
}
