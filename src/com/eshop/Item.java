package com.eshop;

public class Item {

    private Integer quantity;
    private Product product;

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public static final class ItemBuilder {
        private Integer quantity;
        private Product product;

        private ItemBuilder() {
        }

        public static ItemBuilder anItem() {
            return new ItemBuilder();
        }

        public ItemBuilder withQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public ItemBuilder withProduct(Product product) {
            this.product = product;
            return this;
        }

        public Item build() {
            Item item = new Item();
            item.setQuantity(quantity);
            item.setProduct(product);
            return item;
        }
    }
}
