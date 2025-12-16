package DecoratorDesignPattern;

import DecoratorDesignPattern.Decorators.CreamDecorator;
import DecoratorDesignPattern.Decorators.MilkDecorator;
import DecoratorDesignPattern.Decorators.SugarDecorator;

public class CoffeeShop {
    public static void main(String[] args) {
        ICoffee coffee = new BasicCoffee();

        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);
        coffee = new CreamDecorator(coffee);

        System.out.println(coffee.getDescription());
        System.out.println(coffee.getCost());
    }
}
