# Adapter Pattern - Ducks, Turkeys & Drones 🦆

## 📌 Definition
The **Adapter Pattern** allows incompatible interfaces to work together. It acts as a bridge between two incompatible interfaces by wrapping one of the interfaces and exposing the expected methods.

> Think of it like a power adapter that lets your US plug work in a European socket.

---

## ✅ Key Benefits:
- Enables classes with **incompatible interfaces** to collaborate.
- Promotes **code reusability** without altering existing classes.
- Follows the **Open/Closed Principle**: Add functionality by adding new code, not modifying existing code.
- Supports **composition over inheritance** by using wrapper classes.

---

## 🏠 Structure

### UML Representation of the Adapter Pattern:
```
   +------------------+       +---------------------+
   |     Target       |<------|      Adapter        |
   |------------------|       |---------------------|
   | + request()      |       | + request()         |
   +------------------+       +---------------------+
                                     ^
                                     |
                            +---------------------+
                            |    Adaptee          |
                            |---------------------|
                            | + specificRequest() |
                            +---------------------+
```

- **Target**: The interface the client expects (e.g., `DuckAda`)
- **Adapter**: Translates the request to the Adaptee's format (e.g., `TurkeyAdapter`, `DroneAdapter`, `DuckAdapter`)
- **Adaptee**: The existing interface that needs adapting (e.g., `Turkey`, `Drone`, `DuckAda`)
- **Client**: Code that uses the target interface (e.g., `DuckTestDrive`, `TurkeyTestDrive`)

---

## 📝 Implementation (Turkey, Duck & Drone Example)

### 🌟 Step 1: Define the Interfaces
```java
public interface DuckAda {
	void quack();
	void fly();
}

public interface Turkey {
	void gobble();
	void fly();
}

public interface Drone {
	void beep();
	void spin_rotors();
	void take_off();
}
```

### 🌟 Step 2: Implement Concrete Classes
```java
public class MallardDuckAda implements DuckAda {
	public void quack() {
		System.out.println("Quack");
	}
	public void fly() {
		System.out.println("I'm flying");
	}
}

public class WildTurkey implements Turkey {
	public void gobble() {
		System.out.println("Gobble gobble");
	}
	public void fly() {
		System.out.println("I'm flying a short distance");
	}
}

public class SuperDrone implements Drone {
	public void beep() {
		System.out.println("Beep beep beep");
	}
	public void spin_rotors() {
		System.out.println("Rotors are spinning");
	}
	public void take_off() {
		System.out.println("Taking off");
	}
}
```

### 🌟 Step 3: Create Adapters
```java
public class TurkeyAdapter implements DuckAda {
	Turkey turkey;

	public TurkeyAdapter(Turkey turkey) {
		this.turkey = turkey;
	}
	public void quack() {
		turkey.gobble();
	}
	public void fly() {
		for(int i=0;i<5;i++) {
			turkey.fly();
		}
	}
}

public class DuckAdapter implements Turkey {
	DuckAda duckAda;
	Random rand = new Random();

	public DuckAdapter(DuckAda duckAda) {
		this.duckAda = duckAda;
	}
	public void gobble() {
		duckAda.quack();
	}
	public void fly() {
		if (rand.nextInt(5) == 0) {
			duckAda.fly();
		}
	}
}

public class DroneAdapter implements DuckAda {
	Drone drone;

	public DroneAdapter(Drone drone) {
		this.drone = drone;
	}
	public void quack() {
		drone.beep();
	}
	public void fly() {
		drone.spin_rotors();
		drone.take_off();
	}
}
```

### 🌟 Step 4: Test the Adapters
```java
public class DuckTestDrive {
	public static void main(String[] args) {
		DuckAda duck = new MallardDuckAda();
		Turkey turkey = new WildTurkey();
		DuckAda turkeyAdapter = new TurkeyAdapter(turkey);

		System.out.println("The Turkey says...");
		turkey.gobble();
		turkey.fly();

		System.out.println("\nThe Duck says...");
		testDuck(duck);

		System.out.println("\nThe TurkeyAdapter says...");
		testDuck(turkeyAdapter);

		System.out.println("\nThe Drone says...");
		Drone drone = new SuperDrone();
		DuckAda droneAdapter = new DroneAdapter(drone);
		testDuck(droneAdapter);
	}

	static void testDuck(DuckAda duck) {
		duck.quack();
		duck.fly();
	}
}
```

---

## 🚀 Real-World Applications of the Adapter Pattern

| Application Area        | Example                                                                 |
|------------------------|-------------------------------------------------------------------------|
| Legacy Integration     | Wrapping a legacy logging system to adapt to a new standard logger.     |
| GUI Toolkits           | Allowing new widgets to behave like standard ones (e.g., Swing <-> AWT).|
| Payment Gateways       | Adapting new payment processors to work with a unified payment API.     |
| Third-party Libraries  | Wrapping 3rd-party APIs to expose an internal-friendly interface.       |

---

## 🎯 Key Takeaways

- ✅ Adapters allow classes with incompatible interfaces to work together.
- ✅ Follows **composition over inheritance** by wrapping incompatible objects.
- ✅ Promotes **reuse of legacy code**.
- ✅ Can be **two-way** (e.g., Duck → Turkey, Turkey → Duck).

---

## 🎤 Adapter Pattern - Interview Questions & Answers

### 1️⃣ What problem does the Adapter Pattern solve?
**Answer:**
It solves the problem of incompatible interfaces by introducing a wrapper class that translates method calls.

---

### 2️⃣ When should you use the Adapter Pattern?
**Answer:**
- When you want to reuse existing classes whose interface does not match the expected interface.
- When integrating third-party libraries or legacy systems.

---

### 3️⃣ What's the difference between an Adapter and a Decorator?
| Adapter                           | Decorator                          |
|----------------------------------|------------------------------------|
| Changes the interface             | Adds new behavior                  |
| Used for compatibility            | Used for feature enhancement       |
| Typically involves different APIs | Works with the same base interface |

---

### 4️⃣ What's the difference between an Adapter and a Proxy?
| Adapter                          | Proxy                            |
|---------------------------------|----------------------------------|
| Used to convert one interface to another | Controls access to the object       |
| Focuses on compatibility         | Focuses on security, caching, etc.|

---

## 🔹 Summary

| Concept           | Details                                                                 |
|-------------------|-------------------------------------------------------------------------|
| Pattern Type      | Structural                                                              |
| Purpose           | Allow incompatible interfaces to work together                          |
| Key Benefit       | Promotes reuse without modifying existing code                          |
| Example           | Turkey → Duck Adapter, Duck → Turkey Adapter, Drone → Duck Adapter     |
| Common Use Cases  | UI libraries, payment gateways, API adapters                            |


