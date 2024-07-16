import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ20500 {

    static final int MAX_VALUE = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N <= 1) {
            System.out.println(0);
            return;
        }
        int[][] dp = new int[3][N + 1];
        dp[0][2] = 1;
        dp[1][2] = 1;
        for (int i = 3; i <= N; i++) {
            for (int j = 0; j < 3; j++) {
                dp[(j + 1) % 3][i] = (dp[(j + 1) % 3][i] + dp[j][i - 1]) % MAX_VALUE;
                dp[(j + 5) % 3][i] = (dp[(j + 5) % 3][i] + dp[j][i - 1]) % MAX_VALUE;
            }
        }
        System.out.println(dp[0][N]);
    }
}
