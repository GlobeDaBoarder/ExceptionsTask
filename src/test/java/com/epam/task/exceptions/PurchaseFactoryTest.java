package com.epam.task.exceptions;

import com.epam.task.exceptions.customExc.CsvLineException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PurchaseFactoryTest {
    private AbstractPurchase purchase;
    private PurchaseFactory purchaseFactory;

    @BeforeEach
    void initializePurchase(){
        purchase = null;
    }

    @BeforeAll
    void initializeFactory(){
        purchaseFactory = new PurchaseFactory();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "createPurchaseFromFactoryCsv.csv", delimiter = ';')
    void createPurchaseFromFactoryTest(String name, String price, String quantity){
        try {
            this.purchase = this.purchaseFactory.createPurchase(new String[]{name, price, quantity});
        } catch (CsvLineException e) {
            e.printStackTrace();
        }
        assertEquals(Purchase.class, this.purchase.getClass());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "createPriceDiscountPurchaseFromFactoryCsv.csv", delimiter = ';')
    void createPriceDiscountPurchasePurchaseFromFactoryTest(String name, String price, String quantity, String discount){
        try {
            this.purchase = this.purchaseFactory.createPurchase(new String[]{name, price, quantity, discount});
        } catch (CsvLineException e) {
            e.printStackTrace();
        }
        assertEquals(PriceDiscountPurchase.class, this.purchase.getClass());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "brokenPurchaseData.csv", delimiter = ';')
    void purchasesNotCreated(String name, String price, String quantity){
        try {
            this.purchase = this.purchaseFactory.createPurchase(new String[]{name, price, quantity});
        } catch (CsvLineException e) {
            e.printStackTrace();
        }
        assertNull(this.purchase);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "brokenPriceDiscountPurchaseData.csv", delimiter = ';')
    void priceDiscountPurchasesNotCreated(String name, String price, String quantity, String discount){
        try {
            this.purchase = this.purchaseFactory.createPurchase(new String[]{name, price, quantity, discount});
        } catch (CsvLineException e) {
            e.printStackTrace();
        }
        assertNull(this.purchase);
    }
}
