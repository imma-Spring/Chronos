package com.chronos.util.json;

import java.util.Objects;

/**
 * Parses and represents JSON content.
 */
public final class JSONParser implements JSONParse {

    /**
     * The parsed JSON element.
     */
    public AbstractJSON element;

    /**
     * Constructs a JSONParser with the provided content.
     *
     * @param content The JSON content to parse.
     */
    public JSONParser(String content) {
        parseContent(content);
    }

    /**
     * Constructs a JSONParser with the provided AbstractJSON element.
     *
     * @param content The AbstractJSON element.
     */
    public JSONParser(AbstractJSON content) {
        element = content;
    }

    /**
     * Parses the JSON content.
     *
     * @param content The JSON content to parse.
     */
    @Override
    public void parseContent(String content) {
        for (int i = 0; i < content.length(); i++) {
            i = skipWhiteSpace(content, i);
            switch (content.charAt(i)) {
                case '\"' :
                    StringBuilder sb = new StringBuilder();
                    while(i + 1 < content.length() && content.charAt(++i) != '\"')
                        sb.append(content.charAt(i));
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
                default:
                    var q = extractNumber(content, i);
                    i = (int) q[0];
                    element = new JSONNumber(q[1]);
                    break;
            }
        }
    }

    /**
     * Extracts a number from the JSON content.
     *
     * @param content The JSON content.
     * @param i       The current index in the content.
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
     * Skips white space in the JSON content.
     *
     * @param content The JSON content.
     * @param index   The current index in the content.
     * @return The updated index after skipping white space.
     */
    @Override
    public int skipWhiteSpace(String content, int index){
        for (int i = index; i < content.length(); i++) {
            if (content.charAt(i) != ' ') {
                return i;
            }
        }
        throw new RuntimeException();
    }

    /**
     * Converts the parsed element to a JSON formatted string.
     *
     * @return The JSON formatted string.
     */
    public String toJSON() {
        return element.toJSON(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JSONParser that)) return false;
        return Objects.equals(element, that.element);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element);
    }
}
