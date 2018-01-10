package HackerRank.CrackingTheCodingInterview.Algorithms;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/ctci-ice-cream-parlor/problem
 */
public class HashTablesIceCreamParlor {

    public IceCream[] menu;
    public int n, money;

    public HashTablesIceCreamParlor(IceCream[] menu, int n, int money) {
        this.menu = menu;
        Arrays.sort(this.menu);
        this.n = n;
        this.money = money;
    }

    public int binarySearch(int min, int max, int search) {

        int middle = (min + max) >> 1;

        while(min <= max) {

            // Search value is found
            if(menu[middle].cost == search) {
                if(max - min <= 1) {
                    return menu[middle].id;
                }

                max = middle;
            }else {
                if( menu[middle].cost < search) {
                    min = middle + 1;
                } else {
                    max = middle - 1;
                }
            }

            middle = (min + max) >> 1;
        }// END WHILE

        return -1; // No match
    }

    public void solve() {

        for(int i = 0; i < n -1; i++) {

            int search = money - menu[i].cost;

            if(search >= menu[i].cost) {

                // Search for the desired value starting at the first index to the right of the flavor at index i
                int index = binarySearch(i + 1, n - 1, search);
                if( index != -1 ) {
                    System.out.println( Math.min(menu[i].id, index) + " " + Math.max(menu[i].id, index));
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();
        while(t-- > 0) {
            int money = scanner.nextInt();
            int n = scanner.nextInt();
            IceCream[] menu = new IceCream[n];

            // Fill flavor menu and sort
            for (int i = 0; i < n; i++) {
                menu[i] = new IceCream(scanner.nextInt(), i + 1);
            }

            HashTablesIceCreamParlor solution = new HashTablesIceCreamParlor(menu, n, money);
            solution.solve();
        }
        scanner.close();
    }

}
