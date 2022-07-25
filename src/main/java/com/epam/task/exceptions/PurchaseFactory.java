package com.epam.task.exceptions;

public class PurchaseFactory {
    public AbstractPurchase createPurchase(String[] values) {
        if (values.length == 3) {
            try {
                return new Purchase(
                        new Product(
                                values[0],
                                new Euro(Integer.parseInt(values[1]))
                        ),
                        Integer.parseInt(values[2])
                );
            } catch (Exception ignored) {
            }
        }

        if (values.length == 4) {
            try {
                return new PriceDiscountPurchase(
                        new Product(
                                values[0],
                                new Euro(Integer.parseInt(values[1]))
                        ),
                        Integer.parseInt(values[2]),
                        new Euro(Integer.parseInt(values[3]))
                );
            } catch (Exception ignored) {
            }
        }

        return null;
    }
}
