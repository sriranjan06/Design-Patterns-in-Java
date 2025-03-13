# Design-Patterns-in-Java
An implementation of various important design patterns in Java in order to learn how to build extensible and maintainable object-oriented software. Listed below are the design patterns: 


# 1. Strategy Pattern - SimUDuck Example 🦆

## 📌 Definition
The **Strategy Pattern** defines a family of algorithms, encapsulates each one, and makes them **interchangeable**. This pattern lets the algorithm vary independently from the clients that use it.

### ✅ Key Benefits:
- Promotes **loose coupling** by separating behaviors from the classes that use them.
- Supports the **Open/Closed Principle** (OCP) by allowing new behaviors to be added **without modifying existing code**.
- Encourages **code reusability** and **flexibility** in changing behaviors dynamically at runtime.

---

## 🏗 Structure

### UML Representation of Strategy Pattern:
```
+----------------------+         +-----------------+
|      Duck            |◄--------|   FlyBehavior   |
|----------------------|         |-----------------|
| - flyBehavior        |         | + fly(): void   |
| - quackBehavior      |         +-----------------+
|----------------------+                  ▲
| + display()          |                  │
| + performFly()       |                  │
| + performQuack()     |         +----------------------+
| + setFlyBehavior()   |         |  FlyWithWings        |
| + setQuackBehavior() |         |  FlyNoWay            |
+----------------------+         |  FlyRocketPowered    |
                                 +----------------------+
```

- **Duck**: Abstract class that contains references to the behaviors (strategy objects).
- **FlyBehavior, QuackBehavior**: Interfaces representing interchangeable behaviors.
- **FlyWithWings, FlyNoWay, FlyRocketPowered**: Concrete strategy implementations.

---

## 📝 Implementation (SimUDuck Example)

### 🎯 Step 1: Define the Strategy Interfaces

```java
// FlyBehavior interface
public interface FlyBehavior {
    void fly();
}

// QuackBehavior interface
public interface QuackBehavior {
    void quack();
}
```

### 🎯 Step 2: Implement Concrete Strategies
```java 
// Concrete Strategy - FlyWithWings
public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying!!");
    }
}

// Concrete Strategy - FlyNoWay
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I can't fly");
    }
}

// Concrete Strategy - FlyRocketPowered
public class FlyRocketPowered implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying with a rocket!");
    }
}

// Concrete Strategy - Quack
public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Quack");
    }
}

// Concrete Strategy - MuteQuack
public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("<<Silence>>");
    }
}

// Concrete Strategy - Squeak
public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Squeak");
    }
}
```

### 🎯 Step 3: Create the Context (Duck Class)
```java
public abstract class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public Duck() {}

    public abstract void display();

    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void swim() {
        System.out.println("All ducks float, even decoys!");
    }

    public void setFlyBehavior(FlyBehavior fb) {
        flyBehavior = fb;
    }

    public void setQuackBehavior(QuackBehavior qb) {
        quackBehavior = qb;
    }
}
```

### 🎯 Step 4: Create Specific Duck Types
```java 
// Concrete Duck - MallardDuck
public class MallardDuck extends Duck {
    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }
    @Override
    public void display() {
        System.out.println("I'm a Mallard Duck");
    }
}

// Concrete Duck - ModelDuck (doesn't fly initially)
public class ModelDuck extends Duck {
    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }
    @Override
    public void display() {
        System.out.println("I'm a Model Duck");
    }
}
```

### 🎯 Step 5: Simulating Duck Behavior
```java 
public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();

        Duck model = new ModelDuck();
        model.performFly();
        
        // Dynamically change behavior at runtime
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}
```

### 🏃‍♂️ Output:
```
Quack
I'm flying!!
I can't fly
I'm flying with a rocket!
```

## 🚀 Real-World Applications of the Strategy Pattern

| **Application**         | **Example**                                                                 |
|-------------------------|---------------------------------------------------------------------------|
| **Sorting Algorithms**  | Encapsulating different sorting strategies (QuickSort, MergeSort) and allowing users to choose dynamically. |
| **Payment Processing**  | Switching between Credit Card, PayPal, and UPI payments in an e-commerce application. |
| **Compression Algorithms** | Supporting multiple file compression formats (ZIP, RAR, 7z) dynamically. |
| **AI Game Behavior**    | Changing enemy AI behavior (aggressive, defensive, neutral) at runtime. |

## 🎯 Key Takeaways

- ✅ Helps in writing maintainable and flexible code.
- ✅ Decouples behaviors from the main class, making modifications easy.
- ✅ Avoids if-else conditions by using polymorphism.
- ✅ Supports runtime behavior changes dynamically.

## 🎤 Strategy Pattern - Interview Questions & Answers

### 1️⃣ What problem does the Strategy Pattern solve?

**Answer:**
It solves the problem of hardcoding multiple behaviors inside a class, making the code rigid, hard to maintain, and difficult to extend. Instead, behaviors are encapsulated into separate classes and assigned dynamically.

### 2️⃣ How does Strategy Pattern follow the Open/Closed Principle?

**Answer:**
The Strategy Pattern allows us to add new behaviors (strategies) without modifying the existing classes. This keeps the system open for extension but closed for modification.

### 3️⃣ When would you not use the Strategy Pattern?

**Answer:**
If the behavior doesn’t change frequently or there are only a few variations, the overhead of creating multiple classes may not be justified. In such cases, simple inheritance or switch cases might be more efficient.

### 4️⃣ What’s the difference between Strategy Pattern and State Pattern?

**Answer:**

| **Strategy Pattern**                                      | **State Pattern**                                      |
|-----------------------------------------------------------|---------------------------------------------------------|
| Focuses on **behavioral variations** (like different flying behaviors). | Focuses on **state-dependent behavior** (like transitioning between states in a state machine). |
| Client **manually assigns** strategies.                   | State **transitions happen automatically**.             |

### 5️⃣ Can Strategy Pattern be implemented without interfaces?

**Answer:**
Yes, but using interfaces makes it more flexible by enforcing a contract and enabling polymorphism.

## 🎯 Summary

| **Concept**       | **Details**                                                         |
|-------------------|---------------------------------------------------------------------|
| **Pattern Type**  | Behavioral                                                         |
| **Purpose**      | Encapsulates interchangeable algorithms and allows behavior changes at runtime. |
| **Key Benefit**  | Reduces code duplication and improves maintainability.             |
| **Example**      | SimUDuck App                                                        |
| **Common Use Cases** | Payment methods, sorting algorithms, file compression.          |

# 2. Observer Pattern

The observer pattern defines a one-to-many dependency between objects so that when one object changes state, all of its dependents are notified and updated automatically.



