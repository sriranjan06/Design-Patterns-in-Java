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


# Facade Pattern - Home Theater Example 🎥

## 📌 Definition
The **Facade Pattern** provides a **unified interface** to a set of interfaces in a subsystem. Facade defines a higher-level interface that makes the subsystem easier to use.

### ✅ Key Benefits:
- Simplifies complex systems by providing a **simple entry point**.
- **Decouples** the client from the subsystem components.
- Promotes **low coupling** and **clean separation of concerns**.
- Makes a library or framework easier to use, read, and test.

---

## 🏐 Structure

### UML Representation of Facade Pattern:
```
+------------------+         +----------------------+
|     Client       |-------->|     Facade          |
+------------------+         +----------------------+
                               | - subsystemA         |
                               | - subsystemB         |
                               | - subsystemC         |
                               +----------------------+
                                /          |        \
                               /           |         \
            +---------------+ +---------------+ +---------------+
            |  SubsystemA   | |  SubsystemB   | |  SubsystemC   |
            +---------------+ +---------------+ +---------------+
```

- **Client**: Interacts only with the Facade, not individual subsystems.
- **Facade**: Provides a simplified API and delegates to the subsystem objects.
- **Subsystems**: The complex classes that perform actual operations.

---

## 📝 Implementation (Home Theater Example)

### 🌟 Step 1: Subsystem Classes
Each class represents part of the home theater system and provides operations that the facade will simplify.

#### Sample Subsystems:
```java
public class PopcornPopper {
    public void on() { System.out.println("Popcorn Popper on"); }
    public void pop() { System.out.println("Popping popcorn!"); }
    public void off() { System.out.println("Popcorn Popper off"); }
}

public class TheaterLights {
    public void dim(int level) { System.out.println("Lights dimming to " + level + "%"); }
    public void on() { System.out.println("Lights on"); }
}

public class Screen {
    public void down() { System.out.println("Screen going down"); }
    public void up() { System.out.println("Screen going up"); }
}

public class Projector {
    public void on() { System.out.println("Projector on"); }
    public void wideScreenMode() { System.out.println("Widescreen mode on"); }
    public void off() { System.out.println("Projector off"); }
}

public class StreamingPlayer {
    public void on() { System.out.println("Streaming player on"); }
    public void play(String movie) { System.out.println("Playing \"" + movie + "\""); }
    public void stop() { System.out.println("Stopping movie"); }
    public void off() { System.out.println("Streaming player off"); }
}
```

### 🌟 Step 2: Facade Class
```java
public class HomeTheaterFacade {
    private Amplifier amp;
    private Tuner tuner;
    private StreamingPlayer player;
    private Projector projector;
    private Screen screen;
    private TheaterLights lights;
    private PopcornPopper popper;

    public HomeTheaterFacade(Amplifier amp, Tuner tuner, StreamingPlayer player,
                              Projector projector, Screen screen,
                              TheaterLights lights, PopcornPopper popper) {
        this.amp = amp;
        this.tuner = tuner;
        this.player = player;
        this.projector = projector;
        this.screen = screen;
        this.lights = lights;
        this.popper = popper;
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        popper.on();
        popper.pop();
        lights.dim(10);
        screen.down();
        projector.on();
        projector.wideScreenMode();
        amp.on();
        amp.setStreamingPlayer(player);
        amp.setSurroundSound();
        amp.setVolume(5);
        player.on();
        player.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting movie theater down...");
        popper.off();
        lights.on();
        screen.up();
        projector.off();
        amp.off();
        player.stop();
        player.off();
    }
}
```

### 🌟 Step 3: Client Code
```java
public class HomeTheaterTestDrive {
    public static void main(String[] args) {
        Amplifier amp = new Amplifier("Top Amplifier");
        Tuner tuner = new Tuner("AM/FM Tuner", amp);
        StreamingPlayer player = new StreamingPlayer("Netflix Player", amp);
        Projector projector = new Projector("HD Projector", player);
        Screen screen = new Screen("Main Screen");
        TheaterLights lights = new TheaterLights("Ceiling Lights");
        PopcornPopper popper = new PopcornPopper("Movie Popper");

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(
                amp, tuner, player, projector, screen, lights, popper);

        homeTheater.watchMovie("Inception");
        homeTheater.endMovie();
    }
}
```

### 🏆 Output:
```
Get ready to watch a movie...
Movie Popper on
Movie Popper popping popcorn!
Ceiling Lights dimming to 10%
Main Screen going down
HD Projector on
HD Projector in widescreen mode (16x9 aspect ratio)
Top Amplifier on
Top Amplifier setting Streaming player to Netflix Player
Top Amplifier surround sound on (5 speakers, 1 subwoofer)
Top Amplifier setting volume to 5
Netflix Player on
Netflix Player playing "Inception"
...
Shutting movie theater down...
Movie Popper off
Ceiling Lights on
Main Screen going up
HD Projector off
Top Amplifier off
Netflix Player stopped "Inception"
Netflix Player off
```

---

## 🚀 Real-World Applications of the Facade Pattern

| **Application**            | **Example**                                                                 |
|----------------------------|------------------------------------------------------------------------------|
| **Home Automation Systems** | Controlling lights, sound, and screen from one app interface.               |
| **Complex Libraries**       | Providing a simplified wrapper for OpenGL, DirectX, or database drivers.    |
| **Compiler Design**         | Single interface for scanning, parsing, semantic analysis, and code gen.   |
| **Media Players**           | Simplifying interactions with codecs, decoders, and renderers.             |

---

## 🌟 Key Takeaways
- ✅ Hides the complexities of subsystems from the client.
- ✅ Promotes single responsibility by separating orchestration logic.
- ✅ Useful when refactoring legacy code or creating a unified API.
- ✅ Works well with patterns like **Mediator**, **Adapter**, or **Composite**.

---

## 🎤 Facade Pattern - Interview Questions & Answers

### 1️⃣ What problem does the Facade Pattern solve?
**Answer:**
It simplifies the interface to a complex subsystem, making it easier for clients to interact with it.

### 2️⃣ How does it improve maintainability?
**Answer:**
The client does not need to understand the internal workings of multiple subsystems. Changes in the subsystem require fewer changes in client code.

### 3️⃣ What is the difference between Facade and Adapter?
| **Facade**                            | **Adapter**                            |
|--------------------------------------|----------------------------------------|
| Simplifies an existing interface     | Converts one interface to another      |
| Operates on **groups of objects**     | Operates on **single class or interface** |
| Often used for convenience           | Often used for compatibility           |

### 4️⃣ When should you use the Facade Pattern?
- When you have a complex subsystem and want to provide a simplified interface.
- When you want to layer your subsystems cleanly.
- When you want to decouple clients from the subsystem logic.

---

## 📅 Summary

| **Concept**         | **Details**                                                   |
|---------------------|----------------------------------------------------------------|
| **Pattern Type**    | Structural                                                     |
| **Purpose**         | Provide a unified interface to a complex subsystem             |
| **Key Benefit**     | Simplifies usage, reduces coupling                             |
| **Real Example**    | Home Theater Facade controlling amps, players, lights, etc.    |
| **Common Use Cases**| APIs, frameworks, home automation, compilers                   |