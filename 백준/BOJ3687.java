import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ3687 {

    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp = new long[101];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 6;
        dp[7] = 8;
        dp[8] = 10;
        String[] add = {"0", "0", "1", "7", "4", "2", "0", "8"};
        for (int i = 9; i <= 100; i++) {
            for (int j = 2; j <= 7; j++) {
                String v = dp[i - j] + add[j];
                dp[i] = Math.min(Long.parseLong(v), dp[i]);
            }
        }
        int T = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine());
            ans.append(dp[n]).append(" ");
            int cnt = 0;
            while (n > 3) {
                n -= 2;
                cnt++;
            }
            if (n == 3) ans.append(7);
            else ans.append(1);
            for (int i = 0; i < cnt; i++)
                ans.append(1);
            ans.append("\n");
        }
        System.out.println(ans);
    }
}
