import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2240 {

    static int T, W;
    static int[] turn;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        turn = new int[T + 1];
        for (int i = 1; i <= T; i++) {
            turn[i] = Integer.parseInt(br.readLine());
        }
        dp = new int[T + 1][W + 1];
        for (int t = 1; t <= T; t++) {
            for (int w = 0; w <= W; w++) { // w번 움직임
                if (turn[t] == 1) { // 1번 나무에서 열매 떨어짐
                    if (w % 2 == 0) { // 1번 나무
                        dp[t][w] = Math.max(dp[t][w], dp[t - 1][w] + 1);
                        if (w - 1 >= 0) {
                            dp[t][w] = Math.max(dp[t][w], dp[t - 1][w - 1] + 1);
                        }
                    } else { // 2번 나무
                        dp[t][w] = Math.max(dp[t][w], dp[t - 1][w]);
                        dp[t][w] = Math.max(dp[t][w], dp[t - 1][w - 1]);
                    }
                } else { // 2번 나무에서 열매 떨어짐
                    if (w % 2 == 0) { // 1번 나무
                        dp[t][w] = Math.max(dp[t][w], dp[t - 1][w]);
                        if (w - 1 >= 0) {
                            dp[t][w] = Math.max(dp[t][w], dp[t - 1][w - 1]);
                        }
                    } else { // 2번 나무
                        dp[t][w] = Math.max(dp[t][w], dp[t - 1][w] + 1);
                        dp[t][w] = Math.max(dp[t][w], dp[t - 1][w - 1] + 1);
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 0; i <= W; i++) {
            ans = Math.max(ans, dp[T][i]);
        }
        System.out.println(ans);
    }
}
