package com.epam.task.exceptions;

import org.junit.jupiter.api.*;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseListTest {
    private PurchaseList purchaseList;

    @BeforeEach
    void init(){
        this.purchaseList = new PurchaseList("src\\main\\resources\\com\\epam\\task\\exceptions\\input.txt",
                Comparator.comparing(AbstractPurchase::getCost));
    }

    @Test
    void testConstructor(){
        String expected = """
                PriceDiscountPurchase;bread;1.55;1;0.02;1.53
                Purchase;milk;1.31;2;2.62
                Purchase;bread;1.54;3;4.62
                Purchase;bread;1.45;5;7.25
                PriceDiscountPurchase;potato;1.80;2;0.10;3.50
                Purchase;butter;3.70;1;3.70
                PriceDiscountPurchase;butter;3.41;1;0.01;3.40
                PriceDiscountPurchase;meat;11.00;2;0.80;21.20
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
                PriceDiscountPurchase;bread;1.55;1;0.02;1.53
                Purchase;milk;1.31;2;2.62
                Purchase;test product;3.33;3;9.99
                Purchase;bread;1.54;3;4.62
                Purchase;bread;1.45;5;7.25
                PriceDiscountPurchase;potato;1.80;2;0.10;3.50
                Purchase;butter;3.70;1;3.70
                PriceDiscountPurchase;butter;3.41;1;0.01;3.40
                PriceDiscountPurchase;meat;11.00;2;0.80;21.20
                """;

        assertEquals(expected, this.purchaseList.toString());
    }

    @Test
    void removePurchasesTest(){
        this.purchaseList.removeSubList(0, 8);

        String expected = "PriceDiscountPurchase;meat;11.00;2;0.80;21.20\n";

        assertEquals(expected, this.purchaseList.toString());
    }

    @Test
    void sortTest(){
        this.purchaseList.sort();

        String expected = """
                PriceDiscountPurchase;bread;1.55;1;0.02;1.53
                Purchase;milk;1.31;2;2.62
                PriceDiscountPurchase;butter;3.41;1;0.01;3.40
                PriceDiscountPurchase;potato;1.80;2;0.10;3.50
                Purchase;butter;3.70;1;3.70
                Purchase;bread;1.54;3;4.62
                Purchase;bread;1.45;5;7.25
                PriceDiscountPurchase;meat;11.00;2;0.80;21.20
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
            ind = purchaseList.search(new Purchase(new Product("test", new Euro(2120)), 1));

            assertEquals(7, ind);
        }

        @Test
        void searchForCost7(){
            ind = purchaseList.search(new Purchase(new Product("test", new Euro(725)), 1));

            assertEquals(6, ind);
        }

        @Test
        void searchForCost4(){
            ind = purchaseList.search(new Purchase(new Product("test", new Euro(462)), 1));

            assertEquals(5, ind);
        }

        @Test
        void searchForCost3000(){
            ind = purchaseList.search(new Purchase(new Product("test", new Euro(300000)), 1));

            assertTrue(ind < 0);
        }
    }
}
