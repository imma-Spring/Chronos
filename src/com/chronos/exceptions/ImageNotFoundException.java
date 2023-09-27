package com.chronos.exceptions;

import java.io.FileNotFoundException;

/**
 * Custom exception for when an image file is not found.
 */
public class ImageNotFoundException extends FileNotFoundException {
    /**
     * Constructs a new ImageNotFoundException with no detail message.
     */
    public ImageNotFoundException() {
        // Calls the constructor of the superclass (FileNotFoundException) with no message.
    }

    /**
     * Constructs a new ImageNotFoundException with the specified detail message.
     *
     * @param s The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ImageNotFoundException(String s) {
        // Calls the constructor of the superclass (FileNotFoundException) with the provided message.
        super(s);
    }
}
