package com.Chronos.util.json;

import java.util.*;
import java.util.stream.IntStream;

public final class JSONObject extends AbstractJSON implements JSONParse {
    public List<JSONPair> elements;
    public JSONObject(String content) {
        elements = new LinkedList<>();
        parseContent(content);
    }

    public JSONObject(JSONPair... content) {
        elements = new LinkedList<>(List.of(content));
    }

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

    @Override
    public int skipWhiteSpace(String content, int index){
        for (int i = index; i < content.length(); i++) {
            if (!Character.isWhitespace(content.charAt(i))) {
                return i;
            }
        }
        throw new RuntimeException();
    }

    public void add(JSONPair content) {
        if (exists(content)) return;
        elements.add(content);
    }

    public void set(AbstractJSON content, String target) {
        if (!exists(content)) return;
        int i = getIndex(target);
        if (i != -1) {
            elements.get(i).value = content;
        }
    }

    public boolean exists(AbstractJSON content) {
        for (var e : elements) {
            if (e.equals(content) || e.value.equals(content)) return true;
        }
        return false;
    }

    public boolean exists(String content) {
        for (var e : elements) {
            if (e.key.equals(content)) return true;
        }
        return false;
    }

    public int getIndex(String target) {
        return IntStream.range(0, elements.size()).filter(i -> elements.get(i).key.equals(target)).findFirst().orElse(-1);
    }

    public AbstractJSON getElement(String target) {
        for (var e : elements) {
            if (e.key.equals(target)) return e.value;
        }
        return null;
    }

    public AbstractJSON remove(String target) {
        if (!exists(target)) return null;
        int i = getIndex(target);
        return elements.remove(i).value;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JSONObject that)) return false;
        return Objects.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements);
    }
}
