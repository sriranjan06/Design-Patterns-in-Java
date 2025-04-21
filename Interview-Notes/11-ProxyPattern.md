# 🛰️ Proxy Pattern - Gumball Machine Monitoring Example

## 📌 Definition
The **Proxy Pattern** provides a surrogate or placeholder for another object to control access to it.

This pattern is particularly useful when dealing with remote method calls (Remote Proxy), memory-intensive objects (Virtual Proxy), or access protection (Protection Proxy).

---

## 🧠 Key Concepts and Benefits

| Feature                     | Benefit                                                                 |
|----------------------------|-------------------------------------------------------------------------|
| Surrogate for another obj  | Controls access, adds behavior, or optimizes creation                   |
| Remote access abstraction  | Enables client to invoke methods on remote objects as if they were local |
| Clean separation of concern| Client doesn't care if service is local or remote                        |
| Serializable interfaces    | Makes remote communication possible using RMI                           |

---

## 🏗️ UML Structure
```
+----------------------------+
|      GumballMonitorProxy   |          <---- Client-side proxy
+----------------------------+
| - machine: GumballMachine  |
| + report()                 |
+----------------------------+
           |
           ↓
+----------------------------+
| GumballMachineRemoteProxy  | <--- Remote Interface (extends java.rmi.Remote)
+----------------------------+
| + getCount()               |
| + getLocation()            |
| + getState()               |
+----------------------------+
           ↑
           |
+----------------------------+
| GumballMachineProxyProxy   | <--- Remote Object (extends UnicastRemoteObject)
+----------------------------+
| - stateProxy: StateProxy   |
| - count: int               |
| + insertQuarter()          |
| + turnCrank(), etc.        |
+----------------------------+
```

---

## 🧩 Implementation Breakdown

### 🎯 Remote Interface
```java
public interface GumballMachineRemoteProxy extends Remote {
    int getCount() throws RemoteException;
    String getLocation() throws RemoteException;
    StateProxy getState() throws RemoteException;
}
```

### 🎯 Server-Side Implementation (Remote Object)
```java
public class GumballMachineProxyProxy extends UnicastRemoteObject
    implements GumballMachineRemoteProxy {

    StateProxy stateProxy = soldOutStateProxy;
    int count = 0;
    String location;

    public GumballMachineProxyProxy(String location, int numberGumballs) throws RemoteException {
        // Initialize states
        // Set initial state
        this.location = location;
    }

    public int getCount() { return count; }
    public String getLocation() { return location; }
    public StateProxy getState() { return stateProxy; }
    // other methods like insertQuarter, turnCrank, etc.
}
```

### 🎯 Client-Side Proxy (Monitor)
```java
public class GumballMonitorProxy {
    GumballMachineRemoteProxy machine;

    public void report() {
        System.out.println("Location: " + machine.getLocation());
        System.out.println("Inventory: " + machine.getCount());
        System.out.println("State: " + machine.getState());
    }
}
```

### 🎯 Test Drive Client
```java
public class GumballMonitorTestDriveProxy {
    public static void main(String[] args) {
        GumballMachineRemoteProxy machine =
            (GumballMachineRemoteProxy) Naming.lookup("rmi://localhost/gumballmachine");
        GumballMonitorProxy monitor = new GumballMonitorProxy(machine);
        monitor.report();
    }
}
```

---

## 🧵 Types of Proxy Patterns
| Type            | Use Case                                                                 |
|------------------|--------------------------------------------------------------------------|
| **Remote Proxy**     | Communicate with remote objects (e.g., RMI in Java)                      |
| **Virtual Proxy**    | Delay object creation until needed (e.g., heavy images in GUIs)         |
| **Protection Proxy** | Control access to methods based on user roles (e.g., admin/user)        |

---

## 🔍 Real-World Applications
| Application                | Example                                                                 |
|----------------------------|-------------------------------------------------------------------------|
| RMI & Distributed Systems  | Monitoring remote servers, like gumball machines across cities         |
| Web Services               | REST client proxies for interacting with external APIs                 |
| Image Loading (Virtual)    | Load large images lazily in GUI                                        |
| Access Control             | User permission systems                                                |

---

## ✅ Key Takeaways
- Use Proxy when you need to **control or enhance access** to an object.
- Remote proxies allow **network transparency** in distributed systems.
- Java's **RMI** provides built-in support for creating remote proxies.
- Proxy decouples the client from the actual implementation.

---

## 🎤 Interview Questions & Answers

### 1️⃣ What is the Proxy Pattern?
**Answer:** It's a structural design pattern that provides a surrogate for another object to control access to it.

### 2️⃣ What are the types of Proxy Patterns?
**Answer:** Remote, Virtual, Protection, Cache Proxy, Smart Proxy.

### 3️⃣ How does Java RMI support the Proxy Pattern?
**Answer:** Java RMI allows remote interfaces to be called via local proxies (stubs), abstracting away the network.

### 4️⃣ What is the benefit of Serializable in remote proxies?
**Answer:** It allows the state object to be **transmitted across the network**, making RMI communication seamless.

### 5️⃣ How is the Proxy Pattern different from Decorator?
| Proxy                          | Decorator                       |
|-------------------------------|---------------------------------|
| Controls access                | Adds new responsibilities       |
| Same interface as real object | Wraps object to enhance behavior|
| May introduce remote/virtual  | Primarily structural enhancement|

---

## 🧠 Summary
| Concept           | Details                                                                 |
|------------------|-------------------------------------------------------------------------|
| Pattern Type      | Structural                                                              |
| Intent            | Provide a placeholder to control access to another object               |
| Key Benefit       | Adds additional behavior like lazy loading, remote access, permissions  |
| Example           | Gumball Machine RMI monitoring system                                   |
| Common Use Cases  | API Clients, GUIs, Distributed Services, Security Controls              |