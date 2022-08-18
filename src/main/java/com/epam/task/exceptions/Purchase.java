package com.epam.task.exceptions;

public class Purchase extends AbstractPurchase{
    public Purchase(Product product, int purchasedNum) {
        super(product, purchasedNum);
    }

    public Purchase(String[] values){
        super(values);
    }


    @Override
    protected Euro getFinalCost(Euro baseCost) {
        return baseCost;
    }

    @Override
    protected String additionalToString() {
        return "";
    }
}
