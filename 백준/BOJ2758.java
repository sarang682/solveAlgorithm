import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2758 {

    static long[][] dp;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            dp = new long[N + 1][M + 1];
            Arrays.fill(dp[1], 1);
            for (int i = 2; i <= N; i++) {
                for (int j = (int)Math.pow(2,i-1); j <= M; j++) {
                    for (int k = 1; k <= j / 2; k++) {
                        dp[i][j] += dp[i - 1][k];
                    }
                }
            }
            long ans = 0;
            for (int i = N; i <= M; i++) {
                ans += dp[N][i];
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
