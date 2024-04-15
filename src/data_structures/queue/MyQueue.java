package data_structures.queue;

public interface MyQueue<T> {

    T peek();

    /**
     * Add item to the queue
     * @return added item
     */
    T enqueue(T value);

    /**
     * Remove the item from the queue
     * @return the oldest added item
     */
    T dequeue();

    boolean empty();
}
