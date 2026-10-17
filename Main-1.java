import java.util.Scanner;

interface ITemperatureMeasure {
    void setValue();
    double getValue();
}

class Sensor {
    private int value;

    public Sensor() {
        value = 0;
    }

    public void setValue(int value) {
        this.value += value;
    }

    public int getValue() {
        return value;
    }
}

class MeasureCount {
    private int number;
    private static int total;

    public MeasureCount() {
        number = 0;
    }

    public void increment() {
        number++;
        total++;
    }

    public int getNumber() {
        return number;
    }

    public static int getTotal() {
        return total;
    }
}

class TemperatureMeasure implements ITemperatureMeasure {
    private Sensor h;
    private MeasureCount measure;
    private static final Scanner in = new Scanner(System.in);

    public TemperatureMeasure(Sensor h) {
        this.h = h;
        measure = new MeasureCount();
    }

    public int getNumber() {
        return measure.getNumber();
    }

    public double getValue() {
        return (double) h.getValue() / measure.getNumber();
    }

    public void setValue() {
        measure.increment();
        System.out.print("[" + getNumber() + "] = ");
        int value = in.nextInt();
        h.setValue(value);
    }

    public static int getTotal() {
        return MeasureCount.getTotal();
    }
}

class ShowTemperature {
    public static void show(TemperatureMeasure t) {
        System.out.print(t.getNumber() + ": ");
        System.out.println(t.getValue() + " °C");
    }
}

public class Main {
    public static void main(String[] args) {
        Sensor h1 = new Sensor();
        TemperatureMeasure tc1 = new TemperatureMeasure(h1);

        System.out.println("Первый объект TemperatureMeasure:");
        for (int i = 0; i < 5; i++) {
            tc1.setValue();
        }
        ShowTemperature.show(tc1);

        Sensor h2 = new Sensor();
        TemperatureMeasure tc2 = new TemperatureMeasure(h2);

        System.out.println("Второй объект TemperatureMeasure:");
        for (int i = 0; i < 5; i++) {
            tc2.setValue();
        }
        ShowTemperature.show(tc2);

        System.out.println("Всего измерений: " + TemperatureMeasure.getTotal());
    }
}
