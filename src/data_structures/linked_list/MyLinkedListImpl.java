package data_structures.linked_list;

import java.util.Objects;
import java.util.logging.Logger;

public class MyLinkedListImpl<T> implements MyLinkedList<T> {
    private final Logger LOGGER = Logger.getLogger(MyLinkedListImpl.class.getSimpleName());
    private int length;
    private MyEntry<T> head;
    private MyEntry<T> tail;

    public static void main(String[] args) {
        MyLinkedList<Integer> linkedList = new MyLinkedListImpl<>(10);
        linkedList.append(12);
        linkedList.append(14);
        linkedList.append(16);
        linkedList.append(18);
        linkedList.append(20);
        linkedList.append(22);
        linkedList.append(24);

        linkedList.reverse();

        System.out.println("Hej");
    }

    public MyLinkedListImpl(T headValue) {
        this.head = new MyEntry<>(headValue, null, null);
        this.tail = this.head;
        this.length = 1;
    }

    public MyLinkedListImpl() {
        // Add when first item inserted
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public T prepend(T value) {
        // Time complexity O(1)
        // insert the new head
        return insertEdgeNode(value, EdgeNodeType.HEAD);
    }

    @Override
    public T append(T value) {
        // Time complexity O(1)
        // insert the new tail
        insertEdgeNode(value, EdgeNodeType.TAIL);
        return value;
    }

    @Override
    public T get(int index) {
        // Time complexity O(n)
        // Check the index
        checkInRangeIndex(index);

        return getNodeAtIndex(index).value;
    }

    @Override
    public T insert(T value, int index) {
        // Time complexity O(n)
        // Check the index
        checkInRangeIndex(index);
        // Check for inserting head or tail
        if (index == 0 || index == length - 1) {
            return index == 0 ? prepend(value) : append(value);
        }
        // Check the input
        checkNotNullInput(value);

        MyEntry<T> entryToUnlink = getNodeAtIndex(index - 1);

        entryToUnlink.next = new MyEntry<>(value, entryToUnlink.previous, entryToUnlink.next);
        length++;
        return value;
    }

    @Override
    public void delete(int index) {
        // Time complexity O(n)
        // Check the index
        checkInRangeIndex(index);

        if (index == 0) {
            head = head.next;
            head.previous = null;
        } else if (index == length -1) {
            MyEntry<T> newTail = getNodeAtIndex(index - 1);
            newTail.next = null;
            tail = newTail;
        } else {
            MyEntry<T> nodeBeforeDelete = getNodeAtIndex(index - 1);
            MyEntry<T> nodeAfterDelete = nodeBeforeDelete.next.next;
            nodeBeforeDelete.next = nodeAfterDelete;
            if (Objects.nonNull(nodeAfterDelete)) {
                nodeAfterDelete.previous = nodeBeforeDelete;
            }
        }

        length--;
    }

    @Override
    public void reverse() {
        if (length <= 1) return;


        MyEntry<T> oldHead = head;
        MyEntry<T> current = head;
        MyEntry<T> next = null;
        MyEntry<T> previous = null;

        while (current != null) {
            next = current.next;
            current.next = previous;
            current.previous = next;
            previous = current;
            current = next;
        }

        head = previous;
        tail = oldHead;
    }

    private static <T> void checkNotNullInput(T value) throws IllegalArgumentException {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("Value CANNOT be null");
        }
    }

    /**
     * Check if given index is in range of items
     * @param index index to check
     * @throws IllegalArgumentException if range is not in <0; size - 1>
     */
    private void checkInRangeIndex(int index) throws IllegalArgumentException {
        if (index < 0 || index >= length) {
            throw new IllegalArgumentException("Index MUST be in range of <0;size)");
        }
    }

    private MyEntry<T> getNodeAtIndex(int index) {
        int counter = 0;

        MyEntry<T> node;
        if (index < length / 2) {
            node = head;
            while (counter != index) {
                node = node.next;
                counter++;
            }
        } else {
            node = tail;
            while (length - 1 - counter != index) {
                node = node.previous;
                counter++;
            }
        }

        return node;
    }

    /**
     * Insert the edge node (Head or Tail)
     * @param value inserting value
     * @param edgeNodeType type of edge node
     */
    private T insertEdgeNode(T value, EdgeNodeType edgeNodeType) throws IllegalArgumentException {
        // Check the input
        checkNotNullInput(value);
        // Create a new MyEntry
        MyEntry<T> newEdgeNode = new MyEntry<>(value, null, null);

        if (Objects.isNull(tail) || Objects.isNull(head)) {
            // If the tail or head are null (the head or tail are so)
            initHeadAndTail(newEdgeNode);
        } else if (EdgeNodeType.TAIL.equals(edgeNodeType)) {
            // Reset the old tail for the new one
            //  1. - Link the current tail to the new one
            //  2. - Set the tail as the new tail
            newEdgeNode.previous = tail;
            tail.next = newEdgeNode;
            tail = newEdgeNode;
        } else if (EdgeNodeType.HEAD.equals(edgeNodeType)) {
            // Reset the old head for the new one
            //  1.- Link the new head to the next as the old one
            //  2.- Set the new head
            newEdgeNode.next = head;
            head = newEdgeNode;
        }
        // Increase size
        length++;
        return value;
    }

    private void initHeadAndTail(MyEntry<T> entry) {
        head = entry;
        tail = head;
    }

    private enum EdgeNodeType {
        HEAD, TAIL
    }

    /**
     * Private mutable holder of the inserted data
     * and associated next value
     * @param <T> Type of data
     */
    private static class MyEntry<T> {
        T value;
        MyEntry<T> previous;
        MyEntry<T> next;

        public MyEntry(T value, MyEntry<T> previous, MyEntry<T> next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }
}
