import java.io.*;
import java.util.*;

public class BOJ2293 {

    static int N, K;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[K + 1];
        dp[0] = 1;
        for (int i = 0; i < N; i++) {
            int v = Integer.parseInt(br.readLine());
            for (int j = v; j <= K; j++) {
                dp[j] += dp[j - v];
            }
        }
        System.out.println(dp[K]);
    }
}
