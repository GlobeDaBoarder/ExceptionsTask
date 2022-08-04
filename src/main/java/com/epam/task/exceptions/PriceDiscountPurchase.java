package com.epam.task.exceptions;

import com.epam.task.exceptions.customExc.NonPositiveArgumentException;
import com.epam.task.exceptions.customExc.OutOfBoundArgumentException;

public class PriceDiscountPurchase extends AbstractPurchase{
    private final Euro discountAmount;

    public PriceDiscountPurchase(Product product, int purchasedNum, Euro discountAmount) {
        super(product, purchasedNum);
        if(product.getPrice().compareTo(discountAmount) <= 0)
            throw new OutOfBoundArgumentException("Discount can't be bigger than price");
        if (discountAmount.equals(new Euro(0)))
            throw new NonPositiveArgumentException("Discount can't be zero");
        this.discountAmount = discountAmount;
    }

    @Override
    protected Euro getFinalCost(Euro baseCost) {
        return baseCost.sub(discountAmount);
    }

    @Override
    protected String additionalToString() {
        return discountAmount.toString() + ";";
    }
}
