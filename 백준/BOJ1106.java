import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1106 {

    static int C, N;
    static int[] dp;
    static int[][] city; // 비용, 고객
    static final int INF = 200_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        city = new int[N + 1][2];
        int max = 0;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            city[i][0] = Integer.parseInt(st.nextToken());
            city[i][1] = Integer.parseInt(st.nextToken());
            max = Math.max(max, city[i][1]);
        }
        dp = new int[C + max + 1]; // idx명 고객까지 모을 수 있는 최소 비용
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 1; i <= C + max; i++) {
            for (int j = 1; j <= N; j++) {
                if (i - city[j][1] < 0) continue;
                dp[i] = Math.min(dp[i - city[j][1]] + city[j][0], dp[i]);
            }
        }
        int ans = INF;
        for (int i = C; i <= C + max; i++) {
            ans = Math.min(ans, dp[i]);
        }
        System.out.println(ans);
    }
}
