package com.epam.task.exceptions;

import java.io.*;
import java.util.*;

public class PurchaseList{
    private final ArrayList<AbstractPurchase> purchaseList;

    public PurchaseList(String filePath){
        this(new File(filePath));
    }

    public PurchaseList(){
        this.purchaseList = new ArrayList<>();
    }

    public PurchaseList(File inFile) {
        this();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inFile));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                String[] values = line.split(";");

                PurchaseFactory purchaseFactory = new PurchaseFactory();
                AbstractPurchase purchase = purchaseFactory.createPurchase(values);

                if(purchase != null)
                    this.purchaseList.add(purchase);

                System.err.println(line);
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
            totalCost.add(purchase.getCost());
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

    public void sortByCost(){
        Collections.sort(this.purchaseList);
    }

    public int searchByProductCost(Euro searchCost){
        return Collections.binarySearch(this.purchaseList, new Purchase(new Product("dummy", searchCost), 1));
    }
}
