package data_structures.array;

public interface MyArray<T> {
    /**
     * @return the number of elements stored
     */
    int size();
    /**
     * Gets the element on the index
     * @param index of the desired element
     * @return T - List type
     */
    T get(int index);

    /**
     * Pushes the given element to the and
     * @param element data that will be stored
     * @return stored element
     */
    T push(T element);

    /**
     * Removes the last element
     * @return removed element
     */
    T pop();

    /**
     * Deletes the element on the given index
     * @param index of the desired deletion
     * @return the deleted item
     */
    T delete(int index);
}
