import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ5582 {

    static String s1, s2;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s1 = br.readLine();
        s2 = br.readLine();
        dp = new int[s1.length() + 1][s2.length() + 1];
        int ans = 0;
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s2.charAt(j - 1) == s1.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        System.out.println(ans);
    }
}
