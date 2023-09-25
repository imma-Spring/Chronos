package com.Chronos.util.json;

public interface JSONParse {
    void parseContent(String content);
    double[] extractNumber(String content, int i);
    int skipWhiteSpace(String content, int index);
}
