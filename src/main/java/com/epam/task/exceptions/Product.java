package com.epam.task.exceptions;

public class Product {
    private final String name;
    private final Euro price;

    public Product(String name, Euro price) {
        if (name.isBlank())
            throw new IllegalArgumentException("name can't be empty");
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + ';' + price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Product)) {
            return false;
        }

        Product other = (Product) obj;

        return this.price.equals(other.price) && this.name.equals(other.name);
    }

    public Euro getPrice() {
        return price;
    }
}
