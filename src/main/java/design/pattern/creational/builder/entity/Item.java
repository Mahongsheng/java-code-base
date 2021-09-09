package design.pattern.creational.builder.entity;

import design.pattern.creational.builder.packing.Packing;

public interface Item {
    String name();

    Packing packing();

    float price();
}
