package data_structures.tree;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyBSTreeImpl<T extends Comparable<T>> implements MyBSTree<T> {
    static final Logger LOGGER = Logger.getLogger(MyBSTreeImpl.class.getSimpleName());
    private int length;
    private MyNode<T> root;

    public static void main(String[] args) {
        final MyBSTree<Integer> tree = new MyBSTreeImpl<>();
        // Insert values
        tree.insert(9);
        tree.insert(4);
        tree.insert(6);
        tree.insert(20);
        tree.insert(170);
        tree.insert(15);
        tree.insert(1);

//        var node = tree.lookUp(4);
        tree.remove(170);

        LOGGER.log(Level.INFO, "Is tree empty: {0}", tree.isEmpty());
    }

    public MyBSTreeImpl() {
        this.root = null;
        this.length = 0;
    }

    @Override
    public void insert(T value) {
        // Check the input
        checkInput(value);

        if (Objects.isNull(root)) {
            root = new MyNode<>(value);
            return;
        }

        MyNode<T> current = root;
        while (true) {
            if (isLess(value, current)) {
                // Going left
                if (Objects.isNull(current.left)) {
                    // If the left node is empty set the inserting value
                    current.left = new MyNode<>(value);
                    length++; // Increase size
                    return; // End the loop
                }
                // Else go level down to the left
                current = current.left;
            } else {
                // Going right
                if (Objects.isNull(current.right)) {
                    // If the right node is empty set the inserting value
                    current.right = new MyNode<>(value);
                    length++; // Increase size
                    return; // End the loop
                }
                // Else go level down to the right
                current = current.right;
            }
        }
    }

    @Override
    public MyNode<T> lookUp(T value) {
        // Check the input
        checkInput(value);

        if (isEmpty()) {
            return null;
        }

        // Iterate and find the node with the given value
        MyNode<T> currentNode = root;
        while (Objects.nonNull(currentNode)) {
            if (isLess(value, currentNode)) {
                currentNode = currentNode.left;
            } else if (isMore(value, currentNode)) {
                currentNode = currentNode.right;
            } else if (isEquals(value, currentNode)) {
                return currentNode;
            }
        }

        // Nothing found
        return null;
    }

    @Override
    public void remove(T value) {
        checkInput(value);

        if (Objects.isNull(root)) {
            return;
        }

        MyNode<T> current = root;
        MyNode<T> parent = null;
        while (Objects.nonNull(current)) {

            if (isLess(value, current)) {
                // Going left
                parent = current;
                current = current.left;
            } else if (isMore(value, current)) {
                // Going right
                parent = current;
                current = current.right;
            } else if (isEquals(value, current)) {
                // There is a match - find the lowest node than the current
                if (isNull(current.right)) {
                    // Right node is null
                    if (isNull(parent)) {
                        // Deleting the root node - switching the left nodes instead current root
                        root = current.left;
                    } else {
                        if (isLess(current.value, parent)) {
                            // If the parent value is more (current left node)
                            parent.left = current.left;
                        } else if (isMore(current.value, parent)) {
                            // If parent value is less (current right node)
                            parent.right = current.left;
                        }
                    }
                } else if (isNull(current.right.left)) {
                    // Right child doesn't have a left child
                    if (isNull(parent)) {
                        root = current.left;
                    } else {
                        // Set the left child to the right child's left
                        current.right.left = current.left;

                        if (isLess(current.value, parent)) {
                            // If the parent value is more (current left node)
                            parent.left = current.right;
                        } else if (isMore(current.value, parent)) {
                            // If parent value is less (current right node)
                            parent.right = current.right;
                        }
                    }
                } else {
                    // Find the right child's most left child node
                    NodeWithParent<T> nodeWithparent = findTheMostLeftNode(current.right);
                    nodeWithparent.parent.left = nodeWithparent.node.right;
                    nodeWithparent.node.left = current.left;
                    nodeWithparent.node.right = current.right;

                    if (isNull(parent)) {
                        root = nodeWithparent.node;
                    } else {
                        if (isLess(current.value, parent)) {
                            // If the parent value is more (current left node)
                            parent.left = nodeWithparent.node;
                        } else if (isMore(current.value, parent)) {
                            // If parent value is less (current right node)
                            parent.right = nodeWithparent.node;
                        }
                    }
                }
                length--;
                return;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    private void checkInput(T value) {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("Value cannot be null");
        }
    }

    private boolean isLess(T value, MyNode<T> compareToNode) {
        return value.compareTo(compareToNode.value) < 0;
    }

    private boolean isMore(T value, MyNode<T> compareToNode) {
        return value.compareTo(compareToNode.value) > 0;
    }

    private boolean isEquals(T value, MyNode<T> compareToNode) {
        return value.compareTo(compareToNode.value) == 0;
    }

    private boolean isNull(Object object) {
        return Objects.isNull(object);
    }

    private NodeWithParent<T> findTheMostLeftNode(MyNode<T> currentNode) {
        MyNode<T> mostLeft = currentNode.left;
        MyNode<T> leftParent = currentNode;

        while (!isNull(mostLeft)) {
            mostLeft = mostLeft.left;
            leftParent = mostLeft;
        }

        return new NodeWithParent<>(mostLeft, leftParent);
    }

    private record NodeWithParent<T>(MyNode<T> node, MyNode<T> parent) {}

    public static class MyNode<T> {
        public MyNode<T> left;
        public MyNode<T> right;
        public T value;

        public MyNode(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}
