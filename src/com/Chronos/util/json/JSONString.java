package com.Chronos.util.json;

import java.util.Objects;

public final class JSONString extends AbstractJSON {
    public String string;
    public JSONString(String string) {
        this.string = string;
    }
    public String toString() {
        return string;
    }

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
