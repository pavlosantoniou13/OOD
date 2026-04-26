# Code Review Summary

## Responsibilities

### - Does each class have exactly one reason to change?
After looking at our code and what we have so far in the Development branch, we believe that each class has specific responsibilities and only one reason to change.

### - Are method names specific enough to describe a single action?
We made sure all our method names did one single action. Furthermore, we made sure it was precise and easy to follow for other coders if they were to look at our code.

---

## Layer Integrity

### - Does any domain class import from the presentation layer?
No domain classes import anything from the presentation or application layer.

### - Is business logic kept out of the console/UI classes?
Yes we made sure that the presentation classes only focused on what the user sees and what they write, no business logic.

---

## Coupling

### - How many classes change if Material is renamed?
Since Material is a domain entity, it is referenced in many classes, especially in the presentation layer. 

### - Are collaborators injected (good) or instantiated inside (bad)?
In Main, the way we used constructor injection is perfect and exactly how it should be. However, after reviewing our code, we realized that we ended up making ConsoleMenu into a god class where we are instantiating all the submenus inside the constructor. Later on, this makes ConsoleMenu hard to unit test on, because we will not be able to mock, for example, InputParser to simulate a user typing.

---

## Testability

### - Could calculateImpact() be tested without a Scanner?
Since we are using a method in Product that takes the interface ImpactStrategy as an argument. You can easily pass a mock strategy instead in a unit test without touching the console or doing it manually.

### - Are there any static utility calls buried in domain logic?
After reviewing our code, we found no static utility calls except a minor one that we have with generating a random UUID in Product. However, this should not stop us from doing any basic unit tests.
