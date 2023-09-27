package com.chronos.util.json;

import java.util.Objects;

/**
 * Represents a key-value pair in a JSON object.
 */
public final class JSONPair extends AbstractJSON {
    /**
     * The key of the pair.
     */
    public String key;

    /**
     * The value of the pair.
     */
    public AbstractJSON value;

    /**
     * Constructs a JSON pair with the specified key and value.
     *
     * @param key The key of the pair.
     * @param value The value of the pair.
     */
    public JSONPair(String key, AbstractJSON value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns a string representation of the JSON pair in the format "key : value".
     *
     * @return The string representation of the JSON pair.
     */
    public String toString() {
        return "%s : %s".formatted(key, value);
    }

    /**
     * Generates a JSON representation of the pair.
     *
     * This method formats the JSON pair according to the specified indentation layer.
     *
     * @param layer The indentation layer.
     * @return The JSON string representation of the pair.
     */
    @Override
    public String toJSON(int layer) {
        return "\t".repeat(Math.max(0, layer))
                + "\"%s\" : %s".formatted(key, value.toJSON(-layer));
    }

    /**
     * Checks if this JSON pair is equal to another object.
     *
     * This method compares this JSON pair to another object.
     *
     * @param o The object to compare.
     * @return True if the objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JSONPair jsonPair)) return false;
        return Objects.equals(key, jsonPair.key) && Objects.equals(value, jsonPair.value);
    }

    /**
     * Generates a hash code for this JSON pair.
     *
     * This method generates a hash code based on the key and value.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
