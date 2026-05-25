abstract class Calculation {

    // Normal Method
    int add(int x, int y) {

        return x + y;
    }

    // Abstract Method
    abstract int mul(int x, int y);
}

class Demo extends Calculation {

    // Implement Abstract Method
    int mul(int x, int y) {

        return x * y;
    }

    // Own Method
    int calcDiv(int x, int y) {

        return x / y;
    }
}

public class Main {

    public static void main(String[] args) {

        Demo d = new Demo();

        System.out.println("Addition = "
                + d.add(10, 5));

        System.out.println("Multiplication = "
                + d.mul(10, 5));

        System.out.println("Division = "
                + d.calcDiv(10, 5));

        // Abstract Class Reference

        Calculation c = new Demo();

        System.out.println("Addition using reference = "
                + c.add(20, 10));

        System.out.println("Multiplication using reference = "
                + c.mul(20, 10));
    }
}