package chapter03.java;

public class Mocha extends CondimentDecorator {

    private Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " , 모카";
    }

    @Override
    public double cost() {
        return .2 + beverage.cost();
    }
}
