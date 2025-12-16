package DecoratorDesignPattern.Decorators;

import DecoratorDesignPattern.ICoffee;

public class SugarDecorator extends CoffeeDecorator{

    public SugarDecorator(ICoffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return this.coffee.getDescription() + ", with sugar";
    }

    @Override
    public double getCost() {
        return this.coffee.getCost() + 10d;
    }
}
