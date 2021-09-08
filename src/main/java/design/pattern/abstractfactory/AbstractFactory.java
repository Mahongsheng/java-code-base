package design.pattern.abstractfactory;

import design.pattern.abstractfactory.entity.Color;
import design.pattern.abstractfactory.entity.Shape;

public abstract class AbstractFactory {
    public abstract Color getColor(String color);

    public abstract Shape getShape(String shape);
}
