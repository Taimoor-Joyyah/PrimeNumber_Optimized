package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrimeNumberCount {

    public static void count(int limit, int threads) {
        var primeNumberList = Task.getPrimeNumbers(limit);
        List<Task> tasks = new ArrayList<>();
        List<Thread> threadList = new ArrayList<>();
        int start = (int) Math.sqrt(limit);
        start = (start % 2 != 0) ? start + 1 : start;
        for (int i = 0; i < threads; i++) {
            Task task = new Task(primeNumberList, start + (i * 2), limit, threads);
            Thread thread = new Thread(task);
            tasks.add(task);
            threadList.add(thread);
            thread.start();
        }

        var reporter = new Reporter(tasks, primeNumberList.size());
        var reporterThread = new Thread(reporter);
        reporterThread.start();

        try {
            for (var thread : threadList)
                thread.join();
            reporterThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int primeNumbers = primeNumberList.size();
        for (var task : tasks)
            primeNumbers += task.getPrimeNumbers();
        displayPrimeNumbers(primeNumbers, limit);
    }

    public static int getLimit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Prime Number in Limit > ");
        return scanner.nextInt();
    }

    public static void displayPrimeNumbers(int primeNumbers, int limit) {
        System.out.println("Count: " + primeNumbers);
        System.out.println("Dominance: " + ((double) primeNumbers * 100) / limit + "%");
    }
}
//        Prime Number in Limit > 1000000
//        Count: 78498
//        Dominance: 7.8498%

//        Count: 105097563
//        Dominance: 4.893986648%
//        Time Spent: 1517.71 sec