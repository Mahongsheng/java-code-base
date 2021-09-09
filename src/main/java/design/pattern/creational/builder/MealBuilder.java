package design.pattern.creational.builder;

import design.pattern.creational.builder.entity.ChickenBurger;
import design.pattern.creational.builder.entity.Coke;
import design.pattern.creational.builder.entity.Pepsi;
import design.pattern.creational.builder.entity.VegBurger;

public class MealBuilder {

    public Meal prepareVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareNonVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
