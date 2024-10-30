import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BOJ1082 {

    static int N, M;
    static int[] cost;
    static String[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());

        dp = new String[M + 1][M + 1];
        for (int i = 0; i < N; i++) {
            if (M - cost[i] >= 0) dp[1][M - cost[i]] = "" + i;
        }

        for (int i = 2; i <= M; i++) { // 자릿수
            for (int j = N - 1; j >= 0; j--) { // j 넣기
                for (int k = cost[j]; k <= M; k++) {
                    if (dp[i - 1][k] != null && dp[i][k - cost[j]] == null) {
                        dp[i][k - cost[j]] = j + dp[i - 1][k];
                    }
                }
            }
        }
        BigInteger ans = new BigInteger("0");
        for (int i = 1; i <= M; i++) {
            for (int j = 0; j <= M; j++) {
                if (dp[i][j] != null) {
                    BigInteger tmp = new BigInteger(dp[i][j]);
                    if (ans.compareTo(tmp) < 0) ans = tmp;
                }
            }
        }
        System.out.println(ans);
    }
}
