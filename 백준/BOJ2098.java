import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2098 {

    static int N;
    static int[][] W, dp;
    static final int INF = 16_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[N + 1][1 << N];
        for (int i = 1; i <= N; i++) Arrays.fill(dp[i], -1);
        System.out.println(dfs(1, 1));
    }

    static int dfs(int now, int visit) {
        if (visit == (1 << N) - 1) {
            if (W[now][1] == 0) return INF;
            return W[now][1];
        }

        if (dp[now][visit] != -1) return dp[now][visit];
        dp[now][visit] = INF;

        for (int i = 0; i < N; i++) {
            if ((visit & (1 << i)) == 0 && W[now][i + 1] != 0) {
                dp[now][visit] = Math.min(dfs(i + 1, visit | (1 << i)) + W[now][i + 1], dp[now][visit]);
            }
        }
        return dp[now][visit];
    }
}
