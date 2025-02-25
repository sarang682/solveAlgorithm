import java.util.*;
import java.io.*;

public class BOJ2225 {

    static int N, K;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[K + 1][N + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= K; i++) { // k번째
            for (int j = 0; j <= N; j++) { // j선택
                for (int k = j; k <= N; k++) {
                    dp[i][k] += dp[i - 1][k - j];
                    dp[i][k] %= 1_000_000_000;
                }
            }
        }
        System.out.println(dp[K][N]);
    }
}
