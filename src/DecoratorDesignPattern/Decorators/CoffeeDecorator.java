package DecoratorDesignPattern.Decorators;

import DecoratorDesignPattern.ICoffee;

public abstract class CoffeeDecorator implements ICoffee {

    protected ICoffee coffee;

    public CoffeeDecorator(ICoffee coffee) {
        this.coffee = coffee;
    }
}
