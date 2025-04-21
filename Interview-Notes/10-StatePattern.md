# State Pattern - Gumball Machine Example 🍒🔧

## 📌 Definition
The **State Pattern** allows an object to alter its behavior when its **internal state changes**. The object will appear to **change its class**.

In other words, it encapsulates **state-specific behavior** into separate classes and delegates behavior to the current state object.

> "A pattern for managing state-specific behavior without conditionals."

---

## 💪 Key Benefits
- Removes long conditionals or `switch` statements based on state
- Encapsulates each state’s behavior into separate classes
- Makes code easier to understand and extend
- Promotes **Open/Closed Principle**: add new states without changing existing logic
- Enables state transitions to be defined within the states themselves

---

## 📊 UML Structure
```
+----------------+       uses       +------------------+
| GumballMachine |---------------->|      State       |
|----------------|                 +------------------+
| - state        |                 | + insertQuarter()|
| - count        |                 | + ejectQuarter() |
|                |                 | + turnCrank()    |
| + setState()   |                 | + dispense()     |
+----------------+                 +------------------+
       /_\                                  /_\
        |                                    |
+------------------+        +------------------+      ...
| NoQuarterState   |        | HasQuarterState  |      etc.
+------------------+        +------------------+
```
- **Context (`GumballMachine`)** delegates state-specific tasks to the current `State`.
- **State Interface** defines the common interface for all states.
- **Concrete States** implement state-specific behavior.

---

## 🛠️ Implementation (Gumball Machine Example)

### 🔹 Step 1: Define the State Interface
```java
public interface State {
    void insertQuarter();
    void ejectQuarter();
    void turnCrank();
    void dispense();
    void refill();
}
```

### 🔹 Step 2: Implement Concrete States
```java
public class NoQuarterState implements State {
    GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    public void insertQuarter() {
        System.out.println("You inserted a quarter");
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    public void ejectQuarter() {
        System.out.println("You haven't inserted a quarter");
    }

    public void turnCrank() {
        System.out.println("You turned, but there's no quarter");
    }

    public void dispense() {
        System.out.println("You need to pay first");
    }

    public void refill() {}

    public String toString() {
        return "waiting for quarter";
    }
}
```
*(Repeat for `HasQuarterState`, `SoldState`, `SoldOutState`, `WinnerState`)*

### 🔹 Step 3: Define the Context - `GumballMachine`
```java
public class GumballMachine {
    State soldOutState;
    State noQuarterState;
    State hasQuarterState;
    State soldState;
    State winnerState;

    State state = soldOutState;
    int count = 0;

    public GumballMachine(int numberGumballs) {
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);
        winnerState = new WinnerState(this);

        this.count = numberGumballs;
        if (numberGumballs > 0) {
            state = noQuarterState;
        }
    }

    public void insertQuarter() {
        state.insertQuarter();
    }

    public void ejectQuarter() {
        state.ejectQuarter();
    }

    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    void releaseBall() {
        System.out.println("A gumball comes rolling out the slot...");
        if (count > 0) count--;
    }

    void setState(State state) {
        this.state = state;
    }

    public State getState() { return state; }
    public int getCount() { return count; }

    public void refill(int count) {
        this.count += count;
        System.out.println("Machine refilled with " + count + " gumballs");
        state.refill();
    }

    // Getters for all states...
}
```

### 🎮 Step 4: Test it!
```java
public class GumballMachineTestDrive {
    public static void main(String[] args) {
        GumballMachine gumballMachine = new GumballMachine(10);

        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();

        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();

        System.out.println(gumballMachine);
    }
}
```

---

## 🌐 Real-World Applications
| Application         | Example                                                      |
|---------------------|--------------------------------------------------------------|
| Game Development    | Character states (idle, jumping, running, etc.)              |
| UI Widgets          | Button states (hovered, disabled, pressed)                  |
| Media Players       | States like playing, paused, stopped                         |
| Vending Machines    | Gumball Machine, Ticket Vending Machine                      |
| Network Connections | TCP state transitions (closed, listening, established, etc.) |

---

## 🤔 Common Pitfalls
- Placing state logic in the context class leads to spaghetti code.
- Not using interfaces can prevent polymorphism.
- Forgetting to define valid transitions can lead to bugs.

---

## 🎤 State Pattern - Interview Q&A

### 1. How does the State Pattern differ from Strategy?
| **Strategy Pattern**                                     | **State Pattern**                                      |
|----------------------------------------------------------|---------------------------------------------------------|
| Defines interchangeable algorithms                      | Defines behavior based on internal state               |
| Client chooses the behavior                             | Context determines state and transitions               |
| Focus on encapsulating algorithm variations             | Focus on state-specific behavior and transitions       |

### 2. What problem does the State Pattern solve?
**Answer:** Avoids long `if-else` or `switch` statements by encapsulating state-specific behavior in separate classes.

### 3. What principle does the State Pattern follow?
**Answer:** It follows the **Open/Closed Principle** and **Single Responsibility Principle** by separating concerns and allowing behavior extension without modifying the context.

### 4. Can you give an example of a state transition?
**Answer:** In the Gumball Machine, turning the crank when in `HasQuarterState` transitions to either `SoldState` or `WinnerState`.

### 5. Can the context class change state by itself?
**Answer:** Yes, typically states invoke `context.setState()` to trigger transitions.

### 6. What happens if no valid transition exists?
**Answer:** The state class should define a sensible default or throw an exception (e.g., "Can't turn crank now").

### 7. How does this improve testing?
**Answer:** Each state class can be tested in isolation, leading to more modular and maintainable tests.

---

## 🔄 Summary
| Concept             | Details                                                                 |
|----------------------|-------------------------------------------------------------------------|
| Pattern Type         | Behavioral                                                             |
| Intent               | Allow objects to alter behavior when their internal state changes      |
| Structure            | Uses context class and multiple state implementations                  |
| Real World Example   | Gumball machine, player states, buttons, network protocol states       |
| Compared With        | Strategy (interchangeable algorithms) and State (interchangeable states)|