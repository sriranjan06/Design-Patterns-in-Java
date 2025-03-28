# Command Pattern - Remote Control Example 🔌

## 📌 Definition
The **Command Pattern** encapsulates a request as an object, thereby letting you **parameterize clients with different requests**, **queue or log requests**, and **support undoable operations**.

---

## ✅ Key Benefits
- Decouples the **invoker** (button/remote) from the **receiver** (light, fan, TV).
- Supports **undo** and **macro commands**.
- Promotes **extensibility** and **command reusability**.
- Encourages **open/closed principle** for new commands.

---

## 🛏 Structure
### UML Representation of Command Pattern:
```
+-------------+        +---------------+         +-------------+
|  Invoker    |        |   Command     |<--------| ConcreteCmd |
|-------------|        |---------------|         +-------------+
| - slot      |        | + execute()   |               |
| + setCmd()  |        | + undo()      |               |
| + press()   |        +---------------+               |
+-------------+                                 +-------------+
                                               |  Receiver    |
                                               |-------------|
                                               | + action()   |
                                               +-------------+
```

---

## 📝 Implementation (Remote Control Example)

### 🎯 Step 1: Define Command Interface
```java
public interface Command {
    void execute();
    void undo();
}
```

### 🎯 Step 2: Create Receivers (Light, CeilingFan, TV, Stereo, Hottub)
```java
public class Light {
    String location;
    public Light(String location) { this.location = location; }
    public void on() { System.out.println(location + " light is ON"); }
    public void off() { System.out.println(location + " light is OFF"); }
}

public class CeilingFan {
    public static final int HIGH = 3, MEDIUM = 2, LOW = 1, OFF = 0;
    int speed;
    public void high() { speed = HIGH; System.out.println("Ceiling fan on HIGH"); }
    public void off() { speed = OFF; System.out.println("Ceiling fan OFF"); }
    public int getSpeed() { return speed; }
}
```

### 🎯 Step 3: Implement Concrete Commands
```java
public class LightOnCommand implements Command {
    Light light;
    public LightOnCommand(Light light) { this.light = light; }
    public void execute() { light.on(); }
    public void undo() { light.off(); }
}

public class CeilingFanHighCommand implements Command {
    CeilingFan fan;
    int prevSpeed;
    public CeilingFanHighCommand(CeilingFan fan) { this.fan = fan; }
    public void execute() { prevSpeed = fan.getSpeed(); fan.high(); }
    public void undo() {
        if (prevSpeed == CeilingFan.OFF) fan.off();
    }
}
```

### 🎯 Step 4: Create the Invoker
```java
public class RemoteControl {
    Command[] onCommands;
    Command[] offCommands;
    Command undoCommand;

    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];
        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = offCommands[i] = noCommand;
        }
        undoCommand = noCommand;
    }

    public void setCommand(int slot, Command on, Command off) {
        onCommands[slot] = on;
        offCommands[slot] = off;
    }

    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
    }
    public void undoButtonWasPushed() {
        undoCommand.undo();
    }
}
```

### 🚀 MacroCommand
```java
public class MacroCommand implements Command {
    Command[] commands;
    public MacroCommand(Command[] commands) { this.commands = commands; }
    public void execute() {
        for (Command cmd : commands) cmd.execute();
    }
    public void undo() {
        for (int i = commands.length - 1; i >= 0; i--) commands[i].undo();
    }
}
```

---

## 🚨 Real-World Applications
| Application              | Example                                                                 |
|--------------------------|-------------------------------------------------------------------------|
| GUI Buttons              | Connecting buttons to actions like Print, Save                          |
| Home Automation          | Turning lights, fans, and other devices ON/OFF                          |
| Game Development         | Encapsulating player moves as commands (Undo/Redo support)              |
| Transaction Systems      | Queueing commands for deferred execution (batch processing)             |
| Macro Recording          | Recording sequences of user actions (e.g., in IDEs or Photoshop)        |

---

## 🕊 Interview Q&A
### 1️⃣ What is the Command Pattern?
Encapsulates requests as objects, allowing decoupling between sender and receiver.

### 2️⃣ What problem does it solve?
Avoids tight coupling between the invoker (e.g., button) and receiver (e.g., light or fan).

### 3️⃣ How does Command Pattern support undo?
By storing previous state in the command and reversing it in the `undo()` method.

### 4️⃣ Difference from Strategy Pattern?
| Command Pattern              | Strategy Pattern                         |
|-----------------------------|------------------------------------------|
| Focus on encapsulating actions | Focus on encapsulating algorithms       |
| Supports queuing, undo         | No support for undo/queueing            |

---

## 🚀 Key Takeaways
| Concept            | Details                                               |
|--------------------|-------------------------------------------------------|
| Pattern Type       | Behavioral                                            |
| Purpose            | Encapsulates requests as objects                      |
| Key Benefits       | Decouples sender & receiver, supports undo/macro     |
| Common Use Cases   | GUI, batch systems, remote control, game commands    |

---

Ready for the next design pattern? 🚀

