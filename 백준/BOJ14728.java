import java.io.*;
import java.util.*;

public class BOJ14728 {

    static int N, T;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][T + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            for (int j = 0; j <= T; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= t) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - t] + s);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i <= T; i++) {
            ans = Math.max(ans, dp[N][i]);
        }
        System.out.println(ans);
    }
}
