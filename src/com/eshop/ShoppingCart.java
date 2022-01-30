package com.eshop;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    Map<String, Item> cart = new HashMap<>();
    private static final Double FIXED_SALES_TAX = 12.5;

    public Map<String, Item> getItems() {
        return cart;
    }

    public void addItem(Item item) {
        if (cart.containsKey(item.getProduct().getName())) {
            item.setQuantity(item.getQuantity() + cart.get(item.getProduct().getName()).getQuantity());
        }
        
        cart.put(item.getProduct().getName(), item);
    }

    public BigDecimal getTotalPrice() {
        BigDecimal total = getItems().values().stream().map(
                        item -> new BigDecimal(item.getProduct().getPrice()).multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, (a, b) -> (a.add(b)));

        return total.setScale(2, RoundingMode.CEILING);
    }

    public BigDecimal getTotalSalePrice() {
        BigDecimal total = getItems().values().stream().map(
                        item -> new BigDecimal(item.getProduct().getPrice()).multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, (a, b) -> (a.add(b)));

        BigDecimal salesTax = getTotalSalesTax();
        return total.add(salesTax).setScale(2, RoundingMode.CEILING);
    }

    public BigDecimal getTotalSalesTax() {
        BigDecimal salesTax = getItems().values().stream().map(
                        item -> new BigDecimal(item.getProduct().getPrice()
                        ).multiply(new BigDecimal(FIXED_SALES_TAX / 100))
                                .multiply(new BigDecimal(item.getQuantity()))
                )
                .reduce(BigDecimal.ZERO, (a, b) -> (a.add(b)));

        return salesTax.setScale(2, RoundingMode.CEILING);
    }
}
