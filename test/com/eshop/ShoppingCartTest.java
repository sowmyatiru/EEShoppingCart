package com.eshop;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ShoppingCartTest {

    private ShoppingCart shoppingCart;

    @Before
    public void setUp() {
        shoppingCart = new ShoppingCart();
    }

    @Test
    public void testEmptyShoppingCart() {
        assertEquals("Shopping cart should be empty", 0, shoppingCart.getItems().size());
    }

    private Item getItem(String productName, String productPrice, Integer quantity) {
        Product product = Product.ProductBuilder.aProduct().withName(productName).withPrice(productPrice).build();
        return Item.ItemBuilder.anItem().withProduct(product).withQuantity(quantity).build();
    }

    @Test
    public void testAC0AddProductToShoppingCart() {
        Item item1 = getItem("Dove Soap", "39.99", 1);
        shoppingCart.addItem(item1);
        Item itemInTheCart = shoppingCart.getItems().get("Dove Soap");
        assertEquals("The shopping cart should have single item", 1, shoppingCart.getItems().size());
        assertEquals("The product in the shopping cart item should be Dove Soap", "Dove Soap", itemInTheCart.getProduct().getName());
        assertEquals("The price of the product in the shopping cart item should be 39.99", "39.99", itemInTheCart.getProduct().getPrice());
        assertEquals("The shopping cart total should be 39.99", "39.99", shoppingCart.getTotalPrice().toString());
    }

    @Test
    public void testAC1AddMultipleProductsToShoppingCart() {
        Item item1 = getItem("Dove Soap", "39.99", 5);
        shoppingCart.addItem(item1);
        Item item2 = getItem("Dove Soap", "39.99", 3);
        shoppingCart.addItem(item2);
        Map<String, Item> itemsInTheCart = shoppingCart.getItems();
        assertEquals("The shopping cart should contain a single line item ", 1, itemsInTheCart.size());
        assertEquals("The shopping cart should contain 8 dove soaps ", 8, itemsInTheCart.get("Dove Soap").getQuantity().intValue());
        assertEquals("The shopping cart should contain 8 dove soaps with price 39.99 each", "39.99", itemsInTheCart.get("Dove Soap").getProduct().getPrice());
        assertEquals("The shopping cart total should be 319.92", "319.92", shoppingCart.getTotalPrice().toString());
    }

    @Test
    public void testAC2() {
        Item item1 = getItem("Dove Soap", "39.99", 2);
        shoppingCart.addItem(item1);
        Item item2 = getItem("Axe Deo", "99.99", 2);
        shoppingCart.addItem(item2);
        Map<String, Item> itemsInTheCart = shoppingCart.getItems();
        assertEquals("The shopping cart should have 2 line items ", 2, itemsInTheCart.size());
        assertEquals("The shopping cart should contain a line item with 2 Dove Soaps ", 2, itemsInTheCart.get("Dove Soap").getQuantity().intValue());
        assertEquals("The shopping cart should contain dove soaps with price 39.99 each", "39.99", itemsInTheCart.get("Dove Soap").getProduct().getPrice());
        assertEquals("The shopping cart should contain a line item with 2 Axe Deos ", 2, itemsInTheCart.get("Axe Deo").getQuantity().intValue());
        assertEquals("The shopping cart should contain Axe Deos with price 99.99 each", "99.99", itemsInTheCart.get("Axe Deo").getProduct().getPrice());
        assertEquals("Total sales tax amount for the shopping cart should equal 35.00", "35.00", shoppingCart.getTotalSalesTax().toString());
        assertEquals("The shopping cart's total price should equal 314.96", "314.96", shoppingCart.getTotalSalePrice().toString());
    }

}
