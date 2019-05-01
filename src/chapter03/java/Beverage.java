package chapter03.java;

public abstract class Beverage {

    public String description = "제목없음";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}

