package com.epam.task.exceptions;

public class PriceDiscountPurchase extends AbstractPurchase{
    private final Euro discountAmount;

    public PriceDiscountPurchase(Product product, int purchasedNum, Euro discountAmount) {
        super(product, purchasedNum);
        if(product.getPrice().compareTo(discountAmount) <= 0)
            throw new IllegalArgumentException("Discount can't be bigger than price");
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
