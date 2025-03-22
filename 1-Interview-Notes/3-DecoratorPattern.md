# Decorator Pattern - Starbuzz Coffee ☕

## 📌 Definition
The **Decorator Pattern** allows behavior to be added to an individual object, dynamically, without affecting the behavior of other objects from the same class. It is a **structural pattern** that enhances class flexibility and promotes code reuse.

### ✅ Key Benefits:
- Enables **dynamic extension** of object behavior at runtime.
- Promotes **Open/Closed Principle** by allowing new behaviors **without modifying existing classes**.
- Avoids **subclass explosion** that occurs when trying to create multiple variations of a class through inheritance.

---

## 🏗 Structure

### UML Representation of Decorator Pattern:
```
+------------------+          +------------------------+
|   Beverage       |          |   CondimentDecorator   |
|------------------|          |------------------------|
| - description    |◄-------- | - beverage             |
|------------------|          | + getDescription()     |
| + cost()         |          +------------------------+
+------------------+
          ▲                             ▲
          │                             │
+-------------------+        +-------------------+
|  HouseBlend       |        |  Mocha            |
|  DarkRoast        |        |  Soy              |
|  Espresso         |        |  Whip             |
+-------------------+        +-------------------+
```

- **Component (`Beverage`)**: Defines the interface for objects that can have responsibilities added dynamically.
- **Concrete Component (`HouseBlend`, `Espresso`, etc.)**: The core classes that can be decorated.
- **Decorator (`CondimentDecorator`)**: Wraps a component and adds behavior.
- **Concrete Decorators (`Mocha`, `Soy`, `Whip`)**: Adds new responsibilities dynamically.

---

## 📝 Implementation (Starbuzz Coffee Example)

### 🎯 Step 1: Define the `Beverage` and `CondimentDecorator` Abstract Classes

```java
public abstract class Beverage {
    public enum Size { TALL, GRANDE, VENTI };
    Size size = Size.TALL;
    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public Size getSize() {
        return this.size;
    }

    public abstract double cost();
}

public abstract class CondimentDecorator extends Beverage {
    Beverage beverage;
    public abstract String getDescription();
    
    public Size getSize() {
        return beverage.getSize();
    }
}
```

---

### 🎯 Step 2: Implement the Concrete `Beverage` Classes

```java
public class DarkRoast extends Beverage {
    public DarkRoast() {
        description = "Dark Roast Coffee";
    }
    public double cost() {
        return 0.99;
    }
}

public class Espresso extends Beverage {
    public Espresso() {
        description = "Espresso";
    }
    public double cost() {
        return 1.99;
    }
}

public class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "House Blend Coffee";
    }
    public double cost() {
        return 0.89;
    }
}
```

---

### 🎯 Step 3: Implement the Concrete Decorators

```java
public class Mocha extends CondimentDecorator {
    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    public double cost() {
        return beverage.cost() + 0.20;
    }
}

public class Soy extends CondimentDecorator {
    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }

    public double cost() {
        double cost = beverage.cost();
        if(beverage.getSize() == Size.TALL) cost += 0.10;
        else if(beverage.getSize() == Size.GRANDE) cost += 0.15;
        else if(beverage.getSize() == Size.VENTI) cost += 0.20;
        return cost;
    }
}
```

---

### 🎯 Step 4: Simulating Decorator Behavior

```java
public class StarbuzzCoffee {
    public static void main(String args[]) {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $" + String.format("%.2f", beverage.cost()));

        Beverage beverage2 = new DarkRoast();
        beverage2 = new Mocha(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription() + " $" + String.format("%.2f", beverage2.cost()));

        Beverage beverage3 = new HouseBlend();
        beverage3 = new Soy(beverage3);
        beverage3 = new Mocha(beverage3);
        beverage3 = new Whip(beverage3);
        System.out.println(beverage3.getDescription() + " $" + String.format("%.2f", beverage3.cost()));
    }
}
```

---

## 🚀 Real-World Applications of the Decorator Pattern
| **Application**         | **Example**                                                                 |
|-------------------------|---------------------------------------------------------------------------|
| **Java I/O Streams**    | `BufferedInputStream`, `DataInputStream` dynamically add behavior to `InputStream`. |
| **Graphical User Interfaces** | Dynamically adding UI features like scrollbars or themes. |
| **Text Processing**     | Formatting text by adding bold, italic styles without modifying the base text class. |
| **Security Systems**    | Adding encryption, authentication, or logging dynamically. |

---

## 🎯 Key Takeaways
- ✅ Enables dynamic runtime modifications without modifying the base class.
- ✅ Helps follow the **Open/Closed Principle**.
- ✅ Avoids subclass explosion that occurs when trying to create multiple variations.

---

## 🎤 Decorator Pattern - Interview Questions & Answers

### **1️⃣ What problem does the Decorator Pattern solve?**
**Answer:**  
It allows behavior to be **dynamically added** to objects without modifying their structure, avoiding deep class hierarchies.

### **2️⃣ How does the Decorator Pattern follow the Open/Closed Principle?**
**Answer:**  
New decorators can be added **without modifying** existing code, keeping it open for extension but closed for modification.

### **3️⃣ What is the main trade-off of using the Decorator Pattern?**
**Answer:**
- It can lead to **many small objects**, increasing complexity.
- Wrapping multiple decorators may make debugging harder.

### **4️⃣ How does the Decorator Pattern differ from the Proxy Pattern?**
| **Decorator Pattern** | **Proxy Pattern** |
|------------------|----------------------------|
| **Adds behavior dynamically at runtime** | **Controls access or adds logging/security** |
| **Focuses on enhancing functionality** | **Focuses on restricting or controlling access** |

---

## 🎯 Summary
| **Concept** | **Details** |
|------------|------------|
| **Pattern Type** | Structural |
| **Purpose** | Allows dynamic behavior extension without modifying base classes. |
| **Key Benefit** | Reduces subclass explosion, follows Open/Closed Principle. |
| **Example** | Java I/O Streams, UI components. |
| **Common Use Cases** | Text formatting, security layers, UI design. |

