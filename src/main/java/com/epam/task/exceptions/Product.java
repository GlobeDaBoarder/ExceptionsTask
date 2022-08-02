package com.epam.task.exceptions;

import com.epam.task.exceptions.customExc.EmptyArgumentException;
import com.epam.task.exceptions.customExc.NonPositiveArgumentException;

public class Product {
    private final String name;
    private final Euro price;

    public Product(String name, Euro price) throws EmptyArgumentException{
        if (name.isBlank())
            throw new EmptyArgumentException("name can't be empty");
        if(price.equals(new Euro(0)))
            throw new NonPositiveArgumentException("Price can;t be 0");
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

    public final Euro getPrice() {
        return this.price;
    }
    public final String getName() { return this.name;}
}
