package com.chronos.util.tuple;

import java.util.Objects;

/**
 * Represents a generic key-value pair.
 *
 * @param <K> The type of the key.
 * @param <V> The type of the value.
 */
public class Pair<K, V> {

    /**
     * The key of the pair.
     */
    public K key;

    /**
     * The value of the pair.
     */
    public V value;

    /**
     * Constructs a pair with the given key and value.
     *
     * @param key   The key.
     * @param value The value.
     */
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Constructs a pair with null key and value.
     */
    public Pair() {
        this(null, null);
    }

    /**
     * Checks if this pair is equal to another object.
     *
     * @param o The object to compare.
     * @return True if the pairs are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair<?, ?> pair)) return false;
        return Objects.equals(key, pair.key) && Objects.equals(value, pair.value);
    }

    /**
     * Generates the hash code for this pair.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    /**
     * Returns a string representation of this pair.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
