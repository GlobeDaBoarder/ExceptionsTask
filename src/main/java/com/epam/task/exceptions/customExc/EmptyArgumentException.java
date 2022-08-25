package com.epam.task.exceptions.customExc;

public class EmptyArgumentException extends IllegalArgumentException{
    public EmptyArgumentException(String s) {
        super(s);
    }

    public EmptyArgumentException() {
    }
}
