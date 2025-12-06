package FactoryDesignPattern;

public class Client {
    public static void main(String[] args) {
        String burgerType = "premium";
        String garlicBreadType = "cheese";

        MealFactory mealFactory = new HealthyMealFactory();
        Burger burger = mealFactory.prepareBurger(burgerType);
        GarlicBread garlicBread = mealFactory.prepareGarlicBread(garlicBreadType);

        if(burger != null) {
            burger.prepare();
        }
        if(garlicBread != null) {
            garlicBread.prepare();
        }
    }
}
