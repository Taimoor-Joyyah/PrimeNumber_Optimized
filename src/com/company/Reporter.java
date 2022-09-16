package com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reporter implements Runnable {
    private final List<Task> taskList;
    private final int initialPrimeCount;
    private final List<Integer> statusList;
    private final long startTime;
    private boolean stop;

    public Reporter(List<Task> taskList, int initialPrimeCount) {
        this.taskList = taskList;
        this.initialPrimeCount = initialPrimeCount;
        statusList = new ArrayList<>();
        startTime = new Date().getTime();
        stop = false;
    }

    @Override
    public void run() {
        System.out.println("Reporter Thread Started...");
        for (Task task : taskList)
            statusList.add(task.getStatus());
        while (!stop) {
            for (int i = 0; i < taskList.size(); i++) {
                if (statusList.get(i) != taskList.get(i).getStatus()) {
                    statusList.set(i, taskList.get(i).getStatus());
                    report();
                }
            }
        }
        try {
            Thread.sleep(250);
        } catch (InterruptedException ignored) {}
    }

    private void report() {
//        System.out.println("\n\n\n");
        System.out.print("\033[H\033[2J");
        System.out.flush();
        int sum = 0;
        for (int i = 0; i < statusList.size(); i++) {
            System.out.println("Thread " + i + " : " + statusList.get(i) + "%");
            sum += statusList.get(i);
        }
        double average = (double) sum / (double) statusList.size();
        System.out.println("\nMain Thread: " + average + "%");
        if (average == 100)
            stop = true;

        int currentPrimeCount = initialPrimeCount;
        for (var task : taskList)
            currentPrimeCount += task.getPrimeNumbers();
        System.out.println("Current Prime Number Count: " + currentPrimeCount);
        System.out.println("Time Spent: " +
                String.format("%.2f", (double)((new Date()).getTime() - startTime) / 1000) + " sec");
        System.out.println("\n\n");
    }
}
