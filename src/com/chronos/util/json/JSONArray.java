package com.chronos.util.json;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Represents a JSON array.
 */
public final class JSONArray extends AbstractJSON implements JSONParse {

    /**
     * List of elements in the JSON array.
     */
    public List<AbstractJSON> elements;

    /**
     * Constructs a JSONArray from a string content.
     *
     * @param content The string content representing the JSON array.
     */
    public JSONArray(String content) {
        elements = new LinkedList<>();
        parseContent(content);
    }

    /**
     * Constructs a JSONArray from an array of AbstractJSON elements.
     *
     * @param content The elements to include in the JSON array.
     */
    public JSONArray(AbstractJSON... content) {
        elements = new LinkedList<>(List.of(content));
    }

    /**
     * Parses the content of the JSONArray.
     *
     * This method reads through the content character by character, identifying JSON
     * objects, strings, numbers, and whitespace, and creates corresponding AbstractJSON
     * objects to represent them. These objects are then added to the 'elements' list.
     *
     * @param content The string content representing the JSON array.
     */
    @Override
    public void parseContent(String content) {
        for (int i = 0; i < content.length(); i++) {
            StringBuilder sb = new StringBuilder();
            i = skipWhiteSpace(content, i);
            boolean white = false;
            AbstractJSON element = null;
            switch (content.charAt(i)) {
                case '\"' -> {
                    // Parsing a JSON string
                    while (i + 1 < content.length() && content.charAt(++i) != '"')
                        sb.append(content.charAt(i));
                    element = new JSONString(sb.toString());
                }
                case '[' -> {
                    // Parsing a nested JSONArray
                    int sbc = 1;
                    while (i + 1 < content.length() && sbc > 0) {
                        i++;
                        if (content.charAt(i) == '[') sbc++;
                        if (content.charAt(i) == ']') {
                            sbc--;
                        }
                        if (sbc > 0) sb.append(content.charAt(i));
                    }
                    element = new JSONArray(sb.toString());
                }
                case '{' -> {
                    // Parsing a nested JSONObject
                    int bc = 1;
                    while (i + 1 < content.length() && bc > 0) {
                        i++;
                        if (content.charAt(i) == '{') bc++;
                        if (content.charAt(i) == '}') {
                            bc--;
                            continue;
                        }
                        if (bc > 0) sb.append(content.charAt(i));
                    }
                    element = new JSONObject(sb.toString());
                }
                case ' ', ',' -> {
                    white = true;
                    break;
                }
                default -> {
                    // Parsing a JSON number
                    var q = extractNumber(content, i);
                    i = (int) q[0];
                    element = new JSONNumber(q[1]);
                }
            }
            if (!white) elements.add(element);
        }
    }

    /**
     * Extracts a number from the JSON content.
     *
     * This method iterates over the characters in the content to identify and extract
     * numbers, both integers and decimals.
     *
     * @param content The JSON content.
     * @param i       The current index.
     * @return An array containing the new index and the extracted number.
     */
    @Override
    public double[] extractNumber(String content, int i)  {
        boolean decimal = false;
        StringBuilder sb = new StringBuilder();
        while(i < content.length() && (Character.isDigit(content.charAt(i)) || (content.charAt(i) == '.' && !decimal))) {
            sb.append(content.charAt(i));
            if (content.charAt(i) == '.') decimal = true;
            i++;
        }
        return new double[] {i, Double.parseDouble(sb.toString())};
    }

    /**
     * Skips any white spaces in the JSON content.
     *
     * This method advances the index until a non-whitespace character is encountered.
     *
     * @param content The JSON content.
     * @param index   The current index.
     * @return The index after skipping the white spaces.
     */
    @Override
    public int skipWhiteSpace(String content, int index){
        for (int i = index; i < content.length(); i++) {
            if (!(Character.isWhitespace(content.charAt(i)) || content.charAt(i) == ',')) {
                return i;
            }
        }
        return content.length() - 1;
    }

    /**
     * Adds an element to the JSONArray.
     *
     * This method adds an element to the list if it doesn't already exist.
     *
     * @param content The element to add.
     */
    public void add(AbstractJSON content) {
        if (exists(content)) return;
        elements.add(content);
    }

    /**
     * Sets an element in the JSONArray.
     *
     * This method replaces an element in the list if it already exists.
     *
     * @param content The new element.
     * @param target  The element to replace.
     */
    public void set(AbstractJSON content, AbstractJSON target) {
        if (!exists(content)) return;
        int i = getIndex(target);
        if (i != -1) {
            elements.set(i, content);
        }
    }

    /**
     * Checks if an element exists in the JSONArray.
     *
     * This method iterates over the elements to find a match.
     *
     * @param content The element to check.
     * @return True if the element exists, otherwise false.
     */
    public boolean exists(AbstractJSON content) {
        for (var e : elements) {
            if (e.equals(content)) return true;
        }
        return false;
    }

    /**
     * Gets the index of an element in the JSONArray.
     *
     * This method uses a stream to find the index of a matching element.
     *
     * @param target The element to find.
     * @return The index of the element, or -1 if not found.
     */
    public int getIndex(AbstractJSON target) {
        return IntStream.range(0, elements.size()).filter(i -> elements.get(i).equals(target)).findFirst().orElse(-1);
    }

    /**
     * Gets an element from the JSONArray.
     *
     * This method iterates over the elements to find a matching element.
     *
     * @param target The element to retrieve.
     * @return The element if found, otherwise null.
     */
    public AbstractJSON getElement(AbstractJSON target) {
        for (var e : elements) {
            if (e.equals(target)) return e;
        }
        return null;
    }

    /**
     * Removes an element from the JSONArray.
     *
     * This method removes a matching element from the list.
     *
     * @param target The element to remove.
     * @return The removed element, or null if not found.
     */
    public AbstractJSON remove(AbstractJSON target) {
        if (!exists(target)) return null;
        int i = getIndex(target);
        return elements.remove(i);
    }

    /**
     * Generates a JSON representation of the JSONArray.
     *
     * This method builds a JSON string representation of the elements.
     *
     * @param layer The indentation layer.
     * @return The JSON string representation.
     */
    @Override
    public String toJSON(int layer) {
        StringBuilder sb = new StringBuilder();
        sb.append("\t".repeat(Math.max(0, layer)));
        sb.append("[\n");
        List<AbstractJSON> el = new ArrayList<>(List.copyOf(elements));
        for (int i = 0; i < elements.size(); i++) {
            var e = el.get(i);
            sb.append (e.toJSON(Math.abs(layer) + 1));
            if (i != elements.size() - 1) sb.append (",");
            sb.append('\n');
        }
        sb.append("\t".repeat(Math.max(0, Math.abs(layer))));
        sb.append(']');
        return sb.toString();
    }

    /**
     * Generates a string representation of the JSONArray.
     *
     * This method builds a string representation of the elements.
     *
     * @return The string representation.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        List<AbstractJSON> el = new ArrayList<>(List.copyOf(elements));
        for (int i = 0; i < elements.size(); i++) {
            var e = el.get(i);
            sb.append (e);
            if (i + 1 < sb.length()) sb.append (", ");
        }
        sb.append(']');
        return sb.toString();
    }

    /**
     * Checks if this JSONArray is equal to another object.
     *
     * This method compares this JSONArray to another object.
     *
     * @param o The object to compare.
     * @return True if the objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JSONArray jsonArray)) return false;
        return Objects.equals(elements, jsonArray.elements);
    }

    /**
     * Generates a hash code for this JSONArray.
     *
     * This method generates a hash code based on the elements.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(elements);
    }
}
