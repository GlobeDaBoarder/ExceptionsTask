package com.epam.task.exceptions;

import java.io.File;

public class Runner {
    public static void main(String[] args) {
        PurchaseList purchaseList = new PurchaseList(".\\src\\main\\java\\com\\epam\\task\\exceptions\\input.txt");

        for(AbstractPurchase p : purchaseList.purchaseList){
            System.out.println(p);
        }
    }
}
