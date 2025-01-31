package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Make this class immutable. See requirements in task description.
 */
public final class Car implements Cloneable {
    private final int year;
    private final String color;
    private final List<Wheel> wheels;
    private final Engine engine;

    public Car(int year, String color, List<Wheel> wheels, Engine engine) {
        this.year = year;
        this.color = color;
        this.wheels = getWheelsCopy(wheels);
        this.engine = getEngineCopy(engine);
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public ArrayList<Wheel> getWheels() {
        return getWheelsCopy(wheels);
    }

    public Engine getEngine() {
        return getEngineCopy(engine);
    }

    @Override
    public Car clone() {
        try {
            return (Car)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Can't clone this Car object " + e);
        }
    }

    @Override
    public String toString() {
        return "Car{"
            + "year=" + year
            + ", color='" + color + '\''
            + ", wheels=" + wheels
            + ", engine=" + engine
            + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, color, wheels, engine);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Car)) {
            return false;
        }
        Car car = (Car) o;
        return year == car.year && Objects.equals(color, car.color)
                && Objects.equals(wheels, car.wheels) && Objects.equals(engine, car.engine);
    }

    public Car changeColor(String newColor) {
        return new Car(year, newColor, wheels, engine);
    }

    public Car changeEngine(Engine engine) {
        return new Car(year, color, wheels, new Engine(engine.getHorsePower(),
                engine.getManufacturer()));
    }

    public Car addWheel(Wheel newWheel) {
        ArrayList<Wheel> copied = getWheelsCopy(wheels);
        copied.add(newWheel.clone());
        return new Car(year, color, copied, engine);
    }

    private Engine getEngineCopy(Engine engine) {
        return engine == null ? null : engine.clone();
    }

    private ArrayList<Wheel> getWheelsCopy(List<Wheel> wheels) {
        ArrayList<Wheel> copied = new ArrayList<>();
        for (Wheel temp : wheels) {
            copied.add(temp.clone());
        }
        return new ArrayList<>(copied);
    }

}
