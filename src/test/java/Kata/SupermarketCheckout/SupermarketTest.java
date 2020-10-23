package Kata.SupermarketCheckout;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SupermarketTest {


    @Test
    public void noDiscount() {
        SupermarketCatalog catalog = new FakeCatalog();

        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);

        Product apples = new Product("apples", ProductUnit.Kilo);
        catalog.addProduct(apples, 1.99);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 2.5);

        // ACT
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // ASSERT
        assertEquals(4.975, receipt.getTotalPrice(), 0.01);
        assertEquals(Collections.emptyList(), receipt.getDiscounts());
        assertEquals(1, receipt.getItems().size());
        ReceiptItem receiptItem = receipt.getItems().get(0);
        assertEquals(apples, receiptItem.getProduct());
        assertEquals(1.99, receiptItem.getPrice());
        assertEquals(2.5*1.99, receiptItem.getTotalPrice());
        assertEquals(2.5, receiptItem.getQuantity());

    }

    @Test
    public void tenPercentDiscount() {
        SupermarketCatalog catalog = new FakeCatalog();

        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 2);

        // ACT
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // ASSERT
        assertEquals(1.782, receipt.getTotalPrice(), 0.01);
        assertNotEquals(Collections.emptyList(), receipt.getDiscounts());
        assertEquals(1, receipt.getItems().size());

        ReceiptItem receiptItem = receipt.getItems().get(0);
        assertEquals(toothbrush, receiptItem.getProduct());
        assertEquals(0.99, receiptItem.getPrice());
        assertEquals(2 * 0.99, receiptItem.getTotalPrice());
        assertEquals(2, receiptItem.getQuantity());

    }

    @Test
    public void threeForTwo() {
        SupermarketCatalog catalog = new FakeCatalog();

        Product oranges = new Product("oranges", ProductUnit.Kilo);
        catalog.addProduct(oranges, 3.00);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, oranges, .0);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(oranges, 3.0);

        // ACT
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // ASSERT
        assertEquals(6.000, receipt.getTotalPrice(), 0.01);
        assertNotEquals(Collections.emptyList(), receipt.getDiscounts());
        assertEquals(1, receipt.getItems().size());

        ReceiptItem receiptItem = receipt.getItems().get(0);
        assertEquals(oranges, receiptItem.getProduct());
        assertEquals(3.00, receiptItem.getPrice());
        assertEquals(3.0 * 3.00, receiptItem.getTotalPrice());
        assertEquals(3.0, receiptItem.getQuantity());

    }

    @Test
    public void fiveForAmount() {
        SupermarketCatalog catalog = new FakeCatalog();

        Product oranges = new Product("oranges", ProductUnit.Kilo);
        catalog.addProduct(oranges, 3.00);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.FiveForAmount, oranges, 3.0);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(oranges, 6.0);

        // ACT
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // ASSERT
        assertEquals(6.000, receipt.getTotalPrice(), 0.01);
        assertNotEquals(Collections.emptyList(), receipt.getDiscounts());
        assertEquals(1, receipt.getItems().size());

        ReceiptItem receiptItem = receipt.getItems().get(0);
        assertEquals(oranges, receiptItem.getProduct());
        assertEquals(3.00, receiptItem.getPrice());
        assertEquals(6.0 * 3.00, receiptItem.getTotalPrice());
        assertEquals(6.0, receiptItem.getQuantity());

    }

    @Test
    public void twoForAmount() {
        SupermarketCatalog catalog = new FakeCatalog();

        Product oranges = new Product("oranges", ProductUnit.Kilo);
        catalog.addProduct(oranges, 3.00);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, oranges, 1.0);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(oranges, 6.0);

        // ACT
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // ASSERT
        assertEquals(3.000, receipt.getTotalPrice(), 0.01);
        assertNotEquals(Collections.emptyList(), receipt.getDiscounts());
        assertEquals(1, receipt.getItems().size());

        ReceiptItem receiptItem = receipt.getItems().get(0);
        assertEquals(oranges, receiptItem.getProduct());
        assertEquals(3.00, receiptItem.getPrice());
        assertEquals(6.0 * 3.00, receiptItem.getTotalPrice());
        assertEquals(6.0, receiptItem.getQuantity());

    }
}