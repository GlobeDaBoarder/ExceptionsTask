package com.epam.task.exceptions;

import com.epam.task.exceptions.customExc.CsvLineException;

import java.io.*;
import java.util.*;

public class PurchaseList{
    public final List<AbstractPurchase> purchaseList;
    private final Comparator<AbstractPurchase> comparator;
    private boolean sorted = false;

    public PurchaseList(String filePath, Comparator<AbstractPurchase> comparator){
        this(new File(filePath), comparator);
    }

    public PurchaseList(Comparator<AbstractPurchase> comparator){
        this.purchaseList = new ArrayList<>(0);
        this.comparator = comparator;
    }

    public PurchaseList(File inFile, Comparator<AbstractPurchase> comparator) {
        this(comparator);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inFile))){
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                try {
                    AbstractPurchase purchase = PurchaseFactory.createPurchase(line);
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

    public void insertPurchase(AbstractPurchase purchase, int ind){
        try{
            this.purchaseList.add(ind, purchase);
        }catch (IndexOutOfBoundsException e){
            this.purchaseList.add(purchase);
        }
    }

    public int removeSubList(int indFrom, int indTo){
        if (indTo >= this.purchaseList.size())
            indTo = this.purchaseList.size() - 1;

        if (indFrom < 0 )
            indFrom = 0;

        if (indFrom >= indTo)
            return 0;

        this.purchaseList.subList(indFrom, indTo).clear();
        return indTo-indFrom;
    }

    public Euro getTotalCost(){
        Euro totalCost = new Euro();
        for (AbstractPurchase purchase: this.purchaseList){
            totalCost = totalCost.add(purchase.getCost());
        }
        return totalCost;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (AbstractPurchase purchase: this.purchaseList){
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

    public int search(AbstractPurchase abstractPurchase){
        return Collections.binarySearch(this.purchaseList, abstractPurchase, this.comparator);
    }
}
