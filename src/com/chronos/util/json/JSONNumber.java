package com.chronos.util.json;

import java.util.Objects;

/**
 * Represents a JSON number.
 */
public final class JSONNumber extends AbstractJSON {
    /**
     * The numerical value of the JSON number.
     */
    public double element;

    /**
     * Constructs a JSONNumber with a specified value.
     *
     * @param i The numerical value of the JSON number.
     */
    public JSONNumber(double i) {
        element = i;
    }

    /**
     * Converts the JSON number to a string.
     *
     * @return The string representation of the JSON number.
     */
    public String toString() {
        return String.valueOf(element);
    }

    /**
     * Generates a JSON representation of the JSON number.
     *
     * This method formats the JSON number according to the specified indentation layer.
     *
     * @param layer The indentation layer.
     * @return The JSON string representation of the JSON number.
     */
    @Override
    public String toJSON(int layer) {
        return "\t".repeat(Math.max(0, layer))
                + this;
    }

    /**
     * Checks if this JSON number is equal to another object.
     *
     * This method compares this JSON number to another object.
     *
     * @param o The object to compare.
     * @return True if the objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JSONNumber that)) return false;
        return Double.compare(that.element, element) == 0;
    }

    /**
     * Generates a hash code for this JSON number.
     *
     * This method generates a hash code based on the numerical value.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(element);
    }
}
