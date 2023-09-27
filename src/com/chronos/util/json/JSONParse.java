package com.chronos.util.json;

/**
 * Interface for parsing JSON content.
 */
public interface JSONParse {
    /**
     * Parses the content of a JSON element.
     *
     * @param content The content to parse.
     */
    void parseContent(String content);

    /**
     * Extracts a number from the JSON content.
     *
     * @param content The JSON content.
     * @param i The current index.
     * @return An array containing the new index and the extracted number.
     */
    double[] extractNumber(String content, int i);

    /**
     * Skips whitespace characters in the JSON content.
     *
     * @param content The JSON content.
     * @param index The current index.
     * @return The index after skipping whitespace characters.
     */
    int skipWhiteSpace(String content, int index);
}
