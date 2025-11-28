package SOLID.OCP;

import java.util.ArrayList;
import java.util.List;

class Product {
    private final String name;
    private final Double price;

    Product(String name, Double price) {
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

class ShoppingCart {
    List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        System.out.println(product.getName() + " added to the cart.");
        this.products.add(product);
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public Double calculateTotal() {
        Double total = 0d;
        for(Product product : products) {
            total += product.getPrice();
        }
        return total;
    }
}


class InvoiceGenerator {

    private final ShoppingCart shoppingCart;
    InvoiceGenerator(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void generateInvoice() {
        List<Product> products = shoppingCart.getProducts();
        for(Product product : products) {
            System.out.println(product.getName() + " --- Rs." + product.getPrice());
        }
        System.out.println("Total: " + shoppingCart.calculateTotal());
    }
}

interface Checkout {
    void checkout(ShoppingCart shoppingCart);
}

class CashCheckout implements Checkout {
    @Override
    public void checkout(ShoppingCart shoppingCart) {
        System.out.println(shoppingCart.calculateTotal() + " paid by cash");
    }
}

class UpiCheckout implements Checkout {
    @Override
    public void checkout(ShoppingCart shoppingCart) {
        System.out.println(shoppingCart.calculateTotal() + " paid by UPI");
    }
}

class CardCheckout implements Checkout {
    @Override
    public void checkout(ShoppingCart shoppingCart) {
        System.out.println(shoppingCart.calculateTotal() + " paid by Card");
    }
}

public class OCPFollowed {
    public static void main(String[] args) {
        Product product1 = new Product("Shampoo", 450d);
        Product product2 = new Product("Face Wash", 250d);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(product1);
        shoppingCart.addProduct(product2);

        InvoiceGenerator invoiceGenerator = new InvoiceGenerator(shoppingCart);
        invoiceGenerator.generateInvoice();

        Checkout checkout = new CardCheckout();
        checkout.checkout(shoppingCart);
    }
}
