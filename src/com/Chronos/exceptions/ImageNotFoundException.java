package com.Chronos.exceptions;

import java.io.FileNotFoundException;

public class ImageNotFoundException extends FileNotFoundException {
    public ImageNotFoundException() {
    }

    public ImageNotFoundException(String s) {
        super(s);
    }
}
