import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2281 {

    public static int n, m;
    public static long[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        sum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + Long.parseLong(br.readLine());
        }

        long[][] dp = new long[n + 1][n + 1]; // i번째 줄에 j까지 썻을 때 최솟값

        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Long.MAX_VALUE;
            }
        }

        for (int i = 1; i <= n; i++) { // i번째 줄에
            for (int j = i; j <= n; j++) { // j까지 이름 썼을 때 최솟값
                for (int k = 0; k < j; k++) { // 이전 줄까지 0~k까지 씀, 이번 칸은 k+1~j
                    if (dp[i - 1][k] == Long.MAX_VALUE) continue;
                    long space = getSpace(k + 1, j);
                    if (space > m) continue;
                    if (j == n) { // 마지막 줄
                        dp[i][j] = Math.min(
                                dp[i][j],
                                dp[i - 1][k]
                        );
                    } else {
                        dp[i][j] = Math.min(
                                dp[i][j],
                                dp[i - 1][k] + (m - space) * (m - space)
                        );
                    }
                }
            }
        }
        long ans = Long.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            ans = Math.min(ans, dp[i][n]);
        }
        System.out.println(ans);
    }

    public static long getSpace(int s, int e) {
        return sum[e] - sum[s - 1] + (e - s);
    }
}
