package com.chronos.util.json;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Represents a JSON object.
 */
public final class JSONObject extends AbstractJSON implements JSONParse {
    /**
     * List of JSON pairs that make up this object.
     */
    public List<JSONPair> elements;

    /**
     * Constructs a JSONObject by parsing a string.
     *
     * @param content The JSON content in string format.
     */
    public JSONObject(String content) {
        elements = new LinkedList<>();
        parseContent(content);
    }

    /**
     * Constructs a JSONObject with specified JSON pairs.
     *
     * @param content The JSON pairs to include in this object.
     */
    public JSONObject(JSONPair... content) {
        elements = new LinkedList<>(List.of(content));
    }

    /**
     * Parses the JSON content and adds it as JSON pairs to the object.
     *
     * @param content The JSON content in string format.
     */
    @Override
    public void parseContent(String content) {
        int state = 1;
        AbstractJSON element = null, element1 = null;
        String element2 = "";
        for (int i = 0; i < content.length(); i++) {
            i = skipWhiteSpace(content, i);
            if (state == 3) state--;
            switch (content.charAt(i)) {
                case '\"' :
                    StringBuilder sb = new StringBuilder();
                    while(i + 1 < content.length() && content.charAt(++i) != '\"')
                        sb.append(content.charAt(i));
                    if (state == 1)
                        element2 = sb.toString();
                    else
                        element = new JSONString(sb.toString());
                    break;
                case '[' :
                    sb = new StringBuilder();
                    int sbc = 1;
                    while(i + 1 < content.length() && sbc > 0) {
                        i++;
                        if (content.charAt(i) == '[') sbc++;
                        if (content.charAt(i) == ']') {
                            sbc--;
                        }
                        if (sbc > 0) sb.append(content.charAt(i));
                    }
                    element = new JSONArray(sb.toString());
                    break;
                case '{' :
                    sb = new StringBuilder();
                    int bc = 1;
                    while(i + 1 < content.length() && bc > 0) {
                        i++;
                        if (content.charAt(i) == '{') bc++;
                        if (content.charAt(i) == '}') {
                            bc--;
                            continue;
                        }
                        if (bc > 0) sb.append(content.charAt(i));
                    }
                    element = new JSONObject(sb.toString());
                    break;
                case ',' :
                    state = 1;
                    break;
                case ':' :
                    state = 3;
                    break;
                default:
                    var q = extractNumber(content, i);
                    i = (int) q[0];
                    element = new JSONNumber(q[1]);
                    break;
            }
            if (state == 2) {
                elements.add(new JSONPair(element2, element));
            }
        }
    }

    /**
     * Extracts a number from the JSON content.
     *
     * @param content The JSON content in string format.
     * @param i The index to start extracting from.
     * @return An array containing the updated index and the extracted number.
     */
    @Override
    public double[] extractNumber(String content, int i)  {
        boolean decimal = false;
        StringBuilder sb = new StringBuilder();
        while(Character.isDigit(content.charAt(i)) || (content.charAt(i) == '.' && !decimal)) {
            sb.append(content.charAt(i));
            i++;
            if (content.charAt(i) == '.') decimal = true;
        }
        return new double[] {i, Double.parseDouble(sb.toString())};
    }

    /**
     * Skips any white spaces in the JSON content.
     *
     * @param content The JSON content in string format.
     * @param index The index to start skipping from.
     * @return The updated index after skipping white spaces.
     */
    @Override
    public int skipWhiteSpace(String content, int index){
        for (int i = index; i < content.length(); i++) {
            if (!Character.isWhitespace(content.charAt(i))) {
                return i;
            }
        }
        throw new RuntimeException();
    }

    /**
     * Adds a JSON pair to the object.
     *
     * @param content The JSON pair to add.
     */
    public void add(JSONPair content) {
        if (exists(content)) return;
        elements.add(content);
    }

    /**
     * Sets a value for a specified key in the object.
     *
     * @param content The JSON value to set.
     * @param target The key to set the value for.
     */
    public void set(AbstractJSON content, String target) {
        if (!exists(content)) return;
        int i = getIndex(target);
        if (i != -1) {
            elements.get(i).value = content;
        }
    }

    /**
     * Checks if a JSON element exists in the object.
     *
     * @param content The JSON element to check for.
     * @return True if the element exists, otherwise false.
     */
    public boolean exists(AbstractJSON content) {
        for (var e : elements) {
            if (e.equals(content) || e.value.equals(content)) return true;
        }
        return false;
    }

    /**
     * Checks if a specified key exists in the object.
     *
     * @param content The key to check for.
     * @return True if the key exists, otherwise false.
     */
    public boolean exists(String content) {
        for (var e : elements) {
            if (e.key.equals(content)) return true;
        }
        return false;
    }

    /**
     * Gets the index of a specified key.
     *
     * @param target The key to find the index for.
     * @return The index of the key, or -1 if not found.
     */
    public int getIndex(String target) {
        return IntStream.range(0, elements.size()).filter(i -> elements.get(i).key.equals(target)).findFirst().orElse(-1);
    }

    /**
     * Gets the JSON element associated with a specified key.
     *
     * @param target The key to find the element for.
     * @return The JSON element associated with the key, or null if not found.
     */
    public AbstractJSON getElement(String target) {
        for (var e : elements) {
            if (e.key.equals(target)) return e.value;
        }
        return null;
    }

    /**
     * Removes a key-value pair from the object.
     *
     * @param target The key to remove.
     * @return The JSON element associated with the removed key, or null if not found.
     */
    public AbstractJSON remove(String target) {
        if (!exists(target)) return null;
        int i = getIndex(target);
        return elements.remove(i).value;
    }

    /**
     * Generates a JSON representation of the object.
     *
     * This method formats the JSON object according to the specified indentation layer.
     *
     * @param layer The indentation layer.
     * @return The JSON string representation of the object.
     */
    @Override
    public String toJSON(int layer) {
        StringBuilder sb = new StringBuilder();
        sb.append("\t".repeat(Math.max(0, layer)));
        sb.append("{\n");
        for (int i = 0; i < elements.size(); i++) {
            var e = elements.get(i);
            sb.append (e.toJSON(Math.abs(layer) + 1));
            if (i != elements.size() - 1) sb.append (",");
            sb.append('\n');
        }
        sb.append("\t".repeat(Math.max(0, Math.abs(layer))));
        sb.append('}');
        return sb.toString();
    }

    /**
     * Converts the object to a string.
     *
     * @return The string representation of the object.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < elements.size(); i++) {
            var e = elements.get(i);
            sb.append (e);
            if (i + 1 < sb.length()) sb.append (", ");
        }
        sb.append('}');
        return sb.toString();
    }

    /**
     * Checks if this object is equal to another object.
     *
     * This method compares this object to another object.
     *
     * @param o The object to compare.
     * @return True if the objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JSONObject that)) return false;
        return Objects.equals(elements, that.elements);
    }

    /**
     * Generates a hash code for this object.
     *
     * This method generates a hash code based on the list of JSON pairs.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(elements);
    }
}
