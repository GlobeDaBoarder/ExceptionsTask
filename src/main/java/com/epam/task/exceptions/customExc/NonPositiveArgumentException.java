package com.epam.task.exceptions.customExc;

public class NonPositiveArgumentException extends IllegalArgumentException{
    public NonPositiveArgumentException() {
    }

    public NonPositiveArgumentException(String s) {
        super(s);
    }
}
