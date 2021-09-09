package design.pattern.creational.factory;

import design.pattern.creational.factory.entity.Circle;
import design.pattern.creational.factory.entity.Rectangle;
import design.pattern.creational.factory.entity.Shape;
import design.pattern.creational.factory.entity.Square;

public class ShapeFactory {

    //使用 getShape 方法获取形状类型的对象
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }
}
