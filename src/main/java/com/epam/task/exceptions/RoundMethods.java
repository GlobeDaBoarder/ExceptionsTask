package com.epam.task.exceptions;

public enum RoundMethods {
    FLOOR {
        public double roundFunction(double d) {
            return Math.floor(d);
        }
    },
    CEIL{
        public double roundFunction(double d) {
            return Math.ceil(d);
        }
    },
    ROUND{
        public double roundFunction(double d) {
            return Math.round(d);
        }
    };

    public abstract double roundFunction(double value);

    public int round(double roundedValue, int d) {
        int[] tenPowD = {1, 10, 100,1000, 10000, 100000, 1000000, 10000000, 100000000};
        return (int) (this.roundFunction(roundedValue / tenPowD[d])) * tenPowD[d];
    }

}
