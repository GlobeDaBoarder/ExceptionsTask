package com.epam.task.exceptions;

import com.epam.task.exceptions.customExc.CsvLineException;

import java.io.*;
import java.util.*;

public class PurchaseList{
    private final ArrayList<AbstractPurchase> purchaseList;
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

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inFile));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                PurchaseFactory purchaseFactory = new PurchaseFactory();
                AbstractPurchase purchase = null;
                try {
                    purchase = purchaseFactory.createPurchase(line);
                } catch (CsvLineException e) {
                    System.err.println(e + " caused by: " + e.getCause().getClass().getSimpleName() + ": "
                            + e.getCause().getMessage());
                }

                if(purchase != null)
                    this.purchaseList.add(purchase);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertPurchase(AbstractPurchase purchase, int ind){
        try{
            this.purchaseList.add(ind, purchase);
        }catch (Exception e){
            this.purchaseList.add(purchase);
        }
    }

    public void removeSubList(int indFrom, int indTo){
        if (indTo >= this.purchaseList.size())
            indTo = this.purchaseList.size() - 1;

        if (indFrom < 0 )
            indFrom = 0;

        this.purchaseList.subList(indFrom, indTo).clear();
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
        this.purchaseList.sort(comparator);
        sorted = true;
    }

    //generic search??
    //Don't quite understand yet how to search by a parameter that has been chosen in the Comparable
    public int searchByQuantity(int quantityValue){
        return Collections.binarySearch(this.purchaseList, new Purchase(new Product("test", new Euro(100)), quantityValue),
                this.comparator);
    }

    public int searchByProductCost(Euro searchCost){
        if(!sorted){
            this.purchaseList.sort(this.comparator);
            this.sorted = true;
        }
        Collections.sort(this.purchaseList);
        return Collections.binarySearch(this.purchaseList, new Purchase(new Product("dummy", searchCost), 1));
    }
}
