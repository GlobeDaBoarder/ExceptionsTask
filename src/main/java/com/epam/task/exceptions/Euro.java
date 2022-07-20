package com.epam.task.exceptions;

public class Euro implements Comparable<Euro>{
    private final int value;

    public Euro(int valueInCents) {
        if(valueInCents <= 0)
            throw new IllegalArgumentException("can't be negative");
        this.value = valueInCents;
    }

    public Euro(int euros, int cents){
        this(euros*100 + cents);
    }

    public Euro(Euro euro){
        this(euro.value);
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
