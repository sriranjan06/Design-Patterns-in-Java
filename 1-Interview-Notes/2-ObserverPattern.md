# Observer Pattern - WeatherORama Example 🌦️

## 📌 Definition
The **Observer Pattern** defines a **one-to-many dependency** between objects so that when one object (the **Subject**) changes state, all its **dependents (Observers)** are notified **automatically**.

### ✅ Key Benefits:
- Promotes **loose coupling** between Subject and Observers.
- Supports the **Open/Closed Principle** by allowing new observers without modifying the Subject.
- Ensures that all dependents stay in sync with the Subject.

---

## 🏗 Structure

### UML Representation of the Observer Pattern:
```
+----------------------+       +-----------------+
|    Subject           |       |    Observer     |
|----------------------|       |-----------------|
| + registerObserver() |       | + update()      |
| + removeObserver()   |       +-----------------+
| + notifyObservers()  |            ▲
+----------------------+            │
          ▲                         │
          │                         │
+----------------------+       +---------------------+
|    WeatherData       |       |   CurrentConditions |
|----------------------|       |---------------------|
| - observers[]        |       | - temperature       |
| - temperature        |       | - humidity          |
| - humidity           |       | + update()          |
| - pressure           |       | + display()         |
|----------------------|       +---------------------+
| + setMeasurements()  |
+----------------------+
```

- **Subject (Observable):** Defines methods to **register**, **remove**, and **notify** observers.
- **Observer:** Implements an `update()` method that gets triggered when the Subject changes.
- **Concrete Subject (`WeatherData`)**: Maintains state and notifies observers of changes.
- **Concrete Observers (`CurrentConditionsDisplay`, `StatisticsDisplay`, etc.)**: Implement `update()` to react to state changes.

---

## 📝 Implementation (WeatherORama Example)

### 🎯 Step 1: Define the `Observer` and `Subject` Interfaces

```java
public interface Observer {
    public void update();
}

public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}
```

---

### 🎯 Step 2: Implement the `WeatherData` Subject
```java
import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {
    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public float getHumidity() { return humidity; }
    public float getTemperature() { return temperature; }
    public float getPressure() { return pressure; }

    public WeatherData() {
        observers = new ArrayList<>();
    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for(Observer observer : observers) {
            observer.update();
        }
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }
}
```

---

### 🎯 Step 3: Implement Concrete Observers

```java
public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private WeatherData weatherData;

    public CurrentConditionsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update() {
        this.temperature = weatherData.getTemperature();
        this.humidity = weatherData.getHumidity();
        display();
    }

    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity");
    }
}
```

---

### 🎯 Step 4: Simulating Observer Behavior
```java
public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.4f);
    }
}
```

---

## 🚀 Real-World Applications of the Observer Pattern
| **Application** | **Example** |
|-----------------|-------------|
| **GUI Event Listeners** | Java Swing event listeners (e.g., button clicks notify observers). |
| **Stock Market Monitoring** | When stock prices change, all subscribed traders receive updates. |
| **News & RSS Feeds** | Subscribers receive updates when new articles are published. |
| **Messaging Systems** | Real-time chat applications where users receive notifications. |

---

## 🎯 Key Takeaways
✅ Allows multiple objects to observe and react to state changes in another object.  
✅ **Decouples Subject and Observers**, promoting maintainability.  
✅ Follows the **Open/Closed Principle**, allowing new Observers without modifying Subject.  
✅ Used extensively in **event-driven programming**.

---

## 🎤 Observer Pattern - Interview Questions & Answers

### **1️⃣ What problem does the Observer Pattern solve?**
**Answer:**  
It helps establish a **one-to-many dependency** between objects so that multiple observers get **automatically notified** when a subject's state changes.

### **2️⃣ How does Observer Pattern follow the Open/Closed Principle?**
**Answer:**  
The **Subject (Observable)** doesn’t need to be modified to add new Observers. New observers can simply **register themselves** and start receiving updates.

### **3️⃣ What are some drawbacks of the Observer Pattern?**
**Answer:**
- **Memory leaks** if Observers are not removed properly.
- **Uncontrolled notifications** can impact performance if there are too many observers.

### **4️⃣ How is Observer Pattern different from Publisher-Subscriber?**
| Observer Pattern | Publisher-Subscriber Pattern |
|------------------|----------------------------|
| **Observers register directly with the Subject** | **Subscribers register via a messaging broker (decoupled).** |
| **Tightly coupled to Subject** | **Loosely coupled via events/messages.** |

---

## 🎯 Summary
| **Concept** | **Details** |
|------------|------------|
| **Pattern Type** | Behavioral |
| **Purpose** | Defines a one-to-many dependency between objects, ensuring automatic updates. |
| **Key Benefit** | Loose coupling between Subject and Observers. |
| **Example** | WeatherORama App, Stock Market, News Feeds |
| **Common Use Cases** | GUI Listeners, Messaging Systems, Event-Based Systems |

---

