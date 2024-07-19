import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14863 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, K;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[][] walk = new int[N + 1][2];
        int[][] bike = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            walk[i][0] = Integer.parseInt(st.nextToken());
            walk[i][1] = Integer.parseInt(st.nextToken());
            bike[i][0] = Integer.parseInt(st.nextToken());
            bike[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                if (i != 1 && dp[i - 1][j] == 0) continue;
                if (j + walk[i][0] <= K) {
                    dp[i][j + walk[i][0]] = Math.max(dp[i][j + walk[i][0]], dp[i - 1][j] + walk[i][1]);
                }
                if (j + bike[i][0] <= K) {
                    dp[i][j + bike[i][0]] = Math.max(dp[i][j + bike[i][0]], dp[i - 1][j] + bike[i][1]);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i <= K; i++) {
            if (dp[N][i] > ans)
                ans = dp[N][i];
        }
        System.out.println(ans);
    }
}
