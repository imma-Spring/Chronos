package com.chronos.util.json;

/**
 * Represents an abstract JSON object.
 */
public abstract class AbstractJSON {

    /**
     * Converts the object to JSON format with indentation.
     *
     * @param layer The indentation layer.
     * @return The object in JSON format.
     */
    public abstract String toJSON(int layer);
}
