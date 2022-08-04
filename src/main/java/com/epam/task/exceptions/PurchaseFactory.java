package com.epam.task.exceptions;

import com.epam.task.exceptions.customExc.CsvLineException;
import com.epam.task.exceptions.customExc.EmptyArgumentException;
import com.epam.task.exceptions.customExc.NonPositiveArgumentException;
import com.epam.task.exceptions.customExc.OutOfBoundArgumentException;

public class PurchaseFactory {
    private enum PurchaseKind {
        PURCHASE {
            @Override
            protected AbstractPurchase getPurchase(String[] values) {
                return new Purchase(
                        new Product(
                                values[0],
                                new Euro(Integer.parseInt(values[1]))
                        ),
                        Integer.parseInt(values[2])
                );
            }
        },
        PRICE_DISCOUNT_PURCHASE {
            @Override
            protected AbstractPurchase getPurchase(String[] values) {
                return new PriceDiscountPurchase(
                        new Product(
                                values[0],
                                new Euro(Integer.parseInt(values[1]))
                        ),
                        Integer.parseInt(values[2]),
                        new Euro(Integer.parseInt(values[3]))
                );
            }
        };

        protected abstract AbstractPurchase getPurchase(String[] values) throws NumberFormatException, NonPositiveArgumentException,
                EmptyArgumentException;
    }

    private PurchaseKind getPurchaseKind(String[] values) throws OutOfBoundArgumentException {
        if(values.length == 3)
            return PurchaseKind.PURCHASE;
        if (values.length == 4)
            return  PurchaseKind.PRICE_DISCOUNT_PURCHASE;
        throw new OutOfBoundArgumentException("Illegal amount of values");
    }

    public AbstractPurchase createPurchase(String line) throws CsvLineException{
        return createPurchase(line.split(";"));
    }

    public AbstractPurchase createPurchase(String[] values) throws CsvLineException {
        try{
            return getPurchaseKind(values).getPurchase(values);
        } catch (OutOfBoundArgumentException | NumberFormatException | NonPositiveArgumentException |
                EmptyArgumentException e) {
            throw new CsvLineException(values, e);
        }
    }
}
