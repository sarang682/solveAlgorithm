import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ7579 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N, M;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] mem = new int[N + 1];
        int[] cost = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            mem[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][100_01];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < cost[i]; j++) {
                dp[i][j] = dp[i - 1][j];
            }
            for (int j = cost[i]; j <= 100_00; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost[i]] + mem[i]);
            }
        }

        for (int i = 0; i <= 100_00; i++) {
            if (dp[N][i] >= M) {
                System.out.println(i);
                return;
            }
        }
    }
}
