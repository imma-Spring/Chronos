package com.chronos.engine;

import com.chronos.util.tuple.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * The Signal class manages signals associated with specific messages.
 */
public class Signal {
    // Constant for resetting all signals
    public static final String reset = "Signal reset: all";

    // List of signal-message pairs
    private static List<Pair<String, List<Object[]>>> signals = new LinkedList<>();

    /**
     * Sends a signal with a message and associated packets.
     *
     * @param message The message associated with the signal.
     * @param packets Additional data associated with the signal.
     */
    public static void sendSignal(String message, Object... packets) {
        if (message.equals(reset)) {
            // Reset all signals
            signals = new LinkedList<>();
            return;
        }
        // Add the message and packets to the list of signals
        add(message, packets);
    }

    /**
     * Retrieves the signal associated with a specific message.
     *
     * @param message The message associated with the desired signal.
     * @return An Optional containing the list of packets if the signal exists; otherwise, empty.
     */
    public static Optional<List<Object[]>> getSignal(String message) {
        return contains(message) ? Optional.ofNullable(get(message)) : Optional.empty();
    }

    // Private methods for internal signal management

    /**
     * Adds a message and associated packets to the list of signals.
     *
     * @param message The message associated with the signal.
     * @param packets Additional data associated with the signal.
     */
    private static void add(String message, Object[] packets) {
        if (!contains(message)) {
            // If the message is not already in the signals list, create a new entry
            List<Object[]> lo = new ArrayList<>();
            lo.add(packets);
            signals.add(new Pair<>(message, lo));
            return;
        }
        // If the message already exists, add the packets to the existing entry
        for (int i = 0; i < signals.size(); i++) {
            Pair<String, List<Object[]>> stringListPair = signals.get(i);
            if (stringListPair.key.equals(message)) {
                signals.get(i).value.add(packets);
                break;
            }
        }
    }

    /**
     * Checks if a message is already in the list of signals.
     *
     * @param message The message to check for existence in the signals list.
     * @return True if the message already exists; otherwise, false.
     */
    private static boolean contains(String message) {
        return signals.stream().anyMatch(signal -> signal.key.equals(message));
    }

    /**
     * Retrieves the list of packets associated with a specific message.
     *
     * @param message The message associated with the desired signal.
     * @return The list of packets if the signal exists; otherwise, null.
     */
    private static List<Object[]> get(String message) {
        for (Pair<String, List<Object[]>> stringListPair : signals) {
            if (stringListPair.key.equals(message)) {
                return stringListPair.value;
            }
        }
        return null;
    }
}
