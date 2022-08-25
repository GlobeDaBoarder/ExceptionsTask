package com.epam.task.exceptions.customExc;

import java.util.Arrays;

public class CsvLineException extends Exception{
    public CsvLineException(String[] values){
        super(Arrays.toString(values));
    }

    public CsvLineException(String[] values, Throwable cause) {
        super(Arrays.toString(values), cause);
    }
}
