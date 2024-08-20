import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ1633 {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int[][][] dp = new int[1001][16][16];
        for (int i = 0; i <= 1000; i++) {
            for (int j = 0; j <= 15; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        dp[0][0][0] = 0;
        int idx = 0;
        while (sc.hasNext()) {
            idx++;
            int white = sc.nextInt();
            int black = sc.nextInt();
            for (int i = 0; i <= 15; i++) {
                for (int j = 0; j <= 15; j++) {
                    if (dp[idx - 1][i][j] >= 0) {
                        dp[idx][i][j] = Math.max(dp[idx][i][j], dp[idx - 1][i][j]); // 아무도 안넣기
                        if (i + 1 <= 15) {
                            dp[idx][i + 1][j] = Math.max(dp[idx][i + 1][j], dp[idx - 1][i][j] + white); // 백팀 넣기
                        }
                        if (j + 1 <= 15) {
                            dp[idx][i][j + 1] = Math.max(dp[idx][i][j + 1], dp[idx - 1][i][j] + black); // 흑팀 넣기
                        }
                    }
                }
            }
        }
        System.out.println(dp[idx][15][15]);
    }
}
