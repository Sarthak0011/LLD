package DecoratorDesignPattern.Decorators;

import DecoratorDesignPattern.ICoffee;

public class MilkDecorator extends CoffeeDecorator{

    public MilkDecorator(ICoffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return this.coffee.getDescription() + ", with milk";
    }

    @Override
    public double getCost() {
        return this.coffee.getCost() + 20d;
    }
}
