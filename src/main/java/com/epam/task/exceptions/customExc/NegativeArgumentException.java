package com.epam.task.exceptions.customExc;

public class NegativeArgumentException extends NonPositiveArgumentException{
    public NegativeArgumentException() {
    }

    public NegativeArgumentException(String s) {
        super(s);
    }
}
