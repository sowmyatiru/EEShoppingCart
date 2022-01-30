package com.eshop;

public class Product {

    private String name;
    private String price;

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public static final class ProductBuilder {
        private String name;
        private String price;

        private ProductBuilder() {
        }

        public static ProductBuilder aProduct() {
            return new ProductBuilder();
        }

        public ProductBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder withPrice(String price) {
            this.price = price;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.price = this.price;
            product.name = this.name;
            return product;
        }
    }
}
