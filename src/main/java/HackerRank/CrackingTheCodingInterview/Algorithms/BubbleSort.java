package HackerRank.CrackingTheCodingInterview.Algorithms;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/ctci-bubble-sort/problem
 */
public class BubbleSort {

    public static int numberOfSwaps = 0;

    private static void swap(int a[], int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void sort(int[] a) {
        int n = a.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    numberOfSwaps++;
                }
            }

            if (numberOfSwaps == 0) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }

        sort(a);

        System.out.printf("Array is sorted in %s swaps.%n", numberOfSwaps);
        System.out.printf("First Element: %d%n", a[0]);
        System.out.printf("Last Element: %d%n", a[a.length-1]);
    }
}
