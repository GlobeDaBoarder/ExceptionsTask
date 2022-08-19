package com.epam.task.exceptions;

import com.epam.task.exceptions.customExc.NonPositiveArgumentException;

public class Purchase implements  Comparable<Purchase>{
    protected final Product product;
    protected final int purchasedNum;

    public Purchase(Product product, int purchasedNum){
        if (purchasedNum <= 0)
            throw new NonPositiveArgumentException("purchase amount can't be <= 0");
        this.product = product;
        this.purchasedNum = purchasedNum;
    }

    public Purchase(String[] values){
        this(new Product(values[0], new Euro(values[1])), Integer.parseInt(values[2]));
    }

    public Euro getCost(){
        return product.getPrice().mul(purchasedNum);
    }

    @Override
    public int compareTo(Purchase o) {
        return o.getCost().compareTo(this.getCost());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Purchase)) {
            return false;
        }

        Purchase other = (Purchase) obj;

        return this.product.equals(other.product) && this.purchasedNum == other.purchasedNum;
    }

    protected String additionalToString(){
        return "";
    }

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
