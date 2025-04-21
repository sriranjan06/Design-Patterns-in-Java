# Builder Pattern - Comprehensive Guide ⚙️

## 📌 Definition
The **Builder Pattern** is a creational design pattern that allows you to construct complex objects step by step. Unlike other creational patterns, the Builder Pattern doesn’t require products to have a common interface. It is useful when an object must be created with many possible configuration options.

## ✅ Key Benefits
- **Separation of concerns**: Encapsulates the construction and representation logic separately.
- **Improved readability**: Makes the object creation process clearer and more manageable.
- **Flexibility**: Enables building different representations of a complex object using the same construction process.
- **Fluent Interface**: Often uses method chaining to make client code more readable.

---

## 🏐 Structure
### UML Representation of Builder Pattern:
```text
+------------------+       +-----------------+
|     Director     |       |     Builder     |<-----------------------------+
|------------------|       |-----------------|
| - builder        |       | + buildPartA()  |
|------------------|       | + buildPartB()  |
| + construct()    |       | + getResult()   |
+------------------+       +-----------------+
                                      ^
                                      |
               +----------------------+--------------------+
               |                                           |
     +----------------------+             +--------------------------+
     |   ConcreteBuilder1   |             |   ConcreteBuilder2       |
     |----------------------|             |--------------------------|
     | + buildPartA()       |             | + buildPartA()           |
     | + buildPartB()       |             | + buildPartB()           |
     | + getResult()        |             | + getResult()            |
     +----------------------+             +--------------------------+
```

- **Director**: Orchestrates the construction using the Builder.
- **Builder**: Specifies an abstract interface for creating parts of a product.
- **ConcreteBuilder**: Implements the Builder interface and provides specific implementations.
- **Product**: The final object being constructed.

---

## 📈 Real-World Applications
| **Application** | **Example** |
|------------------|-------------|
| UI Layouts | Building UI components like forms step-by-step in Android (e.g., AlertDialog.Builder) |
| Report Generators | Constructing reports from multiple data sections |
| Database Query Builders | Step-by-step SQL query generation |
| Game Development | Creating complex character objects with specific attributes |

---

## 🖋️ Sample Implementation (Java-style Pseudocode)
### Product Class
```java
public class Burger {
    private String bun;
    private String patty;
    private String sauce;

    // Setters
    public void setBun(String bun) { this.bun = bun; }
    public void setPatty(String patty) { this.patty = patty; }
    public void setSauce(String sauce) { this.sauce = sauce; }

    public String toString() {
        return bun + ", " + patty + ", " + sauce;
    }
}
```

### Builder Interface
```java
public interface BurgerBuilder {
    void buildBun();
    void buildPatty();
    void buildSauce();
    Burger getBurger();
}
```

### Concrete Builder
```java
public class VegBurgerBuilder implements BurgerBuilder {
    private Burger burger = new Burger();

    public void buildBun() { burger.setBun("Whole Wheat Bun"); }
    public void buildPatty() { burger.setPatty("Bean Patty"); }
    public void buildSauce() { burger.setSauce("Tomato Sauce"); }
    public Burger getBurger() { return burger; }
}
```

### Director Class
```java
public class Waiter {
    private BurgerBuilder builder;

    public void setBuilder(BurgerBuilder builder) {
        this.builder = builder;
    }

    public Burger constructBurger() {
        builder.buildBun();
        builder.buildPatty();
        builder.buildSauce();
        return builder.getBurger();
    }
}
```

---

## 📆 Builder vs Factory vs Abstract Factory
| **Feature** | **Builder** | **Factory** | **Abstract Factory** |
|------------|-------------|-------------|----------------------|
| Purpose | Constructs complex objects step-by-step | Creates objects without exposing instantiation logic | Creates families of related objects |
| Focus | Construction process | Object creation | Object families |
| Output | One complex object | One object | Multiple related objects |
| Control Flow | Controlled by client or Director | Encapsulated within factory method | Across related factories |

---

## 💡 Key Takeaways
- Use Builder when object construction requires many steps.
- Ideal when constructors get unwieldy with many parameters.
- Supports immutability and fluent interfaces.
- Builder pattern is a great choice for **test data builders**, **DSLs**, and **configuration objects**.

---

## 🎬 Builder Pattern - Interview Questions & Answers

### 1️⃣ What problem does the Builder Pattern solve?
**Answer:** It handles the construction of complex objects by breaking it into a step-by-step process and separating the construction logic from the representation.

### 2️⃣ Can you use Builder with Immutable Objects?
**Answer:** Yes! A Builder can initialize all fields and then construct an immutable object by passing the values into its constructor.

### 3️⃣ What is the difference between Builder and Telescoping Constructors?
**Answer:** Telescoping constructors result in difficult-to-read overloaded constructors, whereas Builder provides readable chained methods.

### 4️⃣ What design principles does the Builder Pattern follow?
- **Single Responsibility**: Separation of construction from representation.
- **Encapsulation**: Hides object creation details.
- **Open/Closed Principle**: Add new builders without modifying existing logic.

---

## 🔄 Summary
| **Concept** | **Details** |
|-------------|-------------|
| Pattern Type | Creational |
| Purpose | Construct complex objects step-by-step |
| Key Benefit | Separation of object construction and representation |
| Example | Building a Burger with layers |
| Common Use Cases | UI builders, configuration objects, game engines |