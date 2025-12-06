package FactoryDesignPattern;

public interface GarlicBread {
    void prepare();
}

class BasicGarlicBread implements GarlicBread {
    @Override
    public void prepare() {
        System.out.println("Preparing basic garlic bread...");
    }
}

class CheeseGarlicBread implements GarlicBread {
    @Override
    public void prepare() {
        System.out.println("Preparing cheese garlic bread...");
    }
}

class BasicWheatGarlicBread implements GarlicBread {
    @Override
    public void prepare() {
        System.out.println("Preparing basic wheat garlic bread...");
    }
}

class CheeseWheatGarlicBread implements GarlicBread {
    @Override
    public void prepare() {
        System.out.println("Preparing cheese wheat garlic bread...");
    }
}