package com.epam.task.exceptions;

import com.epam.task.exceptions.customExc.CsvLineException;

import java.io.*;
import java.util.*;

public class PurchaseList{
    private final List<Purchase> purchaseList;
    private final Comparator<Purchase> comparator;
    private boolean sorted;

    public PurchaseList(String filePath, Comparator<Purchase> comparator){
        this(new File(filePath), comparator);
    }

    public PurchaseList(Comparator<Purchase> comparator){
        this("", comparator);
    }

    public PurchaseList(File inFile, Comparator<Purchase> comparator) {
        this.purchaseList = new ArrayList<>(0);
        this.comparator = comparator;
        this.sorted = false;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inFile))){
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                try {
                    Purchase purchase = PurchaseFactory.createPurchase(line);
                    purchaseList.add(purchase);
                } catch (CsvLineException e) {
                    System.err.println(e + " caused by: " + e.getCause().getClass().getSimpleName() + ": "
                            + e.getCause().getMessage());
                }
            }
        } catch (IOException e) {
            sorted = true;
            e.printStackTrace();
        }
    }

    public void insertPurchase(Purchase purchase, int ind){
        if(ind >= this.purchaseList.size())
            ind = this.purchaseList.size() - 1;

        if(ind < 0){
            ind = 0;
        }

        this.purchaseList.add(ind, purchase);
    }

    public int removeSubList(int indFrom, int indTo){
        if (indFrom >= indTo)
            return 0;

        if (indTo >= this.purchaseList.size())
            indTo = this.purchaseList.size() - 1;

        if (indFrom < 0 )
            indFrom = 0;

        this.purchaseList.subList(indFrom, indTo).clear();
        return indTo-indFrom;
    }

    public Euro getTotalCost(){
        Euro totalCost = new Euro();
        for (Purchase purchase: this.purchaseList){
            totalCost = totalCost.add(purchase.getCost());
        }
        return totalCost;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Purchase purchase: this.purchaseList){
            stringBuilder.append(purchase.toString());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    public void sort(){
        if (!sorted){
            this.purchaseList.sort(comparator);
            sorted = true;
        }
    }

    public int search(Purchase abstractPurchase){
        sort();
        return Collections.binarySearch(this.purchaseList, abstractPurchase, this.comparator);
    }
}
