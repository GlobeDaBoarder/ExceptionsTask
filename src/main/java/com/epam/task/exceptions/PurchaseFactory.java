package com.epam.task.exceptions;

import com.epam.task.exceptions.customExc.*;

import java.util.concurrent.RecursiveTask;

public class PurchaseFactory {
    private static final int PURCHASE_FIELDS_NUMBER = 3;
    private static final int DISCOUNT_PURCHASE_FIELDS_NUMBER = 4;
    private enum PurchaseKind {
        PURCHASE {
            @Override
            protected Purchase getPurchase(String[] values) {
                return new Purchase(values);
            }
        },
        PRICE_DISCOUNT_PURCHASE {
            @Override
            protected Purchase getPurchase(String[] values) {
                return new PriceDiscountPurchase(values);
            }
        };

        protected abstract Purchase getPurchase(String[] values);
    }

    private static PurchaseKind getPurchaseKind(String[] values) throws CsvLineException {
        try {
            return PurchaseKind.values()[values.length - PURCHASE_FIELDS_NUMBER];
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            throw new CsvLineException(values, e);
        }
    }

    public static Purchase createPurchase(String line) throws CsvLineException {
        return createPurchase(line.split(";"));
    }

    public static Purchase createPurchase(String[] values) throws CsvLineException {
        try{
            return getPurchaseKind(values).getPurchase(values);
        } catch (IllegalArgumentException e) {
            throw new CsvLineException(values, e);
        }
    }

    public static boolean isPurchase(String[] values){
        return values.length == PURCHASE_FIELDS_NUMBER;
    }

    public static boolean isDiscountPurchase(String[] values){
        return values.length == DISCOUNT_PURCHASE_FIELDS_NUMBER;
    }
}
