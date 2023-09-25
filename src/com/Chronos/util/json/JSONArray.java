package com.Chronos.util.json;

import java.util.*;
import java.util.stream.IntStream;

public final class JSONArray extends AbstractJSON implements JSONParse {
    public List<AbstractJSON> elements;
    public JSONArray(String content) {
        elements = new LinkedList<>();
        parseContent(content);
    }

    public JSONArray(AbstractJSON... content) {
        elements = new LinkedList<>(List.of(content));
    }

    @Override
    public void parseContent(String content) {
        for (int i = 0; i < content.length(); i++) {
            StringBuilder sb = new StringBuilder();
            i = skipWhiteSpace(content, i);
            boolean white = false;
            AbstractJSON element = null;
            switch (content.charAt(i)) {
                case '\"' -> {
                    while (i + 1 < content.length() && content.charAt(++i) != '"')
                        sb.append(content.charAt(i));
                    element = new JSONString(sb.toString());
                }
                case '[' -> {
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
                    var q = extractNumber(content, i);
                    i = (int) q[0];
                    element = new JSONNumber(q[1]);
                }
            }
            if (!white) elements.add(element);
        }
    }

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

    @Override
    public int skipWhiteSpace(String content, int index){
        for (int i = index; i < content.length(); i++) {
            if (!(Character.isWhitespace(content.charAt(i)) || content.charAt(i) == ',')) {
                return i;
            }
        }
        return content.length() - 1;
    }

    public void add(AbstractJSON content) {
        if (exists(content)) return;
        elements.add(content);
    }

    public void set(AbstractJSON content, AbstractJSON target) {
        if (!exists(content)) return;
        int i = getIndex(target);
        if (i != -1) {
            elements.set(i, content);
        }
    }

    public boolean exists(AbstractJSON content) {
        for (var e : elements) {
            if (e.equals(content)) return true;
        }
        return false;
    }

    public int getIndex(AbstractJSON target) {
        return IntStream.range(0, elements.size()).filter(i -> elements.get(i).equals(target)).findFirst().orElse(-1);
    }

    public AbstractJSON getElement(AbstractJSON target) {
        for (var e : elements) {
            if (e.equals(target)) return e;
        }
        return null;
    }

    public AbstractJSON remove(AbstractJSON target) {
        if (!exists(target)) return null;
        int i = getIndex(target);
        return elements.remove(i);
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JSONArray jsonArray)) return false;
        return Objects.equals(elements, jsonArray.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements);
    }
}
