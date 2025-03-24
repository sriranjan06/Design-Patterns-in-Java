# Factory Method Pattern - Pizza Store Example 🍕

## 📌 Definition
The **Factory Method Pattern** defines an interface for creating an object but lets subclasses decide which class to instantiate. This pattern allows a class to defer instantiation to its subclasses.

### ✅ Key Benefits:
- Encourages **loose coupling** between client code and object creation.
- Follows the **Open/Closed Principle** (OCP) by allowing new object types **without modifying existing code**.
- Promotes **code reuse** and makes **object creation more flexible**.
- Implements the **Dependency Inversion Principle (DIP)**: "Depend upon abstractions; do not depend on concrete classes."

---

## 🏗 Structure
### UML Representation of Factory Method Pattern:
```
+------------------+         +----------------------+
|   PizzaStore     |         |       Pizza          |
|------------------|         |----------------------|
| + orderPizza()   |◄------- | + prepare()          |
| + createPizza()  |         | + bake()             |
|------------------|         | + cut()              |
+------------------+         | + box()              |
         ▲                   +----------------------+
         │
+-------------------+     +-----------------------+
|  NYPizzaStore     |     |  ChicagoPizzaStore    |
|-------------------|     |-----------------------|
| + createPizza()   |     | + createPizza()       |
+-------------------+     +-----------------------+
```

- **PizzaStore**: Defines an abstract method `createPizza()`.
- **Concrete Stores (NYPizzaStore, ChicagoPizzaStore)**: Implement `createPizza()` to return specific pizza types.
- **Pizza Class**: Defines base methods for all pizzas.

---

## 📝 Implementation (Pizza Store Example)

### 🎯 Step 1: Define the `PizzaStore` Abstract Class
```java
public abstract class PizzaStore {
    abstract Pizza createPizza(String item);
    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);
        System.out.println("--- Making a " + pizza.getName() + " ---");
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
```

### 🎯 Step 2: Implement the `Pizza` Abstract Class
```java
import java.util.ArrayList;
public abstract class Pizza {
    String name;
    String dough;
    String sauce;
    ArrayList<String> toppings = new ArrayList<>();
    void prepare() {
        System.out.println("Prepare " + name);
        System.out.println("Tossing dough...");
        System.out.println("Adding sauce...");
    }
    void bake() { System.out.println("Bake for 25 minutes at 350"); }
    void cut() { System.out.println("Cut the pizza into diagonal slices"); }
    void box() { System.out.println("Place pizza in official PizzaStore box"); }
    public String getName() { return name; }
}
```

### 🎯 Step 3: Implement Concrete `PizzaStore` Variants
```java
public class NYPizzaStore extends PizzaStore {
    Pizza createPizza(String item) {
        if (item.equals("cheese")) {
            return new NYStyleCheesePizza();
        } else if (item.equals("veggie")) {
            return new NYStyleVeggiePizza();
        }
        return null;
    }
}

public class ChicagoPizzaStore extends PizzaStore {
    Pizza createPizza(String item) {
        if (item.equals("cheese")) {
            return new ChicagoStyleCheesePizza();
        }
        return null;
    }
}
```

### 🎯 Step 4: Test the Factory Method Pattern
```java
public class PizzaTestDrive {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();
        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println("Ethan ordered a " + pizza.getName() + "\n");
        pizza = chicagoStore.orderPizza("cheese");
        System.out.println("Joel ordered a " + pizza.getName() + "\n");
    }
}
```

---

## 🚀 Real-World Applications of the Factory Method Pattern
| **Application**          | **Example**                                                                 |
|-------------------------|---------------------------------------------------------------------------|
| **Database Connection** | Creating different database connections dynamically (MySQL, PostgreSQL, etc.). |
| **Logging Frameworks**  | Allowing multiple logging implementations (console, file, cloud). |
| **UI Component Factories** | Creating different types of UI buttons based on the platform (Windows, macOS). |
| **Parsing Engines**     | Generating different file parsers dynamically (JSON, XML, CSV). |

---

## 🎯 Key Takeaways
- ✅ Helps in writing maintainable and flexible code.
- ✅ Decouples object creation from the client, making it **easier to introduce new types**.
- ✅ Encourages consistency across object creation in large-scale applications.
- ✅ Adheres to **Open/Closed Principle** (OCP) and **Dependency Inversion Principle** (DIP).

---

## 🎤 Factory Method Pattern - Interview Questions & Answers

### **1️⃣ What problem does the Factory Method Pattern solve?**
**Answer:**  
It solves the problem of **hardcoding object creation**, making it easier to **introduce new types** without modifying the existing code.

### **2️⃣ How does the Factory Method Pattern follow the Open/Closed Principle?**
**Answer:**  
It allows adding **new product types** without modifying existing code, keeping the system open for extension but closed for modification.

### **3️⃣ When should you use the Factory Method Pattern?**
**Answer:**
- When the exact **type of object is determined at runtime**.
- When you want to **decouple object creation** from its usage.
- When multiple subclasses share a common interface but **implement object creation differently**.

### **4️⃣ How does the Factory Method Pattern differ from the Simple Factory?**
| **Factory Method Pattern** | **Simple Factory** |
|----------------------|--------------------------|
| Uses **subclasses** to create objects | Uses a **single factory class** |
| Follows **Open/Closed Principle** | Does **not** follow Open/Closed Principle |
| More flexible and scalable | Simpler but less maintainable |

---

## 🎯 Summary
| **Concept**      | **Details** |
|-----------------|------------|
| **Pattern Type** | Creational |
| **Purpose**      | Allows subclasses to decide which concrete class to instantiate. |
| **Key Benefit**  | Reduces code duplication and improves maintainability. |
| **Example**      | Pizza Store Factory |
| **Common Use Cases** | UI frameworks, logging systems, database connections. |

---


# Abstract Factory Pattern - Pizza Store Example 🍕🏭

## 📌 Definition
The **Abstract Factory Pattern** provides an interface for creating **families of related objects** without specifying their concrete classes. Unlike the **Factory Method Pattern**, which focuses on creating a single product, the **Abstract Factory Pattern** creates **multiple related products** together.

### ✅ Key Benefits:
- **Encapsulates object creation** of related products in one place.
- **Ensures consistency** in object creation (e.g., all NY-style ingredients are from one factory).
- **Decouples client code** from concrete classes, improving maintainability.
- **Supports Open/Closed Principle** by allowing new product families to be added without modifying existing code.

---

## 🏗 Structure
### UML Representation of Abstract Factory Pattern:
```
+---------------------------+
|  PizzaIngredientFactory   |
|---------------------------|
| + createDough()           |
| + createSauce()           |
| + createCheese()          |
| + createVeggies()         |
| + createPepperoni()       |
| + createClams()           |
+---------------------------+
         ▲                ▲
         │                │
+-------------------+  +------------------------+
| NYPizzaIngredient |  | ChicagoPizzaIngredient |
|-------------------|  |------------------------|
| + createDough()   |  | + createDough()        |
| + createSauce()   |  | + createSauce()        |
| + createCheese()  |  | + createCheese()       |
+-------------------+  +------------------------+
```

- **PizzaIngredientFactory**: Abstract interface defining methods for creating pizza ingredients.
- **Concrete Factories (NYPizzaIngredientFactory, ChicagoPizzaIngredientFactory)**: Implement the ingredient creation based on region.
- **Pizza Class**: Uses an ingredient factory to compose pizzas dynamically.

---

## 📝 Implementation (Ingredient Factory Example)

### 🎯 Step 1: Define the `PizzaIngredientFactory` Interface
```java
public interface PizzaIngredientFactory {
    Dough createDough();
    Sauce createSauce();
    Cheese createCheese();
    Veggies[] createVeggies();
    Pepperoni createPepperoni();
    Clams createClam();
}
```

### 🎯 Step 2: Implement the Factory for NY and Chicago Ingredients
```java
public class NYPizzaIngredientFactory implements PizzaIngredientFactory {
    public Dough createDough() { return new ThinCrustDough(); }
    public Sauce createSauce() { return new MarinaraSauce(); }
    public Cheese createCheese() { return new ReggianoCheese(); }
    public Veggies[] createVeggies() { return new Veggies[]{new Garlic(), new Onion(), new Mushroom()}; }
}
```

```java
public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
    public Dough createDough() { return new ThickCrustDough(); }
    public Sauce createSauce() { return new PlumTomatoSauce(); }
    public Cheese createCheese() { return new MozzarellaCheese(); }
}
```

### 🎯 Step 3: Implement the `Pizza` class using Factories
```java
public class CheesePizza extends Pizza {
    PizzaIngredientFactory ingredientFactory;
    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }
    void prepare() {
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        cheese = ingredientFactory.createCheese();
    }
}
```

---

## 🏆 Factory Method vs. Abstract Factory - Key Differences

| **Factory Method Pattern**                          | **Abstract Factory Pattern**                        |
|---------------------------------------------------|------------------------------------------------|
| Creates **one** product at a time                 | Creates **multiple related** products at once  |
| Uses **inheritance** (subclasses decide object creation) | Uses **composition** (a factory creates multiple related objects) |
| Clients create objects through **subclasses**     | Clients create objects through **composition** |
| Best for situations where only **one** object needs to be created dynamically | Best for situations where multiple related objects need to be created consistently |

---

## 🚀 Real-World Applications of the Abstract Factory Pattern
| **Application**             | **Example**                                               |
|-----------------------------|-----------------------------------------------------------|
| **GUI Libraries**           | Creating UI components that work on different platforms (Windows, macOS, Linux). |
| **Database Connectivity**   | Generating database connections based on the environment (MySQL, PostgreSQL, MongoDB). |
| **Game Development**        | Creating different assets depending on game settings (medieval vs. futuristic). |
| **Theming in Applications** | Applying dark/light theme components dynamically. |

---

## 🎯 Key Takeaways
- ✅ The **Factory Method Pattern** lets subclasses decide which class to instantiate.
- ✅ The **Abstract Factory Pattern** groups multiple related product families together.
- ✅ The **Abstract Factory Pattern** ensures consistency in product creation while **decoupling the client from concrete implementations**.
- ✅ Both patterns promote the **Open/Closed Principle**, making the system **extensible without modifying existing code**.

---

## 🎤 Abstract Factory Pattern - Interview Questions & Answers

### **1️⃣ What problem does the Abstract Factory Pattern solve?**
**Answer:**  
It solves the issue of **creating families of related objects** while ensuring consistency across those objects, making the system flexible and scalable.

### **2️⃣ How does the Abstract Factory Pattern follow the Open/Closed Principle?**
**Answer:**  
It allows new product families (e.g., adding an ItalianPizzaIngredientFactory) **without modifying existing factories or products**.

### **3️⃣ When should you use the Abstract Factory Pattern?**
**Answer:**
- When a system needs to be **independent of how objects are created**.
- When you want to **ensure consistency** among related objects.
- When different implementations of objects need to be **swapped easily**.

### **4️⃣ How does the Abstract Factory Pattern differ from Factory Method?**
| **Abstract Factory Pattern** | **Factory Method Pattern** |
|----------------------|--------------------------|
| Creates **multiple related** objects | Creates **only one** object at a time |
| Uses **composition** | Uses **inheritance** |
| More complex but highly flexible | Simpler, but limited to single object creation |

---

## 🎯 Summary
| **Concept**      | **Details** |
|-----------------|------------|
| **Pattern Type** | Creational |
| **Purpose**      | Creates families of related objects while ensuring consistency. |
| **Key Benefit**  | Decouples client code from concrete classes, improving scalability. |
| **Example**      | Pizza Ingredient Factory |
| **Common Use Cases** | UI theming, game assets, database configurations. |

---



