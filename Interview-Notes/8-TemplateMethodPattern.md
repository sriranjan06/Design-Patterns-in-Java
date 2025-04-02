# Template Method Pattern - Caffeine Beverage Example ☕️

## 📌 Definition
The **Template Method Pattern** defines the **skeleton of an algorithm** in a method, deferring some steps to subclasses. It lets subclasses **redefine certain steps** of an algorithm **without changing the algorithm's structure**.

---

## ✅ Key Benefits
- Encourages **code reuse** by putting common behavior in a superclass.
- Promotes the **Hollywood Principle**: "Don't call us, we'll call you."
- Supports the **Open/Closed Principle** by allowing new behavior via subclassing.
- Standardizes the **overall structure** while allowing **flexibility** in specific steps.

---

## 🏋️ Structure

### UML Representation of Template Method Pattern:
```
+-----------------------------+
|     AbstractClass          |
|-----------------------------|
| + templateMethod()         |
| + primitiveOperation1()    |
| + primitiveOperation2()    |
+-----------------------------+
           /\
            |
   +---------------------+
   | ConcreteClass1      |
   +---------------------+
   | + primitiveOperation1()|
   | + primitiveOperation2()|
   +---------------------+
```

- **AbstractClass**: Defines a template method and abstract primitive operations.
- **ConcreteClass**: Implements the primitive operations to carry out subclass-specific steps.

---

## 📅 Implementation (Caffeine Beverage Example)

### ✨ Step 1: Abstract Template - `CaffeineBeverage`
```java
public abstract class CaffeineBeverage {
  final void prepareRecipe() {
    boilWater();
    brew();
    pourInCup();
    addCondiments();
  }

  abstract void brew();
  abstract void addCondiments();

  void boilWater() {
    System.out.println("Boiling water");
  }

  void pourInCup() {
    System.out.println("Pouring into cup");
  }
}
```

### 🍵 Concrete Class - `Tea`
```java
public class Tea extends CaffeineBeverage {
  public void brew() {
    System.out.println("Steeping the tea");
  }
  public void addCondiments() {
    System.out.println("Adding Lemon");
  }
}
```

### ☕️ Concrete Class - `Coffee`
```java
public class Coffee extends CaffeineBeverage {
  public void brew() {
    System.out.println("Dripping Coffee through filter");
  }
  public void addCondiments() {
    System.out.println("Adding Sugar and Milk");
  }
}
```

---

## ⚖️ Hooked Template - `CaffeineBeverageWithHook`
```java
public abstract class CaffeineBeverageWithHook {
  final void prepareRecipe() {
    boilWater();
    brew();
    pourInCup();
    if (customerWantsCondiments()) {
      addCondiments();
    }
  }
  abstract void brew();
  abstract void addCondiments();
  void boilWater() {
    System.out.println("Boiling water");
  }
  void pourInCup() {
    System.out.println("Pouring into cup");
  }
  boolean customerWantsCondiments() {
    return true;
  }
}
```

### 🍵 `TeaWithHook` and ☕️ `CoffeeWithHook`
```java
public class TeaWithHook extends CaffeineBeverageWithHook {
  public void brew() {
    System.out.println("Steeping the tea");
  }
  public void addCondiments() {
    System.out.println("Adding Lemon");
  }
  public boolean customerWantsCondiments() {
    String answer = getUserInput();
    return answer.toLowerCase().startsWith("y");
  }
  private String getUserInput() {
    // Read from user
  }
}
```

---

## 🚀 Real-World Applications of Template Method Pattern

| **Application**               | **Example**                                                        |
|------------------------------|---------------------------------------------------------------------|
| **Beverage Preparation**     | Standard recipe with customizable steps (e.g., tea vs. coffee)     |
| **Frameworks & Toolkits**    | JUnit, Servlet Lifecycle (`init()`, `doGet()`, `destroy()`)         |
| **Game Engines**             | Common game loop with customizable update/render phases             |
| **UI Libraries**             | Standard rendering sequence with user-defined drawing methods       |

---

## 📈 Key Takeaways

- ✅ Defines a clear algorithm structure.
- ✅ Reuses code while allowing subclasses to override certain behavior.
- ✅ Hook methods provide optional customization.
- ✅ Follows **Inversion of Control (Hollywood Principle)**.

---

## 🔊 Template Method Pattern - Interview Q&A

### 1. What is the purpose of the Template Method Pattern?
**Answer**: It provides a skeleton of an algorithm in a method, allowing subclasses to fill in specific steps.

### 2. How does the Template Method Pattern follow the Open/Closed Principle?
**Answer**: It lets you extend behaviors via subclassing without changing the abstract template's core structure.

### 3. What is a hook method?
**Answer**: A method in the superclass with an empty or default implementation that can be optionally overridden.

### 4. What principle does the Template Method support?
**Answer**: The **Hollywood Principle** – "Don't call us, we'll call you."

### 5. What is a drawback of the Template Method Pattern?
**Answer**: It relies on inheritance, which can lead to tight coupling between the abstract class and its subclasses.

---

## 🔹 Summary

| **Concept**           | **Details**                                                         |
|------------------------|---------------------------------------------------------------------|
| **Pattern Type**       | Behavioral                                                         |
| **Purpose**            | Define a skeleton of an algorithm and allow steps to vary           |
| **Key Benefit**        | Encourages code reuse and consistency across variants              |
| **Common Use Cases**   | Frameworks, lifecycle methods, UI rendering, parsing engines       |
| **Principles Applied** | Open/Closed, Hollywood Principle                                   |

