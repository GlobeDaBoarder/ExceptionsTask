package com.epam.task.exceptions;

import com.epam.task.exceptions.customExc.NonPositiveArgumentException;
import com.epam.task.exceptions.customExc.OutOfBoundArgumentException;

public class PriceDiscountPurchase extends Purchase{
    private final Euro discountAmount;

    public PriceDiscountPurchase(Product product, int purchasedNum, Euro discountAmount) {
        super(product, purchasedNum);
        if(product.getPrice().compareTo(discountAmount) <= 0)
            throw new OutOfBoundArgumentException("Discount can't be bigger than price");
        if (discountAmount.equals(new Euro(0)))
            throw new NonPositiveArgumentException("Discount can't be zero");
        this.discountAmount = discountAmount;
    }

    public PriceDiscountPurchase(PriceDiscountPurchase priceDiscountPurchase){
        this(priceDiscountPurchase.getProduct(), priceDiscountPurchase.getPurchasedNum(), priceDiscountPurchase.discountAmount);
    }

    public PriceDiscountPurchase(String[] values) {
        this(validateValues(values));
    }

    private static PriceDiscountPurchase validateValues(String[] values) {
        if (!PurchaseFactory.isDiscountPurchase(values))
            throw new OutOfBoundArgumentException("Wrong number of values");

        return new PriceDiscountPurchase(
                new Product(values[0], new Euro(values[1])),
                Integer.parseInt(values[2]),
                new Euro(values[3])
        );
    }

    @Override
    public Euro getCost() {
        return super.getCost().sub(this.discountAmount.mul(getPurchasedNum()));
    }

    @Override
    protected String additionalToString() {
        return discountAmount + ";";
    }
}
