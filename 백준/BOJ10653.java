import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10653 {

    static final int INF = 2_000_000;
    static int N, K;
    static int[][] point, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        point = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            point[i][0] = Integer.parseInt(st.nextToken());
            point[i][1] = Integer.parseInt(st.nextToken());
        }
        dp = new int[K + 1][N + 1]; // i번 건너 뛰고 j번째까지 왔을 때 거리
        for (int i = 0; i <= K; i++)
            Arrays.fill(dp[i], INF);
        dp[0][1] = 0;
        for (int i = 2; i <= N; i++) {
            for (int k = 0; k <= K; k++) { // k번 건너 뛰기
                for (int j = 0; j + k <= K; j++) {
                    if (i - 1 - k >= 1) {
                        dp[j + k][i] = Math.min(dp[j + k][i],
                                dp[j][i - 1 - k] + getDis(i - 1 - k, i));
                    }
                }
            }
        }
        System.out.println(Math.min(dp[K][N], dp[K - 1][N]));
    }
    public static int getDis(int a, int b) {
        return Math.abs(point[a][0] - point[b][0]) + Math.abs(point[a][1] - point[b][1]);
    }
}
