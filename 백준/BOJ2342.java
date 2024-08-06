import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2342 {

    static final int INF = 400_000;
    static int[][] cost = {
            {1, 2, 2, 2, 2},
            {3, 1, 3, 4, 3},
            {3, 3, 1, 3, 4},
            {3, 4, 3, 1, 3},
            {3, 3, 4, 3, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = st.countTokens() - 1;
        int[][][] dp = new int[size + 1][5][5];
        for (int i = 0; i <= size; i++) {
            for (int j = 0; j <= 4; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }
        dp[0][0][0] = 0;
        for (int l = 1; l <= size; l++) {
            int idx = Integer.parseInt(st.nextToken());
            for (int i = 0; i <= 4; i++) {
                for (int j = 0; j <= 4; j++) {
                    dp[l][i][idx] = Math.min(dp[l][i][idx], dp[l - 1][i][j] + cost[j][idx]); // 오른발 옮기기
                    dp[l][idx][i] = Math.min(dp[l][idx][i], dp[l - 1][j][i] + cost[j][idx]); // 왼발 옮기기
                }
            }
        }

        int ans = INF;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ans = Math.min(ans, dp[size][i][j]);
            }
        }
        System.out.println(ans);
    }
}
