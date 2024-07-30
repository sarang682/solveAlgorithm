import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1509 {

    static char[] str;
    static int L;
    static boolean[][] chk;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine().toCharArray();
        L = str.length;
        chk = new boolean[L][L];
        for (int i = 0; i < L; i++) {
            chk[i][i] = true;
        }
        for (int i = 0; i < L - 1; i++) {
            if (str[i] == str[i + 1])
                chk[i][i + 1] = true;
        }
        for (int i = 3; i <= L; i++) {
            for (int j = 0; j + i - 1 < L; j++) {
                int e = j + i - 1;
                if (chk[j + 1][e - 1] && str[j] == str[e])
                    chk[j][e] = true;
            }
        }
        int[] dp = new int[L];
        dp[0] = 1;
        for (int i = 1; i < L; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int s = 0; s < i; s++) {
                if (chk[s][i]) {
                    if (s == 0) {
                        dp[i] = 1;
                        break;
                    } else dp[i] = Math.min(dp[i], dp[s - 1] + 1);
                }
            }
        }
        System.out.println(dp[L - 1]);
    }
}
