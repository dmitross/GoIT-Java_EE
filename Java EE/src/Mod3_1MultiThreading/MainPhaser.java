package Mod3_1MultiThreading;

import java.util.Arrays;

public class MainPhaser {

    public static void main(String[] arg) {
        SquareSum squareSum = new SquareSumImpl();
        int[] source = new int[100];
        Arrays.fill(source, 2);
        for (int i = 0; i < source.length; i+=3) {
            source[i] = 3;
        }
        long res = squareSum.getSquareSum(source, 5);
        System.out.println("Result: " + res);
    }
}