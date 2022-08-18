package com.epam.task.exceptions;

import com.epam.task.exceptions.customExc.NonPositiveArgumentException;

public abstract class AbstractPurchase implements Comparable<AbstractPurchase>{
    protected final Product product;
    protected final int purchasedNum;

    public AbstractPurchase(Product product, int purchasedNum){
        if (purchasedNum <= 0)
            throw new NonPositiveArgumentException("purchase amount can't be <= 0");
        this.product = product;
        this.purchasedNum = purchasedNum;
    }

    public AbstractPurchase(String[] values){
        this(new Product(values[0], new Euro(values[1])), Integer.parseInt(values[2]));
    }

    protected abstract Euro getFinalCost(Euro baseCost);

    public Euro getCost(){
        Euro baseCost = product.getPrice().mul(purchasedNum);
        return getFinalCost(baseCost);
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
        return getClass().getSimpleName() + ";" + product + ";" +  purchasedNum + ";"
                + additionalToString() + getCost();
    }

    public final int getPurchasedNum() {
        return this.purchasedNum;
    }

    public final Product getProduct() {
        return this.product;
    }
}
