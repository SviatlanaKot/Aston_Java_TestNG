package org.example;

public class Factorial {
    public static long calculate(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Number must be non-negative");
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        int number = 10;
        System.out.println("Факториал числа " + number + " равен " + calculate(number));
    }
}
