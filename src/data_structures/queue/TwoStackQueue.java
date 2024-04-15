package data_structures.queue;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TwoStackQueue<T> implements MyQueue<T> {
    static final Logger LOGGER = Logger.getLogger(TwoStackQueue.class.getSimpleName());
    private Stack<T> input;
    private Stack<T> output;
    private int length;

    public TwoStackQueue() {
        this.input = new Stack<>();
        this.output = new Stack<>();
        this.length = 0;
    }

    public static void main(String[] args) {
        final MyQueue<String> queue = new TwoStackQueue<>();
        queue.enqueue("First");
        queue.enqueue("Second");
        queue.enqueue("Third");

        LOGGER.log(Level.INFO, "Dequeue 1: {0}", queue.dequeue());

        queue.enqueue("FirstSend");

        LOGGER.log(Level.INFO, "The first items waiting is: {0}", queue.peek());
        LOGGER.log(Level.INFO, "Dequeue 2: {0}", queue.dequeue());
        LOGGER.log(Level.INFO, "Dequeue 3: {0}", queue.dequeue());
        LOGGER.log(Level.INFO, "Dequeue 4: {0}", queue.dequeue());
        LOGGER.log(Level.INFO, "Is queue empty: {0}", queue.empty());
    }
    @Override
    public T peek() {
        // Checks if there are any items
        if (length == 0) {
            throw new NoSuchElementException("The queue is empty, cannot peek!");
        }

        return output.empty() ? input.firstElement(): output.peek();
    }

    @Override
    public T enqueue(T value) {
        // Check the input
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("Value cannot be null!");
        }
        input.push(value);
        length++;
        return value;
    }

    @Override
    public T dequeue() {
        // Checks if there are any items
        if (length == 0) {
            throw new NoSuchElementException("The queue is empty, cannot peek!");
        }

        if (output.empty()) {
            while (!input.empty()) {
                output.push(input.pop());
            }
        }
        length--;
        return output.pop();
    }

    @Override
    public boolean empty() {
        return length == 0;
    }
}
