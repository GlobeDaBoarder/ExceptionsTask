package com.epam.task.exceptions;

import com.epam.task.exceptions.customExc.NegativeArgumentException;
import com.epam.task.exceptions.customExc.NonPositiveArgumentException;
import com.epam.task.exceptions.customExc.OutOfBoundArgumentException;

public class Euro implements Comparable<Euro>{
    private final int value;

    public Euro(int valueInCents) throws NonPositiveArgumentException {
        if(valueInCents < 0)
            throw new NegativeArgumentException("value can't be non positive");
        this.value = valueInCents;
    }

    public Euro(int euros, int cents){
        this(validateParameters(euros, cents));
    }

    public Euro(Euro euro){
        this(euro.value);
    }

    public Euro(){
        this(0);
    }

    public Euro(String strCents){
        this(Integer.parseInt(strCents));
    }

    private static int validateParameters(int euros, int cents) throws NonPositiveArgumentException{
        if(euros < 0)
            throw new NegativeArgumentException("euros can't be less than 0");
        if (cents < 0)
            throw  new NegativeArgumentException("cents can't be less than 0");
        if (cents > 99)
            throw new OutOfBoundArgumentException("cent's can't be more than 100");

        return euros*100 + cents;

    }

    public Euro add(Euro euro){
        return new Euro(this.value + euro.value);
    }

    public Euro sub(Euro euro){
        return new Euro(this.value - euro.value);
    }

    public Euro mul(int k){
        return new Euro(this.value * k);
    }

    public Euro mul(double x, RoundMethods roundMethod, int d){
        return new Euro(roundMethod.round(value * x, d));
    }
    public Euro round(RoundMethods roundMethod, int d){
        return new Euro(roundMethod.round(value, d));
    }



    @Override
    public String toString() {
        return value / 100 + "." + value % 100 / 10 + value % 10;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Euro)) {
            return false;
        }

        Euro other = (Euro) o;

        return this.value == other.value;
    }


    @Override
    public int compareTo(Euro o) {
        return this.value - o.value;
    }
}
