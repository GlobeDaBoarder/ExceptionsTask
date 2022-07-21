package com.epam.task.exceptions;

import java.io.File;

public class Runner {
    public static void main(String[] args) {
        PurchaseList purchaseList = new PurchaseList(".\\src\\main\\resources\\input.txt");
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

        purchaseList.removeSubList(0, 2);

        System.out.println(purchaseList);

        purchaseList.sortByCost();

        System.out.println(purchaseList);

        System.out.println(purchaseList.searchByProductCost(new Euro(370)));

    }
}