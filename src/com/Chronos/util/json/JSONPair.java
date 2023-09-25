package com.Chronos.util.json;

import java.util.Objects;

public final class JSONPair extends AbstractJSON {
    public String key;
    public AbstractJSON value;

    public JSONPair(String key, AbstractJSON value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        return "%s : %s".formatted(key, value);
    }

    @Override
    public String toJSON(int layer) {
        return "\t".repeat(Math.max(0, layer))
                + "\"%s\" : %s".formatted(key, value.toJSON(-layer));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JSONPair jsonPair)) return false;
        return Objects.equals(key, jsonPair.key) && Objects.equals(value, jsonPair.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
