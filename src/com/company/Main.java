package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int limit = getLimit();
        List<Integer> primeNumbers = getPrimeNumbers(limit);
        displayPrimeNumbers(primeNumbers, limit);
    }

    private static int getLimit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Prime Number in Limit > ");
        return scanner.nextInt();
    }

    private static List<Integer> getPrimeNumbers(int limit) {
        List<Integer> primeNumbers = new ArrayList<>() {{
            add(2);
        }};
        for (int value = 3; value <= limit; value += 2)
            for (int index = 0; value % primeNumbers.get(index) != 0; index++)
                if (primeNumbers.get(index) > Math.sqrt(value)) {
                    primeNumbers.add(value);
                    break;
            }
        return primeNumbers;
    }

    public static void displayPrimeNumbers(List<Integer> primeNumbers, int limit) {
        System.out.println("PRIME NUMBERS:");
        for (var prime : primeNumbers)
            System.out.println(prime);
        System.out.println("Count: " + primeNumbers.size());
        System.out.println("Dominance: " + ((double) primeNumbers.size() * 100) / limit + "%");
    }
}
