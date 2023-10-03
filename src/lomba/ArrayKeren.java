/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lomba;

import java.util.*;

public class ArrayKeren {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Masukkan ukuran array
        int n = scanner.nextInt();

        // Masukkan array
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int result = findSmallestK(a);
        System.out.println(result);

        scanner.close();
    }

    private static int findSmallestK(int[] a) {
        int n = a.length;
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int product = a[i] * a[j];
                int sqrt = (int) Math.sqrt(product);
                if (sqrt * sqrt == product) {
                    freqMap.put(a[i], freqMap.getOrDefault(a[i], 0) + 1);
                    freqMap.put(a[j], freqMap.getOrDefault(a[j], 0) + 1);
                }
            }
        }

        if (freqMap.isEmpty()) {
            return -1;
        }

        // Mencari nilai terkecil dari K
        int smallestK = Integer.MAX_VALUE;
        for (int freq : freqMap.values()) {
            smallestK = Math.min(smallestK, freq);
        }

        return smallestK;
    }
}
