package data_structures.hash_table;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class represents the simple implementation of HashMap (hash table)
 */
public class MyHashMap<K, V> implements MyMap<K, V> {
    static final Logger LOGGER = Logger.getLogger(MyHashMap.class.getSimpleName());
    private static final int INITIAL_ARRAY_SIZE = 100;
    private int length;
    private List<MyEntry<K, V>>[] data;

    public static void main(String[] args) {
        final MyMap<Integer, String> myMap = new MyHashMap<>();
        myMap.set(14, "Hello World!");
        myMap.set(10, "Hello World!");

        final String value = myMap.get(14);
        LOGGER.log(Level.INFO, Arrays.toString(myMap.keys().toArray()));
        LOGGER.log(Level.INFO, value);
    }

    public MyHashMap() {
        this.length = 0;
        this.data = (List<MyEntry<K, V>>[]) Array.newInstance(List.class, MyHashMap.INITIAL_ARRAY_SIZE);
    }

    /**
     * Simple hashing function
     * @param key inserted
     * @param length actual length of the allocated space for items
     * @return random index for storing the key, value
     */
    private static int hash(Object key, int length) {
        return Math.abs(key.hashCode()) % length;
    }

    @Override
    public V set(K key, V value) {
        // Time complexity is O(1), in the worst scenario O(n), n - items inserted
        // Check is the data are null
        if (areKeyValueNull(key, value)) {
            throw new IllegalArgumentException("Key and value cannot be null!");
        }

        // Create a new address
        final int address = MyHashMap.hash(key, data.length);
        // Check if the space on the address is initialized
        if (Objects.isNull(data[address])) {
            // Initialize with the new LinkedList
            data[address] = new LinkedList<>();
        }

        // Insert the data
        data[address].add(new MyEntry<>(key, value));
        // increase the length
        length++;
        // Return the inserted value
        return value;
    }

    @Override
    public V get(K key) throws IllegalArgumentException, NullPointerException {
        // Time complexity is O(1), in the worst scenario O(n), n - items inserted
        // Check if the key is null
        if (Objects.isNull(key)) {
            throw new IllegalArgumentException("To get the value the key cannot be null!");
        }
        // Get the address and the bucket for the key
        final int address = MyHashMap.hash(key, data.length);
        final List<MyEntry<K, V>> bucket = data[address];
        // Search for the value in the bucket
        final V foundValue = findValueInBucket(key, bucket);
        // Return the value if founded, else throw null pointer exception
        if (Objects.nonNull(foundValue)) {
            return foundValue;
        } else {
            throw new NullPointerException("For the given key:" + key + " value is not found");
        }
    }

    @Override
    public Set<K> keys() {
        // This implementation returns keys in unordered order
        // To have keys or values ordered cache them alongside the data
        final Set<K> keys = new HashSet<>();

        for (List<MyEntry<K, V>> bucket : data) {
            if (Objects.isNull(bucket)) continue;

            for (MyEntry<K, V> entry : bucket) {
                keys.add(entry.key);
            }
        }

        return keys;
    }

    /**
     * Traverse the entry bucket and looks for the associated value
     * @param key not null checked key
     * @param bucket null checked entry bucked
     * @return the associated value with the given key
     */
    private V findValueInBucket(final K key, final Collection<MyEntry<K, V>> bucket) {
        // If the collection is null or empty return null
        if (Objects.isNull(bucket) || bucket.isEmpty()) {
            return  null;
        }
        // Else traverse bucket and look for the key
        return bucket.stream()
                .filter((MyEntry<K, V> entry) -> key.equals(entry.key))
                .map(MyEntry::value)
                .findFirst()
                .orElse(null);
    }

    /**
     * Check whether the key or the value is null
     * @param key
     * @param value
     * @return true if one of the key or the value is null
     */
    private boolean areKeyValueNull(K key, V value) {
        return Objects.isNull(key) || Objects.isNull(value);
    }

    /**
     * Private immutable holder of the inserted key and the value
     * @param key
     * @param value
     * @param <K> key type
     * @param <V> value type
     */
    private record MyEntry<K, V>(K key, V value) {}
}
