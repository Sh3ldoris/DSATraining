package coding_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Algorithm {
    static final Logger LOGGER = Logger.getLogger(Algorithm.class.getSimpleName());
    private final List<Integer> fibbNumberCache; // To cache the numbers

    public static void main(String[] args) {
        final Algorithm algorithm = new Algorithm();
        final int number = 6;
        final int fibb1 = algorithm.fibonacciRecursive(number);
        final int fibb2 = algorithm.fibonacciIterative(number);
        final int fibb3 = algorithm.fibonacciIterative(8);
        LOGGER.log(Level.INFO, "Fibonacci number of {0} is {1} (recursive)", new Object[]{number, fibb1});
        LOGGER.log(Level.INFO, "Fibonacci number of {0} is {1} (iterative)", new Object[]{number, fibb2});
        LOGGER.log(Level.INFO, "Fibonacci number of {0} is {1} (iterative)", new Object[]{8, fibb3});
    }

    public Algorithm() {
        this.fibbNumberCache = new ArrayList<>();
        this.fibbNumberCache.add(0);
        this.fibbNumberCache.add(1);
    }

    public int fibonacciRecursive(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot be negative!");
        }

        if (number < 2) {
            return number;
        }

        return this.fibonacciRecursive(number - 1) + this.fibonacciRecursive(number - 2);
    }

    public int fibonacciIterative(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot be negative!");
        }

        if (number > fibbNumberCache.size()) {
            for (int i = fibbNumberCache.size(); i < number; i++) {
                fibbNumberCache.add(fibbNumberCache.get(i - 1) + fibbNumberCache.get(i - 2));
            }
        }

        return fibbNumberCache.get(number - 1) + fibbNumberCache.get(number - 2);
    }
}
