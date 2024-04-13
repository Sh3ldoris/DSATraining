package data_structures.linked_list;

public interface MyLinkedList<T> {
    int size();

    T prepend(T value);

    T append(T value);

    T get(int index);

    T insert(T value, int index);

    void delete(int index);

    void reverse();
}
