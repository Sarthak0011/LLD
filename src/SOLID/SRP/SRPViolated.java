package SOLID.SRP;

import java.util.ArrayList;
import java.util.List;

class Product {
    private String name;
    private Double price;

    Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return this.name; }

    public Double getPrice() { return this.price; }
}

class ShoppingCart {

    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        System.out.println(product.getName() + " added to the cart.");
        this.products.add(product);
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public Double calculateTotal() {
        Double total = 0.0;
        for(Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    // Violated SRP
    public void printInvoice() {
        for(Product product : products) {
            System.out.println(product.getName() + "--- Rs." + product.getPrice());
        }
        System.out.println("Total: " + this.calculateTotal());
    }

    // Violated SRP
    public void checkout() {
        System.out.println(this.calculateTotal() + " Paid!");
        System.out.println("Thank you for shoppping!");
    }
}

public class SRPViolated {
    public static void main(String[] args) {
        Product product1 = new Product("Shampoo", 450.0);
        Product product2 = new Product("Face Wash", 250.0);

        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(product1);
        cart.addProduct(product2);

        cart.printInvoice();
        cart.checkout();
    }
}

