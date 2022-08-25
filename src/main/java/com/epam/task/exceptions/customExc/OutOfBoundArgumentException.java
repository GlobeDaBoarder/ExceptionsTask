package com.epam.task.exceptions.customExc;

public class OutOfBoundArgumentException extends IllegalArgumentException{
    public OutOfBoundArgumentException() {
    }

    public OutOfBoundArgumentException(String s) {
        super(s);
    }
}
