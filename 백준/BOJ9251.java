import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9251 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();
        int L1 = str1.length, L2 = str2.length;
        int[][] dp = new int[L1 + 1][L2 + 1];

        for (int i = 1; i <= L1; i++) {
            for (int j = 1; j <= L2; j++) {
                if (str1[i - 1] == str2[j - 1]) { // 같음
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else { // 다름
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[L1][L2]);
    }
}
