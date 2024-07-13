import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, -1, 1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] point = new int[N + 1][2];
        for (int i = 0; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            point[i][0] = x;
            point[i][1] = y;
        }
        long[][] dp = new long[5][N + 1];
        dp[0][0] = 0;
        for (int d = 1; d <= 4; d++) dp[d][0] = 1;

        for (int i = 1; i <= N; i++) {
            for (int d = 0; d <= 4; d++) {
                dp[d][i] = Long.MAX_VALUE;
                int now_x = point[i][0] + dx[d];
                int now_y = point[i][1] + dy[d];
                for (int j = 0; j <= 4; j++) {
                    int prev_x = point[i - 1][0] + dx[j];
                    int prev_y = point[i - 1][1] + dy[j];
                    long dis = Math.abs(now_x - prev_x) + Math.abs(now_y - prev_y);
                    dp[d][i] = Math.min(dp[d][i], dis + dp[j][i - 1]);
                }
            }
        }
        long ans = Long.MAX_VALUE;
        for (int d = 0; d <= 4; d++) {
            ans = Math.min(ans, dp[d][N]);
        }
        System.out.println(ans);
    }
}
