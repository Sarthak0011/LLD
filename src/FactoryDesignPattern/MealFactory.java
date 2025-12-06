package FactoryDesignPattern;

public interface MealFactory {
    Burger prepareBurger(String type);
    GarlicBread prepareGarlicBread(String type);
}

class HealthyMealFactory implements MealFactory {
    @Override
    public Burger prepareBurger(String type) {
        switch (type) {
            case "basic" -> {
                return new BasicWheatBurger();
            }
            case "standard" -> {
                return new StandardWheatBurger();
            }
            case "premium" -> {
                return new PremiumWheatBurger();
            }
            default -> {
                System.out.println("Invalid burger type");
                return null;
            }
        }
    }

    @Override
    public GarlicBread prepareGarlicBread(String type) {
        switch (type) {
            case "basic" -> {
                return new BasicWheatGarlicBread();
            }
            case "cheese" -> {
                return new CheeseWheatGarlicBread();
            }
            default -> {
                System.out.println("Invalid garlic bread type");
                return null;
            }
        }
    }
}


class NormalMealFactory implements MealFactory {

    @Override
    public Burger prepareBurger(String type) {
        switch (type) {
            case "basic" -> {
                return new BasicBurger();
            }
            case "standard" -> {
                return new StandardBurger();
            }
            case "premium" -> {
                return new PremiumBurger();
            }
            default -> {
                System.out.println("Invalid burger type");
                return null;
            }
        }
    }

    @Override
    public GarlicBread prepareGarlicBread(String type) {
        switch (type) {
            case "basic" -> {
                return new BasicGarlicBread();
            }
            case "cheese" -> {
                return new CheeseGarlicBread();
            }
            default -> {
                System.out.println("Invalid garlic bread type");
                return null;
            }
        }
    }
}