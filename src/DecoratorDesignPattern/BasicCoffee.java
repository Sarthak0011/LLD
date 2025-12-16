package DecoratorDesignPattern;

public class BasicCoffee implements ICoffee {

    @Override
    public String getDescription() {
        return "Basic Coffee";
    }

    @Override
    public double getCost() {
        return 50d;
    }
}
