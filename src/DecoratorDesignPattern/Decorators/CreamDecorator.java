package DecoratorDesignPattern.Decorators;

import DecoratorDesignPattern.ICoffee;

public class CreamDecorator extends CoffeeDecorator{

    public CreamDecorator(ICoffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return this.coffee.getDescription() + ", with cream";
    }

    @Override
    public double getCost() {
        return this.coffee.getCost() + 25d;
    }
}
