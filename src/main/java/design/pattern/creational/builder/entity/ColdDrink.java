package design.pattern.creational.builder.entity;

import design.pattern.creational.builder.packing.Bottle;
import design.pattern.creational.builder.packing.Packing;

public abstract class ColdDrink implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}
