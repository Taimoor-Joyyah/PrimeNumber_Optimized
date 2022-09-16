package com.company;

import java.util.ArrayList;
import java.util.List;

public class Task implements Runnable{
    private final List<Integer> primeNumberList;
    private final int start;
    private final int limit;
    private int primeNumbers;
    private int status;
    private final int threads;

    public Task(List<Integer> primeNumberList, int start, int limit, int threads) {
        this.primeNumberList = primeNumberList;
        this.start = start;
        this.limit = limit;
        this.threads = threads;
        this.primeNumbers = 0;
        status = 0;
    }

    public static List<Integer> getPrimeNumbers(int limit) {
        List<Integer> primeNumbers = new ArrayList<>() {{
            add(2);
            add(3);
        }};
        for (int value = 5; value <= (int) Math.sqrt(limit); value += 2)
            for (int index = 1; value % primeNumbers.get(index) != 0; index++) {
                if (primeNumbers.get(index) > Math.sqrt(value) || index + 1 == primeNumbers.size()) {
                    primeNumbers.add(value);
                    break;
                }
            }
        return primeNumbers;
    }

    @Override
    public void run() {
        int increment = 2 * threads;
        for (int value = start + 1; value <= limit; value += increment) {
            if ((value - start - 1) % (limit / 100) == 0)
                status = (value - start - 1) / (limit / 100);
            for (int index = 1; value % primeNumberList.get(index) != 0; index++) {
                if (primeNumberList.get(index) > Math.sqrt(value) || index + 1 == primeNumberList.size()) {
                    primeNumbers++;
                    break;
                }
            }
        }
        status = 100;
    }

    public int getPrimeNumbers() {
        return primeNumbers;
    }

    public int getStatus() {
        return status;
    }
}