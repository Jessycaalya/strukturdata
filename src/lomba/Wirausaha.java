/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lomba;

import java.util.*;

public class Wirausaha {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Masukkan P, N, dan K
        int P = scanner.nextInt();
        int N = scanner.nextInt();
        int K = scanner.nextInt();

        // Membersihkan baris kosong setelah pembacaan integer
        scanner.nextLine();

        // Buat array untuk menyimpan data provinsi dan harga produk
        String[] provinsi = new String[P];
        int[][] produk = new int[P][N];

        // Masukkan data provinsi dan harga produk
        for (int i = 0; i < P; i++) {
            provinsi[i] = scanner.nextLine().trim();
            String[] hargaProduk = scanner.nextLine().split(" ");
            for (int j = 0; j < N; j++) {
                produk[i][j] = Integer.parseInt(hargaProduk[j]);
            }
        }

        // Cari provinsi yang memiliki harga produk terdekat dengan K
        int minDiff = Integer.MAX_VALUE;
        String selectedProvinsi = "";

        for (int i = 0; i < P; i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                sum += produk[i][j];
            }
            int diff = Math.abs(sum - K);
            if (diff < minDiff) {
                minDiff = diff;
                selectedProvinsi = provinsi[i];
            } else if (diff == minDiff) {
                // Bandingkan harga produk per provinsi dengan K
                int sumSelected = 0;
                for (int j = 0; j < N; j++) {
                    sumSelected += produk[Arrays.asList(provinsi).indexOf(selectedProvinsi)][j];
                }
                int diffSelected = Math.abs(sumSelected - K);
                int diffCurrent = Math.abs(sum - K);

                // Pilih provinsi dengan selisih terkecil dari K
                if (diffCurrent < diffSelected) {
                    selectedProvinsi = provinsi[i];
                } else if (diffCurrent == diffSelected) {
                    selectedProvinsi = (provinsi[i].compareTo(selectedProvinsi) < 0) ? provinsi[i] : selectedProvinsi;
                }
            }
        }

        System.out.println(selectedProvinsi);

        scanner.close();
    }
}


