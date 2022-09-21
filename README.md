# job4j. II part

Учебный проект. Архитектура JAVA

### Визуализация учебного блока:


``` JAVA
public interface Car {

    int getNeededSpot();
    String getName();
}

public class PassengerCar implements Car {

    public static final int PASSENGERCARLENGTH = 1;
    private String name;

    public PassengerCar(String name) {
        this.name = name;
    }

    @Override
    public int getNeededSpot() {
        return PASSENGERCARLENGTH;
    }

    @Override
    public String getName() {
        return name;
    }
}
```

### **Программа учебного блока:**

>### 1. TDD;

>### 2. SRP;

>### 3. OCP;

>### 4. LSP;

>### 5. ISP;

>### 6. DIP;

...
