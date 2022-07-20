package com.epam.task.exceptions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseList {
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

}
