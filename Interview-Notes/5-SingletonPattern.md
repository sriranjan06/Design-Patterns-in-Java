# Singleton Pattern - Chocolate Boiler Example 🍫

## 📌 Definition
The **Singleton Pattern** ensures that a class has **only one instance**, and provides a **global point of access** to it.

It is a creational pattern that is useful when **exactly one object is needed** to coordinate actions across the system.

---

### ✅ Key Benefits:
- Guarantees a **single, consistent instance** throughout the application.
- **Lazily instantiates** the object when needed (optional).
- **Reduces memory footprint** by avoiding redundant object creation.
- Useful in managing **shared resources** (e.g., logging, cache, configuration).

---

## 🏟 Structure
### UML Representation of Singleton Pattern:
```
+----------------------+
|   ChocolateBoiler    |
|----------------------|
| - uniqueInstance     |
| - empty              |
| - boiled             |
|----------------------|
| + getInstance()      |
| + fill()             |
| + boil()             |
| + drain()            |
| + isEmpty()          |
| + isBoiled()         |
+----------------------+
```

- `uniqueInstance`: A static field storing the singleton instance.
- `getInstance()`: Provides access to the single instance, creating it if necessary.

---

## 📝 Implementation (Chocolate Boiler Example)

### 🎯 Step 1: Create the Singleton Class
```java
public class ChocolateBoiler {
    private boolean empty;
    private boolean boiled;
    private static ChocolateBoiler uniqueInstance;

    private ChocolateBoiler() {
        empty = true;
        boiled = false;
    }

    public static ChocolateBoiler getInstance() {
        if (uniqueInstance == null) {
            System.out.println("Creating unique instance of Chocolate Boiler");
            uniqueInstance = new ChocolateBoiler();
        }
        System.out.println("Returning instance of Chocolate Boiler");
        return uniqueInstance;
    }

    public void fill() { if (isEmpty()) { empty = false; boiled = false; } }
    public void drain() { if (!isEmpty() && isBoiled()) { empty = true; } }
    public void boil() { if (!isEmpty() && !isBoiled()) { boiled = true; } }
    public boolean isEmpty() { return empty; }
    public boolean isBoiled() { return boiled; }
}
```

### 🎯 Step 2: Using the Singleton
```java
public class ChocolateController {
    public static void main(String args[]) {
        ChocolateBoiler boiler = ChocolateBoiler.getInstance();
        boiler.fill();
        boiler.boil();
        boiler.drain();

        // Returns the same instance
        ChocolateBoiler boiler2 = ChocolateBoiler.getInstance();
    }
}
```

---

## ⚡ Singleton Variants

### 1. **Lazy Initialization (Not Thread-Safe)**
```java
public static ChocolateBoiler getInstance() {
    if (uniqueInstance == null) {
        uniqueInstance = new ChocolateBoiler();
    }
    return uniqueInstance;
}
```
> Simple but **not thread-safe**. Multiple threads can create multiple instances.

### 2. **Thread-Safe Synchronized Access**
```java
public static synchronized ChocolateBoiler getInstance() {
    if (uniqueInstance == null) {
        uniqueInstance = new ChocolateBoiler();
    }
    return uniqueInstance;
}
```
> Guarantees thread safety but **reduces performance** due to synchronization.

### 3. **Double-Checked Locking**
```java
public class ChocolateBoiler {
    private volatile static ChocolateBoiler uniqueInstance;
    public static ChocolateBoiler getInstance() {
        if (uniqueInstance == null) {
            synchronized (ChocolateBoiler.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new ChocolateBoiler();
                }
            }
        }
        return uniqueInstance;
    }
}
```
> Combines performance with thread safety using `volatile`.

### 4. **Eager Initialization**
```java
private static final ChocolateBoiler uniqueInstance = new ChocolateBoiler();
```
> Simple and thread-safe, but instance is created **even if never used**.

### 5. **Bill Pugh Singleton (Best Practice)**
```java
public class ChocolateBoiler {
    private ChocolateBoiler() {}
    private static class Holder {
        private static final ChocolateBoiler INSTANCE = new ChocolateBoiler();
    }
    public static ChocolateBoiler getInstance() {
        return Holder.INSTANCE;
    }
}
```
> Best of both worlds: **Lazy**, **thread-safe**, and **efficient**.

---

## 🚀 Real-World Applications of the Singleton Pattern
| **Application**         | **Example**                                                   |
|-------------------------|---------------------------------------------------------------|
| **Logging Systems**     | Ensures a single logger instance logs messages across app     |
| **Configuration Managers** | Single access point to application configuration          |
| **Thread Pools**        | Maintain a single thread pool instance                        |
| **Database Connection Pools** | Prevent excessive connections by using one manager       |
| **UI State Managers**   | Maintain global UI or context state in a central location     |

---

## 🌟 Key Takeaways
- ✅ Use Singleton to manage **shared resources**.
- ✅ Implement with care in **multithreaded environments**.
- ✅ Prefer **Bill Pugh or Double Checked Locking** over basic approaches.
- ✅ Consider testing implications (Singletons can hinder testability).

---

## 🎤 Singleton Pattern - Interview Questions & Answers

### 1️⃣ What problem does the Singleton Pattern solve?
**Answer:**  
Ensures that a class has only one instance and provides a global access point to that instance.

### 2️⃣ What are the drawbacks of using Singleton?
**Answer:**
- Can **violate Single Responsibility Principle**.
- Difficult to **unit test** due to global state.
- Can hide dependencies and lead to **tight coupling**.

### 3️⃣ What is the best way to implement a thread-safe Singleton?
**Answer:**  
Use the **Bill Pugh** approach or **double-checked locking with `volatile`**.

### 4️⃣ How do you ensure lazy loading in Singleton?
**Answer:**  
Avoid eager initialization; use techniques like **Holder class** or **lazy evaluation** in `getInstance()`.

### 5️⃣ How is Singleton different from a static class?
| **Singleton**             | **Static Class**                          |
|---------------------------|-------------------------------------------|
| Can be **extended or mocked** | Cannot be inherited or mocked           |
| Supports **lazy loading** | Loaded on classload                      |
| Implements **interfaces** | Cannot implement interfaces              |
| Allows **instantiation control** | No control over instantiation        |

---

## 🔹 Summary
| **Concept**       | **Details**                                                        |
|-------------------|---------------------------------------------------------------------|
| **Pattern Type**  | Creational                                                          |
| **Purpose**       | Ensure a class has one and only one instance                        |
| **Key Benefit**   | Shared access to a consistent instance throughout the application   |
| **Example**       | ChocolateBoiler, Logger, ConfigManager                              |
| **Common Use Cases** | Logging, configuration, thread pools, caching, DB connections     |

---

# Enum Singleton Pattern - Chocolate Boiler Example 🍫🔢

## 📌 Definition
In Java, the **Enum Singleton Pattern** uses an `enum` to define a single-instance class. This approach is considered the **most effective** and **safest** way to implement a singleton.

Joshua Bloch (author of *Effective Java*) strongly recommends this pattern due to its **simplicity**, **thread-safety**, and **protection against serialization and reflection attacks**.

---

### ✅ Key Benefits:
- **Thread-safe** by default.
- **Serialization-safe** without needing custom code.
- **Reflection-safe**: JVM ensures only one instance exists.
- **Concise** and **easy to implement**.

---

## 📂 Structure
### UML Representation of Enum Singleton:
```
+---------------------------+
|    ChocolateBoiler       |
|---------------------------|
| enum INSTANCE             |
| - empty: boolean          |
| - boiled: boolean         |
|---------------------------|
| + fill()                  |
| + boil()                  |
| + drain()                 |
| + isEmpty()               |
| + isBoiled()              |
+---------------------------+
```

---

## 📝 Implementation (Enum Singleton Chocolate Boiler)

### 🎯 Step 1: Define the Singleton Using Enum
```java
public enum ChocolateBoiler {
    INSTANCE;

    private boolean empty = true;
    private boolean boiled = false;

    public void fill() {
        if (isEmpty()) {
            empty = false;
            boiled = false;
            System.out.println("Filling the boiler...");
        }
    }

    public void boil() {
        if (!isEmpty() && !isBoiled()) {
            boiled = true;
            System.out.println("Boiling contents...");
        }
    }

    public void drain() {
        if (!isEmpty() && isBoiled()) {
            empty = true;
            System.out.println("Draining boiled chocolate...");
        }
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isBoiled() {
        return boiled;
    }
}
```

### 🎯 Step 2: Using the Singleton
```java
public class ChocolateController {
    public static void main(String[] args) {
        ChocolateBoiler boiler = ChocolateBoiler.INSTANCE;
        boiler.fill();
        boiler.boil();
        boiler.drain();

        // Still the same instance
        ChocolateBoiler anotherBoiler = ChocolateBoiler.INSTANCE;
        System.out.println("Same instance: " + (boiler == anotherBoiler));
    }
}
```

### 📊 Output:
```
Filling the boiler...
Boiling contents...
Draining boiled chocolate...
Same instance: true
```

---

## 🚀 Real-World Applications of Enum Singleton
| **Application**         | **Example**                                                   |
|-------------------------|---------------------------------------------------------------|
| **Configuration Managers** | Global config instance                                    |
| **Loggers**             | Single logger instance across the app                         |
| **Service Locators**    | Singleton registry pattern                                    |
| **Shared Caches**       | Maintain a global cache instance                              |
| **App States**          | Managing global app state using enums                         |

---

## ⚡ Enum Singleton vs Classic Singleton
| **Aspect**               | **Classic Singleton**                              | **Enum Singleton**                            |
|--------------------------|-----------------------------------------------------|------------------------------------------------|
| Thread Safe              | No (unless synchronized / volatile / DCL)          | Yes (by JVM design)                           |
| Serialization Safe       | No (must override `readResolve`)                   | Yes                                            |
| Reflection Safe          | No (can be broken via reflection)                  | Yes                                            |
| Lazy Initialization      | Yes (optional)                                     | No (instantiated on class loading)            |
| Code Complexity          | High (in thread-safe versions)                     | Very Low                                       |
| Recommendation           | Use with caution                                  | ✅ Recommended (Effective Java)             |

---

## 🎤 Enum Singleton - Interview Questions & Answers

### 1️⃣ Why is Enum Singleton considered the best way to implement a singleton in Java?
**Answer:**  
Because it is **thread-safe**, **serialization-proof**, and **resistant to reflection**, all by default with **minimal code**.

### 2️⃣ Can Enum Singleton be lazy-loaded?
**Answer:**  
No. Enum singletons are initialized when the class is loaded. If lazy-loading is essential, use other variants like Bill Pugh or DCL.

### 3️⃣ How does Enum Singleton handle serialization?
**Answer:**  
Enums are inherently **serialization-safe**, so no need to override `readResolve()` as with classic singletons.

### 4️⃣ Can reflection break Enum Singleton?
**Answer:**  
No. The Java language **prevents enum constructors from being called via reflection**.

### 5️⃣ When should you use Enum Singleton?
**Answer:**  
Use Enum Singleton when you want a **simple**, **robust**, and **globally accessible singleton** that doesn’t require lazy loading.

---

## 🔹 Summary
| **Concept**       | **Details**                                                        |
|-------------------|---------------------------------------------------------------------|
| **Pattern Type**  | Creational                                                          |
| **Purpose**       | Ensure one instance using a safe, minimal enum                     |
| **Key Benefit**   | Thread-safe, serialization-safe, reflection-safe, concise          |
| **Example**       | ChocolateBoiler (as Enum), ConfigManager                            |
| **Common Use Cases** | Loggers, global config, shared cache, service locator           |

---

