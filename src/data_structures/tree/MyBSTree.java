package data_structures.tree;

public interface MyBSTree<T extends Comparable<T>> {

    void insert(T value);

    MyBSTreeImpl.MyNode<T> lookUp(T value);

    void remove(T value);

    boolean isEmpty();
}
