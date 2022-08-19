package com.epam.task.exceptions;

import com.epam.task.exceptions.customExc.*;

public class PurchaseFactory {
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

    private static PurchaseKind getPurchaseKind(String[] values) {
        if(values.length == 3)
            return PurchaseKind.PURCHASE;
        if (values.length == 4)
            return  PurchaseKind.PRICE_DISCOUNT_PURCHASE;
        throw new OutOfBoundArgumentException("Illegal amount of values");
    }

    public static Purchase createPurchase(String line) throws CsvLineException {
        return createPurchase(line.split(";"));
    }

    public static Purchase createPurchase(String[] values) throws CsvLineException {
        try{
            return getPurchaseKind(values).getPurchase(values);
        } catch (OutOfBoundArgumentException | NumberFormatException | NonPositiveArgumentException |
                EmptyArgumentException e) {
            throw new CsvLineException(values, e);
        }
    }
}
