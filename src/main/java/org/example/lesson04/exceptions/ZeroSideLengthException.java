package org.example.lesson04.exceptions;

public class ZeroSideLengthException extends RuntimeException {

    public ZeroSideLengthException() {
        super();
    }

    public ZeroSideLengthException(String s) {
        super(s);
    }
}
