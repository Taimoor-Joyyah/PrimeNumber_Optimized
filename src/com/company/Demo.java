package com.company;

public class Demo {
    public static void main(String[] args) {
//        int limit = PrimeNumberCount.getLimit();
        PrimeNumberCount.count(Integer.MAX_VALUE, 4);
    }
}
// 5734288 - 4
// 5761455 - 8

// 9838155
/*
0 - 09.19 sec
1 - 13.94 sec
2 - 15.87 sec
3 - 17.41 sec
4 - 18.54 sec
5 - 19.39 sec
6 - 20.04 sec
7 - 20.51 sec
*/