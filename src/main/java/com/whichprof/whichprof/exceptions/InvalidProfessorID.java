package com.whichprof.whichprof.exceptions;

public class InvalidProfessorID extends RuntimeException {
    public InvalidProfessorID(String message) {
        super(message);
    }
}
