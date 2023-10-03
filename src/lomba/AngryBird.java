package lomba;

import java.util.*;

public class AngryBird {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[][] pipes = new int[n][2];

        for (int i = 0; i < n; i++) {
            pipes[i][0] = scanner.nextInt();
            pipes[i][1] = scanner.nextInt();
        }

        String result = solve(n, pipes);
        System.out.println(result);

        scanner.close();
    }

    private static String solve(int n, int[][] pipes) {
        int max_height = 1;
        int best_slope_num = 1;
        int best_slope_denom = 1;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int pipe1_height = pipes[i][0];
                int pipe2_height = pipes[j][0];
                int pipe1_dist = pipes[i][1];
                int pipe2_dist = pipes[j][1];

                int height_diff = pipe2_height - pipe1_height;
                int dist_diff = pipe2_dist - pipe1_dist;

                if (dist_diff == 0) {
                    // Handle the case where the slope is 0
                    if (height_diff == 0) {
                        return "TIDAK";
                    } else {
                        continue;
                    }
                }

                int gcd = gcd(Math.abs(height_diff), Math.abs(dist_diff));
                int slope_num = height_diff / gcd;
                int slope_denom = dist_diff / gcd;

                if (slope_denom > 0 && slope_num * max_height > slope_denom * max_height) {
                    max_height = slope_num;
                    best_slope_num = slope_num;
                    best_slope_denom = slope_denom;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int pipe_height = pipes[i][0];
            int pipe_dist = pipes[i][1];

            int height_diff = best_slope_num * pipe_dist - max_height * pipe_dist;
            int dist_diff = best_slope_denom - max_height;

            if (height_diff < 0 || height_diff > pipe_height * dist_diff) {
                return "TIDAK";
            }
        }

        return "BISA\n" + max_height + " " + best_slope_num + "\n" + best_slope_denom;
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
