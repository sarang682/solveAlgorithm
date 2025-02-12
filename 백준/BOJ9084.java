import java.io.*;
import java.util.*;

public class BOJ9084 {

    static int N, M;
    static int[] value, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_cast = 1; test_cast <= T; test_cast++) {
            N = Integer.parseInt(br.readLine());
            value = new int[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                value[i] = Integer.parseInt(st.nextToken());
            }
            M = Integer.parseInt(br.readLine());
            dp = new int[M + 1];
            dp[0] = 1;
            for (int i = 0; i < N; i++) {
                for (int j = value[i]; j <= M; j++) {
                    dp[j] += dp[j - value[i]];
                }
            }
            System.out.println(dp[M]);
        }
    }
}
