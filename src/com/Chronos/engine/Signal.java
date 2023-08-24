package com.Chronos.engine;

import com.Chronos.util.tuple.Pair;

import java.util.*;

public class Signal {
    public static final String reset = "Signal reset: all";
    private static List<Pair<String, List<Object[]>>> signals = new LinkedList<>();
    public static void sendSignal(String message, Object... packets) {
        if (message.equals(reset)) {
            signals = new LinkedList<>();
            return;
        }
        add(message, packets);
    }

    public static Optional<List<Object[]>> getSignal(String message) {
        if (contains(message)) {
            return Optional.ofNullable(get(message));
        }
        return Optional.empty();
    }

    private static void add(String message, Object[] packets) {
        if (!contains(message)) {
            List<Object[]> lo = new ArrayList<>();
            lo.add(packets);
            signals.add(new Pair<>(message, lo));
            return;
        }
        for (var signal : signals) {
            if (signal.key.equals(message)) {
                signal.value.add(packets);
                return;
            }
        }
    }

    private static boolean contains(String message) {
        for (var signal : signals) {
            if (signal.key.equals(message)) {
                return true;
            }
        }
        return false;
    }

    private static List<Object[]> get(String message) {
        for (var signal : signals) {
            if (signal.key.equals(message)) {
                return signal.value;
            }
        }
        return null;
    }
}
