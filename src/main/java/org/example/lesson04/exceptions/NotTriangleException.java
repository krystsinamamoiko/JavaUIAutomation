package org.example.lesson04.exceptions;

public class NotTriangleException extends RuntimeException {

    public NotTriangleException() {
        super();
    }

    public NotTriangleException(String s) {
        super(s);
    }
}
