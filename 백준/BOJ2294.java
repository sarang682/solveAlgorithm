import java.io.*;
import java.util.*;

public class BOJ2294 {

    static int N, K;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[K + 1];
        Arrays.fill(dp, 99999);
        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            int v = Integer.parseInt(br.readLine());
            for (int j = v; j <= K; j++) {
                dp[j] = Math.min(dp[j], dp[j - v] + 1);
            }
        }
        dp[K] = dp[K] >= 99999 ? -1 : dp[K];
        System.out.println(dp[K]);
    }
}
