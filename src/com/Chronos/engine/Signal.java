package com.Chronos.engine;

import com.Chronos.util.tuple.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
        return contains(message) ? Optional.ofNullable(get(message)) : Optional.empty();
    }

    private static void add(String message, Object[] packets) {
        if (!contains(message)) {
            List<Object[]> lo = new ArrayList<>();
            lo.add(packets);
            signals.add(new Pair<>(message, lo));
            return;
        }
        for (int i = 0; i < signals.size(); i++) {
            Pair<String, List<Object[]>> stringListPair = signals.get(i);
            if (stringListPair.key.equals(message)) {
                signals.get(i).value.add(packets);
                break;
            }
        }
    }

    private static boolean contains(String message) {
        return signals.stream().anyMatch(signal -> signal.key.equals(message));
    }

    private static List<Object[]> get(String message) {
        for (Pair<String, List<Object[]>> stringListPair : signals) {
            if (stringListPair.key.equals(message)) {
                return stringListPair.value;
            }
        }
        return null;
    }
}
