package com.chronos.util.json;

import java.util.Objects;

/**
 * Represents a JSON string.
 */
public final class JSONString extends AbstractJSON {

    /**
     * The string value.
     */
    public String string;

    /**
     * Constructs a JSONString with the provided string value.
     *
     * @param string The string value.
     */
    public JSONString(String string) {
        this.string = string;
    }

    /**
     * Returns the string value.
     *
     * @return The string value.
     */
    public String toString() {
        return string;
    }

    /**
     * Converts the JSON string to a JSON formatted string.
     *
     * @param layer The indentation layer.
     * @return The JSON formatted string.
     */
    @Override
    public String toJSON(int layer) {
        return "\t".repeat(Math.max(0, layer))
                + '\"' + string + '\"';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JSONString that)) return false;
        return Objects.equals(string, that.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string);
    }
}
