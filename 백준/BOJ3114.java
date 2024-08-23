import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3114 {

    static int R, C;
    static int[][] banana, apple, b_sum, a_sum, dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        banana = new int[R + 1][C + 1];
        apple = new int[R + 1][C + 1];
        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                String s = st.nextToken();
                if (s.charAt(0) == 'A') {
                    apple[i][j] = Integer.parseInt(s.replaceAll("[^0-9]", ""));
                } else {
                    banana[i][j] = Integer.parseInt(s.replaceAll("[^0-9]", ""));
                    ;
                }
            }
        }
        b_sum = new int[R + 1][C + 1];
        a_sum = new int[R + 2][C + 1];
        // 바나나
        for (int i = 1; i <= C; i++) {
            for (int j = 1; j <= R; j++) {
                b_sum[j][i] = b_sum[j - 1][i] + banana[j][i];
            }
        }
        // 사과
        for (int i = 1; i <= C; i++) {
            for (int j = R; j >= 0; j--) {
                a_sum[j][i] = a_sum[j + 1][i] + apple[j][i];
            }
        }
        dp = new int[R + 1][C + 1];
        dp[1][1] = a_sum[2][1];
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                // 오른쪽 이동
                if (j > 1) {
                    dp[i][j] = Math.max(dp[i][j],
                            dp[i][j - 1] + b_sum[i - 1][j] + a_sum[i + 1][j]);
                }
                if (i > 1) {
                    // 밑으로 이동
                    dp[i][j] = Math.max(dp[i][j],
                            dp[i - 1][j] - apple[i][j]);
                }
                // 대각선으로 이동
                if (j > 1 && i > 1) {
                    dp[i][j] = Math.max(dp[i][j],
                            dp[i - 1][j - 1] + b_sum[i - 1][j] + a_sum[i + 1][j]);
                }
            }
        }

        System.out.println(dp[R][C]);
    }
}
