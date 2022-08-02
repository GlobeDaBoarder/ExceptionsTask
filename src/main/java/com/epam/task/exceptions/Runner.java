package com.epam.task.exceptions;

import java.util.Comparator;
import java.util.WeakHashMap;

public class Runner {
    public static void main(String[] args) {
        Comparator<AbstractPurchase> comparatorByQuantity = Comparator.comparingInt(AbstractPurchase::getPurchasedNum);
        PurchaseList purchaseList = new PurchaseList("src\\main\\resources\\com\\epam\\task\\exceptions\\input.txt",
                comparatorByQuantity);
        System.out.println(purchaseList);

        purchaseList.insertPurchase(
                new Purchase(
                        new Product(
                                "test",
                                new Euro(320)
                        ),
                        2
                ),
                0
        );

        System.out.println(purchaseList);

        purchaseList.removeSubList(0, 1);

        System.out.println(purchaseList);

        purchaseList.sort();

        System.out.println(purchaseList);

        System.out.println("Index of item with quantity 5 is:" + purchaseList.searchByQuantity(5));

        System.out.println("Total cost: " + purchaseList.getTotalCost());

    }
}
