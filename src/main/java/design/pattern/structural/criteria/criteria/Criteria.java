package design.pattern.structural.criteria.criteria;

import design.pattern.structural.criteria.entity.Person;

import java.util.List;

public interface Criteria {
    List<Person> meetCriteria(List<Person> persons);
}
