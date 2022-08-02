package com.epam.task.exceptions;

import org.junit.jupiter.api.*;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseListTest {
    private PurchaseList purchaseList;

    @BeforeEach
    void init(){
        this.purchaseList = new PurchaseList("src\\main\\resources\\com\\epam\\task\\exceptions\\input.txt",
                Comparator.comparingInt(AbstractPurchase::getPurchasedNum));
    }

    @Test
    void testConstructor(){
        String expected = """
                PriceDiscountPurchase;bread;1.55;1;0.02;1.00
                Purchase;milk;1.31;2;2.00
                Purchase;bread;1.54;3;4.00
                Purchase;bread;1.45;5;7.00
                PriceDiscountPurchase;potato;1.80;2;0.10;3.00
                Purchase;butter;3.70;1;3.00
                PriceDiscountPurchase;butter;3.41;1;0.01;3.00
                PriceDiscountPurchase;meat;11.00;2;0.80;21.00
                """;

        assertEquals(expected, this.purchaseList.toString());
    }

    @Test
    void insertPurchaseTest(){
        this.purchaseList.insertPurchase(
                new Purchase(
                        new Product(
                                "test product",
                                new Euro(333)
                        ),
                        3
                ),
                2
        );

        String expected = """
                PriceDiscountPurchase;bread;1.55;1;0.02;1.00
                Purchase;milk;1.31;2;2.00
                Purchase;test product;3.33;3;9.00
                Purchase;bread;1.54;3;4.00
                Purchase;bread;1.45;5;7.00
                PriceDiscountPurchase;potato;1.80;2;0.10;3.00
                Purchase;butter;3.70;1;3.00
                PriceDiscountPurchase;butter;3.41;1;0.01;3.00
                PriceDiscountPurchase;meat;11.00;2;0.80;21.00
                """;

        assertEquals(expected, this.purchaseList.toString());
    }

    @Test
    void removePurchasesTest(){
        this.purchaseList.removeSubList(0, 8);

        String expected = "PriceDiscountPurchase;meat;11.00;2;0.80;21.00\n";

        assertEquals(expected, this.purchaseList.toString());
    }

    @Test
    void sortTest(){
        this.purchaseList.sort();

        String expected = """
                PriceDiscountPurchase;bread;1.55;1;0.02;1.00
                Purchase;butter;3.70;1;3.00
                PriceDiscountPurchase;butter;3.41;1;0.01;3.00
                Purchase;milk;1.31;2;2.00
                PriceDiscountPurchase;potato;1.80;2;0.10;3.00
                PriceDiscountPurchase;meat;11.00;2;0.80;21.00
                Purchase;bread;1.54;3;4.00
                Purchase;bread;1.45;5;7.00
                """;

        assertEquals(expected, this.purchaseList.toString());
    }

    @Nested
    public class SearchTest{
        private int ind;

        @BeforeEach
        void sortInit(){
            purchaseList.sort();
            this.ind = 0;
        }

        @Test
        void searchForCost21(){
            ind = purchaseList.searchByProductCost(new Euro(21, 0));

            assertEquals(0, ind);
        }

        @Test
        void searchForCost7(){
            ind = purchaseList.searchByProductCost(new Euro(7, 0));

            assertEquals(1, ind);
        }

        @Test
        void searchForCost4(){
            ind = purchaseList.searchByProductCost(new Euro(4, 0));

            assertEquals(2, ind);
        }

        @Test
        void searchForCost3000(){
            ind = purchaseList.searchByProductCost(new Euro(3000, 0));

            assertEquals(-1, ind);
        }
    }
}
