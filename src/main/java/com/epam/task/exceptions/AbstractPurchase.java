package com.epam.task.exceptions;

import com.epam.task.exceptions.customExc.NonPositiveArgumentException;

public abstract class AbstractPurchase implements Comparable<AbstractPurchase>{
    protected final Product product;
    protected final int purchasedNum;

    public AbstractPurchase(Product product, int purchasedNum) throws NonPositiveArgumentException{
        if (purchasedNum <= 0)
            throw new NonPositiveArgumentException("purchase amount can't be <= 0");
        this.product = product;
        this.purchasedNum = purchasedNum;
    }

    protected abstract Euro getFinalCost(Euro baseCost);

    public Euro getCost(){
        Euro baseCost = product.getPrice().mul(purchasedNum);
        Euro finalCost = getFinalCost(baseCost);
		return finalCost.round(RoundMethods.FLOOR, 2);
    }

    @Override
    public int compareTo(AbstractPurchase o) {
        return o.getCost().compareTo(this.getCost());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof AbstractPurchase)) {
            return false;
        }

        AbstractPurchase other = (AbstractPurchase) obj;

        return this.product.equals(other.product) && this.purchasedNum == other.purchasedNum;
    }

    protected abstract String additionalToString();

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ";" + product.toString() + ";" +  purchasedNum + ";"
                + additionalToString() + this.getCost().toString();
    }

    public final int getPurchasedNum() {
        return this.purchasedNum;
    }

    public final Product getProduct() {
        return this.product;
    }
}
