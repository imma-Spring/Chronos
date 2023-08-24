package com.Chronos.util.tuple;

import java.util.Objects;

public class Pair<K, V> {
    public K key;
    public V value;
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Pair() {
        this(null, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair<?, ?> pair)) return false;
        return Objects.equals(key, pair.key) && Objects.equals(value, pair.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
