package HackerRank.CrackingTheCodingInterview.Algorithms;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/ctci-merge-sort/problem
 */
public class MergeSortCountingInversions {

    static long mergeSort(int arr[], int array_size) {

        int temp[] = new int[array_size];
        return _mergeSort(arr, temp, 0, array_size - 1);
    }

    static long _mergeSort(int arr[], int temp[], int left, int right) {

        int mid;
        long inv_count = 0;

        if(right > left) {
            mid = (right + left)/2;
            inv_count = _mergeSort(arr, temp, left, mid);
            inv_count += _mergeSort(arr, temp, mid+1, right);
            inv_count += merge(arr, temp, left, mid+1, right);
        }
        return inv_count;
    }

    static long merge(int arr[], int temp[], int left, int mid, int right) {

        int i, j, k;
        long inv_count = 0;

        i = left;
        j = mid;
        k = left;

        while((i <= mid -1) && (j <= right)) {
            if(arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                inv_count += (mid - i);
            }
        }

        while(i <= mid - 1)
            temp[k++] = arr[i++];

        while(j <= right)
            temp[k++] = arr[j++];

        for(i=left; i <= right; i++)
            arr[i] = temp[i];

        return inv_count;
    }

    static long countInversions(int[] arr) {

        return mergeSort(arr, arr.length);
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int arr_i = 0; arr_i < n; arr_i++) {
                arr[arr_i] = in.nextInt();
            }
            long result = countInversions(arr);
            System.out.println(result);
        }
        in.close();
    }

}
