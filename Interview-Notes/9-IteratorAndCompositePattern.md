# Iterator Pattern - Diner, Pancake, and Cafe Menus 🍽️

## 📌 Definition
The **Iterator Pattern** provides a way to access elements of an aggregate object **sequentially** without exposing its **underlying representation**.

It decouples the client that uses the elements from the data structure that stores them.

## ✅ Key Benefits
- Supports iteration over different types of collections in a **uniform** way
- Encourages **single responsibility principle** by separating collection and iteration logic
- Avoids **exposing internal structure** of the collection
- Simplifies code maintenance

---

## 🛠️ Structure
### UML Overview
```
+------------------+           +----------------------+
|   Aggregate      |<>---------|     Iterator         |
+------------------+           +----------------------+
| + createIterator()|          | + hasNext()          |
+------------------+           | + next()             |
                               +----------------------+
```
- **Aggregate**: Interface or class with `createIterator()`
- **Iterator**: Provides interface for iterating (next, hasNext)
- **ConcreteIterator**: Implements `Iterator` for a specific collection
- **Client**: Uses iterator to loop through items

---

## 🖋️ Implementation (MenuComposite Example)
### 🌟 Step 1: Define the `Iterator` Interface
```java
public interface Iterator {
    boolean hasNext();
    MenuItem next();
}
```

### 🌟 Step 2: Implement Concrete Iterators
#### ☕ Cafe MenuComposite (uses `HashMap` internally)
```java
import java.util.Collection;

public class CafeMenuIterator implements Iterator {
    private java.util.Iterator<MenuItem> iterator;

    public CafeMenuIterator(Collection<MenuItem> items) {
        this.iterator = items.iterator();
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public MenuItem next() {
        return iterator.next();
    }
}
```

#### 🍽 Diner MenuComposite (uses Array)
```java
public class DinerMenuIterator implements Iterator {
    MenuItem[] items;
    int position = 0;

    public DinerMenuIterator(MenuItem[] items) {
        this.items = items;
    }

    public MenuItem next() {
        return items[position++];
    }

    public boolean hasNext() {
        return position < items.length && items[position] != null;
    }
}
```

#### 🥞 Pancake House MenuComposite (uses `ArrayList`)
```java
import java.util.List;

public class PancakeHouseMenuIterator implements Iterator {
    List<MenuItem> items;
    int position = 0;

    public PancakeHouseMenuIterator(List<MenuItem> items) {
        this.items = items;
    }

    public MenuItem next() {
        return items.get(position++);
    }

    public boolean hasNext() {
        return position < items.size();
    }
}
```

---

## 🏙️ Application in Menus

```java
public class Waitress {
    MenuComposite pancakeHouseMenu;
    MenuComposite dinerMenu;
    MenuComposite cafeMenu;

    public Waitress(MenuComposite pancakeHouseMenu, MenuComposite dinerMenu, MenuComposite cafeMenu) {
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinerMenu = dinerMenu;
        this.cafeMenu = cafeMenu;
    }

    public void printMenu() {
        Iterator pancakeIterator = pancakeHouseMenu.createIterator();
        Iterator dinerIterator = dinerMenu.createIterator();
        Iterator cafeIterator = cafeMenu.createIterator();

        printMenu(pancakeIterator);
        printMenu(dinerIterator);
        printMenu(cafeIterator);
    }

    private void printMenu(Iterator iterator) {
        while (iterator.hasNext()) {
            MenuItem item = iterator.next();
            System.out.println(item.getName() + ", $" + item.getPrice() + " -- " + item.getDescription());
        }
    }
}
```

---

## 📄 Java Iterable and Iterator

### 📖 `Iterable<T>` Interface
```java
public interface Iterable<T> {
    Iterator<T> iterator();
}
```
- All Java **collections implement `Iterable`** (e.g., `List`, `Set`, `Queue`, etc.)
- You can use **enhanced for-loop** with anything that is `Iterable`

### 📖 `Iterator<T>` Interface
```java
public interface Iterator<T> {
    boolean hasNext();
    T next();
    default void remove(); // optional
}
```
- Provides access to elements **sequentially**
- Java collections return their **own iterators** for iteration

---

## ➕ Enhanced for-loop in Java
```java
List<String> list = Arrays.asList("a", "b", "c");
for (String item : list) {
    System.out.println(item);
}
```
- Internally uses `.iterator()` under the hood
- Requires the collection to implement **`Iterable`**
- Arrays **do not** implement `Iterable`, but `ArrayList` does

---

## 🚀 Java 8+ `forEach()` with Lambda
```java
List<String> items = Arrays.asList("a", "b", "c");
items.forEach(item -> System.out.println(item));
```
- Accepts a **lambda expression**
- Does **not** expose internal `Iterator`
- More concise and readable

---

## 🚀 Real-World Applications of Iterator
| Application       | Example                                   |
|-------------------|--------------------------------------------|
| Collection APIs   | Iterating over `ArrayList`, `HashMap`, etc.|
| UI Toolkits       | Traversing UI components hierarchies      |
| File Processing   | Line-by-line file iteration                |
| Game Dev          | Looping through enemies, objects           |

---

## 🌟 Key Takeaways
| Concept                | Summary                                                     |
|------------------------|-------------------------------------------------------------|
| Pattern Type           | Behavioral                                                  |
| Purpose                | Access elements without exposing underlying structure       |
| Java Support           | `Iterable` + `Iterator` Interfaces                          |
| Enhanced for-loop      | Works only on `Iterable` collections                        |
| Custom Iterator        | Useful when the collection is non-standard or fixed-size    |
| forEach vs for-each    | `forEach()` uses lambda, `for-each` uses `:` with Iterable |

---

## 🎤 Iterator Pattern - Interview Q&A

### 1. What problem does the Iterator Pattern solve?
**Answer:** It allows sequential access to elements of a collection without revealing its internal structure.

### 2. How does Java support the Iterator Pattern?
**Answer:** Through `Iterator<T>` and `Iterable<T>` interfaces in the Java Collections Framework.

### 3. What's the difference between `Iterator` and `Iterable`?
**Answer:** `Iterator` does the iteration, `Iterable` provides the method to get an `Iterator`.

### 4. Can arrays be used in enhanced for-loops?
**Answer:** Yes, even though they don't implement `Iterable`, the language provides support for them in enhanced for-loops.

### 5. When would you write your own iterator?
**Answer:** When working with custom data structures (e.g., fixed-size arrays) or to customize the iteration logic (e.g., skipping items).

---

## 📆 Summary
| Aspect        | Detail                                                      |
|---------------|-------------------------------------------------------------|
| Pattern Type  | Behavioral                                                  |
| Goal          | Traverse collections without exposing their internals       |
| Java Usage    | Common via `Iterable` and `Iterator`                        |
| Benefits      | Separation of concerns, cleaner code, abstraction            |
| Example       | Menus in a restaurant with different internal structures     |


# Composite Pattern - Menu Example 🍽️📊

## 📌 Definition
The **Composite Pattern** lets you **compose objects into tree structures** to represent part-whole hierarchies. It allows clients to treat **individual objects** and **compositions of objects** uniformly.

> "Use the Composite Pattern when you want to be able to treat individual and composite objects uniformly."

---

## 💪 Key Benefits
- Treats **leaf and composite objects** the same way
- Supports **recursive structures** like trees and menus
- Simplifies client code by removing the need for conditionals
- Follows the **Open/Closed Principle** by allowing new leaf or composite types

---

## 🏢 Structure
```
Component
├── + operation()
├── + add(), remove(), getChild()
│
├── Leaf (e.g., MenuItem)
│     └── Implements operation()
│
└── Composite (e.g., Menu)
      └── Stores children and calls operation() recursively
```

---

## 🛠️ Implementation (Menu Example)

### ✨ Step 1: Abstract Component - `MenuComponent`
```java
public abstract class MenuComponent {
    public void add(MenuComponent menuComponent) { throw new UnsupportedOperationException(); }
    public void remove(MenuComponent menuComponent) { throw new UnsupportedOperationException(); }
    public MenuComponent getChild(int i) { throw new UnsupportedOperationException(); }

    public String getName() { throw new UnsupportedOperationException(); }
    public String getDescription() { throw new UnsupportedOperationException(); }
    public double getPrice() { throw new UnsupportedOperationException(); }
    public boolean isVegetarian() { throw new UnsupportedOperationException(); }

    public void print() { throw new UnsupportedOperationException(); }
}
```

### 🔹 Step 2: Leaf Node - `MenuCompositeItem`
```java
public class MenuCompositeItem extends MenuComponent {
    String name;
    String description;
    boolean vegetarian;
    double price;

    public MenuCompositeItem(String name, String description, boolean vegetarian, double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public boolean isVegetarian() { return vegetarian; }

    public void print() {
        System.out.print("  " + getName());
        if (isVegetarian()) System.out.print("(v)");
        System.out.println(", " + getPrice());
        System.out.println("     -- " + getDescription());
    }
}
```

### 🔹 Step 3: Composite Node - `MenuComposite`
```java
import java.util.ArrayList;
import java.util.Iterator;

public class MenuComposite extends MenuComponent {
    ArrayList<MenuComponent> menuComponents = new ArrayList<>();
    String name;
    String description;

    public MenuComposite(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void add(MenuComponent menuComponent) { menuComponents.add(menuComponent); }
    public void remove(MenuComponent menuComponent) { menuComponents.remove(menuComponent); }
    public MenuComponent getChild(int i) { return menuComponents.get(i); }

    public String getName() { return name; }
    public String getDescription() { return description; }

    public void print() {
        System.out.println("\n" + getName() + ", " + getDescription());
        System.out.println("---------------------");

        Iterator<MenuComponent> iterator = menuComponents.iterator();
        while (iterator.hasNext()) {
            MenuComponent component = iterator.next();
            component.print();
        }
    }
}
```

### 📋 Step 4: Client - `WaitressComposite`
```java
public class WaitressComposite {
    MenuComponent allMenus;

    public WaitressComposite(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }

    public void printMenu() {
        allMenus.print();
    }
}
```

---

## 📊 Output Example:
```
ALL MENUS, All menus combined
---------------------
PANCAKE HOUSE MENU, Breakfast
---------------------
  K&B's Pancake Breakfast(v), 2.99
     -- Pancakes with scrambled eggs and toast
...
```

---

## 🚀 Real-World Applications
| Application     | Example                                             |
|------------------|------------------------------------------------------|
| File Systems     | Files and directories as tree structures             |
| GUI Components   | Panels containing buttons, text fields, etc.         |
| Organization     | Company hierarchies (departments and employees)     |
| Game Engines     | Scene graphs, nested objects                         |

---

## 🌟 Key Takeaways
| Concept                  | Detail                                                         |
|---------------------------|----------------------------------------------------------------|
| Pattern Type              | Structural                                                    |
| Handles                   | Part-whole hierarchies (tree structures)                      |
| Leaf vs Composite         | Unified interface via abstraction                             |
| Client Simplicity         | Treats all components uniformly (no `instanceof` required)     |
| Limitation                | May violate LSP if not careful with unsupported operations     |

---

## 📆 Summary Table
| Role            | Class              | Responsibility                                |
|------------------|---------------------|-----------------------------------------------|
| Component        | `MenuComponent`     | Declares the common interface                 |
| Leaf             | `MenuCompositeItem` | Implements individual menu item logic         |
| Composite        | `MenuComposite`     | Stores children and calls their operations    |
| Client           | `WaitressComposite` | Works with `MenuComponent` uniformly          |

---

## 🎤 Composite Pattern - Interview Q&A

### 1. What problem does the Composite Pattern solve?
**Answer:** It lets you treat individual objects and compositions of objects uniformly.

### 2. How does the Composite Pattern simplify client code?
**Answer:** It eliminates the need for conditional logic to differentiate between leaf and composite objects.

### 3. What are some real-world examples?
**Answer:** Files and folders, company departments, graphical scenes.

### 4. How does it follow the Open/Closed Principle?
**Answer:** You can add new leaf or composite types without modifying existing code.

### 5. What are the trade-offs?
**Answer:** May violate Liskov Substitution Principle (LSP) when clients call unsupported operations on leaf nodes.