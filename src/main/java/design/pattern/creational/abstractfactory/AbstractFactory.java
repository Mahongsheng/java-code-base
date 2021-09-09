package design.pattern.creational.abstractfactory;

import design.pattern.creational.abstractfactory.entity.Color;
import design.pattern.creational.abstractfactory.entity.Shape;

public abstract class AbstractFactory {
    public abstract Color getColor(String color);

    public abstract Shape getShape(String shape);
}
