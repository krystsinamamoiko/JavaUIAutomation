package org.example.lesson04.exceptions;

public class NegativeSideLengthException extends RuntimeException {

    public NegativeSideLengthException() {
        super();
    }

    public NegativeSideLengthException(String s) {
        super(s);
    }
}
