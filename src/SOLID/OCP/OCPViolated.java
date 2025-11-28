package SOLID.OCP;

import java.util.ArrayList;
import java.util.List;

class Products {
    private String name;
    private Double price;

    Products(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public Double getPrice() {
        return this.price;
    }
}

class Cart {
    List<Products> products = new ArrayList<>();

    public void addProduct(Products product) {
        System.out.println(product.getName() + " added to cart.");
        products.add(product);
    }

    public List<Products> getProducts() {
        return this.products;
    }

    public Double calculateTotal() {
        Double total = 0.0;
        for(Products product : products) {
            total += product.getPrice();
        }
        return total;
    }
}

class InvoicePrinter {

    private final Cart cart;
    InvoicePrinter(Cart cart) {
        this.cart = cart;
    }

    public void printInvoice() {
        List<Products> products = cart.getProducts();
        for(Products product : products) {
            System.out.println(product.getName() + " --- Rs." + product.getPrice());
        }
        System.out.println("Total: " + cart.calculateTotal());
    }
}

class CheckoutHandler {

    private final Cart cart;
    CheckoutHandler(Cart cart) {
        this.cart = cart;
    }

    // Violates OCP
    public void cashPayment() {
        System.out.println("Cash Payment");
    }

    // Violates OCP
    public void upiPayment() {
        System.out.println("UPI Payment");
    }

    // Violates OCP
    public void cardPayment() {
        System.out.println("Card Payment");
    }
}

public class OCPViolated {
    public static void main(String[] args) {
        Products p1 = new Products("Shampoo", 450d);
        Products p2 = new Products("Face Wash", 250d);

        Cart cart = new Cart();
        cart.addProduct(p1);
        cart.addProduct(p2);

        InvoicePrinter printer = new InvoicePrinter(cart);
        printer.printInvoice();

        CheckoutHandler checkoutHandler = new CheckoutHandler(cart);
        checkoutHandler.cardPayment();
    }
}
