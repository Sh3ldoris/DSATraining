package data_structures.hash_table;

public interface MyMap<K, V> {
    /**
     * Inset the new key and the associated value
     * @param key
     * @param value
     * @return inserted value
     * @throws IllegalArgumentException if the key or the value is null
     */
    V set(K key, V value) throws IllegalArgumentException;

    /**
     * Gets the stored value associated with the given key
     * @param key
     * @return the stored value associated with the key
     * @throws IllegalArgumentException if the key is null
     * @throws NullPointerException if the key is not present
     */
    V get(K key) throws IllegalArgumentException, NullPointerException;
}
