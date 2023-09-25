package com.Chronos.util.json;

import java.util.Objects;

public final class JSONNumber extends AbstractJSON {
    public double element;
    public JSONNumber(double i) {
        element = i;
    }

    public String toString() {
        return String.valueOf(element);
    }

    @Override
    public String toJSON(int layer) {
        return "\t".repeat(Math.max(0, layer))
                + this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JSONNumber that)) return false;
        return Double.compare(that.element, element) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(element);
    }
}
