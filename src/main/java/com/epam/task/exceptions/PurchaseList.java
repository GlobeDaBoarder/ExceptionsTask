package com.epam.task.exceptions;

import java.io.*;
import java.util.*;

public class PurchaseList{
    ArrayList<AbstractPurchase> purchaseList;

    public PurchaseList(String filePath){
        this(new File(filePath));
    }

    public PurchaseList(File inFile) {
        this.purchaseList = new ArrayList<>();

        try {
            BufferedReader bufferedReader =new BufferedReader(new FileReader(inFile));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                String[] values = line.split(";");
                if(values.length == 3){
                    try{
                        Purchase purchase = new Purchase(
                                new Product(
                                        values[0],
                                        new Euro(Integer.parseInt(values[1]))
                                ),
                                Integer.parseInt(values[2])
                        );
                        this.purchaseList.add(purchase);
                        continue;
                    }catch (Exception ignored){
                    }
                }

                if (values.length == 4){
                    try{
                        PriceDiscountPurchase purchase = new PriceDiscountPurchase(
                                new Product(
                                        values[0],
                                        new Euro(Integer.parseInt(values[1]))
                                ),
                                Integer.parseInt(values[2]),
                                new Euro(Integer.parseInt(values[3]))
                        );
                        this.purchaseList.add(purchase);
                        continue;
                    }catch (Exception ignored){
                    }
                }

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
