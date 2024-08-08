import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9177 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char[] str1 = st.nextToken().toCharArray();
            char[] str2 = st.nextToken().toCharArray();
            char[] str3 = st.nextToken().toCharArray();
            int L1 = str1.length;
            int L2 = str2.length;
            int L3 = str3.length;
            boolean[][] dp = new boolean[L1 + 1][L2 + 1];
            dp[0][0] = true;
            for (int i = 0; i <= L1; i++) {
                for (int j = 0; j <= L2; j++) {
                    if (dp[i][j]) {
                        if (i + 1 <= L1 && str3[i + j] == str1[i])
                            dp[i + 1][j] = true;
                        if (j + 1 <= L2 && str3[i + j] == str2[j])
                            dp[i][j + 1] = true;
                    }
                }
            }
            boolean flag = false;
            for (int j = 0; j <= L1; j++) {
                for (int k = 0; k <= L2; k++) {
                    if (j + k == L3 && dp[j][k]) {
                        flag = true;
                    }
                }
            }
            sb.append("Data set ").append(test_case).append(": ");
            if (flag) sb.append("yes\n");
            else sb.append("no\n");
        }
        System.out.println(sb);
    }
}
