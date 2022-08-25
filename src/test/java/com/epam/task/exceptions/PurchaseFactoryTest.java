package com.epam.task.exceptions;

import com.epam.task.exceptions.customExc.CsvLineException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PurchaseFactoryTest {
    private Purchase purchase;

    @BeforeEach
    void initializePurchase(){
        purchase = null;
    }


    @ParameterizedTest
    @CsvFileSource(resources = "createPurchaseFromFactoryCsv.csv", delimiter = ';')
    void createPurchaseFromFactoryTest(String name, String price, String quantity) throws CsvLineException {
        this.purchase = PurchaseFactory.createPurchase(new String[]{name, price, quantity});
        assertEquals(Purchase.class, this.purchase.getClass());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "createPriceDiscountPurchaseFromFactoryCsv.csv", delimiter = ';')
    void createPriceDiscountPurchasePurchaseFromFactoryTest(String name, String price, String quantity, String discount) throws CsvLineException {
        this.purchase = PurchaseFactory.createPurchase(new String[]{name, price, quantity, discount});
        assertEquals(PriceDiscountPurchase.class, this.purchase.getClass());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "brokenPurchaseData.csv", delimiter = ';')
    void purchasesNotCreated(String name, String price, String quantity) {
        assertThrows(CsvLineException.class, () -> this.purchase = PurchaseFactory.createPurchase(new String[]{name, price, quantity}));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "brokenPriceDiscountPurchaseData.csv", delimiter = ';')
    void priceDiscountPurchasesNotCreated(String name, String price, String quantity, String discount) {
        assertThrows(CsvLineException.class, () -> this.purchase = PurchaseFactory.createPurchase(new String[]{name, price, quantity}));
    }
}
